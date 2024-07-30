package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.InsuranceCompany;


@Repository
public interface InsuranceCompanyRepoJpa extends GenericCrudRepoJpa<InsuranceCompany> {
}
