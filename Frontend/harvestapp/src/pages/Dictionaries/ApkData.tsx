import DictionaryTable from "../../components/Dictionaries/DictionaryTable";

export const ApkData: React.FC = () => (
  <DictionaryTable
    title="PYTANIA APK"
    fetchApiName="apk"
    deleteApiName="apk"
    columns={[
      { key: "pytanie", label: "Pytanie" },
      { key: "komunikat", label: "Komunikat" },
      {
        key: "czyAktywna",
        label: "Status",
        render: (value) => (value ? "AKTYWNA" : "NIEAKTYWNA"),
      },
    ]}
  />
);
