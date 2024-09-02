package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.SoilClassListDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.SoilClass;
import pl.harvestubezpieczenia.harvestapp.domain.ports.SoilClassRepo;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.GenericCrudRepoJpa;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.SoilClassRepoJpa;

import java.util.List;

@Service
public class SoilClassAdapterJpa extends GenericCrudRepoAdapterJpa<SoilClass> implements SoilClassRepo {

    private final SoilClassRepoJpa soilClassRepoJpa;

    public SoilClassAdapterJpa(@Qualifier("soilClassRepoJpa") GenericCrudRepoJpa<SoilClass> genericCrudRepoJpa, SoilClassRepoJpa soilClassRepoJpa) {
        super(genericCrudRepoJpa);
        this.soilClassRepoJpa = soilClassRepoJpa;
    }

    @Override
    public List<SoilClassListDto> getSoilClassNames() {
        return soilClassRepoJpa.getSoilClassNames();
    }

    @Override
    public SoilClass getSoilClassById(int idKlasaGleby) {
        return soilClassRepoJpa.getSoilClassById(idKlasaGleby);
    }
}
