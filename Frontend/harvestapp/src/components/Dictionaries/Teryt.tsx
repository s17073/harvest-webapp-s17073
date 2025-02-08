import React, { useState, useEffect } from "react";
import { fetchWojewodztwa } from "../../api/Shared/fetchWojewodztwa";
import { fetchPowiaty } from "../../api/Shared/fetchPowiaty";
import { fetchGminy } from "../../api/Shared/fetchGminy";
import { PartOfTeryt } from "../../interfaces/PartOfTeryt";
import { Col, Form, Row } from "react-bootstrap";

interface IterytData {
  wojewodztwo: string;
  powiat: string;
  gmina: string;
}

interface TerytProps {
  terytCode: string;
  onChange: (newTerytData: any) => void;
  errors?: any;
}

export const Teryt: React.FC<TerytProps> = ({
  terytCode,
  onChange,
  errors,
}) => {
  const [wojewodztwa, setWojewodztwa] = useState<PartOfTeryt[]>([]);
  const [powiaty, setPowiaty] = useState<PartOfTeryt[]>([]);
  const [gminy, setGminy] = useState<PartOfTeryt[]>([]);
  const [terytData, setTerytData] = useState<IterytData>({
    wojewodztwo: "",
    powiat: "",
    gmina: "",
  });

  const fetchTerytData = async () => {
    if (terytCode.length === 8) {
      setTerytData({
        wojewodztwo: terytCode.substring(0, 2),
        powiat: terytCode.substring(0, 4),
        gmina: terytCode,
      });
    }
  };

  useEffect(() => {
    fetchTerytData();
  }, [terytCode]);

  useEffect(() => {
    fetchWojewodztwa().then(setWojewodztwa);
  }, []);

  useEffect(() => {
    if (terytData.wojewodztwo) {
      fetchPowiaty(terytData.wojewodztwo).then(setPowiaty);
    }
  }, [terytData.wojewodztwo]);

  useEffect(() => {
    if (terytData.powiat) {
      fetchGminy(terytData.powiat).then(setGminy);
    }
  }, [terytData.wojewodztwo, terytData.powiat]);

  const handleFieldChange = (field: string, value: string) => {
    let newTerytData: IterytData = { ...terytData, [field]: value };

    if (field === "wojewodztwo") {
      newTerytData = { wojewodztwo: value, powiat: "", gmina: "" };
      setPowiaty([]);
      setGminy([]);
    }

    if (field === "powiat") {
      newTerytData = { ...newTerytData, powiat: value, gmina: "" };
      setGminy([]);
    }

    setTerytData(newTerytData);

    //   const newTerytData: IterytData = { ...terytData, [field]: value };
    // setTerytData(newTerytData);

    if (field === "gmina") {
      onChange(value);
    } else {
      onChange("");
    }
  };

  return (
    <div>
      <Row>
        <Col xl={2}></Col>
        <Form.Group as={Col} sm="4" xl="3" controlId="wojewodztwo">
          <Form.Label className="teryt-label">Województwo</Form.Label>
          <Form.Select
            value={terytData.wojewodztwo}
            onChange={(e) => handleFieldChange("wojewodztwo", e.target.value)}
          >
            <option value="">Wybierz województwo</option>
            {wojewodztwa.map((wojewodztwo) => (
              <option key={wojewodztwo.kodTeryt} value={wojewodztwo.kodTeryt}>
                {wojewodztwo.nazwa}
              </option>
            ))}
          </Form.Select>
          {errors?.wojewodztwo && (
            <span className="error-message">{errors.wojewodztwo}</span>
          )}
        </Form.Group>
        <Form.Group as={Col} sm="4" xl="3" controlId="powiat">
          <Form.Label className="teryt-label">Powiat</Form.Label>
          <Form.Select
            value={terytData.powiat}
            onChange={(e) => handleFieldChange("powiat", e.target.value)}
            disabled={!terytData.wojewodztwo}
          >
            <option value="">Wybierz powiat</option>
            {powiaty.map((powiat) => (
              <option key={powiat.kodTeryt} value={powiat.kodTeryt}>
                {powiat.nazwa}
              </option>
            ))}
          </Form.Select>
          {errors?.powiat && (
            <span className="error-message">{errors.powiat}</span>
          )}
        </Form.Group>
        <Form.Group as={Col} sm="4" xl="4" controlId="gmina">
          <Form.Label className="teryt-label">Gmina</Form.Label>
          <Form.Select
            value={terytData.gmina}
            onChange={(e) => handleFieldChange("gmina", e.target.value)}
            disabled={!terytData.powiat}
          >
            <option value="">Wybierz gminę</option>
            {gminy.map((gmina) => (
              <option key={gmina.kodTeryt} value={gmina.kodTeryt}>
                {gmina.nazwa}
              </option>
            ))}
          </Form.Select>
          {errors?.gmina && (
            <span className="error-message">{errors.gmina}</span>
          )}
        </Form.Group>
      </Row>
    </div>
  );
};
