package pl.harvestubezpieczenia.harvestapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.*;

@Entity
@Data
@Table(name = "agent")
public class Agent implements GenericCrudModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAgent;

    private String nazwa;
    @Embedded
    private AgentCode kodAgencji;
    @Embedded
    private Nip nip;
    @Embedded
    private Krs krs;

    @ManyToOne
    @JoinColumn(name = "id_adres")
    private Address address;

    private short liczbaPosrednikow;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "telNumber", column = @Column(name = "numer_telefonu_agencji"))
    })
    private TelNumber numerTelefonuAgencji;
    private boolean czyAktywna;

    @Embedded
    private ModificationDate dataModyfikacji;

    @Override
    public int getId() {
        return idAgent;
    }

    public String getName(){
        return nazwa;
    }

}
