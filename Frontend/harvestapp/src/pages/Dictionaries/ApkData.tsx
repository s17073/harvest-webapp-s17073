import { useEffect, useState } from "react";
import { fetchDictionaryData } from "../../api/Dictionaries/fetchDictionaryData";
import { handleDictionaryDelete } from "../../api/Dictionaries/handleDictionaryDelete";
import { Link, useNavigate } from "react-router-dom";
import { AdminPanelNav } from "../../components/Dictionaries/AdminPanelNav";

export interface IApkData {
  id: number;
  pytanie: string;
  komunikat: string;
  czyAktywna: boolean;
}

export const ApkData: React.FC = () => {
  const [data, setData] = useState<IApkData[]>([]);
  const [loading, setLoading] = useState<string | null>(null);
  const [noData, setNoData] = useState<string | null>(null);
  const [fetchError, setFetchError] = useState<string | null>(null);
  const [announcement, setAnnouncement] = useState<string | null>(null);
  const navigate = useNavigate();

  const fetchApkData = () =>
    fetchDictionaryData("apk", setNoData, setData, setFetchError, setLoading);

  const deleteApkData = (id: number) =>
    handleDictionaryDelete(id, "apk", setData, fetchApkData, setAnnouncement);

  useEffect(() => {
    fetchApkData();
  }, []);

  return (
    <>
      <AdminPanelNav />
      <div className="background">
        <div className="admin-content-space">
          <div className="admin-title-container">
            <h1>PYTANIA</h1>
          </div>
          {loading && <p>{loading}</p>}
          {fetchError && <p>{fetchError}</p>}
          <div className="admin-table-space">
            <table className="admin-table">
              <thead>
                <tr>
                  <th>Pytanie</th>
                  <th>Komunikat</th>
                  <th>Status</th>
                  <th>Edytuj</th>
                  <th>Usuń</th>
                </tr>
              </thead>
              <tbody>
                {data.map((data) => (
                  <tr key={data.id} className={`row-${data.id}`}>
                    <td>{data.pytanie}</td>
                    <td>{data.komunikat}</td>
                    <td>{data.czyAktywna ? "AKTYWNA" : "NIEAKTYWNA"}</td>
                    <td>
                      <div>
                        <Link to={`/admin/apk/upsert/${data.id}`}>
                          Edytuj id: {data.id}
                        </Link>
                      </div>
                    </td>
                    <td>
                      <div onClick={() => deleteApkData(data.id)}>
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
