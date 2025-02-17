import { Col, Container, Row } from "react-bootstrap";
import { MainNav } from "../components/Calculation/MainNav";
import { InsurancePeriodForm } from "../components/Calculation/InsurancePeriodForm";
import { PersonalDataForm } from "../components/Calculation/PersonalDataForm";
import { CropForm } from "../components/Calculation/CropForm";
import { CropsFormTable } from "../components/Calculation/CropsFormTable";
import { LivestockForm } from "../components/Calculation/LivestockForm";
import { LivestockFormTable } from "../components/Calculation/LivestockFromTable";
import { Offers } from "../components/Calculation/Offers";

interface CalculationFormProps {
  CalculationStep: React.ComponentType;
}

export const CalculationForm: React.FC<CalculationFormProps> = ({
  CalculationStep,
}) => {
  return (
    <>
      <MainNav />
      <div className="background">
        <Container>
          <div className="content-space">
            <Row>
              <Col className="content-space-form" xs={12} xl={10}>
                <div className="calculation-form-section">
                  <CalculationStep />
                </div>
              </Col>
              <Col xl={2} className="d-none d-xl-block">
                <section className="calculation-steps-section">
                  <div
                    className={`steps-section-step ${
                      CalculationStep === InsurancePeriodForm ? "selected" : ""
                    }`}
                  >
                    <div className="steps-section-circle"></div>
                    <div className="steps-section-name">1. okres ubez.</div>
                  </div>
                  <div className="steps-section-separator"></div>
                  <div
                    className={`steps-section-step ${
                      CalculationStep === PersonalDataForm ? "selected" : ""
                    }`}
                  >
                    <div className="steps-section-circle"></div>
                    <div className="steps-section-name">2. dane osobowe</div>
                  </div>
                  <div className="steps-section-separator"></div>
                  <div
                    className={`steps-section-step ${
                      CalculationStep === CropsFormTable ||
                      CalculationStep === CropForm
                        ? "selected"
                        : ""
                    }`}
                  >
                    <div className="steps-section-circle"></div>
                    <div className="steps-section-name">3. uprawy</div>
                  </div>
                  <div className="steps-section-separator"></div>
                  <div
                    className={`steps-section-step ${
                      CalculationStep === LivestockForm ||
                      CalculationStep === LivestockFormTable
                        ? "selected"
                        : ""
                    }`}
                  >
                    <div className="steps-section-circle"></div>
                    <div className="steps-section-name">4. zwierzęta</div>
                  </div>
                  <div className="steps-section-separator"></div>
                  <div
                    className={`steps-section-step ${
                      CalculationStep === Offers ? "selected" : ""
                    }`}
                  >
                    <div className="steps-section-circle"></div>
                    <div className="steps-section-name">5. oferty</div>
                  </div>
                </section>
              </Col>
            </Row>
          </div>
        </Container>
      </div>
    </>
  );
};
