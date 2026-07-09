# AcrData API — Referencia

Referencia técnica de la API REST de **AcrData / DigitalLatino**. Documento dirigido al equipo interno de ingeniería.

- **Stack:** Spring Boot 2.6.1, Java 17, Spring Data JPA, Spring Security, JWT (jjwt 0.11.5), MSSQL (`acr_data`), Stripe Java 25.10.0, OpenAI.
- **Artefacto:** `com.ml:AcrData:0.0.1-SNAPSHOT`.
- **Puerto por defecto:** `8084` (`server.port=8084` en `application.properties`).
- **Base path:** todos los endpoints viven bajo `/api/...`.
- **Zona horaria de la DB:** `America/Mexico_City`.

## Autenticación

La API usa JWT firmados (HS256, vía `JwtService`) en el header `Authorization`.

```
Authorization: Bearer <jwt>
```

Detalles de la cadena de autenticación:

- `JwtAuthFilter` se ejecuta antes de `UsernamePasswordAuthenticationFilter`, extrae el token del header `Authorization` y carga el `UserDetails` vía `UserDetailsService` cuando el token es válido.
- Política de sesión: `STATELESS`. El servidor no guarda sesión.
- Hashing de passwords: `BCryptPasswordEncoder`.
- Reglas actuales en `SecurityConfig`:
  - `/api/auth/**` → `permitAll`
  - `/api/**` → `permitAll` (heredado, ver nota más abajo)
  - cualquier otro request → `authenticated`

> Nota: hoy `SecurityConfig` permite todo bajo `/api/**`. El filtro JWT se ejecuta de todas formas y popula el `SecurityContext` cuando hay token, pero ningún endpoint exige autenticación a nivel de configuración. Si se quiere proteger un endpoint hay que ajustar las reglas (`authenticated()` en lugar de `permitAll()` para ese path).

### CORS

Configurado en `SecurityConfig#corsConfigurationSource`. Los orígenes y métodos vienen de `application.properties`:

```
cors.allowed.origins=https://digital-latino.com,https://top.digital-latino.com,https://top.monitorlatino.com,http://localhost:8080,http://localhost:8085
cors.allowed.methods=GET,POST,PUT,DELETE,OPTIONS
```

Varios controladores también declaran `@CrossOrigin(origins = "*", allowedHeaders = "*")` a nivel de clase, que prevalece sobre la configuración global para esos paths.

## Convenciones generales

- **Content-Type de request:** `application/json` para `PUT`/`POST` con body. Los `GET` declaran `consumes = MediaType.ALL_VALUE`.
- **Códigos de estado:** los controladores devuelven en su mayoría `201 Created` para `POST`/`PUT` y `202 Accepted` para algunos `GET`. Los errores usan `400`, `401`, `403`, `404`, `500` según el caso (ver detalles por endpoint).
- **Estilo:** los nombres de path mezclan camelCase y kebab-style (`getChartDigital`, `verify-email`); se documentan tal cual están en el código.

---

## `/api/auth` — Autenticación de usuarios

`AuthController`. Registro, verificación por email y login.

### `POST /api/auth/register`

Registra un usuario nuevo. Envía un correo con un enlace de verificación.

Request body:

```json
{
  "firstName": "Ada",
  "lastName": "Lovelace",
  "email": "ada@example.com",
  "password": "secreto",
  "phone": "+525511223344"
}
```

Respuestas:

| Código | Cuerpo |
|--------|--------|
| `200 OK` | `{ "message": "¡Registro exitoso!, enlace enviado al email para verificar la cuenta." }` |
| `400 Bad Request` | `{ "error": "<mensaje de IllegalStateException>" }` (ej. email ya registrado) |
| `500 Internal Server Error` | `{ "error": "Ocurrió un error interno en el servidor.", "details": "<mensaje>" }` |

### `GET /api/auth/verify-email?token=<token>`

Endpoint del enlace de verificación. No regresa JSON — emite un `RedirectView` al frontend.

- `token` válido → redirige a `https://digital-latino.com/auth/callback?token=<jwt>`.
- `token` inválido → redirige a `https://digital-latino.com/?error=invalid_token`.

### `POST /api/auth/login`

Autentica con email/password y devuelve un JWT.

Request body:

```json
{ "email": "ada@example.com", "password": "secreto" }
```

Respuestas:

| Código | Cuerpo |
|--------|--------|
| `200 OK` | `{ "token": "<jwt>" }` |
| `401 Unauthorized` | `{ "error": "<BadCredentialsException>" }` |
| `403 Forbidden` | `{ "error": "<IllegalStateException>" }` (cuenta no verificada, etc.) |

