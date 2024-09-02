interface IPersonalData {
  imie: string;
  nazwisko: string;
  pesel: string;
  dataUrodzenia: string;
  adresEmail: string;
  teryt: string;
  kodPocztowy: string;
  miejscowosc: string;
  ulica: string;
  numerDomu: string;
  numerMieszkania: string | undefined;
}

interface IPersons {
  ubezpieczajacy: IPersonalData;
  ubezpieczony: IPersonalData;
}

export const fetchPersonalData = async (
  id: number,
): Promise<IPersons | undefined> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await fetch(`${apiUrl}/calculation/${id}/personaldata`);
  if (response.status != 200) return undefined;
  const data = await response.json();
  return data;
};
