import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { startNewCalculation } from "../api/Calculation/startNewCalculation";

export const MainPage: React.FC = () => {
  const [message, setMessage] = useState<string | undefined>(undefined);
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const navigate = useNavigate();

  const handleClick = async () => {
    setIsLoading(true);

    const calculationId: number = await startNewCalculation();

    setIsLoading(false);

    if (calculationId === 0) {
      setMessage("Wystąpił błąd, spróbuj ponownie.");
    } else {
      navigate(`/calculation/${calculationId}/insuranceperiod`);
    }
  };

  return (
    <>
      <div>
        <button onClick={handleClick} disabled={isLoading}>
          Nowa Kalkulacja
        </button>
        <div>{isLoading ? "Proszę czekać..." : undefined}</div>
        <div>{message && message}</div>
      </div>
    </>
  );
};
