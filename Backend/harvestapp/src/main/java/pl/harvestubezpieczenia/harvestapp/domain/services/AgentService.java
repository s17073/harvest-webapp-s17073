package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.AgentDto;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.AgentMapper;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.Agent;
import pl.harvestubezpieczenia.harvestapp.domain.model.Teryt;
import pl.harvestubezpieczenia.harvestapp.domain.ports.AddressRepo;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;
import pl.harvestubezpieczenia.harvestapp.domain.ports.TerytRepo;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;

@Service
public class AgentService extends GenericService<Agent, AgentDto> {

    private final GenericCrudRepo<Agent> agentRepo;
    private final AgentMapper agentMapper;
    private final TerytRepo terytRepo;
    private final AddressRepo addressRepo;

    @Autowired
    public AgentService(GenericCrudRepo<Agent> repo, GenericMapper<Agent, AgentDto> map, GenericCrudRepo<Agent> agentRepo, AgentMapper agentMapper, TerytRepo terytRepo, AddressRepo addressRepo) {
        super(repo, map);
        this.agentRepo = agentRepo;
        this.agentMapper = agentMapper;
        this.terytRepo = terytRepo;
        this.addressRepo = addressRepo;
    }

    @Override
    @Transactional
    public ResponseEntity<String> addItem(AgentDto dto){
        Teryt teryt = new Teryt();
//        for(Teryt t: terytRepo.getAllItems()) {
//            if (t.getKodTeryt().equals(dto.getTeryt())) {
//                teryt = t;
//                break;
//            }
//        }
        teryt = terytRepo.getTeryt(dto.getTeryt());


        dto.setTerytData(teryt);

        Agent agentToAdd = agentMapper.mapToEntity(dto);
        addressRepo.addItem(agentToAdd.getAddress());

        agentRepo.addItem(agentToAdd);
        return new ResponseEntity<>(agentToAdd.getName() + " successfully added to the database.", HttpStatus.CREATED);
    }

    @Transactional
    @Override
    public ResponseEntity<String> updateItem(AgentDto dto, int id){
        String nameOfRemovedAgent;
        Agent agentToUpdate = findForModify(id);

        if(agentToUpdate == null){
            return new ResponseEntity<>("ID: " + id + " not found.", HttpStatus.NOT_FOUND);
        } else if (agentToUpdate.getDataModyfikacji().dataUsuniecia() != null){
            return new ResponseEntity<>("ID: " + id + " has already been deleted.", HttpStatus.FORBIDDEN);
        }
        nameOfRemovedAgent = agentToUpdate.getName();
        agentToUpdate.setDataModyfikacji(new ModificationDate(agentToUpdate.getDataModyfikacji().dataDodania()));
        addItem(dto);
        agentRepo.addItem(agentToUpdate);

        return new ResponseEntity<>(nameOfRemovedAgent + " successfully updated.", HttpStatus.OK);
    }

}
