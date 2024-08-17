import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { fetchDictionaryDataById } from "../api/fetchDictionaryDataById";
import { handleDictionaryUpsert } from "../api/handleDictionaryUpsert";

export interface IFormFieldConfig<T> {
  name: keyof T;
  type:
    | "text"
    | "radio"
    | "marketValue"
    | "maxValue"
    | "checkbox"
    | "number"
    | "season";
  label: string;
  options?: string[];
  placeholder?: string;
  required?: boolean;
}

interface IDictionaryFormProps<T> {
  apiEndpoint: string;
  initialData: T;
  fields: IFormFieldConfig<T>[];
}

export const DictionaryForm = <T extends {}>({
  apiEndpoint,
  initialData,
  fields,
}: IDictionaryFormProps<T>) => {
  const [data, setData] = useState<T>(initialData);
  const [announcement, setAnnouncement] = useState<string | null>(null);
  const { id } = useParams();

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

  const sendUpsertRequest = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const apiUrl = id !== undefined ? `${apiEndpoint}/${id}` : apiEndpoint;
    const method = id !== undefined ? "PUT" : "POST";

    handleDictionaryUpsert(event, apiUrl, method, data, setAnnouncement);
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
    <div>
      <form onSubmit={sendUpsertRequest}>
        {fields.map((field) => {
          const value = data[field.name];
          return (
            <div key={field.name as string}>
              <label>{field.label}:</label>
              {field.type === "text" && (
                <input
                  type="text"
                  value={value as string}
                  onChange={(e) => handleOnChange(field.name, e.target.value)}
                  placeholder={field.placeholder}
                  required={field.required}
                />
              )}
              {field.type === "number" && (
                <input
                  type="number"
                  value={value as number}
                  onChange={(e) =>
                    handleOnChange(field.name, parseFloat(e.target.value))
                  }
                  placeholder={field.placeholder}
                  required={field.required}
                />
              )}
              {field.type === "checkbox" && (
                <input
                  type="checkbox"
                  checked={value as boolean}
                  onChange={() =>
                    handleOnChange(field.name, !(value as boolean))
                  }
                />
              )}
              {field.type === "radio" && field.options && (
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
              )}
              {field.type === "season" && (
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
                      onChange={() => handleOnChange(field.name, "CAŁOROCZNA")}
                    />
                    {"CAŁOROCZNA"}
                  </label>
                </div>
              )}
              {field.type === "marketValue" && (
                <label>
                  <input
                    type="text"
                    inputMode="numeric"
                    pattern="\d*"
                    value={value as number}
                    onChange={(e) =>
                      e.target.value
                        ? handleOnChange(field.name, parseFloat(e.target.value))
                        : handleOnChange(field.name, undefined)
                    }
                    placeholder={field.placeholder}
                    required={field.required}
                  />
                </label>
              )}
              {field.type === "maxValue" && (
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
                            Math.round(parseFloat(e.target.value)),
                          )
                        : handleOnChange(field.name, undefined)
                    }
                    placeholder={getMaxValuePlaceholder()}
                  />
                </label>
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
