import axios from "axios";
import { useEffect, useState } from "react";

interface CropKindDataDict {
  id: number;
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

  const fetchData = async () => {
    setLoading(true);
    try {
      const response = await axios.get<CropKindDataDict[]>("api/cropkind");
      console.log("Crop kind data get method status: ", response.status);
      setData(response.data);
    } catch (err) {
      setError((err as Error).message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleDelete = async (id: number) => {
    try {
      const response = await axios.delete(`api/cropkind/${id}`);
      console.log(`Crop kind id: ${id} delete status: `, response.status);

      if (response.status === 200) {
        setData([]);
        await fetchData();
      }
    } catch (err) {
      setError(
        `Wystąpił błąd podczasu usuwania elementu od id ${id}: ${
          (err as Error).message
        }`,
      );
    }
  };

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
        {data.map((crop) => (
          <tr key={crop.id} className={`row-${crop.id}`}>
            <td>{crop.nazwaUprawy}</td>
            <td>{crop.taryfa}</td>
            <td>{crop.wartoscRynkowa}</td>
            <td>{crop.wartoscMax}</td>
            <td>{crop.czyAktywna ? "AKTYWNA" : "NIEAKTYWNA"}</td>
            <td>Edytuj id: {crop.id}</td>
            <td>
              <div onClick={() => handleDelete(crop.id)}>
                Usuń id: {crop.id}
              </div>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};
