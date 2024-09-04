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
    .matches(
      /^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ\s\–\-\(\)\:\.\+\=\*\%\,\?]+$/,
      "nazwa ochrony może zawierać tylko litery i spacje",
    )
    .required("nazwa ochrony is required")
    .min(3, "nazwa ochrony nie może zawierać mniej niż 3 znaki")
    .max(30, "nazwa ochrony nie może zawierać więcej niż 30 znakow"),
  kodAgencji: yup.string().required("Kod Agencji is required"),
  nip: yup.string().required("NIP is required"),
  krs: yup.string().required("KRS is required"),
  nrTel: yup.string().required("Numer Telefonu is required"),
  liczbaPosrednikow: yup
    .number()
    .required("Liczba Pośredników is required")
    .positive()
    .integer(),
  addressData: yup.object().shape({
    kodPocztowy: yup.string().required("Kod Pocztowy is required"),
    miejscowosc: yup.string().required("Miejscowość is required"),
    ulica: yup.string().required("Ulica is required"),
    numerDomu: yup.string().required("Numer Domu is required"),
    numerMieszkania: yup.string().required("Numer Mieszkania is required"),
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
