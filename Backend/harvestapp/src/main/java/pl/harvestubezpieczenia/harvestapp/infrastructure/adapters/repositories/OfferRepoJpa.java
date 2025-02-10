package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.harvestubezpieczenia.harvestapp.domain.model.Offer;

@Repository
public interface OfferRepoJpa extends JpaRepository<Offer, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE harvest.oferta set status_oferty = 'NIEAKTYWNA' where id_kalkulacja = :calcId" , nativeQuery = true)
    void setUnactiveOfferByCalcId(Long calcId);
}
