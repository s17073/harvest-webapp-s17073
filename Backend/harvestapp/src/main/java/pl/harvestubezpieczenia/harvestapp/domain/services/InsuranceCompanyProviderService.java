package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.ports.InsuranceCompanyPricing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InsuranceCompanyProviderService {

    private final Map<Integer, InsuranceCompanyPricing> insuranceCompanies = new HashMap<>();

    @Autowired
    public InsuranceCompanyProviderService(List<InsuranceCompanyPricing> insuranceCompanyPricingList) {
        for(InsuranceCompanyPricing insuranceCompanyPricing : insuranceCompanyPricingList){
            insuranceCompanies.put(insuranceCompanyPricing.getInsuranceCompanyId(), insuranceCompanyPricing);
        }
    }

    public InsuranceCompanyPricing getInsuranceCompanyPricing(int insuranceCompanyId){
        return insuranceCompanies.get(insuranceCompanyId);
    }

}