import DictionaryTable from "../../components/Dictionaries/DictionaryTable";

export const LivestockKindData: React.FC = () => (
  <DictionaryTable
    title="ZWIERZĘTA"
    fetchApiName="livestockkind"
    deleteApiName="livestockkind"
    columns={[
      { key: "nazwaZwierzecia", label: "Nazwa zwierzecia" },
      { key: "taryfa", label: "Taryfa" },
      {
        key: "wartoscRynkowa",
        label: "Wartość rynkowa",
        render: (value) => `${value} zł`,
      },
      {
        key: "wartoscMax",
        label: "Wartość maksymalna",
        render: (value) => `${value} zł`,
      },
      {
        key: "czyAktywna",
        label: "Status",
        render: (value) => (value ? "AKTYWNA" : "NIEAKTYWNA"),
      },
    ]}
  />
);
