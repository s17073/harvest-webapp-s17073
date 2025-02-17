import { useEffect, useState } from "react";
import { ILivestock } from "../../interfaces/ILivestock";
import { useNavigate, useParams } from "react-router-dom";
import { fetchLivestock } from "../../api/Calculation/fetchLivestock";
import { handleDeleteLivestock } from "../../api/Calculation/handleDeleteLivestock";
import { Table } from "react-bootstrap";
import { iconEdit } from "../../assets/icons/edit";
import { iconDelete } from "../../assets/icons/delete";
import BottomBar from "../Shared/BottomBar";

export const LivestockFormTable: React.FC = () => {
  const [livestock, setLivestock] = useState<ILivestock[]>([]);
  const [error, setError] = useState<string | null>(null);
  // const [loading, setLoading] = useState<string | null>(null);
  // const [info, setInfo] = useState<string | null>(null);

  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();

  const fetchData = async () => {
    if (id) {
      const livestockData = await fetchLivestock(parseInt(id));
      if (livestockData) {
        setLivestock(livestockData);
      }
    }
  };

  useEffect(() => {
    fetchData();
  }, [id]);

  const handleEditAnimal = (livestockId: number) => {
    navigate(`/calculation/${id}/livestock/${livestockId}`);
  };

  const handleRemoveAnimal = async (livestockId: number) => {
    if (id) {
      try {
        await handleDeleteLivestock(parseInt(id), livestockId);
        fetchData();
      } catch (e) {
        setError("Wystąpił błąd podczas usuwania");
      }
    }
  };

  const handleAddAnimal = () => {
    navigate(`/calculation/${id}/livestock/add`);
  };

  const handleGoBack = () => {
    navigate(`/calculation/${id}/crops`);
  };

  const handleSubmit = () => {
    navigate(`/calculation/${id}/offers`);
  };

  return (
    <>
      <div className="admin-title-container">
        <h1>Zwierzęta</h1>
      </div>
      <div>
        <div className="admin-table-space">
          <Table
            striped
            bordered
            hover
            responsive="xl"
            size="xl"
            className="admin-table"
          >
            <thead>
              <tr>
                <th className="text-center align-middle lh-base px-0 px-xl-4">
                  Zwierzę
                </th>
                <th className="text-center align-middle lh-base px-0 px-xl-4">
                  Liczba
                </th>
                <th className="text-center align-middle lh-base px-0 px-xl-4">
                  Wartość
                </th>
                <th className="text-center align-middle lh-base px-0 px-xl-4">
                  Hodowla na mięso
                </th>
                <th className="text-center align-middle lh-base px-0 px-xl-4">
                  Suma ubezpieczenia
                </th>
                <th className="text-center align-middle lh-base px-0 px-xl-4">
                  Edytuj
                </th>
                <th className="text-center align-middle lh-base px-0 px-xl-4">
                  Usuń
                </th>
              </tr>
            </thead>
            <tbody>
              {livestock.map((animal) => (
                <tr
                  key={animal.id}
                  className={`row-${animal.id} text-center align-middle lh-1 px-0 px-xl-4`}
                >
                  <td>{animal.nazwaZwierzecia}</td>
                  <td>{animal.liczba}</td>
                  <td>{animal.wartosc}</td>
                  <td>{animal.naMieso ? "TAK" : "NIE"}</td>
                  <td>{animal.sumaUbezpieczenia} zł</td>
                  <td className="dict-delete-icon">
                    <div onClick={() => handleEditAnimal(animal.id)}>
                      {iconEdit()}
                    </div>
                  </td>
                  <td className="dict-delete-icon">
                    <div onClick={() => handleRemoveAnimal(animal.id)}>
                      {iconDelete()}
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
        <div className="d-flex justify-content-end">
          <button
            className="btn-admin-add w-auto"
            type="button"
            onClick={handleAddAnimal}
          >
            Dodaj Zwierzę
          </button>
        </div>
        <BottomBar
          button1={{
            label: "WSTECZ",
            className: "admin-table-cancel",
            onClick: () => handleGoBack(),
          }}
          button2={{
            label: "DALEJ",
            className: "btn-admin-add",
            onClick: () => handleSubmit(),
          }}
        />
        <div>{error && error}</div>
      </div>
    </>
  );
};
