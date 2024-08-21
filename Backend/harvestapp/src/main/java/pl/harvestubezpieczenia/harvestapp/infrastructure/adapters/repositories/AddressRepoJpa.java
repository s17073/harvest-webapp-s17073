package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.Address;

import java.util.List;

@Repository
public interface AddressRepoJpa extends JpaRepository<Address, Long> {

    default List<Address> getAllItems(){
        return findAll();
    }

    default void addItem(Address itemToAdd) {
        save(itemToAdd);
    }

}
