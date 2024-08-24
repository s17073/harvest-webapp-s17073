package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.PartOfTerytDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.Teryt;
import pl.harvestubezpieczenia.harvestapp.domain.ports.TerytRepo;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.TerytRepoJpa;

import java.util.List;

@Service
public class TerytAdapterJpa implements TerytRepo {

    private final TerytRepoJpa terytRepoJpa;

    @Autowired
    public TerytAdapterJpa(TerytRepoJpa terytRepoJpa) {
        this.terytRepoJpa = terytRepoJpa;
    }

    @Override
    public List<Teryt> getAllItems() {
        return terytRepoJpa.getAllItems();
    }

    @Override
    public List<PartOfTerytDto> getWojewodztwa() {
        return terytRepoJpa.getWojewodztwa();
    }

    @Override
    public List<PartOfTerytDto> getPowiaty(String teryt) {
        return terytRepoJpa.getPowiaty(teryt);
    }

    @Override
    public List<PartOfTerytDto> getGminy(String teryt) {
        return terytRepoJpa.getGminy(teryt);
    }

    @Override
    public String getTerytPart(String substring) {
        return terytRepoJpa.getTerytPart(substring);
    }

    @Override
    public Teryt getTeryt(String teryt) {
        return terytRepoJpa.getTeryt(teryt);
    }
}
