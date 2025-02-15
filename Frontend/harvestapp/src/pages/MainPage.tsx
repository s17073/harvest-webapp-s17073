import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { startNewCalculation } from "../api/Calculation/startNewCalculation";
import { Loading } from "../components/Shared/Loading";
import { Col, Row } from "react-bootstrap";
import { Message } from "../components/Shared/Message";
import { handleAddPersonalData } from "../api/Calculation/handleAddPersonalData";
import { IStepPersonalData } from "../interfaces/IStepPersonalData";
import { startNewCalculationWithUserData } from "../api/Calculation/startNewCalculationWithUserData";

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

export const MainPage: React.FC = () => {
  const [log, setLog] = useState<boolean>(false);
  const [message, setMessage] = useState<string | undefined>(undefined);
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const navigate = useNavigate();
  const [profile, setProfile] = useState<string | null>(
    localStorage.getItem("userName"),
  );

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token !== null) {
      setLog(true);
    }
  }, []);

  const handleStartCalculation = async () => {
    setIsLoading(true);

    const timeout = setTimeout(() => {
      setIsLoading(false);
      setMessage(
        `${Date.now()} Serwer nie odpowiada. Srpóbuj ponownie później.`,
      );
    }, 10000);

    try {
      const calculationId: number = await startNewCalculation();

      setIsLoading(false);

      if (calculationId === undefined) {
        setMessage(`${Date.now()} Wystąpił błąd, spróbuj ponownie później.`);
      } else {
        if (profile !== null) {
          await startNewCalculationWithUserData(calculationId, profile);
        }

        navigate(`/calculation/${calculationId}/insuranceperiod`);
      }
    } catch (e) {
      clearTimeout(timeout);
      setIsLoading(false);
      setMessage(
        `${Date.now()} Wystąpił błąd podczas próby rozpoczęcia kalkulacji`,
      );
    }
  };

  return (
    <>
      <div className="admin-title-container">
        <h1>HARVEST</h1>
      </div>
      <div className="admin-title-container">
        <h2>Porównywarka ubezpieczeń upraw rolnych i zwierząt</h2>
      </div>
      <Row className="my-5 mx-0 p-3 d-flex justify-content-center">
        <Col md={6} className="mx-0 px-0 d-flex justify-content-center">
          <button
            onClick={handleStartCalculation}
            disabled={isLoading}
            className="btn-start"
          >
            Porównaj oferty ubezpieczenia!
          </button>
        </Col>
      </Row>
      {isLoading && <Loading />}
      <Message key={message} message={message} />
    </>
  );
};
