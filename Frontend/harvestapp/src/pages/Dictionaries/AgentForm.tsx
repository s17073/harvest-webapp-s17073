import React from "react";
import * as yup from "yup";
import {
  DictionaryForm,
  IFormSchema,
} from "../../components/Dictionaries/DictionaryForm";
import { AdminPanelNav } from "../../components/Dictionaries/AdminPanelNav";
import { useParams } from "react-router-dom";

interface IAgentDict {
  nazwa: string;
  kodAgencji: string;
  nip: string;
  krs: string;
  nrTel: string;
  czyAktywna: boolean;
  liczbaPosrednikow: number;
  teryt: string;
  addressData: {
    kodPocztowy: string;
    miejscowosc: string;
    ulica: string;
    numerDomu: string;
    numerMieszkania: string;
  };
}

const additionalValidationSchema = yup.object().shape({
  nazwa: yup
    .string()
    // .matches(
    //   /^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ\s\–\-\(\)\:\.\+\=\*\%\,\?]+$/,
    //   "nazwa ochrony może zawierać tylko litery i spacje",
    // )
    .required("pole jest wymagane")
    .min(3, "pole musi zawierać przynajmniej 3 znaki")
    .max(50, "pole może zawierać maksymalnie 50 znaków"),
  kodAgencji: yup
    .string()
    .matches(/^A\d{4}$/, "wpisz właściwy kod agencji")
    .required("pole jest wymagane")
    .min(5, "kod agencji ma 5 znaków")
    .max(5, "kod agencji ma 5 znaków"),
  nip: yup
    .string()
    .required("pole jest wymagane")
    .matches(/^\d{10}$/, "wpisz właściwy numer nip"),
  krs: yup
    .string()
    .required("pole jest wymagane")
    .matches(/^\d{10}$/, "wpisz właściwy numer krs"),
  nrTel: yup
    .string()
    .required("pole jest wymagane")
    .matches(/^\d{9}$/, "wpisz właściwy numer telefonu"),
  liczbaPosrednikow: yup
    .number()
    .required("pole jest wymagane")
    .positive()
    .integer(),
  addressData: yup.object().shape({
    kodPocztowy: yup
      .string()
      .required("pole jest wymagane")
      .matches(/^\d{2}-\d{3}$/, "wpisz kod pocztowy we właściwym formacie"),
    miejscowosc: yup
      .string()
      .required("pole jest wymagane")
      .max(50, "miejscowość nie może mieć więcej niż 50 znaków"),
    ulica: yup
      .string()
      .required("pole jest wymagane")
      .max(60, "ulica nie może mieć więcej niż 60 znaków"),
    numerDomu: yup
      .string()
      .required("pole jest wymagane")
      .max(5, "numer domu nie może mieć więcej niż 5 znaków"),
    numerMieszkania: yup
      .string()
      .max(5, "numer mieszkania nie może mieć więcej niż 5 znaków"),
  }),
});

export const AgentForm: React.FC = () => {
  const { id } = useParams();
  const agentFields: IFormSchema<IAgentDict>[] = [
    { name: "nazwa", type: "text", label: "Nazwa", required: true },
    { name: "kodAgencji", type: "text", label: "Kod Agencji", required: true },
    { name: "nip", type: "text", label: "NIP", required: true },
    { name: "krs", type: "text", label: "KRS", required: true },
    { name: "nrTel", type: "text", label: "Numer Telefonu", required: true },
    { name: "czyAktywna", type: "isActive", label: "Czy Aktywna" },
    {
      name: "liczbaPosrednikow",
      type: "number",
      label: "Liczba Pośredników",
      required: true,
    },
    { name: "teryt", type: "teryt", label: "Dane TERYT" },
    { name: "addressData", type: "address", label: "Adres" },
  ];

  return (
    <>
      <AdminPanelNav />
      <div className="background">
        <div className="admin-content-space">
          <div className="admin-title-container">
            <h1>{id !== undefined ? "EDYTUJ AGENTA" : "DODAJ AGENTA"}</h1>
          </div>
          <DictionaryForm<IAgentDict>
            apiEndpoint="agent"
            initialData={{
              nazwa: "",
              kodAgencji: "",
              nip: "",
              krs: "",
              nrTel: "",
              czyAktywna: true,
              liczbaPosrednikow: 50,
              teryt: "",
              addressData: {
                kodPocztowy: "",
                miejscowosc: "",
                ulica: "",
                numerDomu: "",
                numerMieszkania: "",
              },
            }}
            fields={agentFields}
            additionalValidationSchema={additionalValidationSchema}
          />
        </div>
      </div>
    </>
  );
};
