package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.model.Address;
import pl.harvestubezpieczenia.harvestapp.domain.ports.AddressRepo;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.AddressRepoJpa;

import java.util.List;

@Service
public class AddressAdapterJpa implements AddressRepo {

    private final AddressRepoJpa addressRepoJpa;

    @Autowired
    public AddressAdapterJpa(AddressRepoJpa addressRepoJpa) {
        this.addressRepoJpa = addressRepoJpa;
    }

    @Override
    public List<Address> getAllItems() {
        return addressRepoJpa.getAllItems();
    }

    @Override
    public void addItem(Address itemToAdd) {
        addressRepoJpa.addItem(itemToAdd);
    }
}
