CREATE TABLE IF NOT EXISTS person (
  person_uuid VARCHAR,
  first_name VARCHAR,
  last_name VARCHAR,
  birthdate DATE,
);

CREATE TABLE IF NOT EXISTS relations (
  person_uuid VARCHAR,
  relative VARCHAR,
  relation_type VARCHAR
)