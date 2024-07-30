package pl.harvestubezpieczenia.harvestapp.domain.mappers;

import org.springframework.stereotype.Component;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.AddressDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.Address;

@Component
public class AddressMapper {

    public AddressDto mapToDto(Address entity){
        AddressDto dto = new AddressDto();

        dto.setKodPocztowy(entity.getLokalizacja().kodPocztowy());
        dto.setMiejscowosc(entity.getLokalizacja().miejscowosc());
        dto.setUlica(entity.getLokalizacja().ulica());
        dto.setNumerDomu(entity.getLokalizacja().numerDomu());
        dto.setNumerMieszkania(entity.getLokalizacja().numerMieszkania());
        dto.setTeryt(entity.getTeryt());

        return dto;
    }

}
