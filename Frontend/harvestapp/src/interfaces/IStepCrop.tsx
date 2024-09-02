export interface ILand {
  teryt: string;
  numerDzialki: string;
  obreb: string;
  kodObrebu: string;
  czyPoprawna: boolean;
}

export interface IStepCrop {
  idKlasaGleby: number;
  idRodzajUprawy: number;
  idGatunek: number | undefined;
  czyNasienna: boolean;
  powierzchnia: number;
  wartosc: number;
  dzialki: ILand[];
  ryzyka: number[];
}
