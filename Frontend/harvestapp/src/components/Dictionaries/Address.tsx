import React, { useEffect } from "react";

interface AddressProps {
  addressData: {
    kodPocztowy: string;
    miejscowosc: string;
    ulica: string;
    numerDomu: string;
    numerMieszkania?: string;
  };
  onChange: (newAddressData: any) => void;
  errors?: any;
}

export const Address: React.FC<AddressProps> = ({
  addressData,
  onChange,
  errors,
}) => {
  const handleFieldChange = (field: string, value: string) => {
    onChange({ ...addressData, [field]: value });
  };

  return (
    <div>
      <div>
        <label>Kod Pocztowy:</label>
        <input
          type="text"
          value={addressData.kodPocztowy}
          onChange={(e) => handleFieldChange("kodPocztowy", e.target.value)}
        />
        {errors["addressData.kodPocztowy"] && (
          <span className="error-message">
            {errors["addressData.kodPocztowy"]}
          </span>
        )}
      </div>
      <div>
        <label>Miejscowość:</label>
        <input
          type="text"
          value={addressData.miejscowosc}
          onChange={(e) => handleFieldChange("miejscowosc", e.target.value)}
        />
        {errors["addressData.miejscowosc"] && (
          <span className="error-message">
            {errors["addressData.miejscowosc"]}
          </span>
        )}
      </div>
      <div>
        <label>Ulica:</label>
        <input
          type="text"
          value={addressData.ulica}
          onChange={(e) => handleFieldChange("ulica", e.target.value)}
        />
        {errors["addressData.ulica"] && (
          <span className="error-message">{errors["addressData.ulica"]}</span>
        )}
      </div>
      <div>
        <label>Numer Domu:</label>
        <input
          type="text"
          value={addressData.numerDomu}
          onChange={(e) => handleFieldChange("numerDomu", e.target.value)}
        />
        {errors["addressData.numerDomu"] && (
          <span className="error-message">
            {errors["addressData.numerDomu"]}
          </span>
        )}
      </div>
      <div>
        <label>Numer Mieszkania:</label>
        <input
          type="text"
          value={addressData.numerMieszkania}
          onChange={(e) => handleFieldChange("numerMieszkania", e.target.value)}
        />
        {errors["addressData.numerMieszkania"] && (
          <span className="error-message">
            {errors["addressData.numerMieszkania"]}
          </span>
        )}
      </div>
    </div>
  );
};
