import { Spinner } from "react-bootstrap";

export const Loading: React.FC = () => {
  return (
    <>
      <div className="loading-page position-fixed top-0 start-0 w-100 h-100 d-flex align-items-center justify-content-center">
        <Spinner animation="border" className="loading-spinner" />
      </div>
    </>
  );
};
