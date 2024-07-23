package pl.harvestubezpieczenia.harvestapp.domain.Mappers;

import org.springframework.stereotype.Component;
import pl.harvestubezpieczenia.harvestapp.domain.model.GenericCrudModel;

@Component
public interface GenericMapper<E extends GenericCrudModel, D> {

    E mapToEntity(D dto);

}
