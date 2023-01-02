package com.saga.orchestrator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saga.orchestrator.model.request.DispoRequest;
import com.saga.orchestrator.model.request.OrchestratorRequest;
import com.saga.orchestrator.model.request.ProdottiRequest;
import com.saga.orchestrator.model.response.DispoResponse;
import com.saga.orchestrator.model.response.OrchestratorResponse;
import com.saga.orchestrator.model.response.ProdottiResponse;
import com.saga.orchestrator.service.fragment.Iwdb0Client;

@Service
public class OrchestrationService {

	@Autowired
	Iwdb0Client client;

	public OrchestratorResponse orchestrate(OrchestratorRequest request) {

		OrchestratorResponse response = new OrchestratorResponse();

		// chiamata a prodotti, se ok chiamata ad acquisti
		//TODO riprendere modo in cui faceva il tizio



		return response;
	}

	public OrchestratorResponse orchestrateClassic(OrchestratorRequest request) {

		OrchestratorResponse response = new OrchestratorResponse();

		// chiamata a prodotti, se ok chiamata ad acquisti

		// setto request per prodotti
		ProdottiRequest proRequest = new ProdottiRequest();
		proRequest.setCodiceProdotto(request.getCodiceProdotto());

		ProdottiResponse proResponse = client.transactProdotto(proRequest,request.getTrxId());

		if(proResponse.getTransaction() == false) {
			response.setTransaction(false);
			return response;
		}

		DispoRequest dispRequest = new DispoRequest();
		dispRequest.setBtToPay(request.getBtToPay());
		dispRequest.setBtToReceive(request.getBtToReceive());
		dispRequest.setImporto(request.getImporto());


		DispoResponse dispRespo = client.dispoPay(dispRequest,request.getTrxId());

		if(dispRespo.getTransactionOk() == false) {
			// rollback
			client.rollbackProdotto(proRequest);
			return response;
		}

		response.setTransaction(true);

		return response;
	}
}
