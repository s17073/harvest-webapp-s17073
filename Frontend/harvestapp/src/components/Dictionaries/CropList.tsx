import { useEffect, useState } from "react";
import { fetchUprawy } from "../../api/Shared/fetchUprawy";

interface ICropData {
  idUprawy: number;
  nazwaUprawy: string;
}

interface ICropListProps {
  cropId: number;
  onChange: (newCrop: any) => void;
  errors?: any;
}

export const CropList: React.FC<ICropListProps> = ({
  cropId,
  onChange,
  errors,
}) => {
  const [crops, setCrops] = useState<ICropData[]>([]);
  const [cropData, setCropData] = useState<ICropData>({
    idUprawy: 0,
    nazwaUprawy: "",
  });

  const fetchCropListValues = async () => {
    const fetchedCrops = await fetchUprawy();
    setCrops(fetchedCrops);
  };

  useEffect(() => {
    fetchCropListValues();
  }, []);

  useEffect(() => {
    if (cropId > 0 && crops.length > 0) {
      const crop = crops.find((c) => c.idUprawy === cropId)?.nazwaUprawy;

      if (crop) {
        setCropData({
          idUprawy: cropId,
          nazwaUprawy: crop,
        });
      }
    }
  }, [cropId, crops]);

  const handleFieldChange = (field: string, value: string) => {
    const newCropData: ICropData = { ...cropData, [field]: value };
    setCropData(newCropData);
    onChange(value);
  };

  return (
    <div>
      <label>Uprawa:</label>
      <select
        value={cropData.idUprawy}
        onChange={(e) => handleFieldChange("idUprawy", e.target.value)}
      >
        <option value="">Wybierz uprawę</option>
        {crops.map((crop) => (
          <option key={crop.idUprawy} value={crop.idUprawy}>
            {crop.nazwaUprawy}
          </option>
        ))}
      </select>
      {errors?.crop && <span className="error-message">{errors.crop}</span>}
    </div>
  );
};
