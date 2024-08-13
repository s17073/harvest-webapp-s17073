import { BrowserRouter as Router, Route, Routes, Link } from "react-router-dom";
import { AdminPanelCropKindAdd } from "./pages/AdminPanelCropKindAdd";
import { AdminPanelCropKindData } from "./pages/AdminPanelCropKindData";

function App() {
  return (
    <Router>
      <h2>
        <Link to="/cropkinddata">EDYTUJ UPRAWY</Link>
      </h2>
      <h2>
        <Link to="/cropkindadd">DODAJ UPRAWĘ</Link>
      </h2>
      <Routes>
        <Route path="/cropkinddata" element={<AdminPanelCropKindData />} />
        <Route path="/cropkindadd" element={<AdminPanelCropKindAdd />} />
      </Routes>
    </Router>
  );
}

export default App;
