import { AdminPanelNav } from "../components/AdminPanelNav";
import { CropDictionaryTableContent } from "../components/sections/CropDictionaryTableContent";

export const AdminPanelCropKindData: React.FC = () => {
  return (
    <>
      <AdminPanelNav />
      <div>
        <h1>UPRAWY</h1>
        <CropDictionaryTableContent />
      </div>
    </>
  );
};
