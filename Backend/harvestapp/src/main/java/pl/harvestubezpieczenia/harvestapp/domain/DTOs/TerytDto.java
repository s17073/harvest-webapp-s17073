package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.model.Address;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.AddressLocalization;

import java.util.ArrayList;
import java.util.List;

@Data
public class TerytDto {

    private String kodTeryt;
    private String wojewodztwo;
    private String powiat;
    private String gmina;
    private String typ;

    private List<Address> adresy = new ArrayList<>();

    public void addAress(Address address) {
        adresy.add(address);
    }

    public List<AddressLocalization> getAdresy() {
        List<AddressLocalization> addressLocalizations = new ArrayList<>();
        for(Address a : adresy){
            addressLocalizations.add(a.getLokalizacja());
        }
        return addressLocalizations;
    }

}
