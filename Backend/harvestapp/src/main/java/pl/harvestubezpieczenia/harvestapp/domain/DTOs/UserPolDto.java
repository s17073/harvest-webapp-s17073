package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

import java.sql.Date;

@Data
public class UserPolDto {

    private int idPolisa;
    private String numerPolisy;
    private Date poczatekUbezpieczenia;
    private Date koniecUbezpieczenia;
    private String imieUbezpieczonego;
    private String nazwiskoUbezpieczonego;
    private String status;

}

