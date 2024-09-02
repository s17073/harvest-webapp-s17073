interface IApkResponse {
  id: number;
  odpowiedz: boolean;
}

interface IInsurancePeriodData {
  dataPoczatkuOchrony: string;
  dataKoncaOchrony: string;
  apk: IApkResponse[];
}

export const fetchInsurancePeriodData = async (
  id: number,
): Promise<IInsurancePeriodData | undefined> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await fetch(`${apiUrl}/calculation/${id}/insuranceperiod`);
  if (response.status != 200) return undefined;
  const data = await response.json();
  return data;
};
