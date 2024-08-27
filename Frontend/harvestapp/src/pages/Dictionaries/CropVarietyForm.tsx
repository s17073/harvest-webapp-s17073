import React from "react";

import * as yup from "yup";
import { AdminPanelNav } from "../../components/Dictionaries/AdminPanelNav";
import {
  DictionaryForm,
  IFormSchema,
} from "../../components/Dictionaries/DictionaryForm";

interface ICropVarietyDict {
  idUprawa: number;
  nazwaGatunku: string;
  czyAktywna: boolean;
}

const additionalValidationSchema = yup.object().shape({
  nazwaGatunku: yup
    .string()
    .matches(
      /^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ\s\–\-\(\)\:\.\+\=\*\%\,\?]+$/,
      "nazwa ochrony może zawierać tylko litery i spacje",
    )
    .required("nazwa ochrony is required")
    .min(3, "nazwa ochrony nie może zawierać mniej niż 3 znaki")
    .max(30, "nazwa ochrony nie może zawierać więcej niż 30 znakow"),
});

export const CropVarietyFrom: React.FC = () => {
  const cropVarietyFields: IFormSchema<ICropVarietyDict>[] = [
    {
      name: "nazwaGatunku",
      type: "text",
      label: "Nazwa Gatunku",
      required: true,
    },
    { name: "idUprawa", type: "crop", label: "Uprawa", required: true },
    { name: "czyAktywna", type: "isActive", label: "Czy Aktywna" },
  ];

  return (
    <>
      <AdminPanelNav />
      <div className="background">
        <div className="admin-content-space">
          <div className="admin-title-container">
            <h1>DODAJ GATUNEK</h1>
          </div>
          <DictionaryForm<ICropVarietyDict>
            apiEndpoint="cropkindvariety"
            initialData={{
              idUprawa: 0,
              nazwaGatunku: "",
              czyAktywna: true,
            }}
            fields={cropVarietyFields}
            additionalValidationSchema={additionalValidationSchema}
          />
        </div>
      </div>
    </>
  );
};
