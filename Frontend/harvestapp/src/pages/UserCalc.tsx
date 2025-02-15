import { Col, Container, Row, Table } from "react-bootstrap";
import { MainNav } from "../components/Calculation/MainNav";
import { useEffect, useState } from "react";
import { fetchUserCalc } from "../api/Shared/fetchUserCalc";
import { useNavigate } from "react-router-dom";
import { iconEdit } from "../assets/icons/edit";

export const UserCalc: React.FC = () => {
  const [userName, setUserName] = useState<string | null>(
    localStorage.getItem("userName"),
  );
  const [calc, setCalc] = useState<Object[]>();
  const navigate = useNavigate();

  const fetchData = async () => {
    const response: any = await fetchUserCalc(userName);
    if (response) {
      setCalc(response);
    }
  };

  useEffect(() => {
    fetchData();
  }, [userName]);

  const goToCalc = (id: any) => {
    navigate(`../calculation/${id}/insuranceperiod`);
  };

  return (
    <>
      <MainNav />
      <div className="background">
        <Container>
          <div className="content-space">
            <Row>
              <Col className="content-space-form">
                <div className="calculation-form-section">
                  <Row>
                    <Col>
                      <div className="admin-title-container">
                        <h1>{userName}</h1>
                      </div>
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
                                Numer kalkulacji
                              </th>
                              <th className="text-center align-middle lh-base px-0 px-xl-4">
                                Status
                              </th>
                              <th className="text-center align-middle lh-base px-0 px-xl-4">
                                Początek ubezpieczenia
                              </th>
                              <th className="text-center align-middle lh-base px-0 px-xl-4">
                                Koniec ubezpieczenia
                              </th>
                              <th className="text-center align-middle lh-base px-0 px-xl-4">
                                Imię ubezpieczonego
                              </th>
                              <th className="text-center align-middle lh-base px-0 px-xl-4">
                                Nazwisko ubezpieczonego
                              </th>
                              <th className="text-center align-middle lh-base px-0 px-xl-4">
                                Wróć do kalkulacji
                              </th>
                            </tr>
                          </thead>
                          <tbody>
                            {calc?.map((c: any) => (
                              <tr
                                key={c?.idKalkulacja}
                                className={`row-${c?.idKalkulacja} text-center align-middle lh-1 px-0 px-xl-4`}
                              >
                                <td>{c.numerKalkulacji}</td>
                                <td>
                                  {c.statusKalkulacji
                                    ? c.statusKalkulacji
                                    : "KALKULACJA"}
                                </td>
                                <td>{c.dataPoczatkuOchrony}</td>
                                <td>{c.dataKoncaOchrony}</td>
                                <td>{c.imie}</td>
                                <td>{c.nazwisko}</td>
                                <td
                                  onClick={() => {
                                    !c.statusKalkulacji &&
                                      goToCalc(c.idKalkulacja);
                                  }}
                                >
                                  {!c.statusKalkulacji && iconEdit()}
                                </td>
                              </tr>
                            ))}
                          </tbody>
                        </Table>
                      </div>
                    </Col>
                  </Row>
                </div>
              </Col>
            </Row>
          </div>
        </Container>
      </div>
    </>
  );
};
