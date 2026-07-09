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
public class OpenAiServiceV2 {

	private final WebClient webClient;
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Value("${openai.model}")
	private String model;

	public OpenAiServiceV2(@Value("${openai.api.key}") String apiKey) {
		this.webClient = WebClient.builder().baseUrl("https://api.openai.com/v1")
				.defaultHeader("Authorization", "Bearer " + apiKey).defaultHeader("Content-Type", "application/json")
				.build();
	}

	public JsonNode analyzeArtist(JsonNode artistJson) throws Exception {

		String systemPrompt = """
				Eres un estratega senior de marketing musical y growth digital.

				Convierte el JSON recibido en un roadmap ejecutivo de 90 días para dashboard/PDF.

				Reglas:
				- Usa solo datos del JSON.
				- No inventes artistas, playlists, radios, ciudades ni TikTokers.
				- Responde solo JSON válido.
				- Escribe en español.
				- Sé breve, ejecutivo y visual.
				- Pensar como slide premium de industria musical.
				- Prioriza oportunidades Realistic y High priority.
				- No repetir targets entre fases.

				Fases:
				- Fase 1: Momentum inicial, Fastrack, TikTok directo, Meta Ads, ciudades fuertes.
				- Fase 2: Escalamiento, Editorial playlists, radio, TikTokers, nuevos mercados.
				- Fase 3: Expansión, Nodo B, internacionalización y nuevas audiencias.

				TikTokers:
				- Evita artistas oficiales, bandas, fanpages o perfiles promocionales.
				- Prioriza creadores independientes, lifestyle, humor, baile, cultura y reacción.
				- Prioriza consistency, engagement_proxy y views_per_video.
				- No elegir solo por followers.

				Frontend:
				- targets debe ser array de objetos.
				- Cada target:
				  - name
				  - type
				  - url
				  - user_handle
				  - reason
				- Playlist:
				  - usar external_url como url.
				- TikTok:
				  - usar user_handle.
				  - url = https://www.tiktok.com/@{user_handle}
				- Si no existe url o handle usar "".
				- No inventar links ni handles.

				Límites:
				- Máximo 5 acciones por fase.
				- Máximo 5 targets por acción.
				- headline corto.
				- description máximo 2 líneas.
				- dashboard_message corto.
				""";
		
		
		Map<String, Object> schema = buildPlanSchema();

		Map<String, Object> requestBody = Map.of(
			    "model", model,
			    "input", List.of(
			        Map.of("role", "system", "content", systemPrompt),
			        Map.of("role", "user", "content", objectMapper.writeValueAsString(artistJson))
			    ),
			    "text", Map.of(
			        "format", Map.of(
			            "type", "json_schema",
			            "name", "artist_90_day_visual_plan",
			            "strict", true,
			            "schema", schema
			        )
			    )
			);

		String responseText = webClient.post().uri("/responses").bodyValue(requestBody).retrieve()
				.onStatus(status -> status.isError(),
						res -> res.bodyToMono(String.class)
								.flatMap(error -> Mono.error(new RuntimeException("OpenAI API error: " + error))))
				.bodyToMono(JsonNode.class).timeout(Duration.ofSeconds(120)).map(res -> {
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
	}

	
	private Map<String, Object> buildPlanSchema() {

	    Map<String, Object> properties = new java.util.LinkedHashMap<>();

	    properties.put("artist_name", Map.of("type", "string"));
	    properties.put("plan_title", Map.of("type", "string"));
	    properties.put("executive_summary", Map.of("type", "string"));

	    properties.put("phases", Map.of(
	            "type", "array",
	            "items", buildPhaseSchema()
	    ));

	    properties.put("priority_targets", buildPriorityTargetsSchema());

	    properties.put("dashboard_message", Map.of("type", "string"));

	    Map<String, Object> schema = new java.util.LinkedHashMap<>();

	    schema.put("type", "object");
	    schema.put("additionalProperties", false);
	    schema.put("properties", properties);

	    schema.put("required", List.of(
	            "artist_name",
	            "plan_title",
	            "executive_summary",
	            "phases",
	            "priority_targets",
	            "dashboard_message"
	    ));

	    return schema;
	}
	
	private Map<String, Object> buildPhaseSchema() {

	    Map<String, Object> properties = new java.util.LinkedHashMap<>();

	    properties.put("phase", Map.of("type", "string"));
	    properties.put("title", Map.of("type", "string"));
	    properties.put("days", Map.of("type", "string"));

	    properties.put("actions", Map.of(
	            "type", "array",
	            "items", buildActionSchema()
	    ));

	    Map<String, Object> schema = new java.util.LinkedHashMap<>();

	    schema.put("type", "object");
	    schema.put("additionalProperties", false);
	    schema.put("properties", properties);

	    schema.put("required", List.of(
	            "phase",
	            "title",
	            "days",
	            "actions"
	    ));

	    return schema;
	}
	
	private Map<String, Object> buildActionSchema() {

	    Map<String, Object> properties = new java.util.LinkedHashMap<>();

	    properties.put("headline", Map.of("type", "string"));
	    properties.put("description", Map.of("type", "string"));

	    properties.put("targets", Map.of(
	            "type", "array",
	            "items", buildTargetSchema()
	    ));

	    Map<String, Object> schema = new java.util.LinkedHashMap<>();

	    schema.put("type", "object");
	    schema.put("additionalProperties", false);
	    schema.put("properties", properties);

	    schema.put("required", List.of(
	            "headline",
	            "description",
	            "targets"
	    ));

	    return schema;
	}
	
	private Map<String, Object> buildTargetSchema() {

	    Map<String, Object> properties = new java.util.LinkedHashMap<>();

	    properties.put("name", Map.of("type", "string"));
	    properties.put("type", Map.of("type", "string"));
	    properties.put("url", Map.of("type", "string"));
	    properties.put("user_handle", Map.of("type", "string"));
	    properties.put("reason", Map.of("type", "string"));

	    Map<String, Object> schema = new java.util.LinkedHashMap<>();

	    schema.put("type", "object");
	    schema.put("additionalProperties", false);
	    schema.put("properties", properties);

	    schema.put("required", List.of(
	            "name",
	            "type",
	            "url",
	            "user_handle",
	            "reason"
	    ));

	    return schema;
	}
	
	private Map<String, Object> buildPriorityTargetsSchema() {

	    Map<String, Object> properties = new java.util.LinkedHashMap<>();

	    properties.put("playlists", Map.of(
	            "type", "array",
	            "items", buildTargetSchema()
	    ));

	    properties.put("tiktokers", Map.of(
	            "type", "array",
	            "items", buildTargetSchema()
	    ));

	    properties.put("markets", Map.of(
	            "type", "array",
	            "items", buildTargetSchema()
	    ));

	    Map<String, Object> schema = new java.util.LinkedHashMap<>();

	    schema.put("type", "object");
	    schema.put("additionalProperties", false);
	    schema.put("properties", properties);

	    schema.put("required", List.of(
	            "playlists",
	            "tiktokers",
	            "markets"
	    ));

	    return schema;
	}

	


}