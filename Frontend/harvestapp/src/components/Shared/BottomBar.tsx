import { Col, Container, Row } from "react-bootstrap";

interface IButton {
  label: string;
  className: string;
  onClick: () => void;
}

interface IButtonsProps {
  button1: IButton | undefined;
  button2: IButton | undefined;
}

const BottomBar: React.FC<IButtonsProps> = ({ button1, button2 }) => {
  return (
    <>
      <div className="bottom-bar fixed-bottom">
        <Container>
          <Row>
            <Col className="d-flex justify-content-start">
              {button1 && (
                <button className={button1.className} onClick={button1.onClick}>
                  {button1.label}
                </button>
              )}
            </Col>
            <Col className="d-flex justify-content-end">
              {button2 && (
                <button className={button2.className} onClick={button2.onClick}>
                  {button2.label}
                </button>
              )}
            </Col>
          </Row>
        </Container>
      </div>
    </>
  );
};

export default BottomBar;
