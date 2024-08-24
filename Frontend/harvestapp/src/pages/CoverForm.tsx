import { AdminPanelNav } from "../components/AdminPanelNav";
import { DictionaryForm, IFormSchema } from "../components/DictionaryForm";
import * as yup from "yup";

interface ICoverDict {
  nazwa: string;
  grupaMinisterialna: string;
  taryfa: string;
  opis: string;
  czyUprawa: boolean;
  czyZwierze: boolean;
  czyAktywna: boolean;
}

const additionalValidationSchema = yup.object().shape({
  nazwa: yup
    .string()
    .matches(
      /^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ\s]+$/,
      "nazwa ochrony może zawierać tylko litery i spacje",
    )
    .required("nazwa ochrony is required")
    .min(3, "nazwa ochrony nie może zawierać mniej niż 3 znaki")
    .max(30, "nazwa ochrony nie może zawierać więcej niż 30 znakow"),
  grupaMinisterialna: yup
    .string()
    .matches(
      /^(01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18)$/,
      "wprowadź poprawny kod grupy ministerialnej w formacie dwucyfrowym (np. 09)",
    )
    .required(),
  czyUprawa: yup.bool(),
  czyZwierze: yup.bool(),
  opis: yup
    .string()
    .matches(
      /^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ\s]*$/,
      "opis może zawierać tylko litery i spacje",
    )
    .notRequired(),
});

export const CoverForm: React.FC = () => {
  const coverFields: IFormSchema<ICoverDict>[] = [
    {
      name: "nazwa",
      type: "text",
      label: "Nazwa",
      required: true,
    },
    {
      name: "grupaMinisterialna",
      type: "text",
      label: "Grupa Ministerialna",
      required: true,
    },
    {
      name: "taryfa",
      type: "season",
      label: "Taryfa",
    },
    {
      name: "opis",
      type: "text",
      label: "Opis",
      required: true,
    },
    { name: "czyUprawa", type: "isActive", label: "Czy ochrona dotyczy upraw" },
    {
      name: "czyZwierze",
      type: "isActive",
      label: "Czy ochrona dotyczy zwierząt",
    },
    { name: "czyAktywna", type: "isActive", label: "Czy Aktywna" },
  ];

  return (
    <>
      <AdminPanelNav />
      <div className="background">
        <div className="admin-content-space">
          <div className="admin-title-container">
            <h1>DODAJ OCHRONĘ</h1>
          </div>
          <DictionaryForm<ICoverDict>
            apiEndpoint="cover"
            initialData={{
              nazwa: "",
              grupaMinisterialna: "08",
              taryfa: "WIOSNA",
              opis: "",
              czyUprawa: true,
              czyZwierze: true,
              czyAktywna: true,
            }}
            fields={coverFields}
            additionalValidationSchema={additionalValidationSchema}
          />
        </div>
      </div>
    </>
  );
};
