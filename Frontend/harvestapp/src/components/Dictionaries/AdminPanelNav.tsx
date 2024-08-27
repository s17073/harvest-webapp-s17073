import { Link } from "react-router-dom";

export const AdminPanelNav: React.FC = () => {
  return (
    <nav className="admin-nav">
      <Link to="/admin" className="admin-nav-text admin-nav-main-text">
        PANEL ADMINISTRATORA
      </Link>
      <div className="admin-nav-text">Wyloguj się</div>
    </nav>
  );
};
