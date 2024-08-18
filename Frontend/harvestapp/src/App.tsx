import { BrowserRouter, Route, Routes } from "react-router-dom";
import { AdminPanelMainPage } from "./pages/AdminPanelMainPage";
import { CropKindForm } from "./pages/CropKindForm";
import { CropKindData } from "./pages/CropKindData";
import { LivestockKindData } from "./pages/LivestockKindData";
import { LivestockKindForm } from "./pages/LivestockKindForm";
import { CoverData } from "./pages/CoverData";
import { CoverForm } from "./pages/CoverForm";
import { SoilClassData } from "./pages/SoilClassData";
import { SoilClassForm } from "./pages/SoilClassForm";
import { ApkData } from "./pages/ApkData";
import { ApkForm } from "./pages/ApkForm";
import { InsuranceCompanyData } from "./pages/InsuraceCompanyData";

function App() {
  return (
    <BrowserRouter>
      <Routes>
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
      </Routes>
    </BrowserRouter>
  );
}

export default App;
