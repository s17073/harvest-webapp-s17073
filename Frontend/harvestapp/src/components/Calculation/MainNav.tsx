import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export const MainNav: React.FC = () => {
  const [log, setLog] = useState<boolean>(false);
  const [userName, setUserName] = useState<string | null>(
    localStorage.getItem("userName"),
  );

  const navigate = useNavigate();

  useEffect(() => {
    const checkLoginStatus = () => {
      const token = localStorage.getItem("token");
      const userType = localStorage.getItem("userType");

      if (token !== null && (userType === "USER" || userType === "AGENT")) {
        setLog(true);
      } else {
        setLog(false);
      }
    };

    checkLoginStatus();

    window.addEventListener("storage", checkLoginStatus);

    return () => {
      window.removeEventListener("storage", checkLoginStatus);
    };
  }, []);

  const removeToken = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("userType");
    localStorage.removeItem("userName");
    setLog(false);
    navigate("/");
  };

  const adminPanel = () => {
    navigate("/admin");
  };

  const calc = () => {
    navigate("/usercalc");
  };

  const pol = () => {
    navigate("/userpol");
  };

  return (
    <nav className="admin-nav">
      <Link to="/" className="admin-nav-text admin-nav-main-text">
        Strona Główna
      </Link>

      {log ? (
        <div className="h-100 admin-nav-text admin-nav-main-text ">
          <div
            onClick={adminPanel}
            className="admin-nav-text h-100 px-2 admin-nav-main-text color-white"
          >
            Panel Administratora
          </div>
          <div
            onClick={calc}
            className="admin-nav-text h-100 px-2 admin-nav-main-text color-white"
          >
            Twoje kalkulacje
          </div>
          <div
            onClick={pol}
            className="admin-nav-text h-100 px-2 admin-nav-main-text color-white"
          >
            Twoje polisy
          </div>
          <Link
            to="/profile"
            className="admin-nav-text px-2 admin-nav-main-text"
          >
            Profil
          </Link>
          {/* <Link to="/" className="admin-nav-text px-2 admin-nav-main-text">
            Kalkulacje
          </Link>
          <Link to="/" className="admin-nav-text px-2 admin-nav-main-text">
            Polisy
          </Link> */}

          <div
            onClick={removeToken}
            className="admin-nav-text h-100 px-2 admin-nav-main-text color-white"
          >
            Wyloguj się
          </div>
        </div>
      ) : (
        <div className="h-100 admin-nav-text admin-nav-main-text ">
          <div
            onClick={adminPanel}
            className="admin-nav-text h-100 px-2 admin-nav-main-text color-white"
          >
            Panel Administratora
          </div>
          <Link
            to="/login"
            className="admin-nav-text h-100 px-2 admin-nav-main-text color-white"
          >
            Zaloguj się
          </Link>
        </div>
      )}
    </nav>
  );
};
