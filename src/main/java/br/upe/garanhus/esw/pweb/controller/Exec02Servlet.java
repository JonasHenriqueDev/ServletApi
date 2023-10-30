package br.upe.garanhus.esw.pweb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import br.upe.garanhus.esw.pweb.model.Cat;
import br.upe.garanhus.esw.pweb.model.DTO.CatDTO;
import br.upe.garanhus.esw.pweb.model.service.CatService;
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

//		JsonArray catJsonArray = service.getAllCats();
		response.getWriter().write(service.getAllCats().toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		JsonArray catJsonArray = service.getCatById(service.getIdFromRequest(request));
		response.getWriter().write(catJsonArray.toString());

	}

}
