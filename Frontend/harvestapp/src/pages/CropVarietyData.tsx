import { useEffect, useState } from "react";
import { fetchDictionaryData } from "../api/fetchDictionaryData";
import { handleDictionaryDelete } from "../api/handleDictionaryDelete";
import { Link } from "react-router-dom";
import { AdminPanelNav } from "../components/AdminPanelNav";

export interface ICropVarietyData {
  id: number;
  idUprawa: number;
  idGatunek: number;
  nazwaGatunku: string;
  nazwaUprawy: string;
  taryfa: string;
  czyAktywna: boolean;
}

export const CropVarietyData: React.FC = () => {
  const [data, setData] = useState<ICropVarietyData[]>([]);
  const [loading, setLoading] = useState<string | null>(null);
  const [noData, setNoData] = useState<string | null>(null);
  const [fetchError, setFetchError] = useState<string | null>(null);
  const [announcement, setAnnouncement] = useState<string | null>(null);

  const fetchCropVarietyData = () =>
    fetchDictionaryData(
      "cropkindvariety",
      setNoData,
      setData,
      setFetchError,
      setLoading,
    );

  const deleteCropVarietyData = (id: number) =>
    handleDictionaryDelete(
      id,
      "cropvariety",
      setData,
      fetchCropVarietyData,
      setAnnouncement,
    );

  useEffect(() => {
    fetchCropVarietyData();
  }, []);

  return (
    <>
      <AdminPanelNav />
      <div className="background">
        <div className="admin-content-space">
          <div className="admin-title-container">
            <h1>GATUNKI</h1>
          </div>
          {loading && <p>{loading}</p>}
          {fetchError && <p>{fetchError}</p>}
          <div className="admin-table-space">
            <table className="admin-table">
              <thead>
                <tr>
                  <th>Gatunek</th>
                  <th>Uprawa</th>
                  <th>Taryfa</th>
                  <th>Status</th>
                  <th>Edytuj</th>
                  <th>Usuń</th>
                </tr>
              </thead>
              <tbody>
                {data.map((data) => (
                  <tr key={data.idGatunek} className={`row-${data.idGatunek}`}>
                    <td>{data.nazwaGatunku}</td>
                    <td>{data.nazwaUprawy}</td>
                    <td>{data.taryfa}</td>
                    <td>{data.czyAktywna ? "AKTYWNA" : "NIEAKTYWNA"}</td>
                    <td>
                      <div>
                        <Link
                          to={`/admin/cropkindvariety/upsert/${data.idGatunek}`}
                        >
                          Edytuj id: {data.idGatunek}
                        </Link>
                      </div>
                    </td>
                    <td>
                      <div
                        onClick={() => deleteCropVarietyData(data.idGatunek)}
                      >
                        Usuń id: {data.idGatunek}
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
