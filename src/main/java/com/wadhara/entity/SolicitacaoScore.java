package com.wadhara.entity;

public class SolicitacaoScore {

	private String idSolicitacao;

	private String idNota;

	private String conteudoNota;

	private String conteudoDevedor;

	private String conteudoCredor;

	public String getIdSolicitacao() {
		return idSolicitacao;
	}

	public void setIdSolicitacao(String idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}

	public String getIdNota() {
		return idNota;
	}

	public void setIdNota(String idNota) {
		this.idNota = idNota;
	}

	public String getConteudoNota() {
		return conteudoNota;
	}

	public void setConteudoNota(String conteudoNota) {
		this.conteudoNota = conteudoNota;
	}

	public String getConteudoDevedor() {
		return conteudoDevedor;
	}

	public void setConteudoDevedor(String conteudoDevedor) {
		this.conteudoDevedor = conteudoDevedor;
	}

	public String getConteudoCredor() {
		return conteudoCredor;
	}

	public void setConteudoCredor(String conteudoCredor) {
		this.conteudoCredor = conteudoCredor;
	}

	public SolicitacaoScore(String idSolicitacao, String idNota, String conteudoNota, String conteudoDevedor,
			String conteudoCredor) {
		super();
		this.idSolicitacao = idSolicitacao;
		this.idNota = idNota;
		this.conteudoNota = conteudoNota;
		this.conteudoDevedor = conteudoDevedor;
		this.conteudoCredor = conteudoCredor;
	}

	public SolicitacaoScore() {
		super();
	}

}
