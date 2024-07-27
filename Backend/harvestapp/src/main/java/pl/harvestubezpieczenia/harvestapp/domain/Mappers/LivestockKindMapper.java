package pl.harvestubezpieczenia.harvestapp.domain.Mappers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.LivestockKindDTO;
import pl.harvestubezpieczenia.harvestapp.domain.model.LivestockKind;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.InsuredValue;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.LivestockKindName;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.Season;

@Component
@Qualifier("livestockKindMapper")
public class LivestockKindMapper implements GenericMapper<LivestockKind, LivestockKindDTO> {

    @Override
    public LivestockKind mapToEntity(LivestockKindDTO dto) {
        LivestockKind entity = new LivestockKind();

        entity.setNazwa(new LivestockKindName(dto.getNazwaZwierzecia()));
        entity.setTaryfa(new Season(dto.getTaryfa()));
        entity.setCzyAktywna(dto.isCzyAktywna());
        entity.setWartoscUbezpieczenia(new InsuredValue(dto.getWartoscRynkowa()));
        entity.setDataModyfikacji(new ModificationDate());

        return entity;
    }

    @Override
    public LivestockKindDTO mapToDto(LivestockKind entity) {
        LivestockKindDTO dto = new LivestockKindDTO();

        dto.setNazwaZwierzecia(entity.getName());
        dto.setTaryfa(entity.getTaryfa().taryfa());
        dto.setCzyAktywna(entity.isCzyAktywna());
        dto.setWartoscRynkowa(entity.getWartoscUbezpieczenia().wartoscRynkowa());
        dto.setWartoscMax(entity.getWartoscUbezpieczenia().wartoscMax());

        return dto;
    }
}
