import { useEffect, useState } from "react";
import { IAgriculturalLand } from "../../interfaces/IAgriculturalLand";
import { PartOfTeryt } from "../../interfaces/PartOfTeryt";
import { fetchWojewodztwa } from "../../api/Shared/fetchWojewodztwa";
import { fetchPowiaty } from "../../api/Shared/fetchPowiaty";
import { fetchGminy } from "../../api/Shared/fetchGminy";
import { fetchObreby } from "../../api/Shared/fetchObreby";
import { fetchCheckFieldId } from "../../api/Shared/fetchCheckFieldId";
import { Col, Form, Row, Spinner } from "react-bootstrap";

interface ICropFormLandFieldProps {
  dzialka: IAgriculturalLand;
  onUpdateField: (field: string, velue: string | boolean) => void;
  onRemove: () => void;
  removeButtonDisable: boolean;
  setTerytIsIncorrect: React.Dispatch<
    React.SetStateAction<boolean | undefined>
  >;
}

export const CropFormLandField: React.FC<ICropFormLandFieldProps> = ({
  dzialka,
  onUpdateField,
  onRemove,
  removeButtonDisable,
  setTerytIsIncorrect,
}) => {
  const [wojewodztwa, setWojewodztwa] = useState<PartOfTeryt[]>([]);
  const [powiaty, setPowiaty] = useState<PartOfTeryt[]>([]);
  const [gminy, setGminy] = useState<PartOfTeryt[]>([]);
  const [obreby, setObreby] = useState<PartOfTeryt[]>([]);
  const [loading, setLoading] = useState<string | undefined>(undefined);
  const [loadingId, setLoadingId] = useState<string | undefined>(undefined);

  useEffect(() => {
    fetchWojewodztwa().then(setWojewodztwa);
  }, []);

  useEffect(() => {
    if (dzialka.teryt.length === 2)
      fetchPowiaty(dzialka.teryt).then(setPowiaty);
  }, [dzialka.teryt.substring(0, 2)]);

  useEffect(() => {
    if (dzialka.teryt.length === 4) fetchGminy(dzialka.teryt).then(setGminy);
  }, [dzialka.teryt.substring(2, 4)]);

  useEffect(() => {
    if (dzialka.teryt.length >= 8) {
      setObreby([]);
      fetchObreby(dzialka.teryt, setLoading).then(setObreby);
    }
  }, [dzialka.teryt.substring(4, 8)]);

  useEffect(() => {
    const checkId = setTimeout(() => {
      checkFieldId(dzialka.identyfikatorDzialki);
    }, 1000);

    return () => clearTimeout(checkId);
  }, [dzialka.identyfikatorDzialki]);

  useEffect(() => {
    checkTeryt();
  }, [dzialka.teryt]);

  const checkFieldId = async (value: string) => {
    if (value.length > 14) {
      const isValid = fetchCheckFieldId(value, setLoadingId);
      onUpdateField("czyPoprawna", await isValid);
    }
  };

  const checkTeryt = () => {
    if (
      gminy.find((e) => e.kodTeryt === dzialka.teryt)?.kodTeryt != undefined
    ) {
      setTerytIsIncorrect(false);
    } else {
      setTerytIsIncorrect(true);
    }
  };

  const handleTypeFieldId = async (value: string) => {
    onUpdateField("identyfikatorDzialki", value);

    if (value.length < 13) {
      onUpdateField("kodObrebu", "");
      onUpdateField("numerDzialki", "");
    } else {
      onUpdateField("kodObrebu", value.substring(0, 13));
      onUpdateField("obreb", "");
      onUpdateField("numerDzialki", value.substring(14));
    }

    if (value.length < 8) {
      onUpdateField("teryt", "");
    } else if (value.length >= 8 && value.substring(0, 8) != dzialka.teryt) {
      fetchWojewodztwa().then(setWojewodztwa);
      fetchPowiaty(value.substring(0, 2)).then(setPowiaty);
      fetchGminy(value.substring(0, 4)).then(setGminy);
      onUpdateField("teryt", value.substring(0, 8));
    }
  };

  return (
    <>
      <Row>
        <Col xl="4">
          <Form.Label className="teryt-label">Województwo</Form.Label>
          <Form.Select
            value={dzialka.teryt.substring(0, 2)}
            onChange={(e) => {
              onUpdateField("teryt", e.target.value);
              onUpdateField("identyfikatorDzialki", e.target.value);
            }}
          >
            <option value="">Wybierz województwo</option>
            {wojewodztwa.map((wojewodztwo) => (
              <option key={wojewodztwo.kodTeryt} value={wojewodztwo.kodTeryt}>
                {wojewodztwo.nazwa}
              </option>
            ))}
          </Form.Select>
        </Col>

        <Col xl="4">
          <Form.Label className="teryt-label">Powiat</Form.Label>
          <Form.Select
            value={dzialka.teryt.substring(0, 4)}
            onChange={(e) => {
              onUpdateField("teryt", e.target.value);
              onUpdateField("identyfikatorDzialki", e.target.value);
            }}
          >
            <option value="">Wybierz powiat</option>
            {powiaty.map((powiat) => (
              <option key={powiat.kodTeryt} value={powiat.kodTeryt}>
                {powiat.nazwa}
              </option>
            ))}
          </Form.Select>
        </Col>

        <Col xl="4">
          <Form.Label className="teryt-label">Gmina</Form.Label>
          <Form.Select
            value={dzialka.teryt}
            onChange={(e) => {
              onUpdateField("teryt", e.target.value);
              onUpdateField("identyfikatorDzialki", e.target.value);
            }}
          >
            <option value="">Wybierz gminę</option>
            {gminy.map((gmina) => (
              <option key={gmina.kodTeryt} value={gmina.kodTeryt}>
                {gmina.nazwa}
              </option>
            ))}
          </Form.Select>
        </Col>
        <Col xs="10" xl="7">
          <Form.Label className="teryt-label">Obręb</Form.Label>

          <Form.Select
            value={dzialka.kodObrebu}
            onChange={(e) => {
              const selectedObreb = obreby.find(
                (obreb) => obreb.kodTeryt === e.target.value,
              );
              if (selectedObreb) {
                onUpdateField("kodObrebu", selectedObreb.kodTeryt);
                onUpdateField("obreb", selectedObreb.nazwa);
                onUpdateField("identyfikatorDzialki", selectedObreb.kodTeryt);
              }
            }}
          >
            <option value="">Wybierz obręb</option>
            {obreby.map((obreb) => (
              <option key={obreb.kodTeryt} value={obreb.kodTeryt}>
                {obreb.nazwa}
              </option>
            ))}
          </Form.Select>
        </Col>
        <Col xs="2" xl="1" className="d-flex align-items-end pb-1">
          {loading && (
            <Spinner animation="border" role="status">
              <span className="visually-hidden">Loading...</span>
            </Spinner>
          )}
        </Col>
        <Col xl="4">
          <Form.Label className="teryt-label">Numer działki</Form.Label>
          <Form.Control
            type="text"
            value={dzialka.numerDzialki}
            onChange={(e) => {
              onUpdateField("numerDzialki", e.target.value);
              onUpdateField(
                "identyfikatorDzialki",
                dzialka.kodObrebu + "." + e.target.value,
              );
            }}
          />
        </Col>
      </Row>
      <Row>
        <Col sm="6">
          <Form.Label className="teryt-label">Identyfikator działki</Form.Label>
          <Form.Control
            type="text"
            value={dzialka.identyfikatorDzialki}
            onChange={(e) => {
              handleTypeFieldId(e.target.value);
            }}
          />
        </Col>
        <Col sm="3" className="d-flex align-items-end pb-1">
          {loadingId && (
            <Spinner animation="border" role="status">
              <span className="visually-hidden">Loading...</span>
            </Spinner>
          )}
          <div
            className={
              dzialka.czyPoprawna ? "crop-id-valid" : "crop-id-invalid"
            }
          >
            {loadingId ? "" : dzialka.czyPoprawna ? "POPRAWNA" : "NIEPOPRAWNA"}
          </div>
        </Col>
        <Col sm="12" md="3" className="d-flex align-items-end my-sm-2 my-md-0">
          <button
            className="btn-delete-land m-0"
            type="button"
            onClick={onRemove}
            disabled={removeButtonDisable}
          >
            Usuń działkę
          </button>
        </Col>
      </Row>
    </>
  );
};
