import * as yup from "yup";
import {
  DictionaryForm,
  IFormSchema,
} from "../../components/Dictionaries/DictionaryForm";
import { AdminPanelNav } from "../../components/Dictionaries/AdminPanelNav";
import { useParams } from "react-router-dom";

interface ILivestockKindDict {
  nazwaZwierzecia: string;
  taryfa: string;
  czyAktywna: boolean;
  wartoscRynkowa: number;
  wartoscMax: number | undefined;
}

const additionalValidationSchema = yup.object().shape({
  nazwaZwierzecia: yup
    .string()
    .matches(
      /^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ\s]+$/,
      "nazwa zwierzęcia nie może zawierać tylko litery i spacje",
    )
    .required("name is required")
    .min(3, "nazwa zwierzęcia nie może zawierać mniej niż 3 znaki")
    .max(30, "nazwa zwierzęcia nie może zawierać więcej niż 30 znakow"),
});

export const LivestockKindForm: React.FC = () => {
  const { id } = useParams();
  const livestockKindFields: IFormSchema<ILivestockKindDict>[] = [
    {
      name: "nazwaZwierzecia",
      type: "text",
      label: "Nazwa",
      required: true,
    },
    {
      name: "taryfa",
      type: "season",
      label: "Taryfa",
    },
    { name: "czyAktywna", type: "isActive", label: "Czy Aktywna" },
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
    <>
      <AdminPanelNav />
      <div className="background">
        <div className="admin-content-space">
          <div className="admin-title-container">
            <h1>{id !== undefined ? "EDYTUJ ZWIERZĘ" : "DODAJ ZWIERZĘ"}</h1>
          </div>
          <DictionaryForm<ILivestockKindDict>
            apiEndpoint="livestockkind"
            initialData={{
              nazwaZwierzecia: "",
              taryfa: "WIOSNA",
              czyAktywna: true,
              wartoscRynkowa: 0,
              wartoscMax: undefined,
            }}
            fields={livestockKindFields}
            additionalValidationSchema={additionalValidationSchema}
          />
        </div>
      </div>
    </>
  );
};
