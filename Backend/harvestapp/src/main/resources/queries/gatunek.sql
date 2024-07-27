-- table gatunek

truncate table harvest.gatunek cascade;

ALTER SEQUENCE harvest.gatunek_id_gatunek_seq RESTART WITH 1;

insert into harvest.gatunek
(nazwa_gatunku, czy_aktywna, data_dodania, data_usuniecia)
VALUES
('jabłka', true, current_timestamp, null),
('gruszki', true, current_timestamp, null),
('maliny', true, current_timestamp, null);

table harvest.gatunek;