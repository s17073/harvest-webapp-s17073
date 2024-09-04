import DictionaryTable from "../../components/Dictionaries/DictionaryTable";

export const SoilClassData: React.FC = () => (
  <DictionaryTable
    title="KLASY GLEBY"
    fetchApiName="soilclass"
    deleteApiName="soilclass"
    columns={[
      { key: "klasaGleby", label: "Klasa" },
      { key: "opis", label: "Opis" },
      { key: "taryfa", label: "Taryfa" },
      {
        key: "czyAktywna",
        label: "Status",
        render: (value) => (value ? "AKTYWNA" : "NIEAKTYWNA"),
      },
    ]}
  />
);
