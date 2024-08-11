import axios from "axios";
import { useEffect, useState } from "react";

interface CropKindDataDict {
  nazwaUprawy: string;
  taryfa: string;
  czyAktywna: boolean;
  wartoscRynkowa: number;
  wartoscMax: number;
}

export const CropDictionaryTableContent: React.FC = () => {
  const [data, setData] = useState<CropKindDataDict[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get<CropKindDataDict[]>("api/cropkind");
        setData(response.data);
      } catch (err) {
        setError((err as Error).message);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  if (loading) return <p>Ładowanie danych...</p>;
  if (error) return <p>Błąd: {error}</p>;

  return (
    <table>
      <thead>
        <tr>
          <th>Nazwa uprawy</th>
          <th>Taryfa</th>
          <th>Wartość rynkowa</th>
          <th>Wartość maksymalna</th>
          <th>Status</th>
          <th>Edytuj</th>
          <th>Usuń</th>
        </tr>
      </thead>
      <tbody>
        {data.map((crop, index) => (
          <tr key={index} className={`row-${index + 1}`}>
            <td>{crop.nazwaUprawy}</td>
            <td>{crop.taryfa}</td>
            <td>{crop.wartoscRynkowa}</td>
            <td>{crop.wartoscMax}</td>
            <td>{crop.czyAktywna ? "AKTYWNA" : "NIEAKTYWNA"}</td>
            <td>Edytujimg</td>
            <td>Usunimg</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};
