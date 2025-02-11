import { Link } from "react-router-dom";

export const MainNav: React.FC = () => {
  return (
    <nav className="admin-nav">
      <Link to="/" className="admin-nav-text admin-nav-main-text">
        Strona Główna
      </Link>
    </nav>
  );
};
