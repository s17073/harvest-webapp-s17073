import { Link } from "react-router-dom";

export const AdminPanelMainPage: React.FC = () => {
  return (
    <>
      <h2>
        <Link to="/admin/cropkind">EDYTUJ UPRAWY</Link>
      </h2>
      <h2>
        <Link to="/admin/cropkind/add">DODAJ UPRAWĘ</Link>
      </h2>
    </>
  );
};
