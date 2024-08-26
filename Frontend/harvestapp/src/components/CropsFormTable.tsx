import { useState } from "react";
import { IAgriculturalLand } from "../interfaces/IAgriculturalLand";

interface ICropFormTableProps {
  cropData: ICropData;
}

interface ICover {
  idRyzyko: number;
  ryzyko: string;
}

export interface ICropData {
  idUprawy: number;
  idGatunek: number;
  idKlasaGleby: number;
  uprawa: string;
  gatunek: string;
  klasaGleby: string;
  czyNasienna: boolean;
  powierzchnia: number;
  wartosc: number;
  wartoscRynkowa: number;
  wartoscMax: number;
  sumaUbezpieczenia: number;
  ryzyka: number[];
  dzialki: IAgriculturalLand[];
}

export const CropsFormTable: React.FC = () => {
  const [crops, setCrops] = useState<ICropData[]>([]);
  const [loading, setLoading] = useState<string | null>(null);
  const [info, setInfo] = useState<string | null>(null);
  const [error, setError] = useState<string | null>(null);

  return (
    <>
      <div className="calc-form-title">
        <h1>uprawy</h1>
      </div>
      <div>
        <div className="calc-form-table-space">
          <table className="calc-form-table">
            <thead>
              <tr>
                <th>Uprawa</th>
                <th>Nasienna</th>
                <th>Powierzchnia</th>
                <th>Wartość</th>
                <th>Suma ubezpieczenia</th>
                <th>Edytuj</th>
                <th>Usuń</th>
              </tr>
            </thead>
            <tbody>
              {crops.map((crop) => (
                <tr key={crop.idUprawy} className={`row-${crop.idUprawy}`}>
                  <td>{crop.uprawa}</td>
                  <td>{crop.czyNasienna}</td>
                  <td>{crop.powierzchnia}</td>
                  <td>{crop.wartosc}</td>
                  <td>{crop.sumaUbezpieczenia}</td>
                  <td>Edytuj</td>
                  <td>Usuń</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
};
