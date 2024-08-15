import { BrowserRouter, Route, Routes } from "react-router-dom";
import { AdminPanelCropKindData } from "./pages/AdminPanelCropKindData";
import { AdminPanelCropKindAdd } from "./pages/AdminPanelCropKindAdd";
import { AdminPanelMainPage } from "./pages/AdminPanelMainPage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/admin" element={<AdminPanelMainPage />} />
        <Route path="/admin/cropkind" element={<AdminPanelCropKindData />} />
        <Route path="/admin/cropkind/add" element={<AdminPanelCropKindAdd />} />
        <Route
          path="/admin/cropkind/add/:id"
          element={<AdminPanelCropKindAdd />}
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
