import { useEffect, useState } from "react";
import { ILivestock } from "../../interfaces/ILivestock";
import { ICoverList } from "../../interfaces/ICoverList";
import { fetchOchrony } from "../../api/Shared/fetchOchrony";
import { ILivestockList } from "../../interfaces/ILivestockList";
import { fetchZwierzeta } from "../../api/Calculation/fetchZwierzeta";
import { useNavigate, useParams } from "react-router-dom";
import { handleAddLivestock } from "../../api/Calculation/handleAddLivestock";
import { fetchZwierze } from "../../api/Calculation/fetchZwierze";

export const LivestockForm: React.FC = () => {
  const [error, setError] = useState<String | undefined>(undefined);
  const [coverList, setCoverList] = useState<ICoverList[]>([]);
  const [message, setMessage] = useState<string | undefined>(undefined);
  const [livestockList, setLivestockList] = useState<ILivestockList[]>([]);
  const { id } = useParams<{ id: string }>();
  const { livestockid } = useParams<{ livestockid: string }>();
  const navigate = useNavigate();
  const [livestock, setLivestock] = useState<ILivestock>({
    id: 0,
    idRodzajZwierzecia: 0,
    liczba: 0,
    naMieso: false,
    nazwaZwierzecia: "",
    sumaUbezpieczenia: 0,
    wartosc: 0,
    wartoscRynkowa: 0,
    ryzyka: [],
  });

  useEffect(() => {
    fetchOchrony("zwierzeta").then(setCoverList);
  }, []);

  useEffect(() => {
    fetchZwierzeta().then(setLivestockList);
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      if (id && livestockid) {
        const animal = await fetchZwierze(parseInt(id), parseInt(livestockid));
        if (animal) {
          setLivestock(animal);
        }
      }
    };
    fetchData();
  }, [livestockid]);

  const setField = (
    field: keyof ILivestock,
    value: string | boolean | string[] | number,
  ) => {
    setLivestock((livestockField) => ({
      ...livestockField,
      [field]: value,
    }));
  };

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    console.log(livestock);

    if (id) {
      const idCalculation = parseInt(id);
      try {
        if (livestockid) {
          const idLivestock = parseInt(livestockid);
          await handleAddLivestock(idCalculation, livestock, idLivestock);
          navigate(`/calculation/${id}/livestock`);
        } else {
          await handleAddLivestock(idCalculation, livestock);
          navigate(`/calculation/${id}/livestock`);
        }
      } catch (e) {
        setError("Nie udało się wysłać danych");
      }
    }
  };

  const handleGoBack = () => {
    navigate(`/calculation/${id}/livestock`);
  };

  return (
    <>
      <div className="calc-form-title">
        <h1>zwierzeta</h1>
      </div>
      <div>
        <form onSubmit={handleSubmit}>
          <div className="calc-form-section-title">Dodaj zwierzę</div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Rodzaj zwierzęcia</label>
            <select
              value={livestock.idRodzajZwierzecia}
              onChange={(e) => setField("idRodzajZwierzecia", e.target.value)}
            >
              <option value="">Wybierz zwierzę</option>
              {livestockList.map((l) => (
                <option key={l.id} value={l.id}>
                  {l.nazwa}
                </option>
              ))}
            </select>
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Liczba</label>
            <input
              type="number"
              className="calc-form-field-input"
              value={livestock.liczba}
              onChange={(e) => setField("liczba", e.target.value)}
            />
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Hodowla na mięso</label>
            <input
              type="checkbox"
              className="calc-form-field-input"
              checked={livestock.naMieso}
              onChange={() => setField("naMieso", !livestock.naMieso)}
            />
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Wartość</label>
            <input
              className="calc-form-field-input"
              type="number"
              value={livestock.wartosc}
              onChange={(e) => setField("wartosc", e.target.value)}
            />
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Ochrona</label>
            <select
              multiple
              value={livestock.ryzyka.map(String)}
              onChange={(e) => {
                setField(
                  "ryzyka",
                  Array.from(
                    e.target.selectedOptions,
                    (option) => option.value,
                  ),
                );
              }}
              size={coverList.length}
            >
              <option value="">Wybierz ochronę</option>
              {coverList.map((cover) => (
                <option key={cover.idOchrona} value={cover.idOchrona}>
                  {cover.nazwa}
                </option>
              ))}
            </select>
          </div>
          <div>{message && message}</div>
          <button
            className="calc-form-previous-button"
            type="button"
            onClick={handleGoBack}
          >
            Anuluj
          </button>
          <button className="calc-form-next-button" type="submit">
            Dodaj
          </button>
        </form>
      </div>
    </>
  );
};
