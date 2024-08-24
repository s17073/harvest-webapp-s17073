import { useEffect, useState } from "react";
import { fetchDictionaryData } from "../api/fetchDictionaryData";
import { handleDictionaryDelete } from "../api/handleDictionaryDelete";
import { Link } from "react-router-dom";
import { AdminPanelNav } from "../components/AdminPanelNav";

export interface ISoilClassData {
  id: number;
  klasaGleby: string;
  opis: string;
  taryfa: string;
  czyAktywna: boolean;
}

export const SoilClassData: React.FC = () => {
  const [data, setData] = useState<ISoilClassData[]>([]);
  const [loading, setLoading] = useState<string | null>(null);
  const [noData, setNoData] = useState<string | null>(null);
  const [fetchError, setFetchError] = useState<string | null>(null);
  const [announcement, setAnnouncement] = useState<string | null>(null);

  const fetchSoilClassData = () =>
    fetchDictionaryData(
      "soilclass",
      setNoData,
      setData,
      setFetchError,
      setLoading,
    );

  const deleteSoilClassData = (id: number) =>
    handleDictionaryDelete(
      id,
      "soilclass",
      setData,
      fetchSoilClassData,
      setAnnouncement,
    );

  useEffect(() => {
    fetchSoilClassData();
  }, []);

  return (
    <>
      <AdminPanelNav />
      <div className="background">
        <div className="admin-content-space">
          <div className="admin-title-container">
            <h1>Klasy Gleby</h1>
          </div>
          {loading && <p>{loading}</p>}
          {fetchError && <p>{fetchError}</p>}
          <div className="admin-table-space">
            <table className="admin-table">
              <thead>
                <tr>
                  <th>Klasa gleby</th>
                  <th>Opis</th>
                  <th>Taryfa</th>
                  <th>Status</th>
                  <th>Edytuj</th>
                  <th>Usuń</th>
                </tr>
              </thead>
              <tbody>
                {data.map((data) => (
                  <tr key={data.id} className={`row-${data.id}`}>
                    <td>{data.klasaGleby}</td>
                    <td>{data.opis}</td>
                    <td>{data.taryfa}</td>
                    <td>{data.czyAktywna ? "AKTYWNA" : "NIEAKTYWNA"}</td>
                    <td>
                      <div>
                        <Link to={`/admin/soilclass/upsert/${data.id}`}>
                          Edytuj id: {data.id}
                        </Link>
                      </div>
                    </td>
                    <td>
                      <div onClick={() => deleteSoilClassData(data.id)}>
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
        </div>
      </div>
    </>
  );
};
