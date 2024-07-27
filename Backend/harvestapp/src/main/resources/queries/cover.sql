-- table ochrona

truncate table harvest.ochrona cascade;

ALTER SEQUENCE harvest.ochrona_id_ochrona_seq RESTART WITH 1;

insert into harvest.ochrona
(nazwa, grupa_ministerialna, taryfa, opis, czy_aktywna, data_dodania, data_usuniecia, czy_uprawa, czy_zwierze)
VALUES
    ('ogień', '08', 'CAŁOROCZNA', null, true, current_timestamp, null, true, true),
    ('gradobicie', '09', 'ZIMA', null, true, current_timestamp, null, true, false),
    ('kradzież', '09', 'CAŁOROCZNA', null, true, current_timestamp, null, false, true),
    ('wandalizm', '09', 'CAŁOROCZNA', null, true, current_timestamp, null, true, false),
    ('powódź', '08', 'CAŁOROCZNA', null, true, current_timestamp, null, true, false),
    ('susza', '08', 'WIOSNA', null, true, current_timestamp, null, true, false);

table harvest.ochrona;