---

## `/api/users` — Perfil

`UserController`.

### `GET /api/users/me`

Devuelve el perfil del usuario autenticado (resuelto desde el `SecurityContext` por `UserService#getAuthenticatedUserProfile`).

Response (`UserResponse`):

```json
{
  "id": 1,
  "email": "ada@example.com",
  "firstName": "Ada",
  "lastName": "Lovelace"
}
```

Requiere `Authorization: Bearer <jwt>`.

---

## `/api/data` — Ingesta de ACR

`AcrDataController`. Endpoints para que los workers de ACRCloud / scrapers escriban detecciones.

### `PUT /api/data/setData`

Inserta un registro `AcrData` plano.

- Body: entidad `AcrData` (ver `model/AcrData.java`).
- `201 Created` con la entidad guardada, o `404 Not Found` si el servicio no la persiste.

### `PUT /api/data/setAcrData/{channel}`

Path param: `channel` (`Long`, id del canal de origen).

Inserta el batch ACR (`Acr` con lista de `Datum`) filtrando solo los registros cuyo `metadata.dateRecord` sea posterior al timestamp del último `AcrData` guardado para ese canal. Útil para reintentos sin duplicar.

Respuesta: `201 Created` con el `Acr` filtrado realmente insertado.

### `GET /api/data/getLastLog/{channel}`

Devuelve el último `AcrData` registrado para el canal indicado.

Respuesta: `202 Accepted` con la entidad `AcrData` (puede ser `null` si no hay registros).

---

## `/api/channel` — Canales / estaciones

`ChannelController`.

### `PUT /api/channel/setAcrChannel`

Recibe un payload `Acr.Channel` (representación que entrega ACRCloud) y persiste cada `Datum` como una entidad `Channel` interna. El campo `timemap` (entero) se mapea a un `boolean` (`true` cuando es `1`).

Respuesta: `201 Created` con el body recibido.

### `GET /api/channel/getChannelbyId/{channel}`

Path param: `channel` (`Long`).

Si el id no existe, hace fallback al canal `107741`. Respuesta: `201 Created` con el `Channel`.

### `GET /api/channel/getChannels`

Lista todos los canales. Respuesta: `201 Created` con `List<Channel>`.

---

## `/api/track` — Tracks / canciones

`TrackController`.

### `GET /api/track/getTrackbyId/{track}`

Path param: `track` (`Long`). Si no existe, fallback al track `279`. Devuelve la entidad `Track` con sus relaciones JPA (artistas, álbumes, género, label).

### `GET /api/track/getSongbyId/{track}`

Mismo fallback que arriba. Construye un objeto `Song` plano (id, avatar, título, primer artista, label) — pensado para vistas que no necesitan el grafo completo.

### `GET /api/track/getSongStreams/{track}`

Devuelve `List<StationChart>` con la actividad de la canción por estación (procedure `procs.getStationsbySong`).

### `GET /api/track/getSongDigital/{track}`

Devuelve `SongDigital` con las métricas digitales agregadas (procedure `procs.getDigitalbySong`).

---

## `/api/report` — Reportes y analítica

`ReportController`. Es el controlador más amplio: la mayoría de los endpoints son lecturas que invocan procedures de SQL Server vía `ExecProcRepository` y devuelven o un POJO tipado o un string JSON (`JSONArray` / `JSONObject`).

> Convención: los endpoints que producen `application/json` retornan el JSON serializado directamente como `String` (`ResponseEntity<String>`). Los endpoints que devuelven listas tipadas (`List<Chart>`, `List<Country>`, etc.) responden con la entidad serializada por Jackson.

### Charts y rankings

| Método | Path | Descripción |
|--------|------|-------------|
| `GET` | `/api/report/getChart` | `List<Chart>` — chart base agregado. |
| `GET` | `/api/report/getChartDigital/{format}/{country}/{CRG}/{city}?radiooff=0` | `List<ChartDigital>` filtrado por formato, país, CRG, ciudad. `radiooff` opcional (default `0`). |
| `GET` | `/api/report/getTrendingSongs/{format}/{country}/{CRG}/{city}` | `List<TrendingSongs>` — top trending. |
| `GET` | `/api/report/getTrendingDebut/{format}/{country}/{CRG}/{city}` | Top de debuts. |
| `GET` | `/api/report/getTopArtist/{format}/{country}/{city}` | `List<ArtistDigital>`. |
| `GET` | `/api/report/getTopPlatform/{platform}/{format}/{country}` | `List<PlatformTop>` por plataforma (Spotify, YouTube, etc.). |
| `GET` | `/api/report/getTopScores/{fmt}` | JSON array de scores. |
| `GET` | `/api/report/getTopDigtal/{country}` | JSON array — top digital del país (sic, `Digtal` está mal escrito en el código). |
| `GET` | `/api/report/getTopRadio/{country}/{format}` | JSON array — top radio. |
| `GET` | `/api/report/getTopRadioFormat/{country}/{format}` | JSON object — top desglosado por formato. |
| `GET` | `/api/report/getTopFormat/{country}/{option}` | JSON object — top por opción de formato. |

