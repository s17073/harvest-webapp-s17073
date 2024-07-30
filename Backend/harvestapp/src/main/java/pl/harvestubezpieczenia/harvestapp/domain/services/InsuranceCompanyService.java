package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.InsuranceCompanyDto;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.InsuranceCompany;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

@Service
public class InsuranceCompanyService extends GenericService<InsuranceCompany, InsuranceCompanyDto> {

    @Autowired
    public InsuranceCompanyService(GenericCrudRepo<InsuranceCompany> repo, GenericMapper<InsuranceCompany, InsuranceCompanyDto> map) {
        super(repo, map);
    }
}
