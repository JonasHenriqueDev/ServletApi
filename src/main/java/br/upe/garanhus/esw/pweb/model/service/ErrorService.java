package br.upe.garanhus.esw.pweb.model.service;

import br.upe.garanhus.esw.pweb.model.DTO.ErrorDTO;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class ErrorService {

	public ErrorService() {
		// TODO Auto-generated constructor stub
	}
	
	public static JsonObject toJson(ErrorDTO errorDTO) {
        JsonObjectBuilder builder = Json.createObjectBuilder()
            .add("codigo", errorDTO.getCodigo())
            .add("mensagem", errorDTO.getMensagem())
            .add("detalhe", errorDTO.getDetalhe());

        return builder.build();
    }
}
