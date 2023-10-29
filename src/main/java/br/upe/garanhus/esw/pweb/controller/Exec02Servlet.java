package br.upe.garanhus.esw.pweb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import br.upe.garanhus.esw.pweb.model.Cat;
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
@WebServlet("/processa-imagem")
public class Exec02Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CatService service;

	/**
	 * Default constructor.
	 */
	public Exec02Servlet() {
		// TODO Auto-generated constructor stub
	}

	public void init() {
		service = new CatService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JsonArray catJsonArray = service.getAllCats();

		// Define o tipo de conteúdo da resposta como JSON
		response.setContentType("application/json");

		// Escreve o JSON array como resposta
		response.getWriter().write(catJsonArray.toString());

	}

}
