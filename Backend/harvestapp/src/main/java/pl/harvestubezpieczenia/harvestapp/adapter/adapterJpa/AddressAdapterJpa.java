package pl.harvestubezpieczenia.harvestapp.adapter.adapterJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.adapter.repositoryJpa.AddressRepoJpa;
import pl.harvestubezpieczenia.harvestapp.domain.model.Address;
import pl.harvestubezpieczenia.harvestapp.domain.ports.AddressRepo;

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
