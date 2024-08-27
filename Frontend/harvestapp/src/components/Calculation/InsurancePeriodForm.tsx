import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { IApk } from "../../interfaces/IApk";
import { fetchApkQuestions } from "../../api/Shared/fetchApkQuestion";
import { handleAddInsurancePeriod } from "../../api/Calculation/handleAddInsurancePeriod";
import {
  IApkCalculation,
  IStepInsurancePeriod,
} from "../../interfaces/IStepInsurancePeriod";

interface IInsurancePeriodData {
  dateFrom: string;
  dateTo: string;
  apkQuestions: IApk[];
}

export const InsurancePeriodForm: React.FC = () => {
  const [data, setData] = useState<IInsurancePeriodData>({
    dateFrom: String(new Date().toISOString().slice(0, 10)),
    dateTo: String(new Date().toISOString().slice(0, 10)),
    apkQuestions: [],
  });
  const [error, setError] = useState<string | undefined>(undefined);
  const navigate = useNavigate();
  const { id } = useParams<{ id: string }>();

  useEffect(() => {
    const fetchData = async () => {
      const apkQuestions = await fetchApkQuestions();
      setData((prevData) => ({
        ...prevData,
        apkQuestions: apkQuestions,
      }));
    };
    fetchData();
  }, []);

  const setDates = (dateFrom: string) => {
    const newDateTo = new Date(dateFrom);
    newDateTo.setFullYear(newDateTo.getFullYear() + 1);

    setData({
      ...data,
      dateFrom: dateFrom,
      dateTo: newDateTo.toISOString().slice(0, 10),
    });
  };

  const setChangeApkResponse = (questionId: number, response: boolean) => {
    const updateApkQuestions = data.apkQuestions.map((q) =>
      q.id === questionId ? { ...q, odpowiedz: response } : q,
    );

    setData({
      ...data,
      apkQuestions: updateApkQuestions,
    });
  };

  const handleGoBack = () => {
    navigate("/");
  };

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    const apkToAdd: IApkCalculation[] = data.apkQuestions.map((apk) => ({
      idApk: apk.id,
      apkOdpowiedz: apk.odpowiedz ?? false,
    }));

    const insurancePeriodData: IStepInsurancePeriod = {
      dataPoczatkuOchrony: new Date(data.dateFrom),
      dataKoncaOchrony: new Date(data.dateTo),
      apk: apkToAdd,
    };

    if (id) {
      const idCalculation = parseInt(id);
      try {
        await handleAddInsurancePeriod(idCalculation, insurancePeriodData);
        navigate(`/calculation/${id}/personaldata`);
      } catch (e) {
        setError("Nie udało się wysłać danych");
      }
    }
  };

  return (
    <>
      <div>
        <h1>okres ubezpieczenia</h1>
      </div>
      <div>
        <form onSubmit={handleSubmit}>
          <div>Sezon zimowy, sezon letni</div>
          <div>
            <label>Data rozpoczęcia ochrony:</label>
            <input
              type="date"
              value={data.dateFrom}
              onChange={(e) => setDates(e.target.value)}
            />
          </div>
          <div>
            <label>Data końca ochrony:</label>
            <input type="date" value={data.dateTo} readOnly={true} />
          </div>
          <div>Analiza potrzeb klienta</div>
          <div>
            {data.apkQuestions.map((q) => {
              return (
                <div key={q.id}>
                  <label>{q.pytanie}</label>
                  <input
                    type="radio"
                    id={`question-${q.id}-true`}
                    name={`question-${q.id}`}
                    value={"Tak"}
                    checked={q.odpowiedz === true}
                    onChange={() => setChangeApkResponse(q.id, true)}
                  />
                  <label htmlFor={`question-${q.id}-true`}>Tak</label>
                  <input
                    type="radio"
                    id={`question-${q.id}-false`}
                    name={`question-${q.id}`}
                    value={"Nie"}
                    checked={q.odpowiedz === false}
                    onChange={() => setChangeApkResponse(q.id, false)}
                  />
                  <label htmlFor={`question-${q.id}-false`}>Nie</label>
                  <div>{q.odpowiedz ? q.komunikat : null}</div>
                </div>
              );
            })}
            <button type="button" onClick={handleGoBack}>
              Wstecz
            </button>
            <button type="submit">Dalej</button>
          </div>
        </form>
        <div>{error && error}</div>
      </div>
    </>
  );
};
