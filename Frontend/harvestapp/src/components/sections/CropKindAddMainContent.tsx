import { CropKindAddForm } from "./CropKindAddForm";

export const CropKindAddMainContent: React.FC = () => {
  return (
    <div>
      <h1>DODAJ UPRAWĘ</h1>
      <CropKindAddForm id={1} isEdit={true} />
    </div>
  );
};
