package pl.harvestubezpieczenia.harvestapp.domain.services.Pricing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.OfferDto;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.OfferMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.*;
import pl.harvestubezpieczenia.harvestapp.domain.ports.OfferRepo;
import pl.harvestubezpieczenia.harvestapp.domain.services.InsuranceCompanyPricing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InsuranceCompanyB implements InsuranceCompanyPricing {

    private final OfferRepo offerRepo;
    private final OfferMapper offerMapper;

    @Autowired
    public InsuranceCompanyB(OfferRepo offerRepo, OfferMapper offerMapper) {
        this.offerRepo = offerRepo;
        this.offerMapper = offerMapper;
    }

    @Override
    public List<OfferDto> getOffers(Calculation calculation, InsuranceCompany insuranceCompany, Offer offer) {
        List<OfferDto> offerDtoList = new ArrayList<>();

        for(int i = 0; i<2; i++) {
            offer = new Offer();


            OfferDto offerDto = new OfferDto();

            double livestockInsuranceSum = 0;
            double cropInsuranceSum = 0;

            offer.setUbezpieczyciel(insuranceCompany);
            offer.setKalkulacja(calculation);
            offer.setSkladka(1600 * (i+1) - (((i+1)/2)*600));
            offer.setDataWygasniecia(LocalDateTime.now().plusWeeks(1));
            offer.setStatusOferty("AKTYWNA");

            offerRepo.saveOffer(offer);

            List<Livestock> offerLivestock = offer.getKalkulacja().getZwierzeta();
            List<Crop> offerCrops = offer.getKalkulacja().getUprawy();

            for (Livestock livestock : offerLivestock) {
                livestockInsuranceSum = livestockInsuranceSum + livestock.getSumaUbezpieczenia();
            }

            for (Crop crop : offerCrops) {
                cropInsuranceSum = cropInsuranceSum + crop.getSumaUbezpieczenia();
            }

            offerDto.setNumerOferty(offer.getNumerOferty());
            offerDto = offerMapper.mapToDto(offer);
            offerDto.setSumaUbezpieczeniaUpraw(cropInsuranceSum);
            offerDto.setSumaUbezpieczeniaZwierzat(livestockInsuranceSum);


            if (offerDto.getNumerOferty() == null) {
                offerDto.setNumerOferty("O" + String.format("%07d", offer.getIdOferta()));
            }

            offerDtoList.add(offerDto);

        }

        return offerDtoList;

    }

    @Override
    public Integer getInsuranceCompanyId() {
        return 2;
    }

}
