package pl.harvestubezpieczenia.harvestapp.adapter.adapterJpa;

import pl.harvestubezpieczenia.harvestapp.adapter.repositoryJpa.GenericCrudRepoJpa;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

import java.util.List;

public class GenericCrudRepoAdapterJpa<T> implements GenericCrudRepo<T> {

    private final GenericCrudRepoJpa<T> genericCrudRepoJpa;

    public GenericCrudRepoAdapterJpa(GenericCrudRepoJpa<T> genericCrudRepoJpa) {
        this.genericCrudRepoJpa = genericCrudRepoJpa;
    }

    @Override
    public List<T> getAllItems() {
        return genericCrudRepoJpa.getAllItems();
    }

    @Override
    public void addItem(T t) {
        genericCrudRepoJpa.addItem(t);
    }


}
