package com.ml.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Service
public class OpenAiService {

	private final WebClient webClient;
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Value("${openai.model}")
	private String model;

	public OpenAiService(@Value("${openai.api.key}") String apiKey) {
		this.webClient = WebClient.builder().baseUrl("https://api.openai.com/v1")
				.defaultHeader("Authorization", "Bearer " + apiKey).defaultHeader("Content-Type", "application/json")
				.build();
	}

	public JsonNode analyzeArtist(JsonNode artistJson) throws Exception {

		String systemPrompt = """
				Eres un analista estratégico de música, radio y marketing digital para artistas.

				Recibirás un JSON con:
				- artist (incluye artist_tier y monthly_listeners)
				- similar_artists
				- playlist_recommendations
				- tiktoker_recommendations
				- radio_recommendations

				Tu tarea es generar un resumen ejecutivo para dashboard basado únicamente en los datos recibidos.

				OBJETIVO:
				Detectar oportunidades reales de crecimiento para el artista según su tamaño actual y priorizarlas estratégicamente.

				--------------------------------------------------
				REGLAS GENERALES
				--------------------------------------------------
				- Usa únicamente los datos del JSON.
				- No inventes datos.
				- Escribe en español.
				- Mantén tono profesional, estratégico, claro y accionable.
				- Devuelve únicamente JSON válido.
				- No uses markdown.
				- No agregues texto fuera del JSON.

				--------------------------------------------------
				PRIORIZACIÓN ESTRATÉGICA (MUY IMPORTANTE)
				--------------------------------------------------
				Debes priorizar las oportunidades en este orden:

				1. recommendation_level = "Realistic"
				2. recommendation_level = "Stretch"
				3. recommendation_level = "Aspirational"

				- NO pongas como oportunidad principal algo "Aspirational" aunque tenga más alcance.
				- Evalúa siempre el tamaño del artista (artist_tier) contra el tamaño de la oportunidad.

				--------------------------------------------------
				CRITERIOS DE ANÁLISIS
				--------------------------------------------------
				Prioriza usando:

				- opportunity_score
				- realistic_fit_score
				- recommendation_level
				- priority
				- opportunity_type (Missing > Underperforming)
				- audience_gap (radio)
				- followers_count (playlists)
				- total_views_related y total_videos_related (TikTok)
				- related_artists_names

				--------------------------------------------------
				INTERPRETACIÓN
				--------------------------------------------------
				- "Missing" = oportunidad inmediata
				- "Underperforming" = optimización
				- Más artistas similares = mejor fit
				- Consistencia > viralidad
				- Tamaño correcto > tamaño máximo

				--------------------------------------------------
				REGLAS DE OUTPUT
				--------------------------------------------------
				- Máximo 10 elementos en:
				  - top_opportunities
				  - top_creators
				  - top_markets
				  - top_stations
				- priority_actions máximo 5 elementos, ordenalas por las más importantes
				- Si hay suficientes oportunidades Realistic, NO incluir Aspirational en los tops
				- opportunity_score debe ser de 0 a 100 (estimado, no suma literal)
				- Explica decisiones usando datos reales del JSON

				--------------------------------------------------
				COMPORTAMIENTO POR BLOQUE
				--------------------------------------------------

				PLAYLISTS:
				- Prioriza fit antes que tamaño
				- Considera followers + artistas similares

				TIKTOK:
				- Prioriza consistencia (videos)
				- Usa engagement_proxy y views_per_video

				RADIO:
				- Prioriza audience_gap
				- Baja presencia actual
				- Alta afinidad con artistas similares

				--------------------------------------------------
				CASOS VACÍOS
				--------------------------------------------------
				Si un bloque viene vacío:
				- total_opportunities = 0
				- arrays vacíos
				- insight breve

				--------------------------------------------------
				OUTPUT FINAL
				--------------------------------------------------
				- executive_summary: dónde está la mayor oportunidad (conciso y directo, no tanto texto)
				- main_opportunity: acción más importante (esta es la primera acción a recomendar, la más importante
				- priority_actions: acciones claras
				- dashboard_message: corto y directo
				""";
		
		
		
		
		

		Map<String, Object> schema = Map.of("type", "object", "additionalProperties", false, "properties",
				Map.of("artist_name", Map.of("type", "string"), "executive_summary", Map.of("type", "string"),
						"main_opportunity", Map.of("type", "string"), "opportunity_score", Map.of("type", "number"),
						"playlist_summary", buildPlaylistSchema(), "tiktok_summary", buildTiktokSchema(),
						"radio_summary", buildRadioSchema(),

						"priority_actions", Map.of("type", "array", "items", Map.of("type", "string")),
						"dashboard_message", Map.of("type", "string")),
				"required",
				List.of("artist_name", "executive_summary", "main_opportunity", "opportunity_score", "playlist_summary",
						"tiktok_summary", "radio_summary", "priority_actions", "dashboard_message"));

		Map<String, Object> requestBody = Map.of("model", model, "input",
				List.of(Map.of("role", "system", "content", systemPrompt),
						Map.of("role", "user", "content", objectMapper.writeValueAsString(artistJson))),
				"text", Map.of("format",
						Map.of("type", "json_schema", "name", "artist_dashboard", "strict", true, "schema", schema)));

		String responseText = webClient.post().uri("/responses").bodyValue(requestBody).retrieve()
				.onStatus(status -> status.isError(),
						res -> res.bodyToMono(String.class)
								.flatMap(error -> Mono.error(new RuntimeException("OpenAI API error: " + error))))
				.bodyToMono(JsonNode.class).timeout(Duration.ofSeconds(120)).map(res -> {
					//logger.info("OpenAI raw response: " + res.toPrettyString());

					JsonNode output = res.path("output");
					if (output.isArray() && output.size() > 0) {
						JsonNode content = output.get(0).path("content");
						if (content.isArray() && content.size() > 0) {
							return content.get(0).path("text").asText();
						}
					}

					return res.path("output_text").asText();
				}).block();

		if (responseText == null || responseText.isBlank()) {
			throw new RuntimeException("OpenAI returned empty output_text");
		}

		return objectMapper.readTree(responseText);

		/*
		 * String responseText =
		 * webClient.post().uri("/responses").bodyValue(requestBody).retrieve()
		 * .onStatus(status -> status.isError(), res ->
		 * res.bodyToMono(String.class).flatMap(errorBody -> {
		 * System.out.println("OpenAI error body: " + errorBody); return Mono.error(new
		 * RuntimeException("OpenAI API error: " + errorBody));
		 * })).bodyToMono(JsonNode.class).timeout(Duration.ofSeconds(60)).map(res -> {
		 * System.out.println("OpenAI raw response: " + res.toPrettyString()); return
		 * res.path("output_text").asText(); }).block();
		 */
		//return objectMapper.readTree(responseText);
	}

