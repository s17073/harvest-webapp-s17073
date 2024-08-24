import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

interface IApk {
  id: number;
  pytanie: string;
  odpowiedz: boolean | undefined;
  komunikat: string;
}

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
  const navigate = useNavigate();

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
    console.log("idź wstecz");
  };

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    navigate("/calculation/personaldata");
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
      </div>
    </>
  );
};

const fetchApkQuestions = async (): Promise<IApk[]> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await fetch(`${apiUrl}/apk/questions`);
  const dane = await response.json();
  return dane;
};
