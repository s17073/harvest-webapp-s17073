import React, { useState, useEffect } from "react";
import { fetchWojewodztwa } from "../api/fetchWojewodztwa";
import { fetchPowiaty } from "../api/fetchPowiaty";
import { fetchGminy } from "../api/fetchGminy";
import { PartOfTeryt } from "../interfaces/PartOfTeryt";

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
    const newTerytData: IterytData = { ...terytData, [field]: value };
    setTerytData(newTerytData);
    if (field === "gmina") {
      onChange(value);
    }
  };

  return (
    <div>
      <div>
        <label>Województwo:</label>
        <select
          value={terytData.wojewodztwo}
          onChange={(e) => handleFieldChange("wojewodztwo", e.target.value)}
        >
          <option value="">Wybierz województwo</option>
          {wojewodztwa.map((wojewodztwo) => (
            <option key={wojewodztwo.kodTeryt} value={wojewodztwo.kodTeryt}>
              {wojewodztwo.nazwa}
            </option>
          ))}
        </select>
        {errors?.wojewodztwo && (
          <span className="error-message">{errors.wojewodztwo}</span>
        )}
      </div>
      <div>
        <label>Powiat:</label>
        <select
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
        </select>
        {errors?.powiat && (
          <span className="error-message">{errors.powiat}</span>
        )}
      </div>
      <div>
        <label>Gmina:</label>
        <select
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
        </select>
        {errors?.gmina && <span className="error-message">{errors.gmina}</span>}
      </div>
    </div>
  );
};
