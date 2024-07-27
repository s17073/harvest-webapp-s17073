package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.SoilClass;
import pl.harvestubezpieczenia.harvestapp.domain.ports.SoilClassRepo;

@Repository
@Qualifier("soilClassRepJpa")
public interface SoilClassRepoJpa extends GenericCrudRepoJpa<SoilClass, Long>, SoilClassRepo {

}
