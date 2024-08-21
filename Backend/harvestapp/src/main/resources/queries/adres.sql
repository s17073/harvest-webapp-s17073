-- table adres

truncate table harvest.adres cascade;

ALTER SEQUENCE harvest.adres_id_adres_seq RESTART WITH 1;



insert into harvest.adres
(id_teryt, kod_pocztowy, miejscowosc, ulica, numer_domu, numer_mieszkania)
VALUES
('1465011', '00-000', 'Warszawa', 'Al. Jerozolimskie', '0', '0');

table harvest.adres;