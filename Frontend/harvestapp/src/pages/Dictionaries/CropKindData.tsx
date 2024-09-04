import DictionaryTable from "../../components/Dictionaries/DictionaryTable";

export const CropKindData: React.FC = () => (
  <DictionaryTable
    title="UPRAWY"
    fetchApiName="cropkind"
    deleteApiName="cropkind"
    columns={[
      { key: "nazwaUprawy", label: "Nazwa uprawy" },
      { key: "taryfa", label: "Taryfa" },
      {
        key: "czyAktywna",
        label: "Status",
        render: (value) => (value ? "AKTYWNA" : "NIEAKTYWNA"),
      },
      {
        key: "wartoscRynkowa",
        label: "Wartość rynkowa",
        render: (value) => `${value} zł`,
      },
      { key: "wartoscMax", label: "Wartość maksymalna" },
    ]}
  />
);
