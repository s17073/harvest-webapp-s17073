-- table klasa_gleby

truncate table harvest.klasa_gleby cascade;

ALTER SEQUENCE harvest.klasa_gleby_id_klasa_gleby_seq RESTART WITH 1;

insert into harvest.klasa_gleby
(klasa_gleby, taryfa, czy_aktywna, data_dodania, data_usuniecia, opis)
VALUES
('I', 'CAŁOROCZNA', true, current_timestamp, null, 'Gleby klasy I – gleby orne najlepsze. Są to: czarnoziemy, rędziny kredowe, gleby brunatne (tylko te bogate w próchnicę), mady. Są to gleby najbardziej zasobne w składniki pokarmowe, łatwe do uprawy (przewiewne, ciepłe, nie zaskorupiające się).'),
('II', 'CAŁOROCZNA', true, current_timestamp, null, 'Gleby klasy II – gleby orne bardzo dobre. Mają skład i właściwości podobne (lub nieco gorsze) jak gleby klasy I, jednak położone są w mniej korzystnych warunkach terenowych, co powoduje, że plony roślin uprawianych na tej klasie gleb, mogą być niższe niż na glebach klasy I.'),
('IIIa', 'CAŁOROCZNA', true, current_timestamp, null, 'Gleby klasy III (a i b) – gleby orne średnio dobre. Gleby brunatne, gleby bielicowe. W porównaniu do gleb klas I i II, mają gorsze właściwości fizyczne i chemiczne. Odznaczają się dużym wahaniem poziomu wody w zależności od opadów atmosferycznych. Na glebach tej klasy można już zaobserwować procesy ich degradacji.'),
('IIIb', 'CAŁOROCZNA', true, current_timestamp, null, 'Gleby klasy III (a i b) – gleby orne średnio dobre. Gleby brunatne, gleby bielicowe. W porównaniu do gleb klas I i II, mają gorsze właściwości fizyczne i chemiczne. Odznaczają się dużym wahaniem poziomu wody w zależności od opadów atmosferycznych. Na glebach tej klasy można już zaobserwować procesy ich degradacji.'),
('IVa', 'CAŁOROCZNA', true, current_timestamp, null, 'Gleby klasy IV (a i b) – gleby orne średnie. Plony roślin uprawianych na tych glebach są wyraźnie niższe niż na glebach klas wyższych, nawet gdy utrzymywane są one w dobrej kulturze rolnej. Gleby te są bardzo podatne na wahania poziomu wód gruntowych.'),
('IVb', 'CAŁOROCZNA', true, current_timestamp, null, 'Gleby klasy IV (a i b) – gleby orne średnie. Plony roślin uprawianych na tych glebach są wyraźnie niższe niż na glebach klas wyższych, nawet gdy utrzymywane są one w dobrej kulturze rolnej. Gleby te są bardzo podatne na wahania poziomu wód gruntowych.'),
('V', 'CAŁOROCZNA', true, current_timestamp, null, 'Gleby klasy V – gleby orne słabe. Do tej klasy należą gleby kamieniste lub piaszczyste o niskim poziomie próchnicy. Są ubogie w substancje organiczne. Do tej klasy zalicza się również gleby orne słabe położone na terenach nie zmeliorowanych albo takich, które do melioracji się nie nadają.'),
('VI', 'CAŁOROCZNA', true, current_timestamp, null, 'Gleby klasy VI - gleby orne najsłabsze. Próba uprawy roślin na glebach tej klasy niesie ze sobą duże ryzyko uzyskania bardzo niskich plonów, bardzo niski poziom próchnicy. Wyróżnia się klasę VIz nadającą się tylko do zalesienia.');

table harvest.klasa_gleby;