package pl.harvestubezpieczenia.harvestapp.domain.mappers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.AddressDto;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.AgentDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.Address;
import pl.harvestubezpieczenia.harvestapp.domain.model.Agent;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.*;

@Component
@Qualifier("agentMapper")
public class AgentMapper implements GenericMapper<Agent, AgentDto> {


    @Override
    public Agent mapToEntity(AgentDto dto) {
        Agent entity = new Agent();
        Address address = new Address();

        address.setTeryt(dto.getTerytData());
        address.setLokalizacja(new AddressLocalization(
                dto.getAddressData().getKodPocztowy(),
                dto.getAddressData().getMiejscowosc(),
                dto.getAddressData().getUlica(),
                dto.getAddressData().getNumerDomu(),
                dto.getAddressData().getNumerMieszkania()));
        entity.setNazwa(dto.getNazwa());
        entity.setKodAgencji(new AgentCode(dto.getKodAgencji()));
        entity.setNip(new Nip(dto.getNip()));
        entity.setKrs(new Krs(dto.getKrs()));
        entity.setLiczbaPosrednikow(dto.getLiczbaPosrednikow());
        entity.setCzyAktywna(dto.isCzyAktywna());
        entity.setNumerTelefonuAgencji(new TelNumber(dto.getNrTel()));
        entity.setDataModyfikacji(new ModificationDate());
        entity.setAddress(address);

        return entity;
    }

    @Override
    public AgentDto mapToDto(Agent entity) {
        AgentDto dto = new AgentDto();
        AddressDto address = new AddressDto();

        dto.setId(entity.getId());
        dto.setNazwa(entity.getNazwa());
        dto.setKodAgencji(entity.getKodAgencji().kodAgencji());
        dto.setNip(entity.getNip().nip());
        dto.setKrs(entity.getKrs().krs());
        dto.setNrTel(entity.getNumerTelefonuAgencji().telNumber());
        dto.setCzyAktywna(entity.isCzyAktywna());
        dto.setTeryt(entity.getAddress().getTeryt().getIdTeryt());
        dto.setLiczbaPosrednikow(entity.getLiczbaPosrednikow());

        address.setKodPocztowy(entity.getAddress().getLokalizacja().kodPocztowy());
        address.setMiejscowosc(entity.getAddress().getLokalizacja().miejscowosc());
        address.setUlica(entity.getAddress().getLokalizacja().ulica());
        address.setNumerDomu(entity.getAddress().getLokalizacja().numerDomu());
        address.setNumerMieszkania(entity.getAddress().getLokalizacja().numerMieszkania());

        dto.setAddressData(address);

        return dto;
    }
}
