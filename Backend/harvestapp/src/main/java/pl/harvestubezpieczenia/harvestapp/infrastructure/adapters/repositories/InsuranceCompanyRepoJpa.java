package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.InsuranceCompany;
import pl.harvestubezpieczenia.harvestapp.domain.ports.InsuranceCompanyRepo;


@Repository
@Qualifier("insuranceCompanyRepoJpa")
public interface InsuranceCompanyRepoJpa extends GenericCrudRepoJpa<InsuranceCompany, Long>, InsuranceCompanyRepo {
}
