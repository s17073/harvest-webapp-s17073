package pl.harvestubezpieczenia.harvestapp.domain.ports;

import pl.harvestubezpieczenia.harvestapp.domain.model.LivestockKind;

import java.util.List;

public interface LivestockKindRepo extends GenericCrudRepo<LivestockKind>{
    List<LivestockKind> getLivestockList();

    LivestockKind getLivestockById(int idRodzajZwierzecia);
}
