export interface ILivestock {
  id: number;
  idRodzajZwierzecia: number;
  nazwaZwierzecia: string;
  liczba: number;
  wartosc: number;
  wartoscRynkowa: number;
  naMieso: boolean;
  sumaUbezpieczenia: number;
  ryzyka: number[];
}
