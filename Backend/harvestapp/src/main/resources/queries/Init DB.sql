-- tables
-- Table: Adres
CREATE TABLE Adres (
    id_adres int GENERATED ALWAYS AS IDENTITY NOT NULL,
    id_teryt int  NOT NULL,
    kod_pocztowy varchar(6)  NOT NULL,
    miejscowosc varchar(50)  NOT NULL,
    ulica varchar(60)  NOT NULL,
    numer_domu varchar(5)  NOT NULL,
    numer_mieszkania varchar(5)  NULL,
    CONSTRAINT Adres_pk PRIMARY KEY (id_adres)
);

-- Table: Agent
CREATE TABLE Agent (
    id_agent int GENERATED ALWAYS AS IDENTITY NOT NULL,
    nazwa varchar(50)  NOT NULL,
    kod_agencji varchar(10)  NOT NULL,
    nip varchar(10)  NOT NULL,
    krs varchar(10)  NULL,
    id_adres int  NOT NULL,
    liczba_posrednikow smallint  NOT NULL,
    numer_telefonu_agencji varchar(13)  NOT NULL,
    czy_aktywny boolean  NOT NULL,
    data_dodania timestamp  NOT NULL,
    data_usuniecia timestamp NULL,
    CONSTRAINT Agent_pk PRIMARY KEY (id_agent)
);

-- Table: Agent_ubezpieczyciel
CREATE TABLE Agent_ubezpieczyciel (
    id_agent_ubezpieczyciel int GENERATED ALWAYS AS IDENTITY NOT NULL,
    id_ubezpieczyciel int  NOT NULL,
    id_agent int  NOT NULL,
    data_zawarcia_umowy date  NOT NULL,
    prowizja float8  NOT NULL,
    status varchar(15)  NOT NULL,
    CONSTRAINT Agent_ubezpieczyciel_pk PRIMARY KEY (id_agent_ubezpieczyciel)
);

-- Table: Apk
CREATE TABLE Apk (
    id_apk int GENERATED ALWAYS AS IDENTITY NOT NULL,
    pytanie text  NOT NULL,
    komunikat text NULL,
    czy_aktywna boolean  NOT NULL,
    data_dodania timestamp  NOT NULL,
    data_usuniecia timestamp NULL,
    CONSTRAINT Apk_pk PRIMARY KEY (id_apk)
);

-- Table: Apk_kalkulacja
CREATE TABLE Apk_kalkulacja (
    id_apk_kalkulacja int GENERATED ALWAYS AS IDENTITY NOT NULL,
    id_apk int  NOT NULL,
    id_kalkulacja int  NOT NULL,
    odpowiedz boolean  NOT NULL,
    CONSTRAINT Apk_kalkulacja_pk PRIMARY KEY (id_apk_kalkulacja)
);

-- Table: Dzialka
CREATE TABLE Dzialka (
    id_dzialka int GENERATED ALWAYS AS IDENTITY NOT NULL,
    id_teryt int  NOT NULL,
    id_uprawa int  NOT NULL,
    numer_dzialki varchar(10)  NOT NULL,
    obreb varchar(20)  NULL,
    kod_obrebu varchar(20)  NULL,
    czy_poprawna boolean  NOT NULL,
    CONSTRAINT Dzialka_pk PRIMARY KEY (id_dzialka)
);

-- Table: Gatunek
CREATE TABLE Gatunek (
    id_gatunek int GENERATED ALWAYS AS IDENTITY NOT NULL,
    nazwa_gatunku varchar(30)  NOT NULL,
    czy_aktywna boolean  NOT NULL,
    data_dodania timestamp  NOT NULL,
    data_usuniecia timestamp  NULL,
    CONSTRAINT Gatunek_pk PRIMARY KEY (id_gatunek)
);

-- Table: Kalkulacja
CREATE TABLE Kalkulacja (
    id_kalkulacja int GENERATED ALWAYS AS IDENTITY NOT NULL,
    id_adres int  NOT NULL,
    id_ubezpieczajacy int  NOT NULL,
    id_ubezpieczony int  NOT NULL,
    id_posrednik int  NULL,
    numer_kalkulacji varchar(10)  NOT NULL,
    data_poczatku_ochrony date  NOT NULL,
    data_konca_ochrony date  NOT NULL,
    status_kalkulacji varchar(15)  NOT NULL,
    CONSTRAINT Kalkulacja_pk PRIMARY KEY (id_kalkulacja)
);

