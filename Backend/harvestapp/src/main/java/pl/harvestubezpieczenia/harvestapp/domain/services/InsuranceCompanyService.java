package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.InsuranceCompanyDto;
import pl.harvestubezpieczenia.harvestapp.domain.Mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.InsuranceCompany;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

import java.util.Map;

@Service
public class InsuranceCompanyService extends GenericService<InsuranceCompany, InsuranceCompanyDto> {
    public InsuranceCompanyService(Map<String, GenericCrudRepo<InsuranceCompany>> genericCrudRepos, Map<String, GenericMapper<InsuranceCompany, InsuranceCompanyDto>> genericMappers) {
        super(genericCrudRepos, genericMappers);
    }
}