### Catálogos

| Método | Path | Descripción |
|--------|------|-------------|
| `GET` | `/api/report/getCountries` | `List<Country>`. |
| `GET` | `/api/report/getFormatbyCountry/{country}` | `List<Format>` para el país. |
| `GET` | `/api/report/getFormatbyCountryArtist/{country}` | `List<Format>` (hoy ignora el path param y siempre llama `getFormats(0)` — comportamiento legado). |
| `GET` | `/api/report/getFormatInt` | `List<Format>` internacional. |
| `GET` | `/api/report/getPlatforms` | `List<Platform>`. |
| `GET` | `/api/report/getCities/{country}/{CRG}` | `List<City>`. |
| `GET` | `/api/report/getPlaylistType` | JSON array con los tipos de playlist disponibles. |
| `GET` | `/api/report/getLastUpdate` | Última fecha de corte semanal (string, último viernes). |

### Búsqueda

| Método | Path | Descripción |
|--------|------|-------------|
| `GET` | `/api/report/getSearch/{search}` | Busca canciones por texto libre. `List<Song>`. |
| `GET` | `/api/report/getSearchSpotify?query=...` | Llama a `SpotifySearchService.searchSpotifyAndEnrich(query, "20")`. Devuelve `JSONObject` enriquecido. `500` si la API de Spotify falla (`{ "error": "<mensaje>" }`). |
| `GET` | `/api/report/getSpotifyId?cs_song=...` | Resuelve el `spotifyid` a partir de `cs_song`. |
| `GET` | `/api/report/getcssong?spotifyid=...` | Inverso del anterior. |

### Detalle de canción

| Método | Path | Descripción |
|--------|------|-------------|
| `GET` | `/api/report/getSongbyId/{id}` | `Song` por id. |
| `GET` | `/api/report/getSongbyId/{id}/{country}` | `SongScore` con score por país. |
| `GET` | `/api/report/getSongDigital/{cs_song}/{fmt}/{country}` | `SongStatsDigital`. |
| `GET` | `/api/report/getSongbyIdFormat/{id}/{country}` | `List<SongFormat>`. |
| `GET` | `/api/report/getSongHistoricalStreams/{cs_song}` | Histórico de streams (JSON string). |
| `GET` | `/api/report/getSongHistoricalStreamsWeek/{cs_song}/{country}/{fmt}` | Histórico semanal. |

### Geografía

| Método | Path | Descripción |
|--------|------|-------------|
| `GET` | `/api/report/getCityData/{id}/{country}` | `List<CityData>` para una canción. |
| `GET` | `/api/report/getTopMarketRadio/{id}/{country}` | `List<SongCities>` (top radio por ciudad). |
| `GET` | `/api/report/getTopRadioCountries/{id}` | `List<SongCountries>` por país. |

### Playlists, TikTok y curadores

| Método | Path | Descripción |
|--------|------|-------------|
| `GET` | `/api/report/getTopPlaylists/{id}/{type}` | `List<SongPlaylist>`. |
| `GET` | `/api/report/getTopTiktok/{id}` | `List<SongTiktok>`. |
| `GET` | `/api/report/getTiktokPics/{fmt}` | JSON array — picks editoriales. |
| `GET` | `/api/report/getCuratorPics/{fmt}/{type}` | JSON array — picks de curadores. |
| `GET` | `/api/report/getRecommendations/{id}` | `List<SongRecommendation>`. |
| `GET` | `/api/report/getvideos` | JSON array de videos. |

### Comparativas (`vs`)

| Método | Path | Descripción |
|--------|------|-------------|
| `GET` | `/api/report/getVsSong/{cs_song1}/{cs_song2}` | Comparativa general. |
| `GET` | `/api/report/getVsSongPlaylists/{cs_song1}/{cs_song2}` | Comparativa de playlists. |
| `GET` | `/api/report/getVsSongTiktoks/{cs_song1}/{cs_song2}` | Comparativa de TikTok. |

### Artistas

