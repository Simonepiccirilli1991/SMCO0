package com.saga.orchestrator.model.response;


public class Prodotti {

	private int id;
	private String codiceProdotto;
	private int aviableService;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodiceProdotto() {
		return codiceProdotto;
	}
	public void setCodiceProdotto(String codiceProdotto) {
		this.codiceProdotto = codiceProdotto;
	}
	public int getAviableService() {
		return aviableService;
	}
	public void setAviableService(int aviableService) {
		this.aviableService = aviableService;
	}
	
	
}
