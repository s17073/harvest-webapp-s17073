import { DictionaryForm } from "./DictionaryForm";

interface ICropKindDict {
  nazwaUprawy: string;
  taryfa: string;
  czyAktywna: boolean;
  wartoscRynkowa: number;
  wartoscMax: number | string;
}

export const CropKindForm: React.FC = () => {
  const renderCropKindFields = (
    data: ICropKindDict,
    handleOnChange: (field: keyof ICropKindDict, value: any) => void,
  ) => (
    <>
      <div>
        <label>Nazwa:</label>
        <input
          value={data.nazwaUprawy}
          type="text"
          onChange={(e) => handleOnChange("nazwaUprawy", e.target.value)}
          required
        />
      </div>
      <div>
        <label>Taryfa:</label>
        <div>
          <input
            type="radio"
            value="WIOSNA"
            checked={data.taryfa === "WIOSNA"}
            onChange={() => handleOnChange("taryfa", "WIOSNA")}
          />
          <label>WIOSNA</label>
          <input
            type="radio"
            value="ZIMA"
            checked={data.taryfa === "ZIMA"}
            onChange={() => handleOnChange("taryfa", "ZIMA")}
          />
          <label>ZIMA</label>
          <input
            type="radio"
            value="CAŁOROCZNA"
            checked={data.taryfa === "CAŁOROCZNA"}
            onChange={() => handleOnChange("taryfa", "CAŁOROCZNA")}
          />
          <label>CAŁOROCZNA</label>
        </div>
      </div>
      <div>
        <label>Czy Aktywna:</label>
        <input
          type="checkbox"
          checked={data.czyAktywna}
          onChange={() => handleOnChange("czyAktywna", !data.czyAktywna)}
        />
      </div>
      <div>
        <label>Wartość rynkowa:</label>
        <input
          type="number"
          value={data.wartoscRynkowa}
          onChange={(e) =>
            e.target.value
              ? handleOnChange("wartoscRynkowa", parseFloat(e.target.value))
              : handleOnChange("wartoscRynkowa", e.target.value)
          }
          required
        />
      </div>
      <div>
        <label>Wartość maksymalna:</label>
        <input
          type="number"
          value={data.wartoscMax}
          onChange={(e) =>
            e.target.value
              ? handleOnChange("wartoscMax", parseFloat(e.target.value))
              : handleOnChange("wartoscMax", e.target.value)
          }
          placeholder={
            data.wartoscRynkowa
              ? (data.wartoscRynkowa * 1.2).toString()
              : undefined
          }
        />
      </div>
    </>
  );

  return (
    <DictionaryForm<ICropKindDict>
      apiEndpoint="/api/cropkind"
      initialData={{
        nazwaUprawy: "",
        taryfa: "WIOSNA",
        czyAktywna: true,
        wartoscRynkowa: 0,
        wartoscMax: "",
      }}
      renderFields={renderCropKindFields}
    />
  );
};
