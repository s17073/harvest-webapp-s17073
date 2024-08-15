import { useEffect, useState } from "react";
import { handleDictionaryAdd } from "../api/handleDictionaryAdd";
import { fetchDictionaryDataById } from "../api/fetchDictionaryDataById";
import { ICropKindDataDict } from "../interfaces/ICropKindDataDict";

interface AddFormProps {
  id?: number;
  isEdit?: boolean;
}

export const CropKindAddForm: React.FC<AddFormProps> = ({ id, isEdit }) => {
  const [nazwaUprawy, setNazwaUprawy] = useState("");
  const [taryfa, setTaryfa] = useState("");
  const [czyAktywna, setCzyAktywna] = useState(true);
  const [wartoscRynkowa, setWartoscRynkowa] = useState<number>(0);
  const [wartoscMax, setWartoscMax] = useState<number | undefined>(undefined);
  const [cropKind, setCropKind] = useState<ICropKindDataDict>();
  const [announcement, setAnnouncement] = useState<string | null>(null);

  useEffect(() => {
    if (isEdit === true && id !== undefined) {
      id !== undefined
        ? fetchDictionaryDataById(
            id,
            "/api/cropkind",
            setAnnouncement,
            setCropKind,
          )
        : setAnnouncement(`Nie udało się pobrać uprawy o id: ${id}`);
    }
  }, [isEdit, id]);

  const addCropKindData = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const apiUrl = isEdit ? `/api/cropkind/${id}` : "/api/cropkind";
    const method = isEdit ? "PUT" : "POST";

    handleDictionaryAdd(
      event,
      apiUrl,
      method,
      {
        nazwaUprawy,
        taryfa,
        czyAktywna,
        wartoscRynkowa,
        ...(wartoscMax ? { wartoscMax } : {}),
      },
      setAnnouncement,
    );
  };

  return (
    <div>
      <form onSubmit={addCropKindData}>
        <div>
          <label>Nazwa uprawy:</label>
          <input
            defaultValue={nazwaUprawy}
            type="text"
            onChange={(e) => setNazwaUprawy(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Taryfa:</label>
          <div>
            <input
              type="radio"
              value="WIOSNA"
              checked={taryfa === "WIOSNA"}
              onChange={() => setTaryfa("WIOSNA")}
            />
            <label>WIOSNA</label>
            <input
              type="radio"
              value="ZIMA"
              checked={taryfa === "ZIMA"}
              onChange={() => setTaryfa("ZIMA")}
            />
            <label>ZIMA</label>
            <input
              type="radio"
              value="CAŁOROCZNA"
              checked={taryfa === "CAŁOROCZNA"}
              onChange={() => setTaryfa("CAŁOROCZNA")}
            />
            <label>CAŁOROCZNA</label>
          </div>
        </div>
        <div>
          <label>Czy Aktywna:</label>
          <input
            type="checkbox"
            checked={czyAktywna}
            onChange={() => setCzyAktywna(!czyAktywna)}
          />
        </div>
        <div>
          <label>Wartość rynkowa:</label>
          <input
            type="number"
            defaultValue={wartoscRynkowa}
            onChange={(e) => setWartoscRynkowa(parseFloat(e.target.value))}
            required
          />
        </div>
        <div>
          <label>Wartość maksymalna:</label>
          <input
            type="number"
            defaultValue={wartoscMax}
            placeholder={
              wartoscRynkowa ? (wartoscRynkowa * 1.2).toString() : undefined
            }
            onChange={(e) => setWartoscMax(parseFloat(e.target.value))}
          />
        </div>
        <button type="submit">DODAJ</button>
      </form>
      {announcement && <p>{announcement}</p>}
    </div>
  );
};
