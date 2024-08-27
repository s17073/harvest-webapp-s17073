import { useEffect, useState } from "react";
import { fetchDictionaryData } from "../../api/Dictionaries/fetchDictionaryData";
import { AdminPanelNav } from "../../components/Dictionaries/AdminPanelNav";
export interface IInsuranceCompanyData {
  id: 1;
  nazwa: string;
  numerZakladu: string;
  numerTelefonu: string;
  numerKonta: string;
  nip: string;
  krs: string;
  czyAktywna: boolean;
  idAddress: number;
  addres: string;
}

export const InsuranceCompanyData: React.FC = () => {
  const [data, setData] = useState<IInsuranceCompanyData[]>([]);
  const [loading, setLoading] = useState<string | null>(null);
  const [noData, setNoData] = useState<string | null>(null);
  const [fetchError, setFetchError] = useState<string | null>(null);

  const fetchInsuranceCompanyData = () =>
    fetchDictionaryData(
      "insurancecompany",
      setNoData,
      setData,
      setFetchError,
      setLoading,
    );

  useEffect(() => {
    fetchInsuranceCompanyData();
  }, []);

  return (
    <>
      <AdminPanelNav />
      <div className="background">
        <div className="admin-content-space">
          <div className="admin-title-container">
            <h1>FIRMY UBEZPIECZENIOWE</h1>
          </div>
          {loading && <p>{loading}</p>}
          {fetchError && <p>{fetchError}</p>}
          <div className="admin-table-space">
            <table className="admin-table">
              <thead>
                <tr>
                  <th>Nazwa</th>
                  <th>Numer zakładu</th>
                  <th>Numer telefonu</th>
                  <th>NIP</th>
                  <th>KRS</th>
                  <th>Status</th>
                  <th>Adres</th>
                </tr>
              </thead>
              <tbody>
                {data.map((data) => (
                  <tr key={data.id} className={`row-${data.id}`}>
                    <td>{data.nazwa}</td>
                    <td>{data.numerZakladu}</td>
                    <td>{data.numerTelefonu}</td>
                    <td>{data.nip}</td>
                    <td>{data.krs}</td>
                    <td>{data.czyAktywna ? "AKTYWNA" : "NIEAKTYWNA"}</td>
                    <td>{data.addres}</td>
                  </tr>
                ))}
              </tbody>
            </table>
            {noData && <p>{noData}</p>}
          </div>
        </div>
      </div>
    </>
  );
};
