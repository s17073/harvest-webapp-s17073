package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.model.Address;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.AddressLocalization;

import java.util.ArrayList;
import java.util.List;

@Data
public class TerytDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String kodTeryt;
    private String wojewodztwo;
    private String powiat;
    private String gmina;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String typ;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Address> adresy = new ArrayList<>();

    public void addAress(Address address) {
        adresy.add(address);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<AddressLocalization> getAdresy() {
        List<AddressLocalization> addressLocalizations = new ArrayList<>();
        for(Address a : adresy){
            addressLocalizations.add(a.getLokalizacja());
        }
        return addressLocalizations;
    }

}
