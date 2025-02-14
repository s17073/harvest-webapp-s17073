import axios from "axios";
import { useState } from "react";
import { Col, Container, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { MainNav } from "../components/Calculation/MainNav";
import { logInUser } from "../api/Shared/logInUser";

export const Login: React.FC = () => {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [error, setError] = useState<string | null>(null);

  const navigate = useNavigate();

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const response = logInUser(email, password);
      if ((await response) === true) {
        navigate("/");
      } else {
        console.log("NIE UDAŁO SIĘ :< " + localStorage.getItem("token"));
      }
    } catch (e) {
      console.log("NIE UDAŁO SIĘ :< " + localStorage.getItem("token"));
    }
  };

  const handleSignin = async () => {
    navigate("/register");
  };

  return (
    <>
      <MainNav />
      <div className="background vh-100 d-flex align-items-center ">
        <Container className="content-space">
          <Row>
            <Col xs={12} xl={6} className="admin-login-side-section">
              <h2>HARVEST</h2>
              <div>Witaj w panelu logowania!</div>
            </Col>
            <Col xs={12} xl={6} className="admin-login-main-section">
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
              <Row className="d-flex justify-content-center mt-3">
                Nie masz jeszcze konta?
              </Row>
              <Row className="d-flex justify-content-center mt-3">
                <button
                  className="admin-login-login-button w-50"
                  onClick={handleSignin}
                >
                  Zarejestruj się
                </button>
              </Row>
            </Col>
          </Row>
        </Container>
      </div>
    </>
  );
};