-- Table: Klasa_gleby
CREATE TABLE Klasa_gleby (
    id_klasa_gleby int GENERATED ALWAYS AS IDENTITY NOT NULL,
    klasa_gleby varchar(5)  NOT NULL,
    opis text  NOT NULL,
    taryfa varchar(10)  NOT NULL,
    czy_aktywna boolean NOT NULL,
    data_dodania timestamp  NOT NULL,
    data_usuniecia timestamp  NULL,
    CONSTRAINT Klasa_gleby_pk PRIMARY KEY (id_klasa_gleby)
);

-- Table: Ochrona
CREATE TABLE Ochrona (
    id_ochrona int GENERATED ALWAYS AS IDENTITY NOT NULL,
    nazwa varchar(30)  NOT NULL,
    grupa_ministerialna varchar(2)  NOT NULL,
    taryfa varchar(10)  NOT NULL,
    opis text NULL,
    czy_uprawa boolean  NOT NULL,
    czy_zwierze boolean  NOT NULL,
    czy_aktywna boolean  NOT NULL,
    data_dodania timestamp  NOT NULL,
    data_usuniecia timestamp  NULL,
    CONSTRAINT Ochrona_pk PRIMARY KEY (id_ochrona)
);

-- Table: Ochrona_uprawa
CREATE TABLE Ochrona_uprawa (
    id_ochrona_uprawa int GENERATED ALWAYS AS IDENTITY NOT NULL,
    id_uprawa int  NOT NULL,
    id_ochrona int  NOT NULL,
    CONSTRAINT Ochrona_uprawa_pk PRIMARY KEY (id_ochrona_uprawa)
);

-- Table: Ochrona_zwierze
CREATE TABLE Ochrona_zwierze (
    id_ochrona_zwierze int GENERATED ALWAYS AS IDENTITY NOT NULL,
    id_zwierze int  NOT NULL,
    id_ochrona int  NOT NULL,
    CONSTRAINT Ochrona_zwierze_pk PRIMARY KEY (id_ochrona_zwierze)
);

-- Table: Oferta
CREATE TABLE Oferta (
    id_oferta int GENERATED ALWAYS AS IDENTITY NOT NULL,
    id_ubezpieczyciel int  NOT NULL,
    id_kalkulacja int  NOT NULL,
    numer_oferty varchar(10)  NOT NULL,
    skladka float8  NOT NULL,
    data_wygasniecia date  NOT NULL,
    status_oferty varchar(15)  NOT NULL,
    CONSTRAINT Oferta_pk PRIMARY KEY (id_oferta)
);

-- Table: Polisa
CREATE TABLE Polisa (
    id_polisa int GENERATED ALWAYS AS IDENTITY NOT NULL,
    id_oferta int  NOT NULL,
    data_zawarcia_polisy date  NOT NULL,
    numer_polisy varchar(10)  NOT NULL,
    status_polisy varchar(10)  NOT NULL,
    CONSTRAINT Polisa_pk PRIMARY KEY (id_polisa)
);

-- Table: Rodzaj_uprawy
CREATE TABLE Rodzaj_uprawy (
    id_rodzaj_uprawy int GENERATED ALWAYS AS IDENTITY NOT NULL,
    nazwa_uprawy varchar(30)  NOT NULL,
    taryfa varchar(10)  NOT NULL,
    czy_aktywna boolean  NOT NULL,
    wartosc_rynkowa float8  NOT NULL,
    wartosc_max float8  NOT NULL,
    data_dodania timestamp  NOT NULL,
    data_usuniecia timestamp  NULL,
    CONSTRAINT Rodzaj_uprawy_pk PRIMARY KEY (id_rodzaj_uprawy)
);

