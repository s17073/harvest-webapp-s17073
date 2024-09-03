import { BrowserRouter, Route, Routes } from "react-router-dom";
import { AdminPanelMainPage } from "./pages/Dictionaries/AdminPanelMainPage";
import { CropKindForm } from "./pages/Dictionaries/CropKindForm";
import { CropKindData } from "./pages/Dictionaries/CropKindData";
import { LivestockKindData } from "./pages/Dictionaries/LivestockKindData";
import { LivestockKindForm } from "./pages/Dictionaries/LivestockKindForm";
import { CoverData } from "./pages/Dictionaries/CoverData";
import { CoverForm } from "./pages/Dictionaries/CoverForm";
import { SoilClassData } from "./pages/Dictionaries/SoilClassData";
import { SoilClassForm } from "./pages/Dictionaries/SoilClassForm";
import { ApkData } from "./pages/Dictionaries/ApkData";
import { ApkForm } from "./pages/Dictionaries/ApkForm";
import { InsuranceCompanyData } from "./pages/Dictionaries/InsuraceCompanyData";
import { AgentData } from "./pages/Dictionaries/AgentData";
import { AgentForm } from "./pages/Dictionaries/AgentForm";
import { CropVarietyData } from "./pages/Dictionaries/CropVarietyData";
import { CropVarietyFrom } from "./pages/Dictionaries/CropVarietyForm";
import { CalculationForm } from "./pages/CalculationForm";
import { InsurancePeriodForm } from "./components/Calculation/InsurancePeriodForm";
import { PersonalDataForm } from "./components/Calculation/PersonalDataForm";
import { MainPage } from "./pages/MainPage";
import { CropsFormTable } from "./components/Calculation/CropsFormTable";
import { CropForm } from "./components/Calculation/CropForm";
import { LivestockFormTable } from "./components/Calculation/LivestockFromTable";
import { LivestockForm } from "./components/Calculation/LivestockForm";
import { AdminPanelLogin } from "./pages/Dictionaries/AdminPanelLogin";
import { AdminAuthentication } from "./components/Dictionaries/AdminAuthentication";

const AdminRoutes = () => (
  <AdminAuthentication>
    <Routes>
      <Route path="" element={<AdminPanelMainPage />} />
      <Route path="cropkind" element={<CropKindData />} />
      <Route path="cropkind/upsert" element={<CropKindForm />} />
      <Route path="cropkind/upsert/:id" element={<CropKindForm />} />
      <Route path="livestockkind" element={<LivestockKindData />} />
      <Route path="livestockkind/upsert" element={<LivestockKindForm />} />
      <Route path="livestockkind/upsert/:id" element={<LivestockKindForm />} />
      <Route path="cover" element={<CoverData />} />
      <Route path="cover/upsert" element={<CoverForm />} />
      <Route path="cover/upsert/:id" element={<CoverForm />} />
      <Route path="soilclass" element={<SoilClassData />} />
      <Route path="soilclass/upsert" element={<SoilClassForm />} />
      <Route path="soilclass/upsert/:id" element={<SoilClassForm />} />
      <Route path="apk" element={<ApkData />} />
      <Route path="apk/upsert" element={<ApkForm />} />
      <Route path="apk/upsert/:id" element={<ApkForm />} />
      <Route path="insurancecompany" element={<InsuranceCompanyData />} />
      <Route path="agent" element={<AgentData />} />
      <Route path="agent/upsert" element={<AgentForm />} />
      <Route path="agent/upsert/:id" element={<AgentForm />} />
      <Route path="cropkindvariety" element={<CropVarietyData />} />
      <Route path="cropkindvariety/upsert" element={<CropVarietyFrom />} />
      <Route path="cropkindvariety/upsert/:id" element={<CropVarietyFrom />} />
    </Routes>
  </AdminAuthentication>
);

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/admin/*" element={<AdminRoutes />} />
        <Route path="/admin/login" element={<AdminPanelLogin />} />

        <Route path="" element={<MainPage />} />
        <Route
          path="calculation/:id/insuranceperiod"
          element={<CalculationForm CalculationStep={InsurancePeriodForm} />}
        />
        <Route
          path="calculation/:id/personaldata"
          element={<CalculationForm CalculationStep={PersonalDataForm} />}
        />
        <Route
          path="calculation/:id/crops"
          element={<CalculationForm CalculationStep={CropsFormTable} />}
        />
        <Route
          path="calculation/:id/crop"
          element={<CalculationForm CalculationStep={CropForm} />}
        />
        <Route
          path="calculation/:id/crop/:cropid"
          element={<CalculationForm CalculationStep={CropForm} />}
        />
        <Route
          path="calculation/:id/livestock"
          element={<CalculationForm CalculationStep={LivestockFormTable} />}
        />
        <Route
          path="calculation/:id/livestock/add"
          element={<CalculationForm CalculationStep={LivestockForm} />}
        />
        <Route
          path="calculation/:id/livestock/:livestockid"
          element={<CalculationForm CalculationStep={LivestockForm} />}
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