| Método | Path | Descripción |
|--------|------|-------------|
| `GET` | `/api/report/getDataArtist/{spotifyid}` | Datos del artista por `spotifyid`. |
| `GET` | `/api/report/getDataArtistSongid/{cs_song}` | Resuelve el artista a partir de un `cs_song`. |
| `GET` | `/api/report/getDataArtistCountry/{country}/{spotifyid}` | Canciones del artista por ciudades del país. |
| `GET` | `/api/report/getDataArtistCountryRelated/{country}/{spotifyid}` | Artistas relacionados por país. |
| `GET` | `/api/report/getSongsArtist/{spotifyid}/{country}` | JSON array — songs del artista filtradas por país. |
| `GET` | `/api/report/getArtistSongs/{artistid}` | JSON string con todas las songs del artista. |
| `GET` | `/api/report/getArtistPlaylistRelated/{artistid}/{playlisttype}` | Playlists relacionadas. |
| `GET` | `/api/report/getArtistTiktokersRelated/{artistid}` | TikTokers relacionados. |
| `GET` | `/api/report/getArtistRadioRelated/{artistid}/{countryid}` | Estaciones de radio relacionadas. |
| `GET` | `/api/report/getArtistRelatedGraph/{artistid}` | Grafo de artistas relacionados (string JSON). |
| `GET` | `/api/report/getArtistRelatedGraphv2/{artistid}` | Versión v2 del grafo. |
| `GET` | `/api/report/getArtistContext/{artistid}` | Devuelve el contexto del artista *enriquecido por OpenAI* (`OpenAiService.analyzeArtist`). Errores de OpenAI se loguean pero el endpoint puede regresar `null` en `resultJson`. |

### Logs y mensajes

| Método | Path | Descripción |
|--------|------|-------------|
| `POST` | `/api/report/setMessage` | Guarda un mensaje del bot WhatsApp. Body: `MessageChat` (`phone`, `contact`, `country`, `format`, `message`). |
| `POST` | `/api/report/setLogSong` | Loggea apertura de una song/artist por usuario. Body: `{ "userid": Int, "spotifyid": String, "isartist": Boolean }`. |
| `POST` | `/api/report/setContact` | Alta de contacto WhatsApp. Body: `{ "phone", "nombre", "apellido", "rol", "tipo": Int, "paises" }`. |
| `POST` | `/api/report/delContact` | Borra contacto. Body: `{ "phone": "<phone>" }`. |
| `GET`  | `/api/report/getAllContacs` | (sic) JSON array con todos los contactos. |
| `GET`  | `/api/report/getContactbyCountry/{country}` | Contactos por país. |
| `GET`  | `/api/report/getUserLog/{phoneno}` | Log de un usuario por número telefónico. |

### Tarea programada

`processRelatedArtists()` (`@Scheduled(fixedDelay = 3600000, initialDelay = 1)`) procesa cada hora los artistas pendientes (`procexec.getRelatedArtist`) y los enriquece con `SpotifyRelatedArtistService.saveRelatedArtists`. No es un endpoint HTTP, pero vive en este controlador.

---

## `/api/ia` — Bot WhatsApp

`IAController`.

### `GET /api/ia/getConverstation/{phone}`

(sic) Devuelve `List<ChatUser>` con la conversación del número indicado. Respuesta `201 Created`.

### `PUT /api/ia/setLog`

Guarda un turno (mensaje + respuesta) del bot. Body `UserLog`:

```json
{ "phone": "+52...", "message": "...", "response": "..." }
```

Respuesta `201 Created` con el body recibido.

---

## `/api/payments` — Stripe Payment Intents

`PaymentController`.

### `POST /api/payments`

Crea un Payment Intent en Stripe. Body `CreatePaymentIntentRequest`:

```json
{ "priceId": "price_xxx" }
```

Respuesta `CreatePaymentIntentResponse`:

```json
{ "clientSecret": "pi_xxx_secret_yyy" }
```

Stripe puede lanzar `StripeException`. El endpoint no la captura — se traduce en `500` por el manejador por defecto de Spring.

---

## `/api/subscriptions` — Suscripciones Stripe

`SubscriptionController`.

### `POST /api/subscriptions/create`

Crea (o reusa) un customer y le adjunta una suscripción de Stripe.

Request body `CreateSubscriptionRequest`:

```json
{
  "priceId": "price_xxx",
  "email": "ada@example.com",
  "password": "secreto",
  "firstName": "Ada",
  "lastName": "Lovelace"
}
```

Respuestas:

