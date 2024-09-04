import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export const AdminPanelLogin: React.FC = () => {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [error, setError] = useState<string | null>(null);

  const navigate = useNavigate();

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const loginData = {
        email: email,
        haslo: password,
      };
      const apiUrl = import.meta.env.VITE_BACKEND_URL;
      const response = await axios.post(
        `${apiUrl}/user/admin/login`,
        loginData,
      );
      const token = response.data;
      localStorage.setItem("token", token);
      navigate("/admin");
    } catch (error) {
      setError("Niepoprawne dane logowania");
    }
  };

  return (
    <div className="admin-login-background">
      <div className="admin-login-section">
        <div className="admin-login-sections">
          <div className="admin-login-side-section">
            <h2>HARVEST</h2>
            <div>Witaj w panelu logowania administratora!</div>
          </div>

          <div className="admin-login-main-section">
            <div className="admin-login-title">
              <h1>Zaloguj się</h1>
            </div>
            <form onSubmit={handleLogin}>
              <div className="admin-login">
                <label htmlFor="email">Login:</label>
                <input
                  type="text"
                  id="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required
                  autoComplete="username"
                />
              </div>
              <div className="admin-password">
                <label htmlFor="password">Hasło:</label>
                <input
                  type="password"
                  id="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                  autoComplete="current-password"
                />
              </div>
              <button className="admin-login-login-button" type="submit">
                Zaloguj się
              </button>
              {error && <p className="admin-login-error">{error}</p>}
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};
