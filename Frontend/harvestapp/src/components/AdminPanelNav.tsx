import { Link } from "react-router-dom";

export const AdminPanelNav: React.FC = () => {
  return (
    <nav>
      <div>
        <Link to="/admin">PANEL ADMINISTRATORA</Link>
      </div>
      <div>Wyloguj się</div>
    </nav>
  );
};
