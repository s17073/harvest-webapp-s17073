import { Navigate, useLocation } from "react-router-dom";
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
  const location = useLocation();

  useEffect(() => {
    const validate = async () => {
      try {
        console.log("checking token...");
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
  }, [location]);

  if (loading) {
    return <div>Uwierzytelniam...</div>;
  }

  return isAuthenticated ? <>{children}</> : <Navigate to="/admin/login" />;
};
