import { Col, Container, Row, Table } from "react-bootstrap";
import { iconEdit } from "../assets/icons/edit";
import { MainNav } from "../components/Calculation/MainNav";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { fetchUserPol } from "../api/Shared/fetchUserPol";

export const UserPol: React.FC = () => {
  const [userName, setUserName] = useState<string | null>(
    localStorage.getItem("userName"),
  );
  const [pol, setPol] = useState<Object[]>();
  const navigate = useNavigate();

  const fetchData = async () => {
    const response: any = await fetchUserPol(userName);
    if (response) {
      setPol(response);
    }
  };

  useEffect(() => {
    fetchData();
  }, [userName]);

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
                                Numer polisy
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
                                Status
                              </th>
                            </tr>
                          </thead>
                          <tbody>
                            {pol?.map((p: any) => (
                              <tr
                                key={p?.idPolisa}
                                className={`row-${p?.idPolisa} text-center align-middle lh-1 px-0 px-xl-4`}
                              >
                                <td>{p.numerPolisy}</td>
                                <td>{p.poczatekUbezpieczenia}</td>
                                <td>{p.koniecUbezpieczenia}</td>
                                <td>{p.imieUbezpieczonego}</td>
                                <td>{p.nazwiskoUbezpieczonego}</td>
                                <td>{p.status}</td>
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
