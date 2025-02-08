import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { IApk } from "../../interfaces/IApk";
import { fetchApkQuestions } from "../../api/Shared/fetchApkQuestion";
import { handleAddInsurancePeriod } from "../../api/Calculation/handleAddInsurancePeriod";
import {
  IApkCalculation,
  IStepInsurancePeriod,
} from "../../interfaces/IStepInsurancePeriod";
import { fetchInsurancePeriodData } from "../../api/Calculation/fetchInsurancePeriodData";
import { Col, Form, Row } from "react-bootstrap";
import BottomBar from "../Shared/BottomBar";

interface IInsurancePeriodData {
  dateFrom: string;
  dateTo: string;
  apkQuestions: IApk[];
}

export const InsurancePeriodForm: React.FC = () => {
  const [data, setData] = useState<IInsurancePeriodData>({
    dateFrom: String(""),
    dateTo: String(""),
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

  useEffect(() => {
    const fetchData = async () => {
      if (id && data.apkQuestions.length > 0) {
        const insurancePeriodData = await fetchInsurancePeriodData(
          parseInt(id),
        );

        if (insurancePeriodData) {
          const setApkQuestions = data.apkQuestions.map((q) => {
            const setResponse = insurancePeriodData.apk.find(
              (resp) => resp.id === q.id,
            );
            return {
              ...q,
              odpowiedz: setResponse ? setResponse.odpowiedz : undefined,
            };
          });

          setData(() => ({
            dateFrom: insurancePeriodData.dataPoczatkuOchrony,
            dateTo: insurancePeriodData.dataKoncaOchrony,
            apkQuestions: setApkQuestions,
          }));
        }
      }
    };
    fetchData();
  }, [id, data.apkQuestions.length]);

  const setDates = (dateFrom: string) => {
    const newDateTo = new Date(dateFrom);
    newDateTo.setFullYear(newDateTo.getFullYear() + 1);
    newDateTo.setDate(newDateTo.getDate() - 1);

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
      <Row>
        <Col>
          <div className="admin-title-container">
            <h1>Okres ubezpieczenia</h1>
          </div>
        </Col>
      </Row>
      <div>
        <Form onSubmit={handleSubmit}>
          <div className="admin-upsert-fields">
            <div className="section-heading">
              <span className="d-none">Sezon zimowy</span>
              <span>Sezon letni</span>
            </div>
            <Row className="form-indent mb-3 mt-3">
              <Col lg="12" className="mb-3">
                <Row>
                  <Form.Label column lg="3">
                    Data rozpoczęcia ochrony:
                  </Form.Label>
                  <Col lg="3">
                    <Form.Control
                      type="date"
                      value={data.dateFrom}
                      onChange={(e) => setDates(e.target.value)}
                      min={new Date().toISOString().split("T")[0]}
                    />
                  </Col>
                </Row>
              </Col>
              <Col lg="12">
                <Row>
                  <Form.Label column lg="3">
                    Data końca ochrony:
                  </Form.Label>
                  <Col lg="3">
                    <Form.Control
                      type="date"
                      value={data.dateTo}
                      readOnly
                      disabled
                    />
                  </Col>
                </Row>
              </Col>
            </Row>
            <Row className="mb-3 mt-3">
              <div className="section-heading">Analiza potrzeb klienta</div>
            </Row>
            {data.apkQuestions.map((q) => {
              return (
                <Row className="form-indent mb-3 mt-3">
                  <div key={q.id}>
                    <Row>
                      <Form.Label>{q.pytanie}</Form.Label>
                    </Row>
                    <Row>
                      <Col
                        lg="2"
                        className="col-2 d-flex align-items-center gap-2"
                      >
                        <Form.Check
                          type="radio"
                          id={`question-${q.id}-true`}
                          name={`question-${q.id}`}
                          value={"Tak"}
                          checked={q.odpowiedz === true}
                          onChange={() => setChangeApkResponse(q.id, true)}
                        />
                        <Form.Label
                          htmlFor={`question-${q.id}-true`}
                          className="mb-0"
                        >
                          Tak
                        </Form.Label>
                      </Col>
                      <Col
                        lg="2"
                        className="col-2 d-flex align-items-center gap-2"
                      >
                        <Form.Check
                          type="radio"
                          id={`question-${q.id}-false`}
                          name={`question-${q.id}`}
                          value={"Nie"}
                          checked={q.odpowiedz === false}
                          onChange={() => setChangeApkResponse(q.id, false)}
                        />
                        <Form.Label
                          htmlFor={`question-${q.id}-false`}
                          className="mb-0"
                        >
                          Nie
                        </Form.Label>
                      </Col>
                      <Col lg="8">
                        <div>{q.odpowiedz ? q.komunikat : null}</div>
                      </Col>
                    </Row>
                  </div>
                </Row>
              );
            })}
          </div>
          <BottomBar
            button1={{
              label: "ANULUJ",
              className: "admin-upsert-cancel",
              onClick: () => handleGoBack(),
            }}
            button2={{
              label: "DALEJ",
              className: "admin-upsert-submit",
              onClick: () => handleSubmit,
            }}
          />
        </Form>
        <div>{error && error}</div>
      </div>
    </>
  );
};
