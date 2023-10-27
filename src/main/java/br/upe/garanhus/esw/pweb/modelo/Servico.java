package br.upe.garanhus.esw.pweb.modelo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import jakarta.servlet.http.HttpServletResponse;

public class Servico {

	private static HttpClient cliente;

	private static HttpRequest request;

	private static HttpResponse response;

	private static final String BASE_URL = "https://api.thecatapi.com/v1/images/search";

	public Servico() {
		this.cliente = HttpClient.newHttpClient();

		this.request = HttpRequest.newBuilder().uri(java.net.URI.create(BASE_URL)).build();

		this.response = null;
	}

	public void enviarResposta(HttpServletResponse servletResponse){
		try {
			this.response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

			if (response != null) {
				System.out.println("RESPOSTA"+response);
				servletResponse.setContentType("text/html");
				PrintWriter out = servletResponse.getWriter();
				out.println("Resposta da API externa: " + response.body());
			} else {
				servletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();

		}

	}
}
