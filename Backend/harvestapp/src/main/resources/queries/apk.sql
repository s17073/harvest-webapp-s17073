-- table apk

truncate table harvest.apk cascade;

ALTER SEQUENCE harvest.apk_id_apk_seq RESTART WITH 1;

insert into harvest.apk
(pytanie, czy_aktywna, komunikat, data_dodania, data_usuniecia)
VALUES
('Czy jesteś zainteresowany ubezpieczeniem zwierząt?', true, 'Zapraszamy do zapoznania się z ofertą ubezpieczenia zwierząt.', current_timestamp, null),
('Czy jesteś zainteresowany ubezpieczeniem upraw?', true, 'Zapraszamy do zapoznania się z ofertą ubezpieczenia upraw.', current_timestamp, null),
('Czy jesteś zainteresowany ubezpieczeniem domów?', true, 'Na ten moment nie oferujemy ubezpieczenia domów i mieszkań.', current_timestamp, null);

table harvest.apk;