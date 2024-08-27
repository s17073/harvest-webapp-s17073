import { useEffect, useState } from "react";
import { fetchDictionaryData } from "../../api/Dictionaries/fetchDictionaryData";
import { handleDictionaryDelete } from "../../api/Dictionaries/handleDictionaryDelete";
import { Link } from "react-router-dom";
import { AdminPanelNav } from "../../components/Dictionaries/AdminPanelNav";

export interface ICoverData {
  id: number;
  nazwa: string;
  grupaMinisterialna: string;
  taryfa: string;
  opis: string;
  czyUprawa: boolean;
  czyZwierze: boolean;
  czyAktywna: boolean;
}

export const CoverData: React.FC = () => {
  const [data, setData] = useState<ICoverData[]>([]);
  const [loading, setLoading] = useState<string | null>(null);
  const [noData, setNoData] = useState<string | null>(null);
  const [fetchError, setFetchError] = useState<string | null>(null);
  const [announcement, setAnnouncement] = useState<string | null>(null);

  const fetchCoverData = () =>
    fetchDictionaryData("cover", setNoData, setData, setFetchError, setLoading);

  const deleteCoverData = (id: number) =>
    handleDictionaryDelete(
      id,
      "cover",
      setData,
      fetchCoverData,
      setAnnouncement,
    );

  useEffect(() => {
    fetchCoverData();
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
                  <th>Ochrona</th>
                  <th>Grupa ministerialna</th>
                  <th>Taryfa</th>
                  <th>CzyUprawa</th>
                  <th>CzyZwierze</th>
                  <th>Status</th>
                  <th>Edytuj</th>
                  <th>Usuń</th>
                </tr>
              </thead>
              <tbody>
                {data.map((data) => (
                  <tr key={data.id} className={`row-${data.id}`}>
                    <td>{data.nazwa}</td>
                    <td>{data.grupaMinisterialna}</td>
                    <td>{data.taryfa}</td>
                    <td>{data.czyUprawa ? "TAK" : "NIE"}</td>
                    <td>{data.czyZwierze ? "TAK" : "NIE"}</td>
                    <td>{data.czyAktywna ? "AKTYWNA" : "NIEAKTYWNA"}</td>
                    <td>
                      <div>
                        <Link to={`/admin/cover/upsert/${data.id}`}>
                          Edytuj id: {data.id}
                        </Link>
                      </div>
                    </td>
                    <td>
                      <div onClick={() => deleteCoverData(data.id)}>
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