| Código | Cuerpo |
|--------|--------|
| `201 Created` | `CreateSubscriptionResponse` → `{ "suscriptionId": "sub_xxx", "clientSecret": "pi_xxx_secret_yyy" }` (sic, el campo se llama `suscriptionId`). |
| `409 Conflict` | string con `IllegalStateException.message` (usuario ya existe). |
| `500 Internal Server Error` | `"Error al procesar la suscripcón con stripe: <mensaje>"`. |

> Hoy `SubscriptionService` usa `priceId` directamente y el resto del request body solo se persiste si el flujo lo requiere. Revisar `SubscriptionService.createCustomerAndSubscription` antes de cambiar el contrato.

---

## `/api/webhooks/stripe` — Webhook de Stripe

`WebhookController`.

### `POST /api/webhooks/stripe`

Headers requeridos:

- `Stripe-Signature: <firma>`

Body: payload crudo (string) emitido por Stripe.

Comportamiento: delega a `WebhookService.handleStripeEvent(payload, sigHeader)`. El método regresa `void` y Spring responde `200 OK` por defecto. Cualquier excepción se propaga (Stripe reintenta con backoff).

> Recordatorio: este endpoint **debe** consumir el body crudo, no JSON parseado, para validar la firma. Por eso el parámetro es `String`.

---

## Modelos referenciados

Los DTOs y entidades clave:

- `dto/`: `LoginRequest`, `LoginResponse`, `RegisterRequest`, `UserResponse`, `CreatePaymentIntentRequest/Response`, `CreateSubscriptionRequest/Response`.
- `model/`: `User`, `Subscription`, `Payment`, `Channel`, `Track`, `Album`, `Artist`, `Genre`, `Label`, `AcrData`, `AcrDataLog`, `VerificationToken`, `Track_Artist`, `Track_Album`, `Track_Genre`, `Isrc`, `Upc`, `ExternalMetadata`.
- `model/acr/`: representación del payload entrante de ACRCloud (`Acr`, `Datum`, `Metadata`, `Music`, `Spotify`, `Youtube`, `Deezer`, ...).
- `model/report/`: POJOs de lectura para reportes (`Chart`, `ChartDigital`, `Song`, `SongDigital`, `SongStatsDigital`, `SongScore`, `SongPlaylist`, `SongTiktok`, `SongRecommendation`, `TrendingSongs`, `ArtistDigital`, `Country`, `City`, `CityData`, `Format`, `Platform`, `PlatformTop`, `StationChart`, `MessageChat`, `ChatUser`, `UserLog`).
- `model/enums/`: `UserRole`, `SubscriptionStatus`.

## Configuración relevante

`src/main/resources/application.properties`:

- `spring.datasource.url` — instancia MSSQL en RDS (`acr_data`).
- `spring.jpa.hibernate.ddl-auto=none` — no se permiten cambios automáticos al esquema.
- `spring.jpa.properties.hibernate.jdbc.time_zone=America/Mexico_City`.
- `server.port=8084`.
- `cors.allowed.origins`, `cors.allowed.methods`.

Las credenciales (DB, Stripe, OpenAI, Spotify, JWT secret) viven en este archivo en repositorio. **Pendiente de seguridad:** mover a variables de entorno o a un secret manager antes del próximo deploy.

## Cómo correr localmente

```bash
cd AcrData
./mvnw spring-boot:run
# expone http://localhost:8084
```

Smoke test rápido:

```bash
# Healthcheck implícito vía login (devuelve 401/403 con credenciales falsas)
curl -X POST http://localhost:8084/api/auth/login \
  -H 'Content-Type: application/json' \
  -d '{"email":"ada@example.com","password":"x"}'

# Catálogo de países
curl http://localhost:8084/api/report/getCountries
```

## Pendientes / inconsistencias detectadas

Items que conviene atender en una pasada de limpieza, documentados aquí para que no se pierdan:

1. `SecurityConfig` deja `/api/**` como `permitAll`. Endpoints como `getAllContacs`, `setLog*`, ingestas `/api/data/*` y `/api/channel/*` deberían exigir JWT.
2. `getFormatbyCountryArtist/{country}` ignora el path param.
3. Errores tipográficos en paths públicos: `getConverstation`, `getAllContacs`, `getTopDigtal`. Romperlos requiere coordinación con frontends; documentarlos como deuda.
4. `CreateSubscriptionResponse.suscriptionId` (typo) está en el contrato público.
5. `application.properties` contiene secretos en claro (DB password, etc.).
6. `PaymentController#createPaymentIntent` no maneja `StripeException` explícitamente; respuestas de error son opacas.
