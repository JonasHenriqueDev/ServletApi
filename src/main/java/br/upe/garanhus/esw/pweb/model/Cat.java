package br.upe.garanhus.esw.pweb.model;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.upe.garanhus.esw.pweb.model.DTO.CatDTO;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Cat {
	private static HttpClient client;

	private static HttpRequest request;

	private static final String BASE_URL = "https://api.thecatapi.com/v1/images/search?limit=10";

	private static List<CatDTO> catList;

	private static final Logger logger = Logger.getLogger(Cat.class.getName()); // Substitua 'Cat' pelo nome da sua
																				// classe

	public Cat() {
		Cat.client = HttpClient.newHttpClient();

		Cat.request = HttpRequest.newBuilder().uri(URI.create(BASE_URL)).build();

		Cat.catList = new ArrayList<>();
	}

	public String fetchData() {
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				return response.body();
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<CatDTO> parseData(String response) {

		try {
			JsonReader jsonReader = Json.createReader(new StringReader(response));
			JsonArray jsonArray = jsonReader.readArray();

			for (JsonObject catJson : jsonArray.getValuesAs(JsonObject.class)) {
				String id = catJson.getString("id");
				String url = catJson.getString("url");
				int width = catJson.getInt("width");
				int height = catJson.getInt("height");
				CatDTO catDTO = new CatDTO(id, url, width, height);

				catList.add(catDTO);
			}

			logger.info("Tamanho da lista catList: " + catList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		List<CatDTO> teste = catList;
		return teste;
	}
}
