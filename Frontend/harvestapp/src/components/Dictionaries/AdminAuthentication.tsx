import { Navigate } from "react-router-dom";
import { validateAdminToken } from "../../api/Shared/validateAdminToken";
import { useEffect, useState } from "react";

interface IAdminAuthenticationProps {
  children: React.ReactNode;
}

export const AdminAuthentication: React.FC<IAdminAuthenticationProps> = ({
  children,
}) => {
  const [isAuthenticated, setIsAuthenticated] = useState<boolean | null>(null);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const validate = async () => {
      try {
        const token = localStorage.getItem("token");
        const isValid = await validateAdminToken(token);
        setIsAuthenticated(isValid);
        setLoading(false);
      } catch {
        setIsAuthenticated(false);
        setLoading(false);
      }
    };
    validate();
  }, []);

  if (loading) {
    return <div>Uwierzytelniam...</div>;
  }

  return isAuthenticated ? <>{children}</> : <Navigate to="/admin/login" />;
};
