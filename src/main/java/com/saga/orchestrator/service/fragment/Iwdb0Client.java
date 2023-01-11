package com.saga.orchestrator.service.fragment;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.saga.orchestrator.model.request.DispoRequest;
import com.saga.orchestrator.model.request.ProdottiRequest;
import com.saga.orchestrator.model.request.StatusRequest;
import com.saga.orchestrator.model.response.DispoResponse;
import com.saga.orchestrator.model.response.ProdottiResponse;

import reactor.core.publisher.Mono;

@Service
public class Iwdb0Client {

	private String iwdbUri = "http://localhost:8083";
	WebClient webClient = WebClient.create(iwdbUri);

	// transaction Prodotto
	public ProdottiResponse transactProdotto(ProdottiRequest request,int trxId) {

		ProdottiResponse iResp = new ProdottiResponse();
		Mono<ProdottiResponse> response = null;
		
		try {
			response = webClient.post()
					.uri("prodotti/transact")
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.body(Mono.just(request), ProdottiRequest.class)
					.retrieve()
					.bodyToMono(ProdottiResponse.class);
		}
		catch(Exception e) {
			updateStatus(trxId, "Cancelled");
			iResp.setTransaction(false);
			return iResp;
		}
		iResp = response.block();
		
		if(iResp.getTransaction() == false)
			updateStatus(trxId, "Cancelled");
		
		return iResp;
	}


	//rollback Prodotto
	public ProdottiResponse rollbackProdotto(ProdottiRequest request) {

		ProdottiResponse iResp = new ProdottiResponse();
		Mono<ProdottiResponse> response = null;

		try {
			response = webClient.post()
					.uri("prodotti/rollb")
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.body(Mono.just(request), ProdottiRequest.class)
					.retrieve()
					.bodyToMono(ProdottiResponse.class);

		}catch(Exception e) {

		}

		iResp = response.block();

		return iResp;
	}

	//transaction money
	public DispoResponse dispoPay(DispoRequest request, int trxId) {

		DispoResponse iResp = new DispoResponse();
		Mono<DispoResponse> response = null;

		try {
			response = webClient.post()
					.uri("/dispo/transaction")
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.body(Mono.just(request), ProdottiRequest.class)
					.retrieve()
					.bodyToMono(DispoResponse.class);

		
		iResp = response.block();
		
		updateStatus(trxId, "Complete");
		
		}
		catch(Exception e) {
			updateStatus(trxId, "Cancelled");
			iResp.setTransactionOk(false);
			return iResp;
		}
		
		return iResp;
	}




	// metodo che aggiorna status order
//	private void updateStatus(String bt,String status) {
//
//		webClient.put()
//		.uri("/ordini/update"+bt+"/"+status)
//		.accept(MediaType.APPLICATION_JSON)
//		.contentType(MediaType.APPLICATION_JSON)
//		.retrieve();
//	}
	
	private Void updateStatus(int trxId, String status) {
		
		StatusRequest request = new StatusRequest();
		request.setTrxId(trxId);
		request.setStatus(status);
		
		return webClient.put()
				.uri("/ordini/update/"+trxId+"/"+status)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Void.class).block();
	}
}
