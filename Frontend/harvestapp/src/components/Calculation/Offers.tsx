import { Button, Col, Modal, Row } from "react-bootstrap";
import BottomBar from "../Shared/BottomBar";
import { useNavigate, useParams } from "react-router-dom";
import { handleGenerateOffers } from "../../api/Calculation/handleGenerateOffers";
import { useEffect, useState } from "react";

export const Offers: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const [offers, setOffers] = useState<any | null>(null);
  const [offerId, setOfferId] = useState<number | null>(null);
  const [modalShow, setModalShow] = useState(false);

  const handleGoBack = () => {
    navigate(`/calculation/${id}/livestock`);
  };

  const fetchData = async () => {
    if (id) {
      const offerData = await handleGenerateOffers(parseInt(id));
      if (offerData) {
        setOffers(offerData);
        console.log(offerData);
      }
    }
  };

  useEffect(() => {
    fetchData();
  }, [id]);

  //https://react-bootstrap.netlify.app/docs/components/modal
  function MyVerticallyCenteredModal(props: any) {
    return (
      <Modal
        {...props}
        size="lg"
        aria-labelledby="contained-modal-title-vcenter"
        centered
      >
        <Modal.Header closeButton>
          <Modal.Title id="contained-modal-title-vcenter">
            Akceptacja oferty
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <h4>Oświadczenie</h4>
          <p>
            Niniejszym oświadczam, że zapoznałem się z warunkami umowy
            ubezpieczenia i dobrowolnie zgadzam się na zawarcie umowy
            ubezpieczenia na warunkach określonych w Polisie Ubezpieczeniowej.
            <br />
            Zobowiązuję się do zapłaty składki ubezpieczeniowej w wysokości
            wskazanej w ofercie ubezpieczenia, zgodnie z ustalonymi warunkami
            płatności. Jestem świadomy, że nieuiszczenie składki w terminie może
            skutkować nieważnością umowy lub jej rozwiązaniem.
            <br />
            Ponadto, oświadczam, że wszystkie informacje, które przekazałem w
            formularzu ubezpieczeniowym, są zgodne z prawdą i kompletne. W
            przypadku nieprawdziwych informacji, umowa może zostać wypowiedziana
            przez Ubezpieczyciela.
            <br />
            Zgadzam się na przetwarzanie moich danych osobowych przez
            Ubezpieczyciela w celach związanych z zawarciem, realizacją i
            wykonaniem umowy ubezpieczenia, zgodnie z obowiązującymi przepisami
            prawa oraz polityką prywatności Ubezpieczyciela.
          </p>
        </Modal.Body>
        <Modal.Footer>
          <Button
            variant="secondary"
            className="admin-upsert-submit"
            onClick={props.onHide}
          >
            ANULUJ
          </Button>
          <Button
            variant="primary"
            className="admin-upsert-submit button-gold"
            onClick={props.onHide}
          >
            AKCEPTUJ
          </Button>
        </Modal.Footer>
      </Modal>
    );
  }

  return (
    <>
      <div className="admin-title-container">
        <h1>Oferty</h1>
      </div>
      {offerId === null &&
        offers?.map((offer: any) => (
          <>
            <Row className="calc-offer-box d-flex align-items-center">
              <Col md="8" xl="9">
                <Row className=" d-flex align-items-center my-4">
                  <Col xl="4">
                    <p className="calc-offer-title">Numer oferty:</p>
                    <p className="calc-offer-content">{offer.numerOferty}</p>
                  </Col>
                  <Col xl="4">
                    <p className="calc-offer-title">Ubezpieczyciel:</p>
                    <p className="calc-offer-content">
                      {offer.ubezpieczyciel.nazwa}
                    </p>
                  </Col>

                  <Col xl="4">
                    <p className="calc-offer-title">Składka:</p>
                    <p className="calc-offer-content">{offer.skladka}</p>
                  </Col>
                </Row>
              </Col>
              <Col
                md="4"
                xl="3"
                className="d-flex justify-content-end align-items-end"
              >
                <button
                  className="btn-offer-gold"
                  onClick={() => setOfferId(offer.idOferta)}
                >
                  WYBIERZ
                </button>
              </Col>
            </Row>

            <BottomBar
              button1={{
                label: "WSTECZ",
                className: "admin-upsert-cancel",
                onClick: () => handleGoBack(),
              }}
              button2={undefined}
            />
          </>
        ))}
      {offerId !== null &&
        offers
          ?.filter((offer: any) => offer.idOferta === offerId)
          .map((offer: any) => (
            <>
              <Row className="mt-3">
                <div className="section-heading">
                  Oferta nr. {offer.numerOferty}
                </div>
                <Row className="form-indent mb-3">
                  <Row className="mt-3">
                    <Col lg="5">Ubezpieczyciel: </Col>
                    <Col lg="7" className="fst-italic">
                      {offer.ubezpieczyciel.nazwa}
                    </Col>
                  </Row>
                  <Row className="mt-3">
                    <Col lg="5">Ubezpieczający: </Col>
                    <Col lg="7" className="fst-italic">
                      {offer.kalkulacja.ubezpieczajacy.imie +
                        " " +
                        offer.kalkulacja.ubezpieczajacy.nazwisko}
                    </Col>
                  </Row>
                  <Row className="mt-3">
                    <Col lg="5">Ubezpieczony: </Col>
                    <Col lg="7" className="fst-italic">
                      {offer.kalkulacja.ubezpieczony.imie +
                        " " +
                        offer.kalkulacja.ubezpieczony.nazwisko}
                    </Col>
                  </Row>
                  <Row className="mt-3">
                    <Col lg="5">Data początku ochrony: </Col>
                    <Col lg="7" className="fst-italic">
                      {offer.kalkulacja.dataPoczatkuOchrony}
                    </Col>
                  </Row>
                  <Row className="mt-3">
                    <Col lg="5">Data zakończenia ochrony: </Col>
                    <Col lg="7" className="fst-italic">
                      {offer.kalkulacja.dataKoncaOchrony}
                    </Col>
                  </Row>
                  <Row className="mt-3">
                    <Col lg="5">Suma ubezpieczenia upraw: </Col>
                    <Col lg="7" className="fst-italic">
                      {offer.sumaUbezpieczeniaUpraw}
                    </Col>
                  </Row>
                  <Row className="mt-3">
                    <Col lg="5">Suma ubezpieczenia zwierząt: </Col>
                    <Col lg="7" className="fst-italic">
                      {offer.sumaUbezpieczeniaZwierzat}
                    </Col>
                  </Row>
                </Row>
              </Row>
              <Row className="mx-3 my-3">
                <Col className="d-flex justify-content-end calc-premium">
                  Składka: {offer.skladka}
                </Col>
              </Row>
              <BottomBar
                button1={{
                  label: "WSTECZ",
                  className: "admin-upsert-cancel",
                  onClick: () => setOfferId(null),
                }}
                button2={{
                  label: "AKCEPTUJ",
                  className: "admin-upsert-submit button-gold",
                  onClick: () => setModalShow(true),
                }}
              />

              <MyVerticallyCenteredModal
                show={modalShow}
                onHide={() => setModalShow(false)}
              />
            </>
          ))}
    </>
  );
};
