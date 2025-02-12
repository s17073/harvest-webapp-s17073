import { useEffect, useState } from "react";
import { Col, Row } from "react-bootstrap";
import { useLocation, useNavigate } from "react-router-dom";
import { fetchPolicyData } from "../../api/Calculation/fetchPolicyData";

export const PolicyConfirmation: React.FC = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const policyId = location.state?.policyData.policy.idPolisa;
  const [policyData, setPolicyData] = useState<any>(null);

  useEffect(() => {
    console.log(location.state?.policyData);
    fetchData();
  }, []);

  const fetchData = async () => {
    console.log(policyId);
    if (policyId) {
      const policyResponse = await fetchPolicyData(policyId);
      if (policyResponse) {
        setPolicyData(policyResponse);
      }
    }
  };

  return (
    <>
      <div className="admin-title-container">
        <h1>Potwierdzenie</h1>
      </div>
      <div className="my-4 mx-4">
        <Row>
          <Col>
            <h3>
              Uprzejmie informujemy, że umowa ubezpieczenia o numerze:
              <p className="my-2 section-heading">
                {policyData && policyData.numerPolisy}
              </p>{" "}
              została zawarta.
            </h3>
          </Col>
        </Row>
        <Row>
          <Col className="d-flex justify-content-end">
            <button
              className="admin-upsert-cancel"
              onClick={() => {
                navigate("/");
              }}
            >
              POWRÓT
            </button>
          </Col>
        </Row>
      </div>
    </>
  );
};
