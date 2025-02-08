import { useEffect, useState } from "react";
import { IAgriculturalLand } from "../../interfaces/IAgriculturalLand";
import { useNavigate, useParams } from "react-router-dom";
import { fetchCrops } from "../../api/Calculation/fetchCrops";
import { handleDeleteCrop } from "../../api/Calculation/handleDeleteCrop";
import { Table } from "react-bootstrap";
import { iconDelete } from "../../assets/icons/delete";
import { iconEdit } from "../../assets/icons/edit";
import BottomBar from "../Shared/BottomBar";

export interface ICropData {
  id: number;
  idUprawy: number;
  idGatunek: number;
  idKlasaGleby: number;
  uprawa: string;
  gatunek: string;
  klasaGleby: string;
  czyNasienna: boolean;
  powierzchnia: number;
  wartosc: number;
  wartoscRynkowa: number;
  wartoscMax: number;
  sumaUbezpieczenia: number;
  ryzyka: number[];
  dzialki: IAgriculturalLand[];
}

export const CropsFormTable: React.FC = () => {
  const [crops, setCrops] = useState<ICropData[]>([]);
  const [loading, setLoading] = useState<string | null>(null);
  const [info, setInfo] = useState<string | null>(null);
  const [error, setError] = useState<string | null>(null);
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();

  //TODO
  // console.log(loading, setLoading(null), info, setInfo(null));

  const fetchData = async () => {
    if (id) {
      const cropsData = await fetchCrops(parseInt(id));
      if (cropsData) {
        setCrops(cropsData);
      }
    }
  };

  useEffect(() => {
    fetchData();
  }, [id]);

  const handleGoBack = () => {
    navigate(`/calculation/${id}/personaldata`);
  };

  const handleAddCrop = () => {
    navigate(`/calculation/${id}/crop`);
  };

  const handleEditCrop = (cropId: number) => {
    navigate(`/calculation/${id}/crop/${cropId}`);
  };

  const handleRemoveCrop = async (cropId: number) => {
    if (id) {
      try {
        await handleDeleteCrop(parseInt(id), cropId);
        fetchData();
      } catch (e) {
        setError("Wystąpił błąd podczas usuwania uprawy");
      }
    }
  };

  const handleSubmit = () => {
    navigate(`/calculation/${id}/livestock`);
  };

  return (
    <>
      <div className="admin-title-container">
        <h1>uprawy</h1>
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
                  Uprawa
                </th>
                <th className="text-center align-middle lh-base px-0 px-xl-4">
                  Nasienna
                </th>
                <th className="text-center align-middle lh-base px-0 px-xl-4">
                  Powierzchnia
                </th>
                <th className="text-center align-middle lh-base px-0 px-xl-4">
                  Wartość
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
              {crops.map((crop) => (
                <tr
                  key={crop.id}
                  className={`row-${crop.id} text-center align-middle lh-1 px-0 px-xl-4`}
                >
                  <td>{crop.uprawa}</td>
                  <td>{crop.czyNasienna}</td>
                  <td>{crop.powierzchnia}</td>
                  <td>{crop.wartosc}</td>
                  <td>{crop.sumaUbezpieczenia}</td>
                  <td className="dict-delete-icon">
                    <div onClick={() => handleEditCrop(crop.id)}>
                      {iconEdit()}
                    </div>
                  </td>
                  <td className="dict-delete-icon">
                    <div onClick={() => handleRemoveCrop(crop.id)}>
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
            onClick={handleAddCrop}
          >
            Dodaj Uprawę
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
