package com.saga.orchestrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saga.orchestrator.model.request.OrchestratorRequest;
import com.saga.orchestrator.model.response.OrchestratorResponse;
import com.saga.orchestrator.service.OrchestrationService;

@RestController
@RequestMapping("orc")
public class OrchestratorController {

	@Autowired
	OrchestrationService orcService;
	
	
	@PostMapping("do")
	public OrchestratorResponse orcTrans(@RequestBody OrchestratorRequest request) {
		
		return orcService.orchestrateClassic(request);
	}
}
