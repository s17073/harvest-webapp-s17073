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

function App() {
  return (
    <BrowserRouter>
      <Routes>
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
        <Route path="/admin" element={<AdminPanelMainPage />} />
        <Route path="/admin/cropkind" element={<CropKindData />} />
        <Route path="/admin/cropkind/upsert" element={<CropKindForm />} />
        <Route path="/admin/cropkind/upsert/:id" element={<CropKindForm />} />
        <Route path="/admin/livestockkind" element={<LivestockKindData />} />
        <Route
          path="/admin/livestockkind/upsert"
          element={<LivestockKindForm />}
        />
        <Route
          path="/admin/livestockkind/upsert/:id"
          element={<LivestockKindForm />}
        />
        <Route path="/admin/cover" element={<CoverData />} />
        <Route path="/admin/cover/upsert" element={<CoverForm />} />
        <Route path="/admin/cover/upsert/:id" element={<CoverForm />} />
        <Route path="/admin/soilclass" element={<SoilClassData />} />
        <Route path="/admin/soilclass/upsert" element={<SoilClassForm />} />
        <Route path="/admin/soilclass/upsert/:id" element={<SoilClassForm />} />
        <Route path="/admin/apk" element={<ApkData />} />
        <Route path="/admin/apk/upsert" element={<ApkForm />} />
        <Route path="/admin/apk/upsert/:id" element={<ApkForm />} />
        <Route
          path="/admin/insurancecompany"
          element={<InsuranceCompanyData />}
        />
        <Route path="/admin/agent" element={<AgentData />} />
        <Route path="/admin/agent/upsert" element={<AgentForm />} />
        <Route path="/admin/agent/upsert/:id" element={<AgentForm />} />
        <Route path="/admin/cropkindvariety" element={<CropVarietyData />} />
        <Route
          path="/admin/cropkindvariety/upsert"
          element={<CropVarietyFrom />}
        />
        <Route
          path="/admin/cropkindvariety/upsert/:id"
          element={<CropVarietyFrom />}
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
