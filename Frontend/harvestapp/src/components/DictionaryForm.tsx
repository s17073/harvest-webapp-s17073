import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { fetchDictionaryDataById } from "../api/fetchDictionaryDataById";
import { handleDictionaryUpsert } from "../api/handleDictionaryUpsert";

interface DictionaryFormProps<T> {
  apiEndpoint: string;
  initialData: T;
  renderFields: (
    data: T,
    handleInputChange: (field: keyof T, value: any) => void,
  ) => JSX.Element;
}

export const DictionaryForm = <T extends {}>({
  apiEndpoint,
  initialData,
  renderFields,
}: DictionaryFormProps<T>) => {
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

  return (
    <div>
      <form onSubmit={sendUpsertRequest}>
        {renderFields(data, handleOnChange)}
        <button>{"Wróć"}</button>
        <button type="submit">{id !== undefined ? "Edytuj" : "Dodaj"}</button>
      </form>
      {announcement && <p>{announcement}</p>}
    </div>
  );
};
