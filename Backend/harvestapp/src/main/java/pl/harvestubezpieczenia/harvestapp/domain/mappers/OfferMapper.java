package pl.harvestubezpieczenia.harvestapp.domain.mappers;

import org.springframework.stereotype.Component;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.OfferDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.Offer;

@Component
public class OfferMapper {

    public OfferDto mapToDto(Offer offer) {
        OfferDto dto = new OfferDto();

        dto.setIdOferta(offer.getIdOferta());
        dto.setUbezpieczyciel(offer.getUbezpieczyciel());
        dto.setKalkulacja(offer.getKalkulacja());
        dto.setNumerOferty(dto.getNumerOferty());
        dto.setSkladka(offer.getSkladka());
        dto.setDataWygasniecia(offer.getDataWygasniecia());
        dto.setStatusOferty(offer.getStatusOferty());

        return dto;

    }
}
