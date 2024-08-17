import { BrowserRouter, Route, Routes } from "react-router-dom";
import { AdminPanelMainPage } from "./pages/AdminPanelMainPage";
import { CropKindForm } from "./pages/CropKindForm";
import { CropKindData } from "./pages/CropKindData";
import { LivestockKindData } from "./pages/LivestockKindData";
import { LivestockKindForm } from "./pages/LivestockKindForm";

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
      </Routes>
    </BrowserRouter>
  );
}

export default App;