-- Table: Rodzaj_zwierzecia
CREATE TABLE Rodzaj_zwierzecia (
    id_rodzaj_zwierzecia int GENERATED ALWAYS AS IDENTITY NOT NULL,
    nazwa varchar(30)  NOT NULL,
    wartosc_rynkowa float8  NOT NULL,
    wartosc_max float8  NOT NULL,
    taryfa varchar(10)  NOT NULL,
    czy_aktywna boolean  NOT NULL,
    data_dodania timestamp  NOT NULL,
    data_usuniecia timestamp  NULL,
    CONSTRAINT Rodzaj_zwierzecia_pk PRIMARY KEY (id_rodzaj_zwierzecia)
);

-- Table: Teryt
CREATE TABLE Teryt (
    id_teryt int GENERATED ALWAYS AS IDENTITY NOT NULL,
    kod_teryt varchar(20)  NOT NULL,
    wojewodztwo varchar(19)  NOT NULL,
    powiat varchar(30)  NOT NULL,
    gmina varchar(30)  NOT NULL,
    typ varchar(30)  NOT NULL,
    CONSTRAINT Teryt_pk PRIMARY KEY (id_teryt)
);

-- Table: Ubezpieczyciel
CREATE TABLE Ubezpieczyciel (
    id_ubezpieczyciel int GENERATED ALWAYS AS IDENTITY NOT NULL,
    id_adres int  NOT NULL,
    nazwa varchar(50)  NOT NULL,
    numer_zakladu varchar(10)  NOT NULL,
    numer_telefonu varchar(13)  NOT NULL,
    numer_konta varchar(26)  NOT NULL,
    CONSTRAINT Ubezpieczyciel_pk PRIMARY KEY (id_ubezpieczyciel)
);

-- Table: Uprawa
CREATE TABLE Uprawa (
    id_uprawa int GENERATED ALWAYS AS IDENTITY NOT NULL,
    id_kalkulacja int  NOT NULL,
    id_klasa_gleby int  NOT NULL,
    id_rodzaj_uprawy int  NOT NULL,
    id_gatunek int  NULL,
    czy_nasienna boolean  NOT NULL,
    powierzchnia float8  NOT NULL,
    wartosc float8  NOT NULL,
    wartosc_rynkowa float8  NOT NULL,
    suma_ubezpieczenia int  NOT NULL,
    CONSTRAINT Uprawa_pk PRIMARY KEY (id_uprawa)
);

-- Table: Uprawa_gatunek
CREATE TABLE Uprawa_gatunek (
    id_uprawa_gatunek int GENERATED ALWAYS AS IDENTITY NOT NULL,
    id_rodzaj_uprawy int  NOT NULL,
    id_gatunek int  NOT NULL,
    CONSTRAINT Uprawa_gatunek_pk PRIMARY KEY (id_uprawa_gatunek)
);

-- Table: Uzytkownik
CREATE TABLE Uzytkownik (
    id_uzytkownik int GENERATED ALWAYS AS IDENTITY NOT NULL,
    id_adres int  NOT NULL,
    id_agent int  NULL,
    id_konta int  NOT NULL,
    imie varchar(20)  NOT NULL,
    nazwisko varchar(60)  NOT NULL,
    pesel varchar(11)  NOT NULL,
    data_urodzenia date  NOT NULL,
    wiek smallint  NOT NULL,
    email varchar(100)  NOT NULL,
    haslo varchar(30)  NOT NULL,
    kod_posrednika varchar(10)  NOT NULL,
    numer_telefonu varchar(13)  NOT NULL,
    CONSTRAINT Uzytkownik_pk PRIMARY KEY (id_uzytkownik)
);

-- Table: Zwierze
CREATE TABLE Zwierze (
    id_zwierze int GENERATED ALWAYS AS IDENTITY NOT NULL,
    id_kalkulacja int  NOT NULL,
    id_rodzaj_zwierzecia int  NOT NULL,
    liczba int  NOT NULL,
    wartosc float8  NOT NULL,
    wartosc_rynkowa float8  NOT NULL,
    na_mieso boolean  NOT NULL,
    suma_ubezpieczenia int  NOT NULL,
    CONSTRAINT Zwierze_pk PRIMARY KEY (id_zwierze)
);

-- foreign keys
-- Reference: Adres_Teryt (table: Adres)
ALTER TABLE Adres ADD CONSTRAINT Adres_Teryt
    FOREIGN KEY (id_teryt)
    REFERENCES Teryt (id_teryt)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Agent_Adres (table: Agent)
