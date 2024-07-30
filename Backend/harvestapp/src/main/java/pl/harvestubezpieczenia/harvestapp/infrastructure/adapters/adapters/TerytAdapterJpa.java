package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
