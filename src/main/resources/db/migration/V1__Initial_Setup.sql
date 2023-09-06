create table customer(
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL ,
    age INT NOT NULL,
    gender TEXT NOT NULL,
    password TEXT NOT NULL
)