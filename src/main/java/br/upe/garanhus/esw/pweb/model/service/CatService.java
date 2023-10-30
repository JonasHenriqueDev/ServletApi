package br.upe.garanhus.esw.pweb.model.service;

import java.io.IOException;
import java.util.List;

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
	    jsonArrayBuilder = Json.createArrayBuilder(); // Crie um novo JsonArrayBuilder

	    try {
	        for (CatDTO catDTO : catList) {
	            JsonObject catObject = buildCatObject(catDTO); // Crie um objeto JSON para cada CatDTO
	            jsonArrayBuilder.add(catObject);
	        }

	        catJsonArray = jsonArrayBuilder.build();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return catJsonArray;
	}

	public JsonArray getCatById(String id) {
		
		try {
			for (CatDTO catDTO : catList) {
				if (catDTO.getId().equals(id)) {
					JsonObject catArray = buildCatObject(catDTO);
					jsonArrayBuilder.add(catArray);
				}
			}

			catJsonArray = jsonArrayBuilder.build();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return catJsonArray;
	}

	public String getIdFromRequest(HttpServletRequest request) {
		JsonReader reader = null;
		
		try {
			reader = Json.createReader(request.getReader());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JsonObject requestBody = reader.readObject();
		
		return requestBody.getString("id");
	}

	protected JsonObject buildCatObject(CatDTO catDTO) {
	    return Json.createObjectBuilder()
	            .add("id", catDTO.getId())
	            .add("url", catDTO.getUrl())
	            .add("width", catDTO.getWidth())
	            .add("height", catDTO.getHeight())
	            .build();
	}
}
