package pl.harvestubezpieczenia.harvestapp.domain.mappers;

import org.springframework.stereotype.Component;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CalcPerson;
import pl.harvestubezpieczenia.harvestapp.domain.model.Address;
import pl.harvestubezpieczenia.harvestapp.domain.model.Teryt;
import pl.harvestubezpieczenia.harvestapp.domain.model.User;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.AddressLocalization;

@Component
public class CalcPersonMapper {

    public User mapToEntity(CalcPerson dto, Teryt policyHolderTeryt) {
        User entity = new User();
        Address policyHolderAddress = new Address();

        entity.setImie(dto.getImie());
        entity.setNazwisko(dto.getNazwisko());
        entity.setPesel(dto.getPesel());
        entity.setDataUrodzenia(dto.getDataUrodzenia().toLocalDate());
        entity.setEmail(dto.getAdresEmail());
        policyHolderAddress.setTeryt(policyHolderTeryt);
        policyHolderAddress.setLokalizacja(
                new AddressLocalization(
                        dto.getKodPocztowy(),
                        dto.getMiejscowosc(),
                        dto.getUlica(),
                        dto.getNumerDomu(),
                        !(dto.getNumerMieszkania() == null) ? dto.getNumerMieszkania() : null));
        entity.setAddress(policyHolderAddress);

        return entity;
    }

}
