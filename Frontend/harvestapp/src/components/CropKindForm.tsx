import { DictionaryForm, IFormFieldConfig } from "./DictionaryForm";

interface ICropKindDict {
  nazwaUprawy: string;
  taryfa: string;
  czyAktywna: boolean;
  wartoscRynkowa: number;
  wartoscMax: number | string;
}

export const CropKindForm: React.FC = () => {
  const fields: IFormFieldConfig<ICropKindDict>[] = [
    { name: "nazwaUprawy", type: "text", label: "Nazwa", required: true },
    {
      name: "taryfa",
      type: "season",
      label: "Taryfa",
    },
    { name: "czyAktywna", type: "checkbox", label: "Czy Aktywna" },
    {
      name: "wartoscRynkowa",
      type: "marketValue",
      label: "Wartość rynkowa",
      required: true,
    },
    {
      name: "wartoscMax",
      type: "maxValue",
      label: "Wartość maksymalna",
    },
  ];

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
      fields={fields}
    />
  );
};
