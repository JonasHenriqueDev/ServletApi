package br.upe.garanhus.esw.pweb.model.service;

import java.io.IOException;
import java.util.List;

import br.upe.garanhus.esw.pweb.model.AplicacaoException;
import br.upe.garanhus.esw.pweb.model.Cat;
import br.upe.garanhus.esw.pweb.model.DTO.CatDTO;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.servlet.http.HttpServletRequest;

public class CatService {

	private Cat cat;
	private List<CatDTO> catList;
	private JsonArray catJsonArray;
	private JsonArrayBuilder jsonArrayBuilder;

	public CatService() {
		cat = new Cat();
		jsonArrayBuilder = Json.createArrayBuilder();
	}

	public JsonArray getAllCats() {
		catList = cat.parseData(cat.fetchData());

		try {
			for (CatDTO catDTO : catList) {
				JsonObject catObject = buildCatObject(catDTO);
				jsonArrayBuilder.add(catObject);
			}

			catJsonArray = jsonArrayBuilder.build();
		} catch (Exception e) {
			throw new AplicacaoException("Erro ao recuperar todos os gatos", e);
		}

		return catJsonArray;
	}

	public JsonArray getCatById(String id) {
		boolean gatoEncontrado = false;

		try {
			for (CatDTO catDTO : catList) {
				if (catDTO.getId().equals(id)) {
					JsonObject catArray = buildCatObject(catDTO);
					jsonArrayBuilder.add(catArray);
				}
			}

			catJsonArray = jsonArrayBuilder.build();

		} catch (Exception e) {
			throw new AplicacaoException("Nenhum gato econtrado para o id: " + id, e);
		}

		return catJsonArray;
	}

	public String getIdFromRequest(HttpServletRequest request) {
		JsonReader reader = null;

		try {
			reader = Json.createReader(request.getReader());
		} catch (IOException e) {
			throw new AplicacaoException("Erro ao ler ID do corpo da solicitação.", e);
		}

		JsonObject requestBody = reader.readObject();

		return requestBody.getString("id");
	}

	protected JsonObject buildCatObject(CatDTO catDTO) {
		return Json.createObjectBuilder().add("id", catDTO.getId()).add("url", catDTO.getUrl())
				.add("width", catDTO.getWidth()).add("height", catDTO.getHeight()).build();
	}
}
