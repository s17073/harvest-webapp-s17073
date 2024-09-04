import { useEffect, useState } from "react";
import { IAgriculturalLand } from "../../interfaces/IAgriculturalLand";
import { useNavigate, useParams } from "react-router-dom";
import { fetchCrops } from "../../api/Calculation/fetchCrops";
import { handleDeleteCrop } from "../../api/Calculation/handleDeleteCrop";

export interface ICropData {
  id: number;
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
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();

  //TODO
  console.log(loading, setLoading(null), info, setInfo(null));

  const fetchData = async () => {
    if (id) {
      const cropsData = await fetchCrops(parseInt(id));
      if (cropsData) {
        setCrops(cropsData);
      }
    }
  };

  useEffect(() => {
    fetchData();
  }, [id]);

  const handleGoBack = () => {
    navigate(`/calculation/${id}/personaldata`);
  };

  const handleAddCrop = () => {
    navigate(`/calculation/${id}/crop`);
  };

  const handleEditCrop = (cropId: number) => {
    navigate(`/calculation/${id}/crop/${cropId}`);
  };

  const handleRemoveCrop = async (cropId: number) => {
    if (id) {
      try {
        await handleDeleteCrop(parseInt(id), cropId);
        fetchData();
      } catch (e) {
        setError("Wystąpił błąd podczas usuwania uprawy");
      }
    }
  };

  const handleSubmit = () => {
    navigate(`/calculation/${id}/livestock`);
  };

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
                <tr key={crop.id} className={`row-${crop.id}`}>
                  <td>{crop.uprawa}</td>
                  <td>{crop.czyNasienna}</td>
                  <td>{crop.powierzchnia}</td>
                  <td>{crop.wartosc}</td>
                  <td>{crop.sumaUbezpieczenia}</td>
                  <td>
                    <button
                      type="button"
                      onClick={() => handleEditCrop(crop.id)}
                    >
                      Edytuj
                    </button>
                  </td>
                  <td>
                    <button
                      type="button"
                      onClick={() => handleRemoveCrop(crop.id)}
                    >
                      Usuń
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <button
            className="calc-form-add-crop"
            type="button"
            onClick={handleAddCrop}
          >
            Dodaj Uprawę
          </button>
          <button
            className="calc-form-previous-button"
            type="button"
            onClick={handleGoBack}
          >
            Wstecz
          </button>
          <button
            className="calc-form-next-button"
            type="submit"
            onClick={handleSubmit}
          >
            Dalej
          </button>
          <div>{error && error}</div>
        </div>
      </div>
    </>
  );
};
