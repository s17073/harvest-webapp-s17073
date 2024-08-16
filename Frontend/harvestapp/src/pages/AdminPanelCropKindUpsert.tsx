import { AdminPanelNav } from "../components/AdminPanelNav";
import { CropKindForm } from "../components/CropKindForm";

export const AdminPanelCropKindUpsert: React.FC = () => {
  return (
    <>
      <AdminPanelNav />
      <div className="background">
        <div className="admin-content-space">
          <div className="admin-title-container">
            <h1>DODAJ UPRAWĘ</h1>
          </div>
          <CropKindForm />
        </div>
      </div>
    </>
  );
};
