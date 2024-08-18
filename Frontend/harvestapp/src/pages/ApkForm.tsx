import { AdminPanelNav } from "../components/AdminPanelNav";
import { DictionaryForm, IFormSchema } from "../components/DictionaryForm";
import * as yup from "yup";

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
            <h1>DODAJ PYTANIE</h1>
          </div>
          <DictionaryForm<IApkDict>
            apiEndpoint="/api/apk"
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
