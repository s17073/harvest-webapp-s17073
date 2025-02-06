import { useEffect, useState } from "react";
import { fetchDictionaryData } from "../../api/Dictionaries/fetchDictionaryData";
import { AdminPanelNav } from "../../components/Dictionaries/AdminPanelNav";
import { useNavigate } from "react-router-dom";
import { Container, Table } from "react-bootstrap";
export interface IInsuranceCompanyData {
  id: number;
  nazwa: string;
  numerZakladu: string;
  numerTelefonu: string;
  numerKonta: string;
  nip: string;
  krs: string;
  czyAktywna: boolean;
  idAddress: number;
  adres: string;
}

export const InsuranceCompanyData: React.FC = () => {
  const [data, setData] = useState<IInsuranceCompanyData[]>([]);
  const [loading, setLoading] = useState<string | null>(null);
  const [noData, setNoData] = useState<string | null>(null);
  const [fetchError, setFetchError] = useState<string | null>(null);
  const navigate = useNavigate();

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
        <Container>
          <div className="admin-content-space">
            <div className="admin-title-container">
              <h1>FIRMY UBEZPIECZENIOWE</h1>
            </div>
            {loading && <p>{loading}</p>}
            {fetchError && <p>{fetchError}</p>}
            <div className="admin-table-space">
              <Table
                striped
                bordered
                hover
                responsive="xl"
                size="xl"
                className="admin-table"
              >
                <thead>
                  <tr>
                    <th className="text-center align-middle lh-base px-0 px-xl-4">
                      Nazwa
                    </th>
                    <th className="text-center align-middle lh-base px-0 px-xl-4">
                      Numer zakładu
                    </th>
                    <th className="text-center align-middle lh-base px-0 px-xl-4">
                      Numer telefonu
                    </th>
                    <th className="text-center align-middle lh-base px-0 px-xl-4">
                      NIP
                    </th>
                    <th className="text-center align-middle lh-base px-0 px-xl-4">
                      KRS
                    </th>
                    <th className="text-center align-middle lh-base px-0 px-xl-4">
                      Status
                    </th>
                    <th className="text-center align-middle lh-base px-0 px-xl-4">
                      Adres
                    </th>
                  </tr>
                </thead>
                <tbody>
                  {data.map((data) => (
                    <tr
                      key={data.id}
                      className={`row-${data.id} text-center align-middle lh-1 px-0 px-xl-4`}
                    >
                      <td>{data.nazwa}</td>
                      <td>{data.numerZakladu}</td>
                      <td>{data.numerTelefonu}</td>
                      <td>{data.nip}</td>
                      <td>{data.krs}</td>
                      <td>{data.czyAktywna ? "AKTYWNA" : "NIEAKTYWNA"}</td>
                      <td>{data.adres}</td>
                    </tr>
                  ))}
                </tbody>
              </Table>
              {noData && <p>{noData}</p>}
            </div>
            <button
              className="admin-table-cancel"
              onClick={() => navigate("/admin")}
            >
              POWRÓT
            </button>
          </div>
        </Container>
      </div>
    </>
  );
};
