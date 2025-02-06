import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import * as yup from "yup";
import { Address } from "./Address";
import { Teryt } from "./Teryt";
import { CropList } from "./CropList";
import { fetchDictionaryDataById } from "../../api/Dictionaries/fetchDictionaryDataById";
import { handleDictionaryUpsert } from "../../api/Dictionaries/handleDictionaryUpsert";
import { Button, Col, Form, Row } from "react-bootstrap";

export interface IFormSchema<T> {
  name: keyof T;
  type:
    | "text"
    | "textarea"
    | "radio"
    | "marketValue"
    | "maxValue"
    | "isActive"
    | "number"
    | "season"
    | "teryt"
    | "crop"
    | "address";
  label?: string;
  options?: string[];
  placeholder?: string;
  required?: boolean;
}

interface IDictionaryFormProps<T> {
  apiEndpoint: string;
  initialData: T;
  fields: IFormSchema<T>[];
  additionalValidationSchema: yup.ObjectSchema<any>;
}

export const DictionaryForm = <T extends {}>({
  apiEndpoint,
  initialData,
  fields,
  additionalValidationSchema,
}: IDictionaryFormProps<T>) => {
  const [data, setData] = useState<T>(initialData);
  const [errors, setErrors] = useState<any>({});
  const [announcement, setAnnouncement] = useState<string | null>(null);
  const { id } = useParams();
  const navigate = useNavigate();

  const keyExists = (key: keyof T): boolean => {
    return key in data;
  };

  const baseValidationSchema = yup.object().shape({
    ...(keyExists("wartoscRynkowa" as keyof T) && {
      wartoscRynkowa: yup
        .number()
        .required()
        .integer()
        .min(1, "wartość rynkowa musi być powyżej 0"),
    }),
    ...(keyExists("wartoscMax" as keyof T) && {
      wartoscMax: yup
        .number()
        .typeError("Amount must be a number")
        .transform((value) => (Number.isNaN(value) ? null : value))
        .integer()
        .min(1, "wartość maksymalna musi być powyżej 0")
        .notRequired(),
    }),
    ...(keyExists("taryfa" as keyof T) && {
      taryfa: yup.string().required("Wybierz taryfę"),
    }),
    ...(keyExists("czyAktywna" as keyof T) && {
      czyAktywna: yup.bool(),
    }),
  });

  const validationSchema = baseValidationSchema.concat(
    additionalValidationSchema,
  );

  useEffect(() => {
    if (id !== undefined) {
      fetchDictionaryDataById(
        parseInt(id),
        apiEndpoint,
        setAnnouncement,
        setData,
      );
    }
  }, [id, apiEndpoint]);

  const handleOnChange = (field: keyof T, value: any) => {
    setData({ ...data, [field]: value });
  };

  const handleOnBlur = (field: keyof T) => {
    const newErrors = { ...errors };
    delete newErrors[field];
    setErrors(newErrors);
  };

  const sendUpsertRequest = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    try {
      await validationSchema.validate(data, { abortEarly: false });

      const apiUrl = id !== undefined ? `${apiEndpoint}/${id}` : apiEndpoint;
      const method = id !== undefined ? "PUT" : "POST";

      const message = await handleDictionaryUpsert(event, apiUrl, method, data);
      setAnnouncement(message);

      navigate(`../${apiEndpoint}`, {
        state: { stateAnnouncement: message },
      });
    } catch (err) {
      if (err instanceof yup.ValidationError) {
        const fieldErrors: any = {};
        err.inner.forEach((error) => {
          if (error.path) {
            fieldErrors[error.path] = error.message;
          }
        });
        setErrors(fieldErrors);
      }
    }
  };

  const getMaxValuePlaceholder = () => {
    const marketValueField = fields.find(
      (field) => field.type === "marketValue",
    );

    if (marketValueField) {
      const marketValue = data[marketValueField.name];

      if (typeof marketValue === "number" && !isNaN(marketValue)) {
        return Math.round(marketValue * 1.2).toString();
      }
    }
  };

  return (
    <div className="admin-upsert-space">
      <Form onSubmit={sendUpsertRequest}>
        <div className="admin-upsert-fields">
          {fields.map((field) => {
            const value = data[field.name];
            return (
              <Form.Group
                as={Row}
                className="mb-3 mt-3"
                controlId={field.name as string}
                // className="admin-upsert-field-space"
              >
                <Form.Label column xl="2">
                  {field.label}:
                </Form.Label>
                {field.type === "text" && (
                  <Col xl="10">
                    <Form.Control
                      type="text"
                      value={value as string}
                      onChange={(e) =>
                        handleOnChange(field.name, e.target.value)
                      }
                      placeholder={field.placeholder}
                      onBlur={() => handleOnBlur(field.name)}
                    />
                    {errors[field.name as string] && (
                      <span className="error-message">
                        {errors[field.name as string]}
                      </span>
                    )}
                  </Col>
                )}
                {field.type === "textarea" && (
                  <Col xl="10">
                    <Form.Control
                      as="textarea"
                      value={value as string}
                      style={{ height: "7rem" }}
                      onChange={(e) =>
                        handleOnChange(field.name, e.target.value)
                      }
                      placeholder={field.placeholder}
                      onBlur={() => handleOnBlur(field.name)}
                    />
                    {errors[field.name as string] && (
                      <span className="error-message">
                        {errors[field.name as string]}
                      </span>
                    )}
                  </Col>
                )}
                {field.type === "number" && (
                  <Col xl="10">
                    <Form.Control
                      type="number"
                      value={value as number}
                      onChange={(e) =>
                        handleOnChange(field.name, parseFloat(e.target.value))
                      }
                      onBlur={() => handleOnBlur(field.name)}
                      placeholder={field.placeholder}
                      required={field.required}
                    />
                    {errors[field.name as string] && (
                      <span className="error-message">
                        {errors[field.name as string]}
                      </span>
                    )}
                  </Col>
                )}
                {field.type === "isActive" && (
                  <Col xl="10">
                    <Form.Check
                      className="admin-form-checkbox"
                      type="checkbox"
                      checked={value as boolean}
                      onChange={() =>
                        handleOnChange(field.name, !(value as boolean))
                      }
                    />
                    {errors[field.name as string] && (
                      <span className="error-message">
                        {errors[field.name as string]}
                      </span>
                    )}
                  </Col>
                )}

                {field.type === "radio" && field.options && (
                  <Col xl="10">
                    {/* <div> */}
                    {field.options.map((option) => (
                      // <label key={option}>
                      <Form.Check
                        key={option}
                        type="radio"
                        label={option}
                        value={option}
                        checked={value === option}
                        onChange={() => handleOnChange(field.name, option)}
                      />
                      // {option}
                      // </label>
                    ))}
                    {/* </div> */}
                    {errors[field.name as string] && (
                      <span className="error-message">
                        {errors[field.name as string]}
                      </span>
                    )}
                  </Col>
                )}
                {field.type === "season" && (
                  <Col xl="10">
                    <div className="admin-season-container">
                      <Form.Check
                        id={`${field.name as string}-wiosna`}
                        className="d-inline-block me-3 admin-season-radio"
                        type="radio"
                        label="WIOSNA"
                        name={field.name as string}
                        value={"WIOSNA"}
                        checked={value === "WIOSNA"}
                        onChange={() => handleOnChange(field.name, "WIOSNA")}
                      />
                      <Form.Check
                        id={`${field.name as string}-zima`}
                        className="d-inline-block me-3 admin-season-radio"
                        type="radio"
                        label="ZIMA"
                        name={field.name as string}
                        value={"ZIMA"}
                        checked={value === "ZIMA"}
                        onChange={() => handleOnChange(field.name, "ZIMA")}
                      />
                      <Form.Check
                        id={`${field.name as string}-caloroczna`}
                        className="d-inline-block me-3 admin-season-radio"
                        type="radio"
                        label="CAŁOROCZNA"
                        name={field.name as string}
                        value={"CAŁOROCZNA"}
                        checked={value === "CAŁOROCZNA"}
                        onChange={() =>
                          handleOnChange(field.name, "CAŁOROCZNA")
                        }
                      />
                    </div>
                    {errors[field.name as string] && (
                      <span className="error-message">
                        {errors[field.name as string]}
                      </span>
                    )}
                  </Col>
                )}
                {field.type === "marketValue" && (
                  <Col xl="10">
                    <Form.Control
                      type="text"
                      inputMode="numeric"
                      pattern="\d*"
                      value={value as number}
                      onChange={(e) =>
                        e.target.value
                          ? handleOnChange(
                              field.name,
                              parseFloat(e.target.value),
                            )
                          : handleOnChange(field.name, 0)
                      }
                      onBlur={() => handleOnBlur(field.name)}
                      placeholder={field.placeholder}
                      required={field.required}
                    />
                    {errors[field.name as string] && (
                      <span className="error-message">
                        {errors[field.name as string]}
                      </span>
                    )}
                  </Col>
                )}
                {field.type === "maxValue" && (
                  <Col xl="10">
                    <Form.Control
                      type="text"
                      inputMode="numeric"
                      pattern="\d*"
                      value={value as number}
                      onChange={(e) =>
                        !Number.isNaN(Math.round(parseFloat(e.target.value)))
                          ? handleOnChange(
                              field.name,
                              Math.round(parseFloat(e.target.value)),
                            )
                          : handleOnChange(field.name, "")
                      }
                      onBlur={() => handleOnBlur(field.name)}
                      placeholder={getMaxValuePlaceholder()}
                    />
                    {errors[field.name as string] && (
                      <span className="error-message">
                        {errors[field.name as string]}
                      </span>
                    )}
                  </Col>
                )}
                {field.type === "address" && (
                  <Address
                    addressData={value as any}
                    onChange={(newAddressData) =>
                      handleOnChange(field.name, newAddressData)
                    }
                    errors={errors}
                  />
                )}
                {field.type === "teryt" && (
                  <Teryt
                    terytCode={value as string}
                    onChange={(terytData) =>
                      handleOnChange(field.name, terytData)
                    }
                    errors={errors[field.name]}
                  />
                )}
                {field.type === "crop" && (
                  <CropList
                    cropId={value as number}
                    onChange={(cropList) =>
                      handleOnChange(field.name, cropList)
                    }
                    errors={errors[field.name]}
                  />
                )}
              </Form.Group>
            );
          })}
        </div>
        <div className="d-flex justify-content-between admin-upsert-buttons">
          {/* <button
            className="admin-upsert-cancel"
            type="button"
            onClick={() => {
              navigate(-1);
            }}
          >
            Anuluj
          </button>
          <button type="submit" className="admin-upsert-submit">
            {id !== undefined ? "Edytuj" : "Dodaj"}
          </button> */}
          <Button
            variant="secondary"
            className="admin-upsert-cancel"
            onClick={() =>
              id !== undefined ? navigate(`../${apiEndpoint}`) : navigate(-1)
            }
          >
            Anuluj
          </Button>
          <Button
            variant="primary"
            type="submit"
            className="admin-upsert-submit"
          >
            {id !== undefined ? "Edytuj" : "Dodaj"}
          </Button>
        </div>
      </Form>
    </div>
  );
};
