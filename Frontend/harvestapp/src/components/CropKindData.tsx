import { useEffect, useState } from "react";
import { fetchDictionaryData } from "../api/fetchDictionaryData";
import { handleDictionaryDelete } from "../api/handleDictionaryDelete";
import { Link } from "react-router-dom";

export interface ICropKindData {
  id: number;
  nazwaUprawy: string;
  taryfa: string;
  czyAktywna: boolean;
  wartoscRynkowa: number;
  wartoscMax: number;
}

export const CropKindData: React.FC = () => {
  const [data, setData] = useState<ICropKindData[]>([]);
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
    <div className="admin-table-space">
      <table className="admin-table">
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
              <td>{crop.wartoscRynkowa} zł</td>
              <td>{crop.wartoscMax} zł</td>
              <td>{crop.czyAktywna ? "AKTYWNA" : "NIEAKTYWNA"}</td>
              <td>
                <div>
                  <Link to={`/admin/cropkind/upsert/${crop.id}`}>
                    Edytuj id: {crop.id}
                  </Link>
                </div>
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