ALTER TABLE Agent ADD CONSTRAINT Agent_Adres
    FOREIGN KEY (id_adres)
    REFERENCES Adres (id_adres)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Agent_ubezpieczyciela_Agent (table: Agent_ubezpieczyciel)
ALTER TABLE Agent_ubezpieczyciel ADD CONSTRAINT Agent_ubezpieczyciela_Agent
    FOREIGN KEY (id_agent)
    REFERENCES Agent (id_agent)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Agent_ubezpieczyciela_Ubezpieczyciel (table: Agent_ubezpieczyciel)
ALTER TABLE Agent_ubezpieczyciel ADD CONSTRAINT Agent_ubezpieczyciela_Ubezpieczyciel
    FOREIGN KEY (id_ubezpieczyciel)
    REFERENCES Ubezpieczyciel (id_ubezpieczyciel)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Apk_oferta_Apk (table: Apk_kalkulacja)
ALTER TABLE Apk_kalkulacja ADD CONSTRAINT Apk_oferta_Apk
    FOREIGN KEY (id_apk)
    REFERENCES Apk (id_apk)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Apk_oferta_Kalkulacja (table: Apk_kalkulacja)
ALTER TABLE Apk_kalkulacja ADD CONSTRAINT Apk_oferta_Kalkulacja
    FOREIGN KEY (id_kalkulacja)
    REFERENCES Kalkulacja (id_kalkulacja)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Dzialka_Teryt (table: Dzialka)
ALTER TABLE Dzialka ADD CONSTRAINT Dzialka_Teryt
    FOREIGN KEY (id_teryt)
    REFERENCES Teryt (id_teryt)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Dzialka_Uprawa (table: Dzialka)
ALTER TABLE Dzialka ADD CONSTRAINT Dzialka_Uprawa
    FOREIGN KEY (id_uprawa)
    REFERENCES Uprawa (id_uprawa)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Kalkulacja_Adres (table: Kalkulacja)
ALTER TABLE Kalkulacja ADD CONSTRAINT Kalkulacja_Adres
    FOREIGN KEY (id_adres)
    REFERENCES Adres (id_adres)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Kalkulacja_Uzytkownik_Posrednik (table: Kalkulacja)
ALTER TABLE Kalkulacja ADD CONSTRAINT Kalkulacja_Uzytkownik_Posrednik
    FOREIGN KEY (id_posrednik)
    REFERENCES Uzytkownik (id_uzytkownik)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Kalkulacja_Uzytkownik_Ubezpieczajacy (table: Kalkulacja)
ALTER TABLE Kalkulacja ADD CONSTRAINT Kalkulacja_Uzytkownik_Ubezpieczajacy
    FOREIGN KEY (id_ubezpieczajacy)
    REFERENCES Uzytkownik (id_uzytkownik)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Kalkulacja_Uzytkownik_Ubezpieczony (table: Kalkulacja)
ALTER TABLE Kalkulacja ADD CONSTRAINT Kalkulacja_Uzytkownik_Ubezpieczony
    FOREIGN KEY (id_ubezpieczony)
    REFERENCES Uzytkownik (id_uzytkownik)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Ochrona_uprawa_Ochrona (table: Ochrona_uprawa)
ALTER TABLE Ochrona_uprawa ADD CONSTRAINT Ochrona_uprawa_Ochrona
    FOREIGN KEY (id_ochrona)
    REFERENCES Ochrona (id_ochrona)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Ochrona_uprawa_Uprawa (table: Ochrona_uprawa)
ALTER TABLE Ochrona_uprawa ADD CONSTRAINT Ochrona_uprawa_Uprawa
    FOREIGN KEY (id_uprawa)
    REFERENCES Uprawa (id_uprawa)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Ochrona_zwierze_Ochrona (table: Ochrona_zwierze)
ALTER TABLE Ochrona_zwierze ADD CONSTRAINT Ochrona_zwierze_Ochrona
    FOREIGN KEY (id_ochrona)
    REFERENCES Ochrona (id_ochrona)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Ochrona_zwierze_Zwierze (table: Ochrona_zwierze)
