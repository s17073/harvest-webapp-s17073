import * as yup from "yup";
import {
  DictionaryForm,
  IFormSchema,
} from "../../components/Dictionaries/DictionaryForm";
import { AdminPanelNav } from "../../components/Dictionaries/AdminPanelNav";
import { useParams } from "react-router-dom";

interface IApkDict {
  pytanie: string;
  komunikat: string;
  czyAktywna: boolean;
}

const additionalValidationSchema = yup.object().shape({
  pytanie: yup
    .string()
    .matches(
      /^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ\s\–\-\(\)\:\.\+\=\*\%\,\?]+$/,
      "nazwa ochrony może zawierać tylko litery i spacje",
    )
    .required("nazwa ochrony is required")
    .min(3, "nazwa ochrony nie może zawierać mniej niż 3 znaki")
    .max(30, "nazwa ochrony nie może zawierać więcej niż 30 znakow"),
  komunikat: yup
    .string()
    .matches(
      /^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ\s\–\-\(\)\:\.\+\=\*\%\,\?]+$/,
      "nazwa ochrony może zawierać tylko litery i spacje",
    )
    .required("nazwa ochrony is required")
    .min(3, "nazwa ochrony nie może zawierać mniej niż 3 znaki")
    .max(30, "nazwa ochrony nie może zawierać więcej niż 30 znakow"),
});

export const ApkForm: React.FC = () => {
  const { id } = useParams();
  const apkFields: IFormSchema<IApkDict>[] = [
    {
      name: "pytanie",
      type: "text",
      label: "pytanie",
      required: true,
    },
    {
      name: "komunikat",
      type: "text",
      label: "komunikat",
      required: true,
    },
    { name: "czyAktywna", type: "isActive", label: "Czy Aktywna" },
  ];

  return (
    <>
      <AdminPanelNav />
      <div className="background">
        <div className="admin-content-space">
          <div className="admin-title-container">
            <h1>{id !== undefined ? "EDYTUJ PYTANIE" : "DODAJ PYTANIE"}</h1>
          </div>
          <DictionaryForm<IApkDict>
            apiEndpoint="apk"
            initialData={{
              pytanie: "",
              komunikat: "",
              czyAktywna: true,
            }}
            fields={apkFields}
            additionalValidationSchema={additionalValidationSchema}
          />
        </div>
      </div>
    </>
  );
};
