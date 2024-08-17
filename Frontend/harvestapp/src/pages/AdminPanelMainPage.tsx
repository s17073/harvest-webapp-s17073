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
        </div>
      </div>
    </>
  );
};
