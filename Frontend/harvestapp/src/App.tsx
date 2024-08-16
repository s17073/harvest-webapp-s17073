import { BrowserRouter, Route, Routes } from "react-router-dom";
import { AdminPanelCropKindData } from "./pages/AdminPanelCropKindData";
import { AdminPanelMainPage } from "./pages/AdminPanelMainPage";
import { AdminPanelCropKindUpsert } from "./pages/AdminPanelCropKindUpsert";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/admin" element={<AdminPanelMainPage />} />
        <Route path="/admin/cropkind" element={<AdminPanelCropKindData />} />
        <Route
          path="/admin/cropkind/upsert"
          element={<AdminPanelCropKindUpsert />}
        />
        <Route
          path="/admin/cropkind/upsert/:id"
          element={<AdminPanelCropKindUpsert />}
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
