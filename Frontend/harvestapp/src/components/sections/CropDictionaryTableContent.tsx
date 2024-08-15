import { useEffect, useState } from "react";
import { fetchDictionaryData } from "../api/fetchDictionaryData";
import { handleDictionaryDelete } from "../api/handleDictionaryDelete";
import { ICropKindDataDict } from "../interfaces/ICropKindDataDict";

export const CropDictionaryTableContent: React.FC = () => {
  const [data, setData] = useState<ICropKindDataDict[]>([]);
  const [loading, setLoading] = useState<string | null>(null);
  const [noData, setNoData] = useState<string | null>(null);
  const [fetchError, setFetchError] = useState<string | null>(null);
  const [announcement, setAnnouncement] = useState<string | null>(null);

  const fetchCropKindData = () =>
    fetchDictionaryData(
      "/api/cropkind",
      setNoData,
      setData,
      setFetchError,
      setLoading,
    );

  const deleteCropKindData = (id: number) =>
    handleDictionaryDelete(id, setData, fetchCropKindData, setAnnouncement);

  useEffect(() => {
    fetchCropKindData();
  }, []);

  if (loading) return <p>{loading}</p>;
  if (fetchError) return <p>{fetchError}</p>;

  return (
    <div>
      <table>
        <thead>
          <tr>
            <th>Nazwa uprawy</th>
            <th>Taryfa</th>
            <th>Wartość rynkowa</th>
            <th>Wartość maksymalna</th>
            <th>Status</th>
            <th>Edytuj</th>
            <th>Usuń</th>
          </tr>
        </thead>
        <tbody>
          {data.map((crop) => (
            <tr key={crop.id} className={`row-${crop.id}`}>
              <td>{crop.nazwaUprawy}</td>
              <td>{crop.taryfa}</td>
              <td>{crop.wartoscRynkowa}</td>
              <td>{crop.wartoscMax}</td>
              <td>{crop.czyAktywna ? "AKTYWNA" : "NIEAKTYWNA"}</td>
              <td>
                <div>Edytuj id: {crop.id}</div>
              </td>
              <td>
                <div onClick={() => deleteCropKindData(crop.id)}>
                  Usuń id: {crop.id}
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      {noData && <p>{noData}</p>}
      {announcement && <p>{announcement}</p>}
    </div>
  );
};
