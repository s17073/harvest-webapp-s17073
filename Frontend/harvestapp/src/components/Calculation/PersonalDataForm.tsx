import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { fetchWojewodztwa } from "../../api/Shared/fetchWojewodztwa";
import { fetchPowiaty } from "../../api/Shared/fetchPowiaty";
import { fetchGminy } from "../../api/Shared/fetchGminy";
import { PartOfTeryt } from "../../interfaces/PartOfTeryt";

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

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    navigate("/calculation/crops");
  };

  const copyPolicyHolderData = () => {
    setInsured(policyHolder);
  };

  return (
    <>
      <div className="calc-form-title">
        <h1>dane osobowe</h1>
      </div>
      <div>
        <form onSubmit={handleSubmit}>
          <div className="calc-form-section-title">Dane ubezpieczającego</div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Imię</label>
            <input
              className="calc-form-field-input"
              type="text"
              value={policyHolder.imie}
              onChange={(e) =>
                setField("imie", setPolicyHolder, e.target.value)
              }
            />
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Nazwisko</label>
            <input
              className="calc-form-field-input"
              type="text"
              value={policyHolder.nazwisko}
              onChange={(e) =>
                setField("nazwisko", setPolicyHolder, e.target.value)
              }
            />
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Pesel</label>
            <input
              className="calc-form-field-input"
              type="number"
              value={policyHolder.pesel}
              onChange={(e) =>
                setField("pesel", setPolicyHolder, e.target.value)
              }
            />
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Data urodzenia</label>
            <input
              className="calc-form-field-input"
              type="date"
              value={policyHolder.dataUrodzenia}
              onChange={(e) =>
                setField("dataUrodzenia", setPolicyHolder, e.target.value)
              }
            />
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Adres e-mail</label>
            <input
              className="calc-form-field-input"
              type="email"
              value={policyHolder.adresEmail}
              onChange={(e) =>
                setField("adresEmail", setPolicyHolder, e.target.value)
              }
            />
          </div>
          <div className="calc-form-section-title">Adres do korespondencji</div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Województwo</label>
            <select
              value={policyHolder.teryt.substring(0, 2)}
              onChange={(e) =>
                setField("teryt", setPolicyHolder, e.target.value)
              }
            >
              <option value="">Wybierz województwo</option>
              {wojewodztwa.map((wojewodztwo) => (
                <option key={wojewodztwo.kodTeryt} value={wojewodztwo.kodTeryt}>
                  {wojewodztwo.nazwa}
                </option>
              ))}
            </select>
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Powiat</label>
            <select
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
            </select>
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Gmina</label>
            <select
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
            </select>
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Kod pocztowy</label>
            <input
              className="calc-form-field-input"
              type="text"
              value={policyHolder.kodPocztowy}
              onChange={(e) =>
                setField("kodPocztowy", setPolicyHolder, e.target.value)
              }
            />
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Miejscowość</label>
            <input
              className="calc-form-field-input"
              type="text"
              value={policyHolder.miejscowosc}
              onChange={(e) =>
                setField("miejscowosc", setPolicyHolder, e.target.value)
              }
            />
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Ulica</label>
            <input
              className="calc-form-field-input"
              type="text"
              value={policyHolder.ulica}
              onChange={(e) =>
                setField("ulica", setPolicyHolder, e.target.value)
              }
            />
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Numer domu</label>
            <input
              className="calc-form-field-input"
              type="text"
              value={policyHolder.numerDomu}
              onChange={(e) =>
                setField("numerDomu", setPolicyHolder, e.target.value)
              }
            />
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Numer mieszkania</label>
            <input
              className="calc-form-field-input"
              type="text"
              value={policyHolder.numerMieszkania}
              onChange={(e) =>
                setField("numerMieszkania", setPolicyHolder, e.target.value)
              }
            />
          </div>
          <div className="calc-form-section-title">Dane ubezpieczonego</div>
          <div className="copy-policy-holder" onClick={copyPolicyHolderData}>
            Takie same jak ubezpieczającego
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Imię</label>
            <input
              className="calc-form-field-input"
              type="text"
              value={insured.imie}
              onChange={(e) => setField("imie", setInsured, e.target.value)}
            />
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Nazwisko</label>
            <input
              className="calc-form-field-input"
              type="text"
              value={insured.nazwisko}
              onChange={(e) => setField("nazwisko", setInsured, e.target.value)}
            />
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Pesel</label>
            <input
              className="calc-form-field-input"
              type="number"
              value={insured.pesel}
              onChange={(e) => setField("pesel", setInsured, e.target.value)}
            />
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Data urodzenia</label>
            <input
              className="calc-form-field-input"
              type="date"
              value={insured.dataUrodzenia}
              onChange={(e) =>
                setField("dataUrodzenia", setInsured, e.target.value)
              }
            />
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Adres e-mail</label>
            <input
              className="calc-form-field-input"
              type="email"
              value={insured.adresEmail}
              onChange={(e) =>
                setField("adresEmail", setInsured, e.target.value)
              }
            />
          </div>
          <div className="calc-form-section-title">Adres do korespondencji</div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Województwo</label>
            <select
              value={insured.teryt.substring(0, 2)}
              onChange={(e) => setField("teryt", setInsured, e.target.value)}
            >
              <option value="">Wybierz województwo</option>
              {wojewodztwa.map((wojewodztwo) => (
                <option key={wojewodztwo.kodTeryt} value={wojewodztwo.kodTeryt}>
                  {wojewodztwo.nazwa}
                </option>
              ))}
            </select>
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Powiat</label>
            <select
              value={insured.teryt.substring(0, 4)}
              onChange={(e) => setField("teryt", setInsured, e.target.value)}
            >
              <option value="">Wybierz powiat</option>
              {powiatyInsurer.map((powiat) => (
                <option key={powiat.kodTeryt} value={powiat.kodTeryt}>
                  {powiat.nazwa}
                </option>
              ))}
            </select>
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Gmina</label>
            <select
              value={insured.teryt}
              onChange={(e) => setField("teryt", setInsured, e.target.value)}
            >
              <option value="">Wybierz gminę</option>
              {gminyInsurer.map((gmina) => (
                <option key={gmina.kodTeryt} value={gmina.kodTeryt}>
                  {gmina.nazwa}
                </option>
              ))}
            </select>
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Kod pocztowy</label>
            <input
              className="calc-form-field-input"
              type="text"
              value={insured.kodPocztowy}
              onChange={(e) =>
                setField("kodPocztowy", setInsured, e.target.value)
              }
            />
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Miejscowość</label>
            <input
              className="calc-form-field-input"
              type="text"
              value={insured.miejscowosc}
              onChange={(e) =>
                setField("miejscowosc", setInsured, e.target.value)
              }
            />
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Ulica</label>
            <input
              className="calc-form-field-input"
              type="text"
              value={insured.ulica}
              onChange={(e) => setField("ulica", setInsured, e.target.value)}
            />
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Numer domu</label>
            <input
              className="calc-form-field-input"
              type="text"
              value={insured.numerDomu}
              onChange={(e) =>
                setField("numerDomu", setInsured, e.target.value)
              }
            />
          </div>
          <div className="calc-form-field">
            <label className="calc-form-field-label">Numer mieszkania</label>
            <input
              className="calc-form-field-input"
              type="text"
              value={insured.numerMieszkania}
              onChange={(e) =>
                setField("numerMieszkania", setInsured, e.target.value)
              }
            />
          </div>
          <button
            className="calc-form-previous-button"
            type="button"
            onClick={handleGoBack}
          >
            Wstecz
          </button>
          <button className="calc-form-next-button" type="submit">
            Dalej
          </button>
        </form>
      </div>
    </>
  );
};
