import DictionaryTable from "../../components/Dictionaries/DictionaryTable";

export const CoverData: React.FC = () => (
  <DictionaryTable
    title="OCHRONY"
    fetchApiName="cover"
    deleteApiName="cover"
    columns={[
      { key: "nazwa", label: "Ochrona" },
      { key: "grupaMinisterialna", label: "Grupa ministerialna" },
      { key: "taryfa", label: "Taryfa" },
      {
        key: "czyUprawa",
        label: "Czy Uprawa",
        render: (value) => (value ? "TAK" : "NIE"),
      },
      {
        key: "czyZwierze",
        label: "CzyZwierze",
        render: (value) => (value ? "TAK" : "NIE"),
      },
      {
        key: "czyAktywna",
        label: "Status",
        render: (value) => (value ? "AKTYWNA" : "NIEAKTYWNA"),
      },
    ]}
  />
);
