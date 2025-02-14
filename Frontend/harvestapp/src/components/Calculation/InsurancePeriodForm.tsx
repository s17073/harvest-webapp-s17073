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
import { Message } from "../Shared/Message";
import { Loading } from "../Shared/Loading";
import * as yup from "yup";

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
  const navigate = useNavigate();
  const { id } = useParams<{ id: string }>();
  const [message, setMessage] = useState<string | undefined>(undefined);
  const [isLoadingApk, setIsLoadingApk] = useState<boolean>(false);
  const [isLoadingDate, setIsLoadingDate] = useState<boolean>(false);
  const [errors, setErrors] = useState<any>({});

  const validationSchema = yup.object().shape({
    dateFrom: yup.string().required("Data jest wymagana"),
    apkQuestions: yup.array().of(
      yup.object().shape({
        odpowiedz: yup.boolean().required("Odpowiedź na pytanie jest wymagana"),
      }),
    ),
  });

  useEffect(() => {
    setIsLoadingApk(true);

    const fetchData = async () => {
      const apkQuestions = await fetchApkQuestions();
      setData((prevData) => ({
        ...prevData,
        apkQuestions: apkQuestions,
      }));
      setIsLoadingApk(false);
    };
    fetchData();
  }, []);

  useEffect(() => {
    setIsLoadingDate(true);
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
      setIsLoadingDate(false);
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

    try {
      await validationSchema.validate(data, { abortEarly: false });
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
          setMessage(`${Date.now()} Wystąpił błąd, spróbuj ponownie później.`);
        }
      }
    } catch (err) {
      if (err instanceof yup.ValidationError) {
        const fieldErrors: any = {};
        err.inner.forEach((error) => {
          if (error.path) {
            fieldErrors[error.path] = error.message;
          }
        });
        setErrors(fieldErrors);
        console.log(errors);
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
                    {(errors.dateFrom as string) && (
                      <span className="error-message">
                        {errors.dateFrom as string}
                      </span>
                    )}
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
                          required={true}
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
                      {errors?.apkQuestions &&
                        errors.apkQuestions[1]?.odpowiedz && (
                          <span className="error-message">
                            {errors.apkQuestions[1].odpowiedz}
                          </span>
                        )}

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
        {(isLoadingApk || isLoadingDate) && <Loading />}
        <Message key={message} message={message} />
      </div>
    </>
  );
};
