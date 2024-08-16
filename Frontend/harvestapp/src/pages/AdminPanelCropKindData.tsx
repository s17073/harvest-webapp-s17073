import { AdminPanelNav } from "../components/AdminPanelNav";
import { CropKindData } from "../components/CropKindData";

export const AdminPanelCropKindData: React.FC = () => {
  return (
    <>
      <AdminPanelNav />
      <div className="background">
        <div className="admin-content-space">
          <div className="admin-title-container">
            <h1>UPRAWY</h1>
          </div>
          <CropKindData />
        </div>
      </div>
    </>
  );
};
