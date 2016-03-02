INSERT INTO person (person_uuid, first_name, last_name, birthdate) VALUES ('73c30299-e6c7-475f-a68b-61d6eb9b65a2', 'Matthias', 'Diekmann', '1980-07-11');
INSERT INTO person (person_uuid, first_name, last_name, birthdate) VALUES ('73c30299-e6c7-475f-a68b-61d6eb9b65a1', 'Wolfgang', 'Diekmann', '1944-03-03');
INSERT INTO person (person_uuid, first_name, last_name, birthdate) VALUES ('73c30299-e6c7-475f-a68b-61d6eb9b65a3', 'Christa', 'Diekmann', '1953-04-10');


INSERT INTO relations (person_uuid, RELATIVE, relation_type) VALUES ('73c30299-e6c7-475f-a68b-61d6eb9b65a2', '73c30299-e6c7-475f-a68b-61d6eb9b65a1', 'parent');
INSERT INTO relations (person_uuid, RELATIVE, relation_type) VALUES ('73c30299-e6c7-475f-a68b-61d6eb9b65a2', '73c30299-e6c7-475f-a68b-61d6eb9b65a3', 'parent');
-- SELECT * FROM person LIMIT 1;