package pl.harvestubezpieczenia.harvestapp.adapter.adapterJpa;

import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.adapter.repositoryJpa.OfferRepoJpa;
import pl.harvestubezpieczenia.harvestapp.domain.model.Offer;
import pl.harvestubezpieczenia.harvestapp.domain.ports.OfferRepo;

import java.util.Optional;

@Service
public class OfferAdapterJpa implements OfferRepo {

    private final OfferRepoJpa offerRepoJpa;

    public OfferAdapterJpa(OfferRepoJpa offerRepoJpa) {
        this.offerRepoJpa = offerRepoJpa;
    }

    @Override
    public void saveOffer(Offer offer) {
        offerRepoJpa.save(offer);
    }

    @Override
    public Optional<Offer> findOfferById(Long id) {
        return offerRepoJpa.findById(id);
    }

    @Override
    public void setUnactiveOfferByCalcId(Long calcId) {
        offerRepoJpa.setUnactiveOfferByCalcId(calcId);
    }

}
