import { useEffect, useState } from "react";
import { fetchDictionaryData } from "../../api/Dictionaries/fetchDictionaryData";
import { handleDictionaryDelete } from "../../api/Dictionaries/handleDictionaryDelete";
import { Link, useNavigate } from "react-router-dom";
import { AdminPanelNav } from "../../components/Dictionaries/AdminPanelNav";

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
  const navigate = useNavigate();

  const fetchLivestockKindData = () =>
    fetchDictionaryData(
      "livestockkind",
      setNoData,
      setData,
      setFetchError,
      setLoading,
    );

  const deleteLivestockKindData = (id: number) =>
    handleDictionaryDelete(
      id,
      "livestockkind",
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
                {data.map((data) => (
                  <tr key={data.id} className={`row-${data.id}`}>
                    <td>{data.nazwaZwierzecia}</td>
                    <td>{data.taryfa}</td>
                    <td>{data.wartoscRynkowa} zł</td>
                    <td>{data.wartoscMax} zł</td>
                    <td>{data.czyAktywna ? "AKTYWNA" : "NIEAKTYWNA"}</td>
                    <td>
                      <div>
                        <Link to={`/admin/LivestockKind/upsert/${data.id}`}>
                          Edytuj id: {data.id}
                        </Link>
                      </div>
                    </td>
                    <td>
                      <div onClick={() => deleteLivestockKindData(data.id)}>
                        Usuń id: {data.id}
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
            {noData && <p>{noData}</p>}
            {announcement && <p>{announcement}</p>}
          </div>
          <button
            className="admin-table-cancel"
            onClick={() => navigate("/admin")}
          >
            POWRÓT
          </button>
        </div>
      </div>
    </>
  );
};
