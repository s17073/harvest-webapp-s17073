import { Link } from "react-router-dom";
import { AdminPanelNav } from "../components/AdminPanelNav";

export const AdminPanelMainPage: React.FC = () => {
  return (
    <>
      <AdminPanelNav />
      <div className="background">
        <div className="admin-content-space">
          <div className="admin-title-container">
            <h1>Słowniki</h1>
          </div>
          <div>
            <h2>
              <Link to="/admin/cropkind">EDYTUJ UPRAWY</Link>
            </h2>
            <h2>
              <Link to="/admin/cropkind/upsert">DODAJ UPRAWĘ</Link>
            </h2>
          </div>
          <div>
            <h2>
              <Link to="/admin/livestockkind">EDYTUJ ZWIERZĘTA</Link>
            </h2>
            <h2>
              <Link to="/admin/livestockkind/upsert">DODAJ ZWIERZĘ</Link>
            </h2>
          </div>
          <div>
            <h2>
              <Link to="/admin/cover">EDYTUJ OCHRONY</Link>
            </h2>
            <h2>
              <Link to="/admin/cover/upsert">DODAJ OCHRONĘ</Link>
            </h2>
          </div>
          <div>
            <h2>
              <Link to="/admin/soilclass">EDYTUJ KLASY GLEBY</Link>
            </h2>
            <h2>
              <Link to="/admin/soilclass/upsert">DODAJ KLASĘ GLEBY</Link>
            </h2>
          </div>
          <div>
            <h2>
              <Link to="/admin/apk">EDYTUJ PYTANIA APK</Link>
            </h2>
            <h2>
              <Link to="/admin/apk/upsert">DODAJ PYTANIE APK</Link>
            </h2>
          </div>
          <div>
            <h2>
              <Link to="/admin/agent">EDYTUJ AGENTÓW</Link>
            </h2>
            <h2>
              <Link to="/admin/agent/upsert">DODAJ AGENTA</Link>
            </h2>
          </div>
          <div>
            <h2>
              <Link to="/admin/insurancecompany">PODGLĄD UBEZPIECZYCIELI</Link>
            </h2>
          </div>
        </div>
      </div>
    </>
  );
};
