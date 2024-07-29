package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.Address;
import pl.harvestubezpieczenia.harvestapp.domain.ports.AddressRepo;

import java.util.List;

@Repository
public interface AddressRepoJpa extends JpaRepository<Address, Long>, AddressRepo {

    default List<Address> getAllItems(){
        return findAll();
    }

}