ALTER TABLE Ochrona_zwierze ADD CONSTRAINT Ochrona_zwierze_Zwierze
    FOREIGN KEY (id_zwierze)
    REFERENCES Zwierze (id_zwierze)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Oferta_Kalkulacja (table: Oferta)
ALTER TABLE Oferta ADD CONSTRAINT Oferta_Kalkulacja
    FOREIGN KEY (id_kalkulacja)
    REFERENCES Kalkulacja (id_kalkulacja)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Oferta_Ubezpieczyciel (table: Oferta)
ALTER TABLE Oferta ADD CONSTRAINT Oferta_Ubezpieczyciel
    FOREIGN KEY (id_ubezpieczyciel)
    REFERENCES Ubezpieczyciel (id_ubezpieczyciel)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Osoba_Adres (table: Uzytkownik)
ALTER TABLE Uzytkownik ADD CONSTRAINT Osoba_Adres
    FOREIGN KEY (id_adres)
    REFERENCES Adres (id_adres)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Polisa_Oferta (table: Polisa)
ALTER TABLE Polisa ADD CONSTRAINT Polisa_Oferta
    FOREIGN KEY (id_oferta)
    REFERENCES Oferta (id_oferta)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Ubezpieczyciel_Adres (table: Ubezpieczyciel)
ALTER TABLE Ubezpieczyciel ADD CONSTRAINT Ubezpieczyciel_Adres
    FOREIGN KEY (id_adres)
    REFERENCES Adres (id_adres)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Uprawa_Gatunek (table: Uprawa)
ALTER TABLE Uprawa ADD CONSTRAINT Uprawa_Gatunek
    FOREIGN KEY (id_gatunek)
    REFERENCES Gatunek (id_gatunek)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Uprawa_Kalkulacja (table: Uprawa)
ALTER TABLE Uprawa ADD CONSTRAINT Uprawa_Kalkulacja
    FOREIGN KEY (id_kalkulacja)
    REFERENCES Kalkulacja (id_kalkulacja)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Uprawa_Klasa_gleby (table: Uprawa)
ALTER TABLE Uprawa ADD CONSTRAINT Uprawa_Klasa_gleby
    FOREIGN KEY (id_klasa_gleby)
    REFERENCES Klasa_gleby (id_klasa_gleby)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Uprawa_Rodzaj_uprawy (table: Uprawa)
ALTER TABLE Uprawa ADD CONSTRAINT Uprawa_Rodzaj_uprawy
    FOREIGN KEY (id_rodzaj_uprawy)
    REFERENCES Rodzaj_uprawy (id_rodzaj_uprawy)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Uprawa_gatunek_Gatunek (table: Uprawa_gatunek)
ALTER TABLE Uprawa_gatunek ADD CONSTRAINT Uprawa_gatunek_Gatunek
    FOREIGN KEY (id_gatunek)
    REFERENCES Gatunek (id_gatunek)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Uprawa_gatunek_Rodzaj_uprawy (table: Uprawa_gatunek)
ALTER TABLE Uprawa_gatunek ADD CONSTRAINT Uprawa_gatunek_Rodzaj_uprawy
    FOREIGN KEY (id_rodzaj_uprawy)
    REFERENCES Rodzaj_uprawy (id_rodzaj_uprawy)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Uzytkownik_Agent (table: Uzytkownik)
ALTER TABLE Uzytkownik ADD CONSTRAINT Uzytkownik_Agent
    FOREIGN KEY (id_agent)
    REFERENCES Agent (id_agent)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Zwierze_Kalkulacja (table: Zwierze)
ALTER TABLE Zwierze ADD CONSTRAINT Zwierze_Kalkulacja
    FOREIGN KEY (id_kalkulacja)
    REFERENCES Kalkulacja (id_kalkulacja)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Zwierze_Rodzaj_zwierzecia (table: Zwierze)
ALTER TABLE Zwierze ADD CONSTRAINT Zwierze_Rodzaj_zwierzecia
    FOREIGN KEY (id_rodzaj_zwierzecia)
    REFERENCES Rodzaj_zwierzecia (id_rodzaj_zwierzecia)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- End of file.