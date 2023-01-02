package com.saga.orchestrator.model.request;

public class DispoRequest {

	private String btToPay;
	private String btToReceive; 
	private Double importo;
	
	public String getBtToPay() {
		return btToPay;
	}
	public void setBtToPay(String btToPay) {
		this.btToPay = btToPay;
	}
	public String getBtToReceive() {
		return btToReceive;
	}
	public void setBtToReceive(String btToReceive) {
		this.btToReceive = btToReceive;
	}
	public Double getImporto() {
		return importo;
	}
	public void setImporto(Double importo) {
		this.importo = importo;
	} 
	
	
}
