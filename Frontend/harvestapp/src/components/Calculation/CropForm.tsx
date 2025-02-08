import { useEffect, useState } from "react";
import { ICropData } from "./CropsFormTable";
import { CropFormLandField } from "./CropFormLandField";
import { ICrop } from "../../interfaces/ICrop";
import { ISoilClassList } from "../../interfaces/ISoilClassList";
import { ICoverList } from "../../interfaces/ICoverList";
import { ICropVarietyList } from "../../interfaces/ICropVarietyList";
import { fetchUprawy } from "../../api/Shared/fetchUprawy";
import { fetchGatunki } from "../../api/Shared/fetchGatunki";
import { fetchKlasyGleby } from "../../api/Shared/fetchKlasyGleby";
import { fetchOchrony } from "../../api/Shared/fetchOchrony";
import { useNavigate, useParams } from "react-router-dom";
import { ILand, IStepCrop } from "../../interfaces/IStepCrop";
import { handleAddCrop } from "../../api/Calculation/handleAddCrop";
import { fetchUprawa } from "../../api/Calculation/fetchUprawa";
import { Col, Form, Row } from "react-bootstrap";

export const CropForm: React.FC = () => {
  const [cropsList, setCropsList] = useState<ICrop[]>([]);
  const [soilClassList, setSoilClassList] = useState<ISoilClassList[]>([]);
  const [error, setError] = useState<String | undefined>(undefined);
  const [coverList, setCoverList] = useState<ICoverList[]>([]);
  const { id } = useParams<{ id: string }>();
  const { cropid } = useParams<{ cropid: string }>();
  const navigate = useNavigate();
  const [cropVarietyList, setCropVarietyList] = useState<ICropVarietyList[]>(
    [],
  );
  const [terytIsIncorrect, setTerytIsIncorrect] = useState<boolean | undefined>(
    undefined,
  );
  const [message, setMessage] = useState<string | undefined>(undefined);
  const [crop, setCrop] = useState<ICropData>({
    id: 0,
    idUprawy: 0,
    idGatunek: 0,
    idKlasaGleby: 0,
    uprawa: "",
    gatunek: "",
    klasaGleby: "",
    czyNasienna: true,
    powierzchnia: 0,
    wartosc: 0,
    wartoscRynkowa: 0,
    wartoscMax: 0,
    sumaUbezpieczenia: 0,
    ryzyka: [],
    dzialki: [
      {
        id: Date.now(),
        teryt: "",
        czyPoprawna: false,
        kodObrebu: "",
        numerDzialki: "",
        obreb: "",
        identyfikatorDzialki: "",
      },
    ],
  });

  useEffect(() => {
    fetchUprawy().then(setCropsList);
  }, []);

  useEffect(() => {
    fetchGatunki(crop.idUprawy).then(setCropVarietyList);
  }, [crop.idUprawy]);

  useEffect(() => {
    fetchKlasyGleby().then(setSoilClassList);
  }, []);

  useEffect(() => {
    fetchOchrony("uprawy").then(setCoverList);
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      if (id && cropid) {
        const crop = await fetchUprawa(parseInt(id), parseInt(cropid));
        if (crop) {
          setCrop(crop);
        }
      }
    };
    fetchData();
  }, [cropid]);

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    const landsToAdd: ILand[] = crop.dzialki.map((dzialka) => {
      const land: ILand = {
        teryt: dzialka.teryt,
        numerDzialki: dzialka.numerDzialki,
        czyPoprawna: dzialka.czyPoprawna,
        kodObrebu: dzialka.kodObrebu,
        obreb: dzialka.obreb,
      };
      return land;
    });

    const cropDataToAdd: IStepCrop = {
      idRodzajUprawy: crop.idUprawy,
      idGatunek: crop.idGatunek,
      idKlasaGleby: crop.idKlasaGleby,
      czyNasienna: crop.czyNasienna,
      powierzchnia: crop.powierzchnia,
      wartosc: crop.wartosc,
      ryzyka: crop.ryzyka,
      dzialki: landsToAdd,
    };

    if (id) {
      const idCalculation = parseInt(id);
      try {
        if (cropid) {
          const idCrop = parseInt(cropid);
          await handleAddCrop(idCalculation, cropDataToAdd, idCrop);
          navigate(`/calculation/${id}/crops`);
        } else {
          await handleAddCrop(idCalculation, cropDataToAdd);
          navigate(`/calculation/${id}/crops`);
        }
      } catch (e) {
        setError("Nie udało się wysłać danych");
      }
    }
  };

  const handleGoBack = () => {
    navigate(`/calculation/${id}/crops`);
  };

  const setField = (
    field: keyof ICropData,
    value: string | boolean | number[],
  ) => {
    setCrop((cropFields) => ({
      ...cropFields,
      [field]: value,
    }));
  };

  const handleCoverChange = (id: number) => {
    const newRyzyka = crop.ryzyka.includes(id)
      ? crop.ryzyka.filter((item) => item !== id)
      : [...crop.ryzyka, id];

    setField("ryzyka", newRyzyka);
  };

  const isPreviousLandComplete = (index: number) => {
    const dzialka = crop.dzialki[index];
    return (
      dzialka.teryt !== "" &&
      dzialka.numerDzialki !== "" &&
      dzialka.kodObrebu !== ""
    );
  };

  const addLand = () => {
    if (isPreviousLandComplete(crop.dzialki.length - 1)) {
      setCrop((cropData) => ({
        ...cropData,
        dzialki: [
          ...cropData.dzialki,
          {
            id: Date.now(),
            teryt: "",
            czyPoprawna: false,
            kodObrebu: "",
            numerDzialki: "",
            identyfikatorDzialki: "",
            obreb: "",
          },
        ],
      }));
    } else {
      setMessage("Uzupełnij poprzednią działkę");
    }
  };

  const removeLand = (id: number) => {
    setCrop((cropData) => ({
      ...cropData,
      dzialki: cropData.dzialki.filter((dzialka) => dzialka.id !== id),
    }));
  };

  const updateLandField = (
    id: number,
    field: string,
    value: string | boolean,
  ) => {
    setCrop((cropData) => ({
      ...cropData,
      dzialki: cropData.dzialki.map((dzialka) =>
        dzialka.id === id ? { ...dzialka, [field]: value } : dzialka,
      ),
    }));
  };

  return (
    <>
      <div className="admin-title-container">
        <h1>uprawy</h1>
      </div>
      <div>
        <Form onSubmit={handleSubmit}>
          <div className="section-heading">Dodaj uprawę</div>
          <Row className="form-indent mb-3">
            <Row className="mt-3">
              <Form.Label column lg="2">
                Rodzaj uprawy
              </Form.Label>
              <Col lg="4">
                <Form.Select
                  value={crop.idUprawy}
                  onChange={(e) => setField("idUprawy", e.target.value)}
                >
                  <option value="">Wybierz uprawę</option>
                  {cropsList.map((crop) => (
                    <option key={crop.idUprawy} value={crop.idUprawy}>
                      {crop.nazwaUprawy}
                    </option>
                  ))}
                </Form.Select>
              </Col>
            </Row>

            {!(cropVarietyList.length == 0) ? (
              <Row className="mt-3">
                <Form.Label column lg="2">
                  Gatunek uprawy
                </Form.Label>
                <Col lg="4">
                  <Form.Select
                    value={crop.idGatunek}
                    onChange={(e) => setField("idGatunek", e.target.value)}
                  >
                    <option value="">Wybierz gatunek</option>
                    {cropVarietyList.map((cropVariety) => (
                      <option
                        key={cropVariety.idGatunek}
                        value={cropVariety.idGatunek}
                      >
                        {cropVariety.nazwaGatunku}
                      </option>
                    ))}
                  </Form.Select>
                </Col>
              </Row>
            ) : undefined}

            <Row className="mt-3">
              <Form.Label column lg="2">
                Klasa gleby
              </Form.Label>
              <Col lg="4">
                <Form.Select
                  value={crop.idKlasaGleby}
                  onChange={(e) => setField("idKlasaGleby", e.target.value)}
                >
                  <option value="">Wybierz klasę</option>
                  {soilClassList.map((soilClass) => (
                    <option
                      key={soilClass.idKlasaGleby}
                      value={soilClass.idKlasaGleby}
                    >
                      {soilClass.klasaGleby}
                    </option>
                  ))}
                </Form.Select>
              </Col>
            </Row>

            <Row className="mt-3">
              <Form.Label column lg="2">
                Uprawa nasienna
              </Form.Label>
              <Col lg="4">
                <Form.Check
                  className="calc-form-field-input admin-form-checkbox"
                  type="checkbox"
                  checked={crop.czyNasienna}
                  onChange={() => setField("czyNasienna", !crop.czyNasienna)}
                />
              </Col>
            </Row>

            <Row className="mt-3">
              <Form.Label column lg="2">
                Powierzchnia (w ha)
              </Form.Label>
              <Col lg="4">
                <Form.Control
                  className="calc-form-field-input"
                  type="number"
                  value={crop.powierzchnia}
                  onChange={(e) => setField("powierzchnia", e.target.value)}
                />
              </Col>
            </Row>

            <Row className="mt-3">
              <Form.Label column lg="2">
                Wartość
              </Form.Label>
              <Col lg="4">
                <Form.Control
                  className="calc-form-field-input"
                  type="number"
                  value={crop.wartosc}
                  onChange={(e) => setField("wartosc", e.target.value)}
                />
              </Col>
            </Row>

            <Row className="mt-3">
              <Form.Label column lg="2">
                Ochrona
              </Form.Label>
              <Col lg="10">
                <Row>
                  {coverList.map((cover) => {
                    return (
                      <Col xs="6" md="4">
                        <Form.Group
                          controlId={`${cover.idOchrona}`}
                          className="d-flex"
                        >
                          <Form.Check
                            className="admin-form-checkbox"
                            type="checkbox"
                            onChange={() => handleCoverChange(cover.idOchrona)}
                            checked={crop.ryzyka.includes(cover.idOchrona)}
                          />
                          <Form.Label className="admin-form-checkbox px-3">
                            {cover.nazwa}
                          </Form.Label>
                        </Form.Group>
                      </Col>
                    );
                  })}
                </Row>
              </Col>
            </Row>
          </Row>

          <div className="section-heading mb-3">Dodaj działki</div>
          <Row className="form-indent mb-3">
            {crop.dzialki.map((dzialka) => (
              <div key={dzialka.id}>
                <CropFormLandField
                  dzialka={dzialka}
                  onUpdateField={(field, value) => {
                    updateLandField(dzialka.id, field, value);
                  }}
                  onRemove={() => removeLand(dzialka.id)}
                  removeButtonDisable={crop.dzialki.length === 1}
                  setTerytIsIncorrect={setTerytIsIncorrect}
                />
              </div>
            ))}
          </Row>
          <button type="button" onClick={addLand} disabled={terytIsIncorrect}>
            Dodaj działkę
          </button>
          {terytIsIncorrect &&
            "Dane przynajmniej w zakresie teryt muszą się zgadzać"}
          <div>{message && message}</div>
          <button
            className="calc-form-previous-button"
            type="button"
            onClick={handleGoBack}
          >
            Anuluj
          </button>
          <button className="calc-form-next-button" type="submit">
            Dodaj
          </button>
        </Form>
        {error && error}
      </div>
    </>
  );
};
