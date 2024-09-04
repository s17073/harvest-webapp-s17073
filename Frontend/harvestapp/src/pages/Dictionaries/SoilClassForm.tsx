import * as yup from "yup";
import {
  DictionaryForm,
  IFormSchema,
} from "../../components/Dictionaries/DictionaryForm";
import { AdminPanelNav } from "../../components/Dictionaries/AdminPanelNav";
import { useParams } from "react-router-dom";

interface ISoilClassDict {
  klasaGleby: string;
  opis: string;
  taryfa: string;
  czyAktywna: boolean;
}

const additionalValidationSchema = yup.object().shape({
  klasaGleby: yup
    .string()
    .matches(
      /^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ\s]+$/,

      "klasa gleby może zawierać tylko litery i spacje",
    )
    .required("wpisz klasę gleby")
    .min(1, "klasa gleby musi zawierać conajmniej 1 znak")
    .max(5, "klasa gleby nie może zawierać więcej niż 5 znaków"),
  opis: yup
    .string()
    .matches(
      /^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ\s\–\-\(\)\:\.\+\=\*\%\,\?]+$/,
      "opis może zawierać tylko litery i spacje",
    )
    .notRequired(),
});

export const SoilClassForm: React.FC = () => {
  const { id } = useParams();
  const soilClassFields: IFormSchema<ISoilClassDict>[] = [
    {
      name: "klasaGleby",
      type: "text",
      label: "klasaGleby",
      required: true,
    },
    {
      name: "opis",
      type: "text",
      label: "opis",
    },
    {
      name: "taryfa",
      type: "season",
      label: "Taryfa",
    },
    { name: "czyAktywna", type: "isActive", label: "Czy Aktywna" },
  ];

  return (
    <>
      <AdminPanelNav />
      <div className="background">
        <div className="admin-content-space">
          <div className="admin-title-container">
            <h1>
              {id !== undefined ? "EDYTUJ KLASĘ GLEBY" : "DODAJ KLASĘ GLEBY"}
            </h1>
          </div>
          <DictionaryForm<ISoilClassDict>
            apiEndpoint="soilclass"
            initialData={{
              klasaGleby: "",
              opis: "",
              taryfa: "WIOSNA",
              czyAktywna: true,
            }}
            fields={soilClassFields}
            additionalValidationSchema={additionalValidationSchema}
          />
        </div>
      </div>
    </>
  );
};
