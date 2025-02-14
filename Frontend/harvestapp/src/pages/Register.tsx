import { Col, Container, Form, FormGroup, Row } from "react-bootstrap";
import { MainNav } from "../components/Calculation/MainNav";
import BottomBar from "../components/Shared/BottomBar";
import { useEffect, useState } from "react";
import { fetchAgenci } from "../api/Shared/fetchAgenci";
import { signInUser } from "../api/Shared/signInUser";
import { logInUser } from "../api/Shared/logInUser";
import * as yup from "yup";
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

export const Register: React.FC = () => {
  const [agenci, setAgenci] = useState<any>([]);
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
  const [errors, setErrors] = useState<any>({});
  const navigate = useNavigate();

  const userSchema = yup.object().shape({
    imie: yup
      .string()
      .required("Imię jest wymagane")
      .min(2, "Imię musi mieć co najmniej 2 znaki")
      .max(50, "Imię może mieć maksymalnie 50 znaków"),

    nazwisko: yup
      .string()
      .required("Nazwisko jest wymagane")
      .min(2, "Nazwisko musi mieć co najmniej 2 znaki")
      .max(50, "Nazwisko może mieć maksymalnie 50 znaków"),

    pesel: yup
      .string()
      .required("Pesel jest wymagany")
      .matches(/^[0-9]{11}$/, "Pesel musi składać się z 11 cyfr"),

    dataUrodzenia: yup
      .string()
      .required("Data urodzenia jest wymagana")
      .matches(
        /^\d{4}-\d{2}-\d{2}$/,
        "Data urodzenia musi być w formacie YYYY-MM-DD",
      ),

    email: yup
      .string()
      .required("Email jest wymagany")
      .email("Email musi być poprawnym adresem email"),

    haslo: yup
      .string()
      .required("Hasło jest wymagane")
      .min(6, "Hasło musi mieć co najmniej 6 znaków")
      .max(20, "Hasło może mieć maksymalnie 20 znaków"),

    numerTelefonu: yup
      .string()
      .required("Numer telefonu jest wymagany")
      .matches(
        /^[0-9]{9}$/,
        "Numer telefonu musi składać się z 9 cyfr (np. 123456789)",
      ),

    kodPosrednika: yup.string().nullable().notRequired(),

    idAgenta: yup
      .number()
      .nullable()
      .notRequired()
      .min(1, "Id Agenta musi być większe niż 0"),
  });

  useEffect(() => {
    if (user.kodPosrednika !== undefined && user.idAgenta !== 0) {
      setField("rola", setUser, "AGENT");
    } else {
      setField("rola", setUser, "USER");
    }
  }, [user.idAgenta, user.kodPosrednika]);

  const setField = (
    field: keyof IUser,
    setMethod: React.Dispatch<React.SetStateAction<IUser>>,
    value: string,
  ) => {
    setMethod((userFields) => ({
      ...userFields,
      [field]: value,
    }));
  };

  useEffect(() => {
    fetchAgenci().then(setAgenci);
  }, []);

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    try {
      await userSchema.validate(user, { abortEarly: false });

      if (user.idAgenta === 0 && user.kodPosrednika === undefined) {
        setUser((prevUser) => ({
          ...prevUser,
          rola: "USER",
        }));

        setTimeout(async () => {
          const token = await signInUser(user);

          if (token !== false) {
            const response = await logInUser(user.email, user.haslo);

            if (response === true) {
              console.log("Zalogowano!");
            }
          }
        }, 0);
      }
    } catch (e) {
      if (e instanceof yup.ValidationError) {
        const fieldErrors: any = {};
        e.inner.forEach((error) => {
          if (error.path) {
            fieldErrors[error.path] = error.message;
          }
        });
        setErrors(fieldErrors);
      }
    }
    navigate("/");
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
                        <h1>REJESTRACJA</h1>
                      </div>
                    </Col>
                  </Row>
                  <div>
                    <Form onSubmit={handleSubmit}>
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
                                onChange={(e) =>
                                  setField("imie", setUser, e.target.value)
                                }
                              />
                            </Col>
                            {(errors.imie as string) && (
                              <span className="error-message">
                                {errors.imie as string}
                              </span>
                            )}
                          </Row>
                          <Row className="mt-3">
                            <Form.Label>Nazwisko</Form.Label>
                            <Col lg="4">
                              <Form.Control
                                className="calc-form-field-input"
                                type="text"
                                value={user.nazwisko}
                                onChange={(e) =>
                                  setField("nazwisko", setUser, e.target.value)
                                }
                              />
                            </Col>
                            {(errors.nazwisko as string) && (
                              <span className="error-message">
                                {errors.nazwisko as string}
                              </span>
                            )}
                          </Row>
                          <Row className="mt-3">
                            <Form.Label>Pesel</Form.Label>
                            <Col lg="4">
                              <Form.Control
                                className="calc-form-field-input"
                                type="number"
                                value={user.pesel}
                                onChange={(e) =>
                                  setField("pesel", setUser, e.target.value)
                                }
                              />
                            </Col>
                            {(errors.pesel as string) && (
                              <span className="error-message">
                                {errors.pesel as string}
                              </span>
                            )}
                          </Row>
                          <Row className="mt-3">
                            <Form.Label>Data urodzenia</Form.Label>
                            <Col lg="4">
                              <Form.Control
                                className="calc-form-field-input"
                                type="date"
                                value={user.dataUrodzenia}
                                onChange={(e) =>
                                  setField(
                                    "dataUrodzenia",
                                    setUser,
                                    e.target.value,
                                  )
                                }
                              />
                            </Col>
                            {(errors.dataUrodzenia as string) && (
                              <span className="error-message">
                                {errors.dataUrodzenia as string}
                              </span>
                            )}
                          </Row>
                          <Row className="mt-3">
                            <Form.Label>E-mail</Form.Label>
                            <Col lg="4">
                              <Form.Control
                                className="calc-form-field-input"
                                type="email"
                                value={user.email}
                                onChange={(e) =>
                                  setField("email", setUser, e.target.value)
                                }
                              />
                            </Col>
                            {(errors.email as string) && (
                              <span className="error-message">
                                {errors.email as string}
                              </span>
                            )}
                          </Row>
                          <Row className="mt-3">
                            <Form.Label>Hasło</Form.Label>
                            <Col lg="4">
                              <Form.Control
                                className="calc-form-field-input"
                                type="password"
                                value={user.haslo}
                                onChange={(e) =>
                                  setField("haslo", setUser, e.target.value)
                                }
                              />
                            </Col>
                            {(errors.haslo as string) && (
                              <span className="error-message">
                                {errors.haslo as string}
                              </span>
                            )}
                          </Row>
                          <Row className="mt-3">
                            <Form.Label>Numer telefonu</Form.Label>
                            <Col lg="4">
                              <Form.Control
                                className="calc-form-field-input"
                                type="number"
                                value={user.numerTelefonu}
                                onChange={(e) =>
                                  setField(
                                    "numerTelefonu",
                                    setUser,
                                    e.target.value,
                                  )
                                }
                              />
                            </Col>
                            {(errors.numerTelefonu as string) && (
                              <span className="error-message">
                                {errors.numerTelefonu as string}
                              </span>
                            )}
                          </Row>
                        </Row>
                        <div className="section-heading">
                          Jeżeli jesteś pośrednikiem ubezpieczeniowym, uzupełnij
                          poniższe pola
                        </div>
                        <Row className="form-indent mb-3">
                          <Row className="mt-3">
                            <Form.Label>Twój kod pośrednika</Form.Label>
                            <Col lg="4">
                              <Form.Control
                                className="calc-form-field-input"
                                type="text"
                                value={user.kodPosrednika}
                                onChange={(e) =>
                                  setField(
                                    "kodPosrednika",
                                    setUser,
                                    e.target.value,
                                  )
                                }
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
                              value={user.idAgenta}
                              onChange={(e) =>
                                setField("idAgenta", setUser, e.target.value)
                              }
                            >
                              <option value="">Wybierz agenta</option>
                              {agenci.map((agent: any) => (
                                <option key={agent.id} value={agent.id}>
                                  {agent.nazwa + " " + agent.kodAgencji}
                                </option>
                              ))}
                            </Form.Select>
                          </FormGroup>
                        </Row>
                      </div>
                      <BottomBar
                        button1={undefined}
                        button2={{
                          label: "ZAŁÓŻ KONTO",
                          className: "admin-upsert-submit",
                          onClick: () => {
                            handleSubmit;
                          },
                        }}
                      />
                    </Form>
                  </div>
                </div>
              </Col>
            </Row>
          </div>
        </Container>
      </div>
    </>
  );
};
