package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.model.Address;

import java.util.Set;

@Data
public class TerytDto {

    private String kodTeryt;
    private String wojewodztwo;
    private String powiat;
    private String gmina;
    private String typ;

    private Set<Address> adresy;

}
