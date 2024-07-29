-- table ubezpieczyciel

truncate table harvest.ubezpieczyciel cascade;

ALTER SEQUENCE harvest.ubezpieczyciel_id_ubezpieczyciel_seq RESTART WITH 1;



insert into harvest.ubezpieczyciel
(id_adres, nazwa, numer_zakladu, nip, krs, czy_aktywna, data_dodania, data_usuniecia, numer_telefonu, numer_konta)
VALUES
(1, 'Ubezpieczyciel1','U00001','00000','00000',true,current_timestamp,null,'00000','00000'),
(1, 'Ubezpieczyciel2','U00001','00000','00000',true,current_timestamp,null,'00000','00000');

table harvest.ubezpieczyciel;