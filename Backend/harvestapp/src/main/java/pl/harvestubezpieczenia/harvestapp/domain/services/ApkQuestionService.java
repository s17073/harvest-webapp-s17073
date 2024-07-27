package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.ApkQuestionDto;
import pl.harvestubezpieczenia.harvestapp.domain.Mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.ApkQuestion;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

import java.util.Map;

@Service
public class ApkQuestionService extends GenericService<ApkQuestion, ApkQuestionDto> {

    public ApkQuestionService(Map<String, GenericCrudRepo<ApkQuestion>> genericCrudRepos, Map<String, GenericMapper<ApkQuestion, ApkQuestionDto>> genericMappers) {
        super(genericCrudRepos, genericMappers);
    }
}
