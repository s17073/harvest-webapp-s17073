package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.ApkQuestionDto;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.ApkQuestion;
import pl.harvestubezpieczenia.harvestapp.domain.ports.ApkQuestionRepo;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApkQuestionService extends GenericService<ApkQuestion, ApkQuestionDto> {
    private final ApkQuestionRepo apkQuestionRepo;

    @Autowired
    public ApkQuestionService(GenericCrudRepo<ApkQuestion> repo, GenericMapper<ApkQuestion, ApkQuestionDto> map, ApkQuestionRepo apkQuestionRepo) {
        super(repo, map);
        this.apkQuestionRepo = apkQuestionRepo;
    }


    public ResponseEntity<List<ApkQuestionDto>> getApkQuestions() {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+": rozpoczynam serwis!");
        List<ApkQuestionDto> apkQuestionDtos = new ArrayList<>();
        List<ApkQuestion> apkQuestions = apkQuestionRepo.getApkQuestions();

        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+": rozpoczynam iterację po apk!");
        for(ApkQuestion apkQuestion : apkQuestions) {
            System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+": Znalazłem APK!");
            apkQuestionDtos.add(map.mapToDto(apkQuestion));
        }

        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+": zakończyłem iterację po apk!");

        if(apkQuestionDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+": sprawdziłem czy są pytania!");

        return new ResponseEntity<>(apkQuestionDtos, HttpStatus.OK);

    }
}
