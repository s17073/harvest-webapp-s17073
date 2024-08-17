import { useEffect, useState } from "react";
import { fetchDictionaryData } from "../api/fetchDictionaryData";
import { handleDictionaryDelete } from "../api/handleDictionaryDelete";
import { Link } from "react-router-dom";
import { AdminPanelNav } from "../components/AdminPanelNav";

export interface ILivestockKindData {
  id: number;
  nazwaZwierzecia: string;
  taryfa: string;
  czyAktywna: boolean;
  wartoscRynkowa: number;
  wartoscMax: number;
}

export const LivestockKindData: React.FC = () => {
  const [data, setData] = useState<ILivestockKindData[]>([]);
  const [loading, setLoading] = useState<string | null>(null);
  const [noData, setNoData] = useState<string | null>(null);
  const [fetchError, setFetchError] = useState<string | null>(null);
  const [announcement, setAnnouncement] = useState<string | null>(null);

  const fetchLivestockKindData = () =>
    fetchDictionaryData(
      "/api/livestockkind",
      setNoData,
      setData,
      setFetchError,
      setLoading,
    );

  const deleteLivestockKindData = (id: number) =>
    handleDictionaryDelete(
      id,
      setData,
      fetchLivestockKindData,
      setAnnouncement,
    );

  useEffect(() => {
    fetchLivestockKindData();
  }, []);

  return (
    <>
      <AdminPanelNav />
      <div className="background">
        <div className="admin-content-space">
          <div className="admin-title-container">
            <h1>UPRAWY</h1>
          </div>
          {loading && <p>{loading}</p>}
          {fetchError && <p>{fetchError}</p>}
          <div className="admin-table-space">
            <table className="admin-table">
              <thead>
                <tr>
                  <th>Nazwa zwierzecia</th>
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
                    <td>{crop.nazwaZwierzecia}</td>
                    <td>{crop.taryfa}</td>
                    <td>{crop.wartoscRynkowa} zł</td>
                    <td>{crop.wartoscMax} zł</td>
                    <td>{crop.czyAktywna ? "AKTYWNA" : "NIEAKTYWNA"}</td>
                    <td>
                      <div>
                        <Link to={`/admin/LivestockKind/upsert/${crop.id}`}>
                          Edytuj id: {crop.id}
                        </Link>
                      </div>
                    </td>
                    <td>
                      <div onClick={() => deleteLivestockKindData(crop.id)}>
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
        </div>
      </div>
    </>
  );
};
