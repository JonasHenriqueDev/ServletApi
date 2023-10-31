package br.upe.garanhus.esw.pweb.model.service;

import java.io.IOException;
import java.util.function.Supplier;
import br.upe.garanhus.esw.pweb.model.AplicacaoException;
import br.upe.garanhus.esw.pweb.model.DTO.ErrorDTO;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ErrorService {

  public ErrorService() {
    // TODO Auto-generated constructor stub
  }

  public static JsonObject toJson(ErrorDTO errorDTO) {
    JsonObjectBuilder builder = Json.createObjectBuilder().add("codigo", errorDTO.getCodigo())
        .add("mensagem", errorDTO.getMensagem()).add("detalhe", errorDTO.getDetalhe());

    System.out.println("DETALHEEEEEEEEEEEEEEEEEEEE  " + errorDTO.getDetalhe());

    return builder.build();
  }

  public static void handleErrorResponse(HttpServletResponse response, AplicacaoException e)
      throws IOException {

    int httpStatusCode = determineHttpStatusCode(e);


    response.setStatus(httpStatusCode);
    ErrorDTO errorResponse = new ErrorDTO(httpStatusCode, e.getMessage(), e.getCause().toString());
    JsonObject errorJson = ErrorService.toJson(errorResponse);
    response.getWriter().write(errorJson.toString());
  }

  public static void handleRequest(HttpServletRequest request, HttpServletResponse response,
      Supplier<JsonArray> action) throws IOException {
    try {
      JsonArray catJsonArray = action.get();
      response.getWriter().write(catJsonArray.toString());
    } catch (AplicacaoException e) {
      handleErrorResponse(response, e);
    }
  }

  private static Integer determineHttpStatusCode(AplicacaoException e) {
    if (e.getHttpStatusCode() != null) {
      return e.getHttpStatusCode();
    } else {
      return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    }
  }
}
