import * as yup from "yup";
import {
  DictionaryForm,
  IFormSchema,
} from "../../components/Dictionaries/DictionaryForm";
import { AdminPanelNav } from "../../components/Dictionaries/AdminPanelNav";
import { useParams } from "react-router-dom";
import { Container } from "react-bootstrap";

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
      "nazwa może zawierać tylko litery i spacje",
    )
    .required("nazwa is required")
    .min(3, "nazwa nie może zawierać mniej niż 3 znaki")
    .max(30, "nazwa nie może zawierać więcej niż 30 znakow"),
  komunikat: yup
    .string()
    .matches(
      /^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ\s\–\-\(\)\:\.\+\=\*\%\,\?]+$/,
      "nazwa może zawierać tylko litery i spacje",
    )
    .required("nazwa is required")
    .min(3, "nazwa nie może zawierać mniej niż 3 znaki")
    .max(30, "nazwa nie może zawierać więcej niż 30 znakow"),
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
        <Container>
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
        </Container>
      </div>
    </>
  );
};
