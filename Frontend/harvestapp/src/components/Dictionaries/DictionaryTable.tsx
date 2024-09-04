import { useEffect, useState } from "react";
import { fetchDictionaryData } from "../../api/Dictionaries/fetchDictionaryData";
import { handleDictionaryDelete } from "../../api/Dictionaries/handleDictionaryDelete";
import { Link, useNavigate } from "react-router-dom";
import { AdminPanelNav } from "../../components/Dictionaries/AdminPanelNav";
import { iconEdit } from "../../assets/icons/edit";
import { iconDelete } from "../../assets/icons/delete";

interface DictionaryData {
  id: number;
  [key: string]: any;
}

interface ColumnConfig {
  key: string;
  label: string;
  render?: (value: any, row: DictionaryData) => React.ReactNode;
}

interface DictionaryTableProps {
  title: string;
  fetchApiName: string;
  deleteApiName: string;
  columns: ColumnConfig[];
}

const DictionaryTable: React.FC<DictionaryTableProps> = ({
  title,
  fetchApiName,
  deleteApiName,
  columns,
}) => {
  const [data, setData] = useState<DictionaryData[]>([]);
  const [loading, setLoading] = useState<string | null>(null);
  const [noData, setNoData] = useState<string | null>(null);
  const [fetchError, setFetchError] = useState<string | null>(null);
  const [announcement, setAnnouncement] = useState<string | null>(null);
  const navigate = useNavigate();

  const fetchData = () =>
    fetchDictionaryData(
      fetchApiName,
      setNoData,
      setData,
      setFetchError,
      setLoading,
    );
  const deleteData = (id: number) =>
    handleDictionaryDelete(
      id,
      deleteApiName,
      setData,
      fetchData,
      setAnnouncement,
    );

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <>
      <AdminPanelNav />
      <div className="background">
        <div className="admin-content-space">
          <div className="admin-title-container">
            <h1>{title}</h1>
          </div>
          {loading && <p>{loading}</p>}
          {fetchError && <p>{fetchError}</p>}
          <div className="admin-table-space">
            <table className="admin-table">
              <thead>
                <tr>
                  {columns.map((col) => (
                    <th key={col.key}>{col.label}</th>
                  ))}
                  <th>Edytuj</th>
                  <th>Usuń</th>
                </tr>
              </thead>
              <tbody>
                {data.map((item) => (
                  <tr key={item.id}>
                    {columns.map((col) => (
                      <td key={col.key}>
                        {col.render
                          ? col.render(item[col.key], item)
                          : item[col.key]}
                      </td>
                    ))}
                    <td>
                      <div>
                        <Link to={`/admin/${fetchApiName}/upsert/${item.id}`}>
                          {iconEdit()}
                        </Link>
                      </div>
                    </td>
                    <td>
                      <div onClick={() => deleteData(item.id)}>
                        {iconDelete()}
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
            {noData && <p>{noData}</p>}
            {announcement && <p>{announcement}</p>}
          </div>
          <button
            className="admin-table-cancel"
            onClick={() => navigate("/admin")}
          >
            POWRÓT
          </button>
        </div>
      </div>
    </>
  );
};

export default DictionaryTable;
