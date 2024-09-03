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
    <div>
      <h2>Login</h2>
      <form onSubmit={handleLogin}>
        <div>
          <label htmlFor="email">Nazwa użytkownika:</label>
          <input
            type="text"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            autoComplete="username"
          />
        </div>
        <div>
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
        {error && <p>{error}</p>}
        <button type="submit">Zaloguj się</button>
      </form>
    </div>
  );
};
