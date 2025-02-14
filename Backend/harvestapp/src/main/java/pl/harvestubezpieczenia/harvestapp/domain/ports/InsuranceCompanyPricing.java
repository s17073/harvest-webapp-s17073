package pl.harvestubezpieczenia.harvestapp.domain.ports;

import pl.harvestubezpieczenia.harvestapp.domain.DTOs.OfferDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.Calculation;
import pl.harvestubezpieczenia.harvestapp.domain.model.InsuranceCompany;
import pl.harvestubezpieczenia.harvestapp.domain.model.Offer;

import java.util.List;

public interface InsuranceCompanyPricing {

    List<OfferDto> getOffers(Calculation calculation, InsuranceCompany insuranceCompany, Offer offer);
    Integer getInsuranceCompanyId();


}
