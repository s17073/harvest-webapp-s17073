package pl.harvestubezpieczenia.harvestapp.adapter.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.harvestubezpieczenia.harvestapp.domain.model.Policy;
import pl.harvestubezpieczenia.harvestapp.domain.services.PolicyService;

@Controller
@RequestMapping("policy")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping("{policyId}")
    public ResponseEntity<Policy> getPolicyById(@PathVariable("policyId") int id){
        return policyService.getPolicyById(id);
    }
}
