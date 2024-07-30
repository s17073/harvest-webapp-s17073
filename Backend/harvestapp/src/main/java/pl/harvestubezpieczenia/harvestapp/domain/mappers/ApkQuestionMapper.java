package pl.harvestubezpieczenia.harvestapp.domain.mappers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.ApkQuestionDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.ApkQuestion;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.Question;

@Component
@Qualifier("apkQuestionMapper")
public class ApkQuestionMapper implements GenericMapper<ApkQuestion, ApkQuestionDto> {
    @Override
    public ApkQuestion mapToEntity(ApkQuestionDto dto) {
        ApkQuestion entity = new ApkQuestion();

        entity.setPytanie(new Question(dto.getPytanie()));
        entity.setKomunikat(dto.getKomunikat());
        entity.setCzyAktywna(dto.isCzyAktywna());
        entity.setDataModyfikacji(new ModificationDate());

        return entity;
    }

    @Override
    public ApkQuestionDto mapToDto(ApkQuestion entity) {
        ApkQuestionDto dto = new ApkQuestionDto();

        dto.setPytanie(entity.getPytanie().pytanie());
        dto.setKomunikat(entity.getKomunikat());
        dto.setCzyAktywna(entity.isCzyAktywna());

        return dto;
    }
}
