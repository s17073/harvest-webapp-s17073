package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CoverListDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.Cover;
import pl.harvestubezpieczenia.harvestapp.domain.ports.CoverRepo;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.CoverRepoJpa;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.GenericCrudRepoJpa;

import java.util.List;

@Service
public class CoverAdapterJpa extends GenericCrudRepoAdapterJpa<Cover> implements CoverRepo {

    public final CoverRepoJpa coverRepoJpa;

    public CoverAdapterJpa(@Qualifier("coverRepoJpa") GenericCrudRepoJpa<Cover> genericCrudRepoJpa, CoverRepoJpa coverRepoJpa) {
        super(genericCrudRepoJpa);
        this.coverRepoJpa = coverRepoJpa;
    }

    @Override
    public List<CoverListDto> getCoverListOfLivestock() {
        return coverRepoJpa.getCoverListOfLivestock();
    }

    @Override
    public List<CoverListDto> getCoverListOfCrops() {
        return coverRepoJpa.getCoverListOfCrops();
    }

    @Override
    public Cover getCoverById(int id) {
        return coverRepoJpa.getCoverById(id);
    }


}
