import { Link } from "react-router-dom";
import { AdminPanelNav } from "../../components/Dictionaries/AdminPanelNav";

export const AdminPanelMainPage: React.FC = () => {
  return (
    <>
      <AdminPanelNav />
      <div className="background">
        <div className="admin-content-space">
          <div className="admin-title-container">
            <h1>Słowniki</h1>
          </div>
          <div className="admin-panel-options-container">
            <div className="admin-panel-options">
              <div className="admin-panel-options-title">
                <h2>UPRAWY</h2>
              </div>
              <div className="admin-container-buttons">
                <h3>
                  <Link to="/admin/cropkind">EDYTUJ</Link>
                </h3>
                <h3>
                  <Link to="/admin/cropkind/upsert">DODAJ</Link>
                </h3>
              </div>
            </div>
            <div className="admin-panel-options">
              <div className="admin-panel-options-title">
                <h2>ZWIERZĘTA</h2>
              </div>
              <div className="admin-container-buttons">
                <h3>
                  <Link to="/admin/livestockkind">EDYTUJ</Link>
                </h3>
                <h3>
                  <Link to="/admin/livestockkind/upsert">DODAJ</Link>
                </h3>
              </div>
            </div>
            <div className="admin-panel-options">
              <div className="admin-panel-options-title">
                <h2>OCHRONY</h2>
              </div>
              <div className="admin-container-buttons">
                <h3>
                  <Link to="/admin/cover">EDYTUJ</Link>
                </h3>
                <h3>
                  <Link to="/admin/cover/upsert">DODAJ</Link>
                </h3>
              </div>
            </div>
            <div className="admin-panel-options">
              <div className="admin-panel-options-title">
                <h2>KLASY GLEBY</h2>
              </div>
              <div className="admin-container-buttons">
                <h3>
                  <Link to="/admin/soilclass">EDYTUJ</Link>
                </h3>
                <h3>
                  <Link to="/admin/soilclass/upsert">DODAJ</Link>
                </h3>
              </div>
            </div>
            <div className="admin-panel-options">
              <div className="admin-panel-options-title">
                <h2>APK</h2>
              </div>
              <div className="admin-container-buttons">
                <h3>
                  <Link to="/admin/apk">EDYTUJ</Link>
                </h3>
                <h3>
                  <Link to="/admin/apk/upsert">DODAJ</Link>
                </h3>
              </div>
            </div>
            <div className="admin-panel-options">
              <div className="admin-panel-options-title">
                <h2>AGENCI</h2>
              </div>
              <div className="admin-container-buttons">
                <h3>
                  <Link to="/admin/agent">EDYTUJ</Link>
                </h3>
                <h3>
                  <Link to="/admin/agent/upsert">DODAJ</Link>
                </h3>
              </div>
            </div>
            <div className="admin-panel-options">
              <div className="admin-panel-options-title">
                <h2>GATUNKI UPRAW</h2>
              </div>
              <div className="admin-container-buttons">
                <h3>
                  <Link to="/admin/cropkindvariety">EDYTUJ</Link>
                </h3>
                <h3>
                  <Link to="/admin/cropkindvariety/upsert">DODAJ</Link>
                </h3>
              </div>
            </div>

            <div className="admin-panel-options">
              <div className="admin-panel-options-title">
                <h2>UBEZPIECZYCIELE</h2>
              </div>
              <div className="admin-container-buttons">
                <h3>
                  <Link to="/admin/insurancecompany">PODGLĄD</Link>
                </h3>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
