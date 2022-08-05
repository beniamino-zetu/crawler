CREATE TABLE IF NOT EXISTS COUNTRIES(
    country_id INTEGER PRIMARY KEY,
    country_name TEXT UNIQUE,
    population INTEGER,
    area NUMERIC(10,2)
);

CREATE TABLE IF NOT EXISTS CITIES (
    city_id INTEGER PRIMARY KEY,
    country_id INTEGER,
    name TEXT UNIQUE,
    population INTEGER,
    density NUMERIC (10,2)
);
