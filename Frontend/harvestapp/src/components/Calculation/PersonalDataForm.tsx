import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { fetchWojewodztwa } from "../../api/Shared/fetchWojewodztwa";
import { fetchPowiaty } from "../../api/Shared/fetchPowiaty";
import { fetchGminy } from "../../api/Shared/fetchGminy";
import { PartOfTeryt } from "../../interfaces/PartOfTeryt";
import { IStepPersonalData } from "../../interfaces/IStepPersonalData";
import { handleAddPersonalData } from "../../api/Calculation/handleAddPersonalData";
import { fetchPersonalData } from "../../api/Calculation/fetchPersonalData";
import { Col, FloatingLabel, Form, Row } from "react-bootstrap";
import BottomBar from "../Shared/BottomBar";

interface IPersonalData {
  imie: string;
  nazwisko: string;
  pesel: string;
  dataUrodzenia: string;
  adresEmail: string;
  teryt: string;
  kodPocztowy: string;
  miejscowosc: string;
  ulica: string;
  numerDomu: string;
  numerMieszkania: string | undefined;
}

export const PersonalDataForm: React.FC = () => {
  const [wojewodztwa, setWojewodztwa] = useState<PartOfTeryt[]>([]);
  const [powiaty, setPowiaty] = useState<PartOfTeryt[]>([]);
  const [gminy, setGminy] = useState<PartOfTeryt[]>([]);
  const [powiatyInsurer, setPowiatyInsurer] = useState<PartOfTeryt[]>([]);
  const [gminyInsurer, setGminyInsurer] = useState<PartOfTeryt[]>([]);
  const [error, setError] = useState<string | undefined>(undefined);
  const [policyHolder, setPolicyHolder] = useState<IPersonalData>({
    imie: "",
    nazwisko: "",
    pesel: "",
    dataUrodzenia: "",
    adresEmail: "",
    teryt: "",
    kodPocztowy: "",
    miejscowosc: "",
    ulica: "",
    numerDomu: "",
    numerMieszkania: "",
  });
  const [insured, setInsured] = useState<IPersonalData>({
    imie: "",
    nazwisko: "",
    pesel: "",
    dataUrodzenia: "",
    adresEmail: "",
    teryt: "",
    kodPocztowy: "",
    miejscowosc: "",
    ulica: "",
    numerDomu: "",
    numerMieszkania: "",
  });
  const navigate = useNavigate();
  const { id } = useParams<{ id: string }>();

  useEffect(() => {
    fetchWojewodztwa().then(setWojewodztwa);
  }, []);

  useEffect(() => {
    if (policyHolder.teryt.length === 2)
      fetchPowiaty(policyHolder.teryt).then(setPowiaty);
    if (insured.teryt.length === 2)
      fetchPowiaty(insured.teryt).then(setPowiatyInsurer);
  }, [policyHolder.teryt.substring(0, 2), insured.teryt.substring(0, 2)]);

  useEffect(() => {
    if (policyHolder.teryt.length === 4)
      fetchGminy(policyHolder.teryt).then(setGminy);
    if (insured.teryt.length === 4)
      fetchGminy(insured.teryt).then(setGminyInsurer);
  }, [policyHolder.teryt.substring(2, 4), insured.teryt.substring(2, 4)]);

  const setField = (
    field: keyof IPersonalData,
    setMethod: React.Dispatch<React.SetStateAction<IPersonalData>>,
    value: string,
  ) => {
    setMethod((personFields) => ({
      ...personFields,
      [field]: value,
    }));
  };

  const handleGoBack = () => {
    navigate(`/calculation/${id}/insuranceperiod`);
  };

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    const personalDataToAdd: IStepPersonalData = {
      ubezpieczajacy: {
        imie: policyHolder.imie,
        nazwisko: policyHolder.nazwisko,
        pesel: policyHolder.pesel,
        dataUrodzenia: new Date(policyHolder.dataUrodzenia),
        adresEmail: policyHolder.adresEmail,
        teryt: policyHolder.teryt,
        kodPocztowy: policyHolder.kodPocztowy,
        miejscowosc: policyHolder.miejscowosc,
        ulica: policyHolder.ulica,
        numerDomu: policyHolder.numerDomu,
        numerMieszkania: policyHolder.numerMieszkania,
      },
      ubezpieczony: {
        imie: insured.imie,
        nazwisko: insured.nazwisko,
        pesel: insured.pesel,
        dataUrodzenia: new Date(insured.dataUrodzenia),
        adresEmail: insured.adresEmail,
        teryt: insured.teryt,
        kodPocztowy: insured.kodPocztowy,
        miejscowosc: insured.miejscowosc,
        ulica: insured.ulica,
        numerDomu: insured.numerDomu,
        numerMieszkania: insured.numerMieszkania,
      },
    };

    if (id) {
      const idCalculation = parseInt(id);
      try {
        await handleAddPersonalData(idCalculation, personalDataToAdd);
        navigate(`/calculation/${id}/crops`);
      } catch (e) {
        setError("Nie udało się wysłać danych");
      }
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      if (id) {
        const personalData = await fetchPersonalData(parseInt(id));

        if (personalData) {
          const policyHolder: IPersonalData = {
            imie: personalData.ubezpieczajacy.imie,
            nazwisko: personalData.ubezpieczajacy.nazwisko,
            pesel: personalData.ubezpieczajacy.pesel,
            dataUrodzenia: personalData.ubezpieczajacy.dataUrodzenia,
            adresEmail: personalData.ubezpieczajacy.adresEmail,
            teryt: personalData.ubezpieczajacy.teryt,
            kodPocztowy: personalData.ubezpieczajacy.kodPocztowy,
            miejscowosc: personalData.ubezpieczajacy.miejscowosc,
            ulica: personalData.ubezpieczajacy.ulica,
            numerDomu: personalData.ubezpieczajacy.numerDomu,
            numerMieszkania: personalData.ubezpieczajacy.numerMieszkania,
          };

          const policyInsured: IPersonalData = {
            imie: personalData.ubezpieczony.imie,
            nazwisko: personalData.ubezpieczony.nazwisko,
            pesel: personalData.ubezpieczony.pesel,
            dataUrodzenia: personalData.ubezpieczony.dataUrodzenia,
            adresEmail: personalData.ubezpieczony.adresEmail,
            teryt: personalData.ubezpieczony.teryt,
            kodPocztowy: personalData.ubezpieczony.kodPocztowy,
            miejscowosc: personalData.ubezpieczony.miejscowosc,
            ulica: personalData.ubezpieczony.ulica,
            numerDomu: personalData.ubezpieczony.numerDomu,
            numerMieszkania: personalData.ubezpieczony.numerMieszkania,
          };

          setPolicyHolder(policyHolder);
          setInsured(policyInsured);
        }
      }
    };
    fetchData();
  }, [id]);

  const copyPolicyHolderData = () => {
    setInsured(policyHolder);
  };

  return (
    <>
      <Row>
        <Col>
          <div className="admin-title-container">
            <h1>Dane osobowe</h1>
          </div>
        </Col>
      </Row>
      <div>
        <Form onSubmit={handleSubmit}>
          <div className="admin-upsert-fields">
            <div className="section-heading">Dane ubezpieczającego</div>
            <Row className="form-indent mb-3">
              <Row className="mt-3">
                <Form.Label column lg="2">
                  Imię
                </Form.Label>
                <Col lg="4">
                  <Form.Control
                    className="calc-form-field-input"
                    type="text"
                    value={policyHolder.imie}
                    onChange={(e) =>
                      setField("imie", setPolicyHolder, e.target.value)
                    }
                  />
                </Col>
              </Row>
              <Row className="mt-3">
                <Form.Label column lg="2">
                  Nazwisko
                </Form.Label>
                <Col lg="4">
                  <Form.Control
                    className="calc-form-field-input"
                    type="text"
                    value={policyHolder.nazwisko}
                    onChange={(e) =>
                      setField("nazwisko", setPolicyHolder, e.target.value)
                    }
                  />
                </Col>
              </Row>
              <Row className="mt-3">
                <Form.Label column lg="2">
                  Pesel
                </Form.Label>
                <Col lg="4">
                  <Form.Control
                    className="calc-form-field-input"
                    type="number"
                    value={policyHolder.pesel}
                    onChange={(e) =>
                      setField("pesel", setPolicyHolder, e.target.value)
                    }
                  />
                </Col>
              </Row>
              <Row className="mt-3">
                <Form.Label column lg="2">
                  Data urodzenia
                </Form.Label>
                <Col lg="4">
                  <Form.Control
                    className="calc-form-field-input"
                    type="date"
                    value={policyHolder.dataUrodzenia}
                    onChange={(e) =>
                      setField("dataUrodzenia", setPolicyHolder, e.target.value)
                    }
                  />
                </Col>
              </Row>
              <Row className="mt-3">
                <Form.Label column lg="2">
                  Adres e-mail
                </Form.Label>
                <Col lg="4">
                  <Form.Control
                    className="calc-form-field-input"
                    type="email"
                    value={policyHolder.adresEmail}
                    onChange={(e) =>
                      setField("adresEmail", setPolicyHolder, e.target.value)
                    }
                  />
                </Col>
              </Row>
            </Row>
            <div className="section-heading ml-0">Adres do korespondencji</div>
            <Row className="form-indent mt-3">
              {/* <Col lg="4"> */}
              {/* <Row className="mb-3 mt-3"> */}
              <Form.Group
                as={Col}
                lg="4"
                controlId="wojewodztwo"
                className="mb-3"
              >
                <Form.Label className="teryt-label">Województwo</Form.Label>

                <Form.Select
                  value={policyHolder.teryt.substring(0, 2)}
                  onChange={(e) =>
                    setField("teryt", setPolicyHolder, e.target.value)
                  }
                >
                  <option value="">Wybierz województwo</option>
                  {wojewodztwa.map((wojewodztwo) => (
                    <option
                      key={wojewodztwo.kodTeryt}
                      value={wojewodztwo.kodTeryt}
                    >
                      {wojewodztwo.nazwa}
                    </option>
                  ))}
                </Form.Select>
              </Form.Group>

              <Form.Group as={Col} lg="4" controlId="powiat" className="mb-3">
                <Form.Label className="teryt-label">Powiat</Form.Label>

                <Form.Select
                  value={policyHolder.teryt.substring(0, 4)}
                  onChange={(e) =>
                    setField("teryt", setPolicyHolder, e.target.value)
                  }
                >
                  <option value="">Wybierz powiat</option>
                  {powiaty.map((powiat) => (
                    <option key={powiat.kodTeryt} value={powiat.kodTeryt}>
                      {powiat.nazwa}
                    </option>
                  ))}
                </Form.Select>
              </Form.Group>

              <Form.Group as={Col} lg="4" controlId="powiat" className="mb-3">
                <Form.Label className="teryt-label">Gmina</Form.Label>

                <Form.Select
                  value={policyHolder.teryt}
                  onChange={(e) =>
                    setField("teryt", setPolicyHolder, e.target.value)
                  }
                >
                  <option value="">Wybierz gminę</option>
                  {gminy.map((gmina) => (
                    <option key={gmina.kodTeryt} value={gmina.kodTeryt}>
                      {gmina.nazwa}
                    </option>
                  ))}
                </Form.Select>
              </Form.Group>

              <Row className="mt-3">
                <FloatingLabel
                  as={Col}
                  sm={4}
                  xl={2}
                  label="Kod pocztowy"
                  className="mb-3"
                  contolId="kodPocztowy"
                >
                  <Form.Control
                    type="text"
                    placeholder="Kod pocztowy"
                    value={policyHolder.kodPocztowy}
                    onChange={(e) =>
                      setField("kodPocztowy", setPolicyHolder, e.target.value)
                    }
                  />
                </FloatingLabel>

                <FloatingLabel
                  as={Col}
                  sm={8}
                  xl={10}
                  label="Miejscowość"
                  className="mb-3"
                  contolId="miejscowosc"
                >
                  <Form.Control
                    type="text"
                    placeholder="Miejscowość"
                    value={policyHolder.miejscowosc}
                    onChange={(e) =>
                      setField("miejscowosc", setPolicyHolder, e.target.value)
                    }
                  />
                </FloatingLabel>

                <FloatingLabel
                  as={Col}
                  md={6}
                  xl={8}
                  label="Ulica"
                  className="mb-3"
                  contolId="ulica"
                >
                  <Form.Control
                    type="text"
                    placeholder="Ulica"
                    value={policyHolder.ulica}
                    onChange={(e) =>
                      setField("ulica", setPolicyHolder, e.target.value)
                    }
                  />
                </FloatingLabel>

                <FloatingLabel
                  as={Col}
                  md={3}
                  xl={2}
                  label="Nr. domu"
                  className="mb-3"
                  contolId="numerDomu"
                >
                  <Form.Control
                    type="text"
                    placeholder="Nr. domu"
                    value={policyHolder.numerDomu}
                    onChange={(e) =>
                      setField("numerDomu", setPolicyHolder, e.target.value)
                    }
                  />
                </FloatingLabel>

                <FloatingLabel
                  as={Col}
                  md={3}
                  xl={2}
                  label="Nr. mieszkania"
                  className="mb-3"
                  contolId="numerMieszkania"
                >
                  <Form.Control
                    type="text"
                    placeholder="Nr. mieszkania"
                    value={policyHolder.numerMieszkania}
                    onChange={(e) =>
                      setField(
                        "numerMieszkania",
                        setPolicyHolder,
                        e.target.value,
                      )
                    }
                  />
                </FloatingLabel>
              </Row>
            </Row>
            <div>
              <div className="section-heading">Dane ubezpieczającego</div>
              <div
                className="copy-policy-holder"
                onClick={copyPolicyHolderData}
              >
                Kliknij, żeby przekopiować dane ubezpieczającego.
              </div>
            </div>
            <Row className="form-indent mb-3">
              <Row className="mt-3">
                <Form.Label column lg="2">
                  Imię
                </Form.Label>
                <Col lg="4">
                  <Form.Control
                    className="calc-form-field-input"
                    type="text"
                    value={insured.imie}
                    onChange={(e) =>
                      setField("imie", setInsured, e.target.value)
                    }
                  />
                </Col>
              </Row>
              <Row className="mt-3">
                <Form.Label column lg="2">
                  Nazwisko
                </Form.Label>
                <Col lg="4">
                  <Form.Control
                    className="calc-form-field-input"
                    type="text"
                    value={insured.nazwisko}
                    onChange={(e) =>
                      setField("nazwisko", setInsured, e.target.value)
                    }
                  />
                </Col>
              </Row>
              <Row className="mt-3">
                <Form.Label column lg="2">
                  Pesel
                </Form.Label>
                <Col lg="4">
                  <Form.Control
                    className="calc-form-field-input"
                    type="number"
                    value={insured.pesel}
                    onChange={(e) =>
                      setField("pesel", setInsured, e.target.value)
                    }
                  />
                </Col>
              </Row>
              <Row className="mt-3">
                <Form.Label column lg="2">
                  Data urodzenia
                </Form.Label>
                <Col lg="4">
                  <Form.Control
                    className="calc-form-field-input"
                    type="date"
                    value={insured.dataUrodzenia}
                    onChange={(e) =>
                      setField("dataUrodzenia", setInsured, e.target.value)
                    }
                  />
                </Col>
              </Row>
              <Row className="mt-3">
                <Form.Label column lg="2">
                  Adres e-mail
                </Form.Label>
                <Col lg="4">
                  <Form.Control
                    className="calc-form-field-input"
                    type="email"
                    value={insured.adresEmail}
                    onChange={(e) =>
                      setField("adresEmail", setInsured, e.target.value)
                    }
                  />
                </Col>
              </Row>
            </Row>
            <div className="section-heading ml-0">Adres do korespondencji</div>
            <Row className="form-indent mt-3">
              <Form.Group
                as={Col}
                lg="4"
                controlId="wojewodztwo"
                className="mb-3"
              >
                <Form.Label className="teryt-label">Województwo</Form.Label>

                <Form.Select
                  value={insured.teryt.substring(0, 2)}
                  onChange={(e) =>
                    setField("teryt", setInsured, e.target.value)
                  }
                >
                  <option value="">Wybierz województwo</option>
                  {wojewodztwa.map((wojewodztwo) => (
                    <option
                      key={wojewodztwo.kodTeryt}
                      value={wojewodztwo.kodTeryt}
                    >
                      {wojewodztwo.nazwa}
                    </option>
                  ))}
                </Form.Select>
              </Form.Group>

              <Form.Group as={Col} lg="4" controlId="powiat" className="mb-3">
                <Form.Label className="teryt-label">Powiat</Form.Label>

                <Form.Select
                  value={insured.teryt.substring(0, 4)}
                  onChange={(e) =>
                    setField("teryt", setInsured, e.target.value)
                  }
                >
                  <option value="">Wybierz powiat</option>
                  {powiatyInsurer.map((powiat) => (
                    <option key={powiat.kodTeryt} value={powiat.kodTeryt}>
                      {powiat.nazwa}
                    </option>
                  ))}
                </Form.Select>
              </Form.Group>

              <Form.Group as={Col} lg="4" controlId="powiat" className="mb-3">
                <Form.Label className="teryt-label">Gmina</Form.Label>

                <Form.Select
                  value={insured.teryt}
                  onChange={(e) =>
                    setField("teryt", setInsured, e.target.value)
                  }
                >
                  <option value="">Wybierz gminę</option>
                  {gminyInsurer.map((gmina) => (
                    <option key={gmina.kodTeryt} value={gmina.kodTeryt}>
                      {gmina.nazwa}
                    </option>
                  ))}
                </Form.Select>
              </Form.Group>

              <Row className="mt-3">
                <FloatingLabel
                  as={Col}
                  sm={4}
                  xl={2}
                  label="Kod pocztowy"
                  className="mb-3"
                  contolId="kodPocztowy"
                >
                  <Form.Control
                    type="text"
                    placeholder="Kod pocztowy"
                    value={insured.kodPocztowy}
                    onChange={(e) =>
                      setField("kodPocztowy", setInsured, e.target.value)
                    }
                  />
                </FloatingLabel>

                <FloatingLabel
                  as={Col}
                  sm={8}
                  xl={10}
                  label="Miejscowość"
                  className="mb-3"
                  contolId="miejscowosc"
                >
                  <Form.Control
                    type="text"
                    placeholder="Miejscowość"
                    value={insured.miejscowosc}
                    onChange={(e) =>
                      setField("miejscowosc", setInsured, e.target.value)
                    }
                  />
                </FloatingLabel>

                <FloatingLabel
                  as={Col}
                  md={6}
                  xl={8}
                  label="Ulica"
                  className="mb-3"
                  contolId="ulica"
                >
                  <Form.Control
                    type="text"
                    placeholder="Ulica"
                    value={insured.ulica}
                    onChange={(e) =>
                      setField("ulica", setInsured, e.target.value)
                    }
                  />
                </FloatingLabel>

                <FloatingLabel
                  as={Col}
                  md={3}
                  xl={2}
                  label="Nr. domu"
                  className="mb-3"
                  contolId="numerDomu"
                >
                  <Form.Control
                    type="text"
                    placeholder="Nr. domu"
                    value={insured.numerDomu}
                    onChange={(e) =>
                      setField("numerDomu", setInsured, e.target.value)
                    }
                  />
                </FloatingLabel>

                <FloatingLabel
                  as={Col}
                  md={3}
                  xl={2}
                  label="Nr. mieszkania"
                  className="mb-3"
                  contolId="numerMieszkania"
                >
                  <Form.Control
                    type="text"
                    placeholder="Nr. mieszkania"
                    value={insured.numerMieszkania}
                    onChange={(e) =>
                      setField("numerMieszkania", setInsured, e.target.value)
                    }
                  />
                </FloatingLabel>
              </Row>
            </Row>
          </div>
          <BottomBar
            button1={{
              label: "WSTECZ",
              className: "admin-upsert-cancel",
              onClick: () => {
                handleGoBack();
              },
            }}
            button2={{
              label: "DALEJ",
              className: "admin-upsert-submit",
              onClick: () => handleSubmit,
            }}
          />
        </Form>
        <div>{error && error}</div>
      </div>
    </>
  );
};
