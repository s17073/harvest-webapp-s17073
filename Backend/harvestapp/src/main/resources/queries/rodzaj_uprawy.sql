-- table rodzaj_uprawy

truncate table harvest.rodzaj_uprawy cascade;

ALTER SEQUENCE harvest.rodzaj_uprawy_id_rodzaj_uprawy_seq RESTART WITH 1;

insert into harvest.rodzaj_uprawy
(nazwa_uprawy, taryfa, czy_aktywna, wartosc_rynkowa, wartosc_max, data_dodania, data_usuniecia)
values
    ('pszenica', 'WIOSNA', true, 26600, 26600*1.2,  current_timestamp, null),
    ('rzepak', 'WIOSNA', true, 18100 , 18100*1.2, current_timestamp, null),
    ('rzepik', 'WIOSNA', true, 18100 , 18100*1.2, current_timestamp, null),
    ('kukurydza', 'WIOSNA', true, 17600, 17600*1.2, current_timestamp, null),
    ('chmiel', 'WIOSNA', true, 60000, 60000*1.2, current_timestamp, null),
    ('tytoń', 'WIOSNA', true, 46800, 60000*1.2, current_timestamp, null),
    ('drzewa', 'WIOSNA', true, 130900, 130900*1.2, current_timestamp, null),
    ('krzewy', 'WIOSNA', true, 130900, 130900*1.2, current_timestamp, null);

table harvest.rodzaj_uprawy;