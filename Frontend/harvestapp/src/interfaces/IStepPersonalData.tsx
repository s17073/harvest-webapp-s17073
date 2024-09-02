interface IPersonalData {
  imie: string;
  nazwisko: string;
  pesel: string;
  dataUrodzenia: Date;
  adresEmail: string;
  teryt: string;
  kodPocztowy: string;
  miejscowosc: string;
  ulica: string;
  numerDomu: string;
  numerMieszkania: string | undefined;
}

export interface IStepPersonalData {
  ubezpieczajacy: IPersonalData;
  ubezpieczony: IPersonalData;
}
