import { useEffect, useState } from "react";
import { ILivestock } from "../../interfaces/ILivestock";
import { useNavigate, useParams } from "react-router-dom";
import { fetchLivestock } from "../../api/Calculation/fetchLivestock";
import { handleDeleteLivestock } from "../../api/Calculation/handleDeleteLivestock";

export const LivestockFormTable: React.FC = () => {
  const [livestock, setLivestock] = useState<ILivestock[]>([]);
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState<string | null>(null);
  const [info, setInfo] = useState<string | null>(null);

  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();

  const fetchData = async () => {
    if (id) {
      const livestockData = await fetchLivestock(parseInt(id));
      if (livestockData) {
        setLivestock(livestockData);
      }
    }
  };

  useEffect(() => {
    fetchData();
  }, [id]);

  const handleEditAnimal = (livestockId: number) => {
    navigate(`/calculation/${id}/livestock/${livestockId}`);
  };

  const handleRemoveAnimal = async (livestockId: number) => {
    if (id) {
      try {
        await handleDeleteLivestock(parseInt(id), livestockId);
        fetchData();
      } catch (e) {
        setError("Wystąpił błąd podczas usuwania");
      }
    }
  };

  const handleAddAnimal = () => {
    navigate(`/calculation/${id}/livestock/add`);
  };

  const handleGoBack = () => {
    navigate(`/calculation/${id}/crops`);
  };

  return (
    <>
      <div className="calc-form-title">
        <h1>zwierzeta</h1>
      </div>
      <div>
        <div className="calc-form-table-space">
          <table className="calc-form-table">
            <thead>
              <tr>
                <th>Zwierzę</th>
                <th>Liczba</th>
                <th>Wartość</th>
                <th>Hodowla na mięso</th>
                <th>Suma ubezpieczenia</th>
                <th>Edytuj</th>
                <th>Usuń</th>
              </tr>
            </thead>
            <tbody>
              {livestock.map((animal) => (
                <tr key={animal.id} className={`row-${animal.id}`}>
                  <td>{animal.nazwaZwierzecia}</td>
                  <td>{animal.liczba}</td>
                  <td>{animal.wartosc}</td>
                  <td>{animal.naMieso}</td>
                  <td>{animal.sumaUbezpieczenia}</td>
                  <td>
                    <button
                      type="button"
                      onClick={() => handleEditAnimal(animal.id)}
                    >
                      Edytuj
                    </button>
                  </td>
                  <td>
                    <button
                      type="button"
                      onClick={() => handleRemoveAnimal(animal.id)}
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
            onClick={handleAddAnimal}
          >
            Dodaj Zwierzę
          </button>
          <button
            className="calc-form-previous-button"
            type="button"
            onClick={handleGoBack}
          >
            Wstecz
          </button>
          <button className="calc-form-next-button" type="submit">
            Dalej
          </button>
          <div>{error && error}</div>
        </div>
      </div>
    </>
  );
};
