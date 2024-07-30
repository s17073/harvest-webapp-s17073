package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.model.InsuranceCompany;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.GenericCrudRepoJpa;

@Service
public class InsuranceCompanyAdapterJpa extends GenericCrudRepoAdapterJpa<InsuranceCompany> {
    public InsuranceCompanyAdapterJpa(@Qualifier("insuranceCompanyRepoJpa") GenericCrudRepoJpa<InsuranceCompany> genericCrudRepoJpa) {
        super(genericCrudRepoJpa);
    }
}
