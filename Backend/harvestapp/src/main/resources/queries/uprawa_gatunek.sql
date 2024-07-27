-- table uprawa_gatunek

truncate table harvest.uprawa_gatunek cascade;

ALTER SEQUENCE harvest.uprawa_gatunek_id_uprawa_gatunek_seq RESTART WITH 1;

insert into harvest.uprawa_gatunek
(id_rodzaj_uprawy, id_gatunek)
VALUES
(7,1),
(7,2),
(8,3);

table harvest.uprawa_gatunek;