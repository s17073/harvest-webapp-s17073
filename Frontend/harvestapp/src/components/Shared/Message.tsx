import { useEffect, useState } from "react";
import { Alert } from "react-bootstrap";

interface IMessageProps {
  message: string | undefined;
}

export const Message: React.FC<IMessageProps> = ({ message }) => {
  const [messageDisplay, setMessageDisplay] = useState<string | undefined>(
    undefined,
  );

  useEffect(() => {
    if (message !== undefined) {
      setMessageDisplay(message.substring(message.indexOf(" ") + 1));
    }
  }, [message]);

  return (
    <>
      {message && (
        <div className="floating-alert">
          <Alert variant="success">{messageDisplay}</Alert>
        </div>
      )}
    </>
  );
};
