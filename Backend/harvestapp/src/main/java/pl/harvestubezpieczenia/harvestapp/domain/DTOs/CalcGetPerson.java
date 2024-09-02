package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import java.sql.Date;

public interface CalcGetPerson {

    String getImie();
    String getNazwisko();
    String getPesel();
    Date getDataUrodzenia();
    String getAdresEmail();
    String getTeryt();
    String getKodPocztowy();
    String getMiejscowosc();
    String getUlica();
    String getNumerDomu();
    String getNumerMieszkania();

}
