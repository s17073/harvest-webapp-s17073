package pl.harvestubezpieczenia.harvestapp.domain.ports;


import pl.harvestubezpieczenia.harvestapp.domain.model.Address;

import java.util.List;

public interface AddressRepo {

    List<Address> getAllItems();

    void addItem(Address itemToAdd);
}
