import { Col, Container, Form, FormGroup, Row } from "react-bootstrap";
import { MainNav } from "../components/Calculation/MainNav";
import { useEffect, useState } from "react";
import BottomBar from "../components/Shared/BottomBar";
import { fetchUser } from "../api/Shared/fetchUser";
import { useNavigate } from "react-router-dom";

interface IUser {
  imie: string;
  nazwisko: string;
  pesel: string;
  dataUrodzenia: string;
  email: string;
  haslo: string;
  numerTelefonu: string;
  kodPosrednika: string | undefined;
  idAgenta: number | undefined;
  rola: string;
}

export const Profile: React.FC = () => {
  const [agenci, setAgenci] = useState<any>([]);
  const [userName, setUserName] = useState<string | null>(
    localStorage.getItem("userName"),
  );
  const [userRole, setUserRole] = useState<string | null>(
    localStorage.getItem("userType"),
  );
  const navigate = useNavigate();
  const [user, setUser] = useState<IUser>({
    imie: "",
    nazwisko: "",
    pesel: "",
    dataUrodzenia: "",
    email: "",
    haslo: "",
    numerTelefonu: "",
    kodPosrednika: undefined,
    idAgenta: 0,
    rola: "",
  });

  useEffect(() => {
    const loadUser = async () => {
      try {
        const response = await fetchUser(userName);

        const userToSet: IUser = {
          imie: response?.imie,
          nazwisko: response?.nazwisko,
          pesel: response?.pesel,
          dataUrodzenia: response?.dataUrodzenia,
          email: response.email,
          haslo: "******",
          numerTelefonu: response.numerTelefonu,
          kodPosrednika: response.kodPosrednika,
          idAgenta: response.idAgenta,
          rola: "USER",
        };

        setUser(userToSet);
        console.log(user);
      } catch (e) {}
    };
    loadUser();
  }, []);

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
                      <div>
                        <Form>
                          <div className="admin-upsert-fields">
                            <div className="section-heading">Dane konta</div>
                            <Row className="form-indent mb-3">
                              <Row className="mt-3">
                                <Form.Label>Imię</Form.Label>
                                <Col lg="4">
                                  <Form.Control
                                    className="calc-form-field-input"
                                    type="text"
                                    value={user.imie}
                                    disabled={true}
                                  />
                                </Col>
                              </Row>
                              <Row className="mt-3">
                                <Form.Label>Nazwisko</Form.Label>
                                <Col lg="4">
                                  <Form.Control
                                    className="calc-form-field-input"
                                    type="text"
                                    value={user.nazwisko}
                                    disabled={true}
                                  />
                                </Col>
                              </Row>
                              <Row className="mt-3">
                                <Form.Label>Pesel</Form.Label>
                                <Col lg="4">
                                  <Form.Control
                                    className="calc-form-field-input"
                                    type="number"
                                    value={user.pesel}
                                    disabled={true}
                                  />
                                </Col>
                              </Row>
                              <Row className="mt-3">
                                <Form.Label>Data urodzenia</Form.Label>
                                <Col lg="4">
                                  <Form.Control
                                    className="calc-form-field-input"
                                    type="date"
                                    value={user.dataUrodzenia}
                                    disabled={true}
                                  />
                                </Col>
                              </Row>
                              <Row className="mt-3">
                                <Form.Label>E-mail</Form.Label>
                                <Col lg="4">
                                  <Form.Control
                                    className="calc-form-field-input"
                                    type="email"
                                    value={user.email}
                                    disabled={true}
                                  />
                                </Col>
                              </Row>
                              <Row className="mt-3">
                                <Form.Label>Hasło</Form.Label>
                                <Col lg="4">
                                  <Form.Control
                                    className="calc-form-field-input"
                                    type="password"
                                    value={user.haslo}
                                    disabled={true}
                                  />
                                </Col>
                              </Row>
                              <Row className="mt-3">
                                <Form.Label>Numer telefonu</Form.Label>
                                <Col lg="4">
                                  <Form.Control
                                    className="calc-form-field-input"
                                    type="number"
                                    value={user.numerTelefonu}
                                    disabled={true}
                                  />
                                </Col>
                              </Row>
                            </Row>
                            {userRole === "AGENT" && (
                              <Row className="form-indent mb-3">
                                <Row className="mt-3">
                                  <Form.Label>Twój kod pośrednika</Form.Label>
                                  <Col lg="4">
                                    <Form.Control
                                      className="calc-form-field-input"
                                      type="text"
                                      value={user.kodPosrednika}
                                      disabled={true}
                                    />
                                  </Col>
                                </Row>
                                <FormGroup
                                  as={Col}
                                  lg="4"
                                  controlId="agent"
                                  className="mt-3"
                                >
                                  <Form.Label>
                                    Kod twojej agencji ubezpieczeniowej
                                  </Form.Label>

                                  <Form.Select
                                    disabled={true}
                                    value={user.idAgenta}
                                  >
                                    <option value="">Agent</option>
                                    {agenci.map((agent: any) => (
                                      <option key={agent.id} value={agent.id}>
                                        {agent.nazwa + " " + agent.kodAgencji}
                                      </option>
                                    ))}
                                  </Form.Select>
                                </FormGroup>
                              </Row>
                            )}
                          </div>
                          <BottomBar
                            button1={{
                              label: "POWRÓT",
                              className: "admin-upsert-submit",
                              onClick: () => {
                                navigate("/");
                              },
                            }}
                            button2={undefined}
                          />
                        </Form>
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
