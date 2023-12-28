package com.wadhara.entity;

import java.util.List;

public class ResponseSolicitacaoScore {
	
	private String idTransacao;
	
	private String numeroNfe;
	
	private List<Constatacao> constatacao;

	public String getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(String idTransacao) {
		this.idTransacao = idTransacao;
	}

	public String getNumeroNfe() {
		return numeroNfe;
	}

	public void setNumeroNfe(String numeroNfe) {
		this.numeroNfe = numeroNfe;
	}

	public List<Constatacao> getConstatacao() {
		return constatacao;
	}

	public void setConstatacao(List<Constatacao> constatacao) {
		this.constatacao = constatacao;
	}

	public ResponseSolicitacaoScore(String idTransacao, String numeroNfe, List<Constatacao> constatacao) {
		super();
		this.idTransacao = idTransacao;
		this.numeroNfe = numeroNfe;
		this.constatacao = constatacao;
	}

	public ResponseSolicitacaoScore() {
		super();
	}

	
	
}