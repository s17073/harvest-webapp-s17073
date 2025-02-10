package pl.harvestubezpieczenia.harvestapp.domain.ports;

import pl.harvestubezpieczenia.harvestapp.domain.model.Offer;

import java.util.Optional;

public interface OfferRepo {

    void saveOffer(Offer offer);
    Optional<Offer> findOfferById(Long id);
    void setUnactiveOfferByCalcId(Long calcId);

}
