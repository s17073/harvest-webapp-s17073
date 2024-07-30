package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.ApkQuestionDto;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.ApkQuestion;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

@Service
public class ApkQuestionService extends GenericService<ApkQuestion, ApkQuestionDto> {

    @Autowired
    public ApkQuestionService(GenericCrudRepo<ApkQuestion> repo, GenericMapper<ApkQuestion, ApkQuestionDto> map) {
        super(repo, map);
    }
}
