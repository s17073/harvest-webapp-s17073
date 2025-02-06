import React from "react";
import { Col, FloatingLabel, Form, Row } from "react-bootstrap";

interface AddressProps {
  addressData: {
    kodPocztowy: string;
    miejscowosc: string;
    ulica: string;
    numerDomu: string;
    numerMieszkania?: string;
  };
  onChange: (newAddressData: any) => void;
  errors?: any;
}

export const Address: React.FC<AddressProps> = ({
  addressData,
  onChange,
  errors,
}) => {
  const handleFieldChange = (field: string, value: string) => {
    onChange({ ...addressData, [field]: value });
  };

  return (
    <div>
      <Row>
        <Col xl={2}></Col>
        <FloatingLabel
          as={Col}
          sm={4}
          xl={2}
          label="Kod pocztowy"
          className="mb-3"
          contolId="kodPocztowy"
        >
          <Form.Control
            type="text"
            placeholder="Kod pocztowy"
            value={addressData.kodPocztowy}
            onChange={(e) => handleFieldChange("kodPocztowy", e.target.value)}
          />

          {errors["addressData.kodPocztowy"] && (
            <span className="error-message">
              {errors["addressData.kodPocztowy"]}
            </span>
          )}
        </FloatingLabel>
        <FloatingLabel
          as={Col}
          sm={8}
          xl={8}
          label="Miejscowość"
          className="mb-3"
          contolId="miejscowosc"
        >
          <Form.Control
            type="text"
            placeholder="Miejscowość"
            value={addressData.miejscowosc}
            onChange={(e) => handleFieldChange("miejscowosc", e.target.value)}
          />
          {errors["addressData.miejscowosc"] && (
            <span className="error-message">
              {errors["addressData.miejscowosc"]}
            </span>
          )}
        </FloatingLabel>
      </Row>
      <Row>
        <Col xl={2}></Col>
        <FloatingLabel
          as={Col}
          sm={6}
          xl={6}
          label="Ulica"
          className="mb-3"
          controlId="ulica"
        >
          <Form.Control
            type="text"
            placeholder="Ulica"
            value={addressData.ulica}
            onChange={(e) => handleFieldChange("ulica", e.target.value)}
          />
          {errors["addressData.ulica"] && (
            <span className="error-message">{errors["addressData.ulica"]}</span>
          )}
        </FloatingLabel>
        <FloatingLabel
          as={Col}
          sm={3}
          xl={2}
          label="Numer domu"
          className="mb-3"
          controlId="numerDomu"
        >
          <Form.Control
            type="text"
            placeholder="Numer domu"
            value={addressData.numerDomu}
            onChange={(e) => handleFieldChange("numerDomu", e.target.value)}
          />
          {errors["addressData.numerDomu"] && (
            <span className="error-message">
              {errors["addressData.numerDomu"]}
            </span>
          )}
        </FloatingLabel>
        <FloatingLabel
          as={Col}
          sm={3}
          xl={2}
          label="Numer mieszkania"
          className="mb-3"
          controlId="numerMieszkania"
        >
          <Form.Control
            type="text"
            placeholder="Numer mieszkania"
            value={addressData.numerMieszkania}
            onChange={(e) =>
              handleFieldChange("numerMieszkania", e.target.value)
            }
          />
          {errors["addressData.numerMieszkania"] && (
            <span className="error-message">
              {errors["addressData.numerMieszkania"]}
            </span>
          )}
        </FloatingLabel>
      </Row>
    </div>
  );
};
