package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.model.Policy;
import pl.harvestubezpieczenia.harvestapp.domain.ports.PolicyRepo;

@Service
public class PolicyService {

    private final PolicyRepo policyRepo;

    public PolicyService(PolicyRepo policyRepo) {
        this.policyRepo = policyRepo;
    }

    public ResponseEntity<Policy> getPolicyById(int id) {
        Policy policy = policyRepo.getPolicyById(id).orElse(null);

        if(policy == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(policy, HttpStatus.OK);

    }
}
