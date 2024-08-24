import { useEffect, useState } from "react";
import { fetchDictionaryData } from "../api/fetchDictionaryData";
import { handleDictionaryDelete } from "../api/handleDictionaryDelete";
import { Link } from "react-router-dom";
import { AdminPanelNav } from "../components/AdminPanelNav";

export interface IAgentData {
  id: number;
  nazwa: string;
  kodAgencji: string;
  nip: string;
  krs: string;
  nrTel: string;
  czyAktywna: boolean;
}

export const AgentData: React.FC = () => {
  const [data, setData] = useState<IAgentData[]>([]);
  const [loading, setLoading] = useState<string | null>(null);
  const [noData, setNoData] = useState<string | null>(null);
  const [fetchError, setFetchError] = useState<string | null>(null);
  const [announcement, setAnnouncement] = useState<string | null>(null);

  const fetchAgentData = () =>
    fetchDictionaryData("agent", setNoData, setData, setFetchError, setLoading);

  const deleteAgentData = (id: number) =>
    handleDictionaryDelete(
      id,
      "agent",
      setData,
      fetchAgentData,
      setAnnouncement,
    );

  useEffect(() => {
    fetchAgentData();
  }, []);

  return (
    <>
      <AdminPanelNav />
      <div className="background">
        <div className="admin-content-space">
          <div className="admin-title-container">
            <h1>AGENCI</h1>
          </div>
          {loading && <p>{loading}</p>}
          {fetchError && <p>{fetchError}</p>}
          <div className="admin-table-space">
            <table className="admin-table">
              <thead>
                <tr>
                  <th>Agent</th>
                  <th>Kod</th>
                  <th>Nip</th>
                  <th>Krs</th>
                  <th>Numer kontaktowy</th>
                  <th>Status</th>
                  <th>Edytuj</th>
                  <th>Usuń</th>
                </tr>
              </thead>
              <tbody>
                {data.map((data) => (
                  <tr key={data.id} className={`row-${data.id}`}>
                    <td>{data.nazwa}</td>
                    <td>{data.kodAgencji}</td>
                    <td>{data.nip}</td>
                    <td>{data.krs}</td>
                    <td>{data.nrTel}</td>
                    <td>{data.czyAktywna ? "AKTYWNA" : "NIEAKTYWNA"}</td>
                    <td>
                      <div>
                        <Link to={`/admin/agent/upsert/${data.id}`}>
                          Edytuj id: {data.id}
                        </Link>
                      </div>
                    </td>
                    <td>
                      <div onClick={() => deleteAgentData(data.id)}>
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
