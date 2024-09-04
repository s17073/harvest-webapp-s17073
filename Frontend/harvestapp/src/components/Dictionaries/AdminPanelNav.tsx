import { Link } from "react-router-dom";

export const AdminPanelNav: React.FC = () => {
  const removeToken = () => {
    localStorage.removeItem("token");
  };

  return (
    <nav className="admin-nav">
      <Link to="/admin" className="admin-nav-text admin-nav-main-text">
        PANEL ADMINISTRATORA
      </Link>
      <Link to="/admin/login" className="admin-nav-text" onClick={removeToken}>
        Wyloguj się
      </Link>
    </nav>
  );
};
