import DictionaryTable from "../../components/Dictionaries/DictionaryTable";

export const CropVarietyData: React.FC = () => (
  <DictionaryTable
    title="GATUNKI"
    fetchApiName="cropkindvariety"
    deleteApiName="cropvariety"
    columns={[
      { key: "nazwaGatunku", label: "Gatunek" },
      { key: "nazwaUprawy", label: "Uprawa" },
      { key: "taryfa", label: "Taryfa" },
      {
        key: "czyAktywna",
        label: "Status",
        render: (value) => (value ? "AKTYWNA" : "NIEAKTYWNA"),
      },
    ]}
  />
);
