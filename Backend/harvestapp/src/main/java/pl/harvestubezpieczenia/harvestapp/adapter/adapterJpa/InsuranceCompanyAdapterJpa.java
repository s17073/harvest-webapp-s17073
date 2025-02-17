package pl.harvestubezpieczenia.harvestapp.adapter.adapterJpa;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.adapter.repositoryJpa.GenericCrudRepoJpa;
import pl.harvestubezpieczenia.harvestapp.domain.model.InsuranceCompany;
import pl.harvestubezpieczenia.harvestapp.domain.ports.InsuranceCompanyRepo;

@Service
public class InsuranceCompanyAdapterJpa extends GenericCrudRepoAdapterJpa<InsuranceCompany> implements InsuranceCompanyRepo {
    public InsuranceCompanyAdapterJpa(@Qualifier("insuranceCompanyRepoJpa") GenericCrudRepoJpa<InsuranceCompany> genericCrudRepoJpa) {
        super(genericCrudRepoJpa);
    }
}
