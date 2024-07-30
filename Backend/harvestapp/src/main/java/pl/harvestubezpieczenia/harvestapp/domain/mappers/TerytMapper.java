package pl.harvestubezpieczenia.harvestapp.domain.mappers;

import org.springframework.stereotype.Component;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.TerytDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.Address;
import pl.harvestubezpieczenia.harvestapp.domain.model.Teryt;

@Component
public class TerytMapper {

    public TerytDto mapToDto(Teryt entity){
        TerytDto dto = new TerytDto();

        dto.setKodTeryt(entity.getKodTeryt());
        dto.setWojewodztwo(entity.getWojewodztwo());
        dto.setPowiat(entity.getPowiat());
        dto.setGmina(entity.getGmina());
        dto.setTyp(entity.getTyp());

        if (!entity.getAddresses().isEmpty()) {
            for (Address a : entity.getAddresses()) {
                dto.addAress(a);
            }
        }

        return dto;
    }

}
