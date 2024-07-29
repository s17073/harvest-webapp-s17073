-- table teryt

truncate table harvest.teryt cascade;

ALTER SEQUENCE harvest.teryt_id_teryt_seq RESTART WITH 1;



insert into harvest.teryt
(kod_teryt, wojewodztwo, powiat, gmina, typ)
VALUES
(100,'mazwwieckie','warszawski','Warszawa-miasto','miasto');
table harvest.teryt;