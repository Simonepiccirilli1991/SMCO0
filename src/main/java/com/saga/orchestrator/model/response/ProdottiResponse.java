package com.saga.orchestrator.model.response;


public class ProdottiResponse {

	private Prodotti prodotto;
	private Boolean transaction;
	
	public Prodotti getProdotto() {
		return prodotto;
	}
	public void setProdotto(Prodotti prodotto) {
		this.prodotto = prodotto;
	}
	public Boolean getTransaction() {
		return transaction;
	}
	public void setTransaction(Boolean transaction) {
		this.transaction = transaction;
	}
	
	
}
