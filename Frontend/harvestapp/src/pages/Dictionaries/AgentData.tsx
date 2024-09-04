import DictionaryTable from "../../components/Dictionaries/DictionaryTable";

export const AgentData: React.FC = () => (
  <DictionaryTable
    title="AGENCI"
    fetchApiName="agent"
    deleteApiName="agent"
    columns={[
      { key: "nazwa", label: "Agent" },
      { key: "kodAgencji", label: "Kod" },
      { key: "nip", label: "Nip" },
      { key: "krs", label: "Krs" },
      { key: "nrTel", label: "Numer kontaktowy" },
      {
        key: "czyAktywna",
        label: "Status",
        render: (value) => (value ? "AKTYWNA" : "NIEAKTYWNA"),
      },
    ]}
  />
);
