package br.upe.garanhus.esw.pweb.controller;

import java.io.IOException;

import br.upe.garanhus.esw.pweb.model.AplicacaoException;
import br.upe.garanhus.esw.pweb.model.DTO.ErrorDTO;
import br.upe.garanhus.esw.pweb.model.service.CatService;
import br.upe.garanhus.esw.pweb.model.service.ErrorService;
import jakarta.json.JsonArray;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Exec02Servlet
 */
@WebServlet(name = "Exec02Servlet", urlPatterns = { "/processa-imagem" })
public class Exec02Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CatService service;

	public Exec02Servlet() {
		// TODO Auto-generated constructor stub
	}

	public void init() {
		service = new CatService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		try {
			JsonArray catJsonArray = service.getAllCats();
			response.getWriter().write(catJsonArray.toString());
		} catch (AplicacaoException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			ErrorDTO errorResponse = new ErrorDTO(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro na solicitação",
					e.getMessage());
			response.getWriter().write(ErrorService.toJson(errorResponse).toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		try {
			JsonArray catJsonArray = service.getCatById(service.getIdFromRequest(request));
			response.getWriter().write(catJsonArray.toString());
		} catch (AplicacaoException e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			ErrorDTO errorResponse = new ErrorDTO(HttpServletResponse.SC_NOT_FOUND, "Não encontrado",
					e.getMessage());
			response.getWriter().write(ErrorService.toJson(errorResponse).toString());
		}
	}
}
