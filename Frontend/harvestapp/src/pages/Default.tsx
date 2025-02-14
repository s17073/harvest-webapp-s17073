import { Col, Container, Row } from "react-bootstrap";
import { MainNav } from "../components/Calculation/MainNav";

interface CalculationFormProps {
  Content: React.ComponentType;
}

export const Default: React.FC<CalculationFormProps> = ({ Content }) => {
  return (
    <>
      <MainNav />
      <div className="background">
        <Container>
          <div className="content-space">
            <Row>
              <Col className="content-space-form" xs={12}>
                <div className="calculation-form-section">
                  <Content />
                </div>
              </Col>
            </Row>
          </div>
        </Container>
      </div>
    </>
  );
};
