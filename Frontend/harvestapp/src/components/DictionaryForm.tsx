import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { fetchDictionaryDataById } from "../api/fetchDictionaryDataById";
import { handleDictionaryUpsert } from "../api/handleDictionaryUpsert";
import * as yup from "yup";
import { Address } from "./Address";
import { Teryt } from "./Teryt";

export interface IFormSchema<T> {
  name: keyof T;
  type:
    | "text"
    | "radio"
    | "marketValue"
    | "maxValue"
    | "isActive"
    | "number"
    | "season"
    | "teryt"
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

      handleDictionaryUpsert(event, apiUrl, method, data, setAnnouncement);
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
      <form onSubmit={sendUpsertRequest}>
        {fields.map((field) => {
          const value = data[field.name];
          return (
            <div key={field.name as string}>
              <label>{field.label}:</label>
              {field.type === "text" && (
                <>
                  <input
                    type="text"
                    value={value as string}
                    onChange={(e) => handleOnChange(field.name, e.target.value)}
                    placeholder={field.placeholder}
                    onBlur={() => handleOnBlur(field.name)}
                  />
                  {errors[field.name as string] && (
                    <span className="error-message">
                      {errors[field.name as string]}
                    </span>
                  )}
                </>
              )}
              {field.type === "number" && (
                <>
                  <input
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
                </>
              )}
              {field.type === "isActive" && (
                <>
                  <input
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
                </>
              )}

              {field.type === "radio" && field.options && (
                <>
                  <div>
                    {field.options.map((option) => (
                      <label key={option}>
                        <input
                          type="radio"
                          value={option}
                          checked={value === option}
                          onChange={() => handleOnChange(field.name, option)}
                        />
                        {option}
                      </label>
                    ))}
                  </div>
                  {errors[field.name as string] && (
                    <span className="error-message">
                      {errors[field.name as string]}
                    </span>
                  )}
                </>
              )}
              {field.type === "season" && (
                <>
                  <div>
                    <label>
                      <input
                        type="radio"
                        value={"WIOSNA"}
                        checked={value === "WIOSNA"}
                        onChange={() => handleOnChange(field.name, "WIOSNA")}
                      />
                      {"WIOSNA"}
                    </label>
                    <label>
                      <input
                        type="radio"
                        value={"ZIMA"}
                        checked={value === "ZIMA"}
                        onChange={() => handleOnChange(field.name, "ZIMA")}
                      />
                      {"ZIMA"}
                    </label>
                    <label>
                      <input
                        type="radio"
                        value={"CAŁOROCZNA"}
                        checked={value === "CAŁOROCZNA"}
                        onChange={() =>
                          handleOnChange(field.name, "CAŁOROCZNA")
                        }
                      />
                      {"CAŁOROCZNA"}
                    </label>
                  </div>
                  {errors[field.name as string] && (
                    <span className="error-message">
                      {errors[field.name as string]}
                    </span>
                  )}
                </>
              )}
              {field.type === "marketValue" && (
                <>
                  <label>
                    <input
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
                  </label>
                  {errors[field.name as string] && (
                    <span className="error-message">
                      {errors[field.name as string]}
                    </span>
                  )}
                </>
              )}
              {field.type === "maxValue" && (
                <>
                  <label>
                    <input
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
                  </label>
                  {errors[field.name as string] && (
                    <span className="error-message">
                      {errors[field.name as string]}
                    </span>
                  )}
                </>
              )}
              {field.type === "address" && (
                <Address
                  addressData={value as any}
                  onChange={(newAddressData) =>
                    handleOnChange(field.name, newAddressData)
                  }
                  errors={errors[field.name]}
                />
              )}
              {field.type === "teryt" && (
                <Teryt
                  onChange={(terytData) =>
                    handleOnChange(field.name, terytData)
                  }
                  errors={errors[field.name]}
                />
              )}
            </div>
          );
        })}
        <button type="submit">{id !== undefined ? "Edytuj" : "Dodaj"}</button>
      </form>
      {announcement && <p>{announcement}</p>}
    </div>
  );
};
