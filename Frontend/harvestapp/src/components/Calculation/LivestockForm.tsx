import { useEffect, useState } from "react";
import { ILivestock } from "../../interfaces/ILivestock";
import { ICoverList } from "../../interfaces/ICoverList";
import { fetchOchrony } from "../../api/Shared/fetchOchrony";
import { ILivestockList } from "../../interfaces/ILivestockList";
import { fetchZwierzeta } from "../../api/Calculation/fetchZwierzeta";
import { useNavigate, useParams } from "react-router-dom";
import { handleAddLivestock } from "../../api/Calculation/handleAddLivestock";
import { fetchZwierze } from "../../api/Calculation/fetchZwierze";
import { Col, Form, Row } from "react-bootstrap";
import BottomBar from "../Shared/BottomBar";

export const LivestockForm: React.FC = () => {
  const [error, setError] = useState<String | undefined>(undefined);
  const [coverList, setCoverList] = useState<ICoverList[]>([]);
  const [message, setMessage] = useState<string | undefined>(undefined);
  const [livestockList, setLivestockList] = useState<ILivestockList[]>([]);
  const { id } = useParams<{ id: string }>();
  const { livestockid } = useParams<{ livestockid: string }>();
  const navigate = useNavigate();
  const [livestock, setLivestock] = useState<ILivestock>({
    id: 0,
    idRodzajZwierzecia: 0,
    liczba: 0,
    naMieso: false,
    nazwaZwierzecia: "",
    sumaUbezpieczenia: 0,
    wartosc: 0,
    wartoscRynkowa: 0,
    ryzyka: [],
  });

  //TODO
  console.log(error);
  console.log(setMessage);

  useEffect(() => {
    fetchOchrony("zwierzeta").then(setCoverList);
  }, []);

  useEffect(() => {
    fetchZwierzeta().then(setLivestockList);
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      if (id && livestockid) {
        const animal = await fetchZwierze(parseInt(id), parseInt(livestockid));
        if (animal) {
          setLivestock(animal);
        }
      }
    };
    fetchData();
  }, [livestockid]);

  const setField = (
    field: keyof ILivestock,
    value: string | boolean | number[] | number,
  ) => {
    setLivestock((livestockField) => ({
      ...livestockField,
      [field]: value,
    }));
  };

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    console.log(livestock);

    if (id) {
      const idCalculation = parseInt(id);
      try {
        if (livestockid) {
          const idLivestock = parseInt(livestockid);
          await handleAddLivestock(idCalculation, livestock, idLivestock);
          navigate(`/calculation/${id}/livestock`);
        } else {
          await handleAddLivestock(idCalculation, livestock);
          navigate(`/calculation/${id}/livestock`);
        }
      } catch (e) {
        setError("Nie udało się wysłać danych");
      }
    }
  };

  const handleGoBack = () => {
    navigate(`/calculation/${id}/livestock`);
  };

  const handleCoverChange = (id: number) => {
    const newRyzyka = livestock.ryzyka.includes(id)
      ? livestock.ryzyka.filter((item) => item !== id)
      : [...livestock.ryzyka, id];

    setField("ryzyka", newRyzyka);
  };

  return (
    <>
      <div className="admin-title-container">
        <h1>Zwierzeta</h1>
      </div>
      <div>
        <Form onSubmit={handleSubmit}>
          <div className="section-heading">Dodaj zwierzę</div>
          <Row className="form-indent mb-3">
            <Row className="mt-3">
              <Form.Label column lg="2">
                Rodzaj zwierzęcia
              </Form.Label>
              <Col lg="4">
                <Form.Select
                  value={livestock.idRodzajZwierzecia}
                  onChange={(e) =>
                    setField("idRodzajZwierzecia", e.target.value)
                  }
                >
                  <option value="">Wybierz zwierzę</option>
                  {livestockList.map((l) => (
                    <option key={l.id} value={l.id}>
                      {l.nazwa}
                    </option>
                  ))}
                </Form.Select>
              </Col>
            </Row>

            <Row className="mt-3">
              <Form.Label column lg="2">
                Liczba
              </Form.Label>
              <Col lg="4">
                <Form.Control
                  type="number"
                  className="calc-form-field-input"
                  value={livestock.liczba}
                  onChange={(e) => setField("liczba", e.target.value)}
                />
              </Col>
            </Row>

            <Row className="mt-3">
              <Form.Label column lg="2">
                Hodowla na mięso
              </Form.Label>
              <Col lg="4">
                <Form.Group className="d-flex">
                  <Form.Check
                    type="checkbox"
                    className="admin-form-checkbox"
                    checked={livestock.naMieso}
                    onChange={() => setField("naMieso", !livestock.naMieso)}
                  />
                </Form.Group>
              </Col>
            </Row>

            <Row className="mt-3">
              <Form.Label column lg="2">
                Wartość (za szt.)
              </Form.Label>
              <Col lg="4">
                <Form.Control
                  type="number"
                  className="calc-form-field-input"
                  value={livestock.wartosc}
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
                            checked={livestock.ryzyka.includes(cover.idOchrona)}
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
          <div>{message && message}</div>

          <BottomBar
            button1={{
              label: "ANULUJ",
              className: "admin-upsert-cancel",
              onClick: () => handleGoBack(),
            }}
            button2={{
              label: "DODAJ",
              className: "admin-upsert-submit",
              onClick: () => handleSubmit,
            }}
          />
        </Form>
      </div>
    </>
  );
};
