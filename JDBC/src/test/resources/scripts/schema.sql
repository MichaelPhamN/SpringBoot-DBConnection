--DROP TABLE Account IF EXISTS;

CREATE TABLE Account (
   id INTEGER PRIMARY KEY,
   email VARCHAR(30),
   password  VARCHAR(50)
);
