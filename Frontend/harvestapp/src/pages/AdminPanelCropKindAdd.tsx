import { AdminPanelNav } from "../components/AdminPanelNav";
import { CropKindAddForm } from "../components/sections/CropKindAddForm";

export const AdminPanelCropKindAdd: React.FC = () => {
  return (
    <>
      <AdminPanelNav />
      <div>
        <h1>DODAJ UPRAWĘ</h1>
        <CropKindAddForm />
      </div>
    </>
  );
};
