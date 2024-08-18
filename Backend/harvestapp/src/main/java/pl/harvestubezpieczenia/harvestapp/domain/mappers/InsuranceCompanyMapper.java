package pl.harvestubezpieczenia.harvestapp.domain.mappers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.InsuranceCompanyDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.InsuranceCompany;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.*;

@Component
@Qualifier("insuranceCompanyMapper")
public class InsuranceCompanyMapper implements GenericMapper<InsuranceCompany, InsuranceCompanyDto> {
    @Override
    public InsuranceCompany mapToEntity(InsuranceCompanyDto dto) {
        InsuranceCompany entity = new InsuranceCompany();

        entity.setNazwa(new InsuranceCompanyName(dto.getNazwa()));
        entity.setNumerZakladu(new InsuranceCompanyNumber(dto.getNumerZakladu()));
        entity.setNumerTelefonu(new PhoneNumber(dto.getNumerTelefonu()));
        entity.setNumerKonta(new BankAccountNumber(dto.getNumerKonta()));
        entity.setCzyAktywna(dto.isCzyAktywna());
        entity.setDataModyfikacji(new ModificationDate());
        entity.setAddress(dto.getAddressObj());

        return entity;
    }

    @Override
    public InsuranceCompanyDto mapToDto(InsuranceCompany entity) {
        InsuranceCompanyDto dto = new InsuranceCompanyDto();

        dto.setId(entity.getId());
        dto.setNazwa(entity.getNazwa().nazwa());
        dto.setNumerZakladu(entity.getNumerZakladu().numerZakladu());
        dto.setNumerTelefonu(entity.getNumerTelefonu().numerTelefonu());
        dto.setNumerKonta(entity.getNumerKonta().numerKonta());
        dto.setNip(entity.getNip().nip());
        dto.setKrs(entity.getKrs().krs());
        dto.setCzyAktywna(entity.isCzyAktywna());
        dto.setIdAddress(entity.getAddress().getIdAdres());
        dto.setAddres(
                entity.getAddress().getLokalizacja().kodPocztowy() + " " +
                entity.getAddress().getLokalizacja().miejscowosc() + " " +
                entity.getAddress().getLokalizacja().ulica() + " " +
                entity.getAddress().getLokalizacja().numerDomu() + " " +
                entity.getAddress().getLokalizacja().numerMieszkania()
        );

        return dto;
    }
}
