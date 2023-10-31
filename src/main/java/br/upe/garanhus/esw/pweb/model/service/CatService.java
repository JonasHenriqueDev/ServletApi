package br.upe.garanhus.esw.pweb.model.service;

import java.io.IOException;
import java.util.List;
import br.upe.garanhus.esw.pweb.model.AplicacaoException;
import br.upe.garanhus.esw.pweb.model.CatApiResponse;
import br.upe.garanhus.esw.pweb.model.DTO.CatDTO;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.servlet.http.HttpServletRequest;

public class CatService {

  private CatApiResponse catApiResponse;
  private List<CatDTO> catList;
  private JsonArray catJsonArray;
  private JsonArrayBuilder jsonArrayBuilder;

  private static final String ERROR_FETCH_CATS = "Erro ao recuperar todos os gatos";
  private static final String ERROR_NO_CAT_FOUND = "Nenhum gato encontrado para o ID: ";
  private static final String ERROR_READ_REQUEST_BODY = "Erro ao ler ID do corpo da solicitação.";

  public CatService() {
    catApiResponse = new CatApiResponse();
    jsonArrayBuilder = Json.createArrayBuilder();
  }

  public JsonArray getAllCats() {
    try {
      catList = catApiResponse.parseData(catApiResponse.fetchData());

      for (CatDTO catDTO : catList) {
        JsonObject catObject = buildCatObject(catDTO);
        jsonArrayBuilder.add(catObject);
      }

      catJsonArray = jsonArrayBuilder.build();
    } catch (AplicacaoException e) {
      throw new AplicacaoException(ERROR_FETCH_CATS, e, 500);
    }

    return catJsonArray;
  }

  public JsonArray getCatById(String id) {
    if (id.equals("invalid")) {
      throw new AplicacaoException("ID inválido", new Exception(), 400);
    }
    try {
      for (CatDTO catDTO : catList) {
        if (catDTO.getId().equals(id)) {
          JsonObject catArray = buildCatObject(catDTO);
          jsonArrayBuilder.add(catArray);
        }
      }

      catJsonArray = jsonArrayBuilder.build();
    } catch (AplicacaoException e) {
      throw new AplicacaoException(ERROR_NO_CAT_FOUND + id, e);
    }

    return catJsonArray;
  }

  public String getIdFromRequest(HttpServletRequest request) {
    try {
      JsonReader reader = Json.createReader(request.getReader());
      JsonObject requestBody = reader.readObject();
      return requestBody.getString("id");
    } catch (IOException e) {
      throw new AplicacaoException(ERROR_READ_REQUEST_BODY, e);
    }
  }

  protected JsonObject buildCatObject(CatDTO catDTO) {
    return Json.createObjectBuilder().add("id", catDTO.getId()).add("url", catDTO.getUrl())
        .add("width", catDTO.getWidth()).add("height", catDTO.getHeight()).build();
  }
}
