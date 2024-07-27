-- table rodzaj_zwierzecia

truncate table harvest.rodzaj_zwierzecia cascade;

ALTER SEQUENCE harvest.rodzaj_zwierzecia_id_rodzaj_zwierzecia_seq RESTART WITH 1;

insert into harvest.rodzaj_zwierzecia
(nazwa, wartosc_rynkowa, wartosc_max, taryfa, czy_aktywna, data_dodania, data_usuniecia)
values
('krowa', 14200, 14200*1.2, 'WIOSNA', true, current_timestamp, null),
('koń', 14400, 14400*1.2, 'WIOSNA', true, current_timestamp, null),
('owca', 1100, 1100*1.2, 'WIOSNA', true, current_timestamp, null),
('świnia', 1800, 1800*1.2, 'WIOSNA', true, current_timestamp, null),
('kura', 60, 60*1.2, 'WIOSNA', true, current_timestamp, null),
('gęś', 250, 250*1.2, 'WIOSNA', true, current_timestamp, null);


table harvest.rodzaj_zwierzecia;