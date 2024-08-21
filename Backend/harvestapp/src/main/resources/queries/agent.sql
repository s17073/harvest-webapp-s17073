-- table agent

truncate table harvest.agent cascade;

ALTER SEQUENCE harvest.agent_id_agent_seq RESTART WITH 1;



insert into harvest.agent
(nazwa, kod_agencji, nip, krs, id_adres, liczba_posrednikow, numer_telefonu_agencji, czy_aktywna, data_dodania, data_usuniecia)
VALUES
    ('Agent1','A00001','000000','000000',1,20,'300100200', true, current_timestamp, null),
    ('Agent2','A00001','000000','000000',1,50,'300100200', true, current_timestamp, null);

table harvest.agent;