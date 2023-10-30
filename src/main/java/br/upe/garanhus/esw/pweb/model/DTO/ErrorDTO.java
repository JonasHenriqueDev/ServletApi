package br.upe.garanhus.esw.pweb.model.DTO;

public class ErrorDTO {

	private int codigo;
	private String mensagem;
	private String detalhe;

	public ErrorDTO() {}

	public ErrorDTO(int codigo, String mensagem, String detalhe) {
		this.codigo = codigo;
		this.mensagem = mensagem;
		this.detalhe = detalhe;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}
}
