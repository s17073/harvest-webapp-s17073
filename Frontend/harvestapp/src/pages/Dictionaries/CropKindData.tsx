import { useEffect, useState } from "react";
import { fetchDictionaryData } from "../../api/Dictionaries/fetchDictionaryData";
import { handleDictionaryDelete } from "../../api/Dictionaries/handleDictionaryDelete";
import { Link } from "react-router-dom";
import { AdminPanelNav } from "../../components/Dictionaries/AdminPanelNav";

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
      "cropkind",
      setNoData,
      setData,
      setFetchError,
      setLoading,
    );

  const deleteCropKindData = (id: number) =>
    handleDictionaryDelete(
      id,
      "cropkind",
      setData,
      fetchCropKindData,
      setAnnouncement,
    );

  useEffect(() => {
    fetchCropKindData();
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
                {data.map((data) => (
                  <tr key={data.id} className={`row-${data.id}`}>
                    <td>{data.nazwaUprawy}</td>
                    <td>{data.taryfa}</td>
                    <td>{data.wartoscRynkowa} zł</td>
                    <td>{data.wartoscMax} zł</td>
                    <td>{data.czyAktywna ? "AKTYWNA" : "NIEAKTYWNA"}</td>
                    <td>
                      <div>
                        <Link to={`/admin/cropkind/upsert/${data.id}`}>
                          Edytuj id: {data.id}
                        </Link>
                      </div>
                    </td>
                    <td>
                      <div onClick={() => deleteCropKindData(data.id)}>
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