	// -------------------------
	// SCHEMA HELPERS
	// -------------------------

	private Map<String, Object> buildPlaylistSchema() {
		Map<String, Object> itemSchema = Map.of("type", "object", "additionalProperties", false, "properties",
				Map.of("playlist_name", Map.of("type", "string"), "type_name", Map.of("type", "string"), "priority",
						Map.of("type", "string"), "recommendation_level", Map.of("type", "string"), "opportunity_score",
						Map.of("type", "number"), "reason", Map.of("type", "string")),
				"required", List.of("playlist_name", "type_name", "priority", "recommendation_level",
						"opportunity_score", "reason"));

		return Map.of("type", "object", "additionalProperties", false, "properties",
				Map.of("total_opportunities", Map.of("type", "number"), "top_opportunities",
						Map.of("type", "array", "items", itemSchema), "insight", Map.of("type", "string"),
						"recommended_action", Map.of("type", "string")),
				"required", List.of("total_opportunities", "top_opportunities", "insight", "recommended_action"));
	}

	private Map<String, Object> buildTiktokSchema() {
		Map<String, Object> itemSchema = Map.of("type", "object", "additionalProperties", false, "properties",
				Map.of("user_name", Map.of("type", "string"), "user_handle", Map.of("type", "string"), "priority",
						Map.of("type", "string"), "recommendation_level", Map.of("type", "string"), "opportunity_score",
						Map.of("type", "number"), "total_views_related", Map.of("type", "number"),
						"total_videos_related", Map.of("type", "number"), "reason", Map.of("type", "string")),
				"required", List.of("user_name", "user_handle", "priority", "recommendation_level", "opportunity_score",
						"total_views_related", "total_videos_related", "reason"));

		return Map.of("type", "object", "additionalProperties", false, "properties",
				Map.of("total_opportunities", Map.of("type", "number"), "top_creators",
						Map.of("type", "array", "items", itemSchema), "insight", Map.of("type", "string"),
						"recommended_action", Map.of("type", "string")),
				"required", List.of("total_opportunities", "top_creators", "insight", "recommended_action"));
	}

	private Map<String, Object> buildSummarySchema(String arrayName) {
		return Map.of("type", "object", "additionalProperties", false, "properties",
				Map.of("total_opportunities", Map.of("type", "number"), arrayName,
						Map.of("type", "array", "items", Map.of("type", "object", "additionalProperties", false)),
						"insight", Map.of("type", "string"), "recommended_action", Map.of("type", "string")),
				"required", List.of("total_opportunities", arrayName, "insight", "recommended_action"));
	}

	private Map<String, Object> buildRadioSchema() {

		Map<String, Object> marketSchema = Map.of("type", "object", "additionalProperties", false, "properties",
				Map.of("market", Map.of("type", "string"), "country_id", Map.of("type", "number"),
						"recommendation_level", Map.of("type", "string"), "opportunity_score", Map.of("type", "number"),
						"reason", Map.of("type", "string")),
				"required", List.of("market", "country_id", "recommendation_level", "opportunity_score", "reason"));

		Map<String, Object> stationSchema = Map.of("type", "object", "additionalProperties", false, "properties",
				Map.of("station_name", Map.of("type", "string"), "market", Map.of("type", "string"), "country_id",
						Map.of("type", "number"), "opportunity_type", Map.of("type", "string"), "recommendation_level",
						Map.of("type", "string"), "opportunity_score", Map.of("type", "number"), "audience_gap",
						Map.of("type", "number"), "spins_gap", Map.of("type", "number"), "reason",
						Map.of("type", "string")),
				"required", List.of("station_name", "market", "country_id", "opportunity_type", "recommendation_level",
						"opportunity_score", "audience_gap", "spins_gap", "reason"));

		return Map.of("type", "object", "additionalProperties", false, "properties",
				Map.of("total_opportunities", Map.of("type", "number"), "top_markets",
						Map.of("type", "array", "items", marketSchema), "top_stations",
						Map.of("type", "array", "items", stationSchema), "insight", Map.of("type", "string"),
						"recommended_action", Map.of("type", "string")),
				"required",
				List.of("total_opportunities", "top_markets", "top_stations", "insight", "recommended_action"));
	}
}
