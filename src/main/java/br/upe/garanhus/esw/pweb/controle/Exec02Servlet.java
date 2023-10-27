package br.upe.garanhus.esw.pweb.controle;

import java.io.IOException;
import java.io.PrintWriter;

import br.upe.garanhus.esw.pweb.modelo.Servico;
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
	private Servico servico;

	/**
	 * Default constructor.
	 */
	public Exec02Servlet() {
		// TODO Auto-generated constructor stub
	}

	public void init() {
		// Inicialize a instância de Servico no método init do Servlet
		servico = new Servico();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		servico.enviarResposta(response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
