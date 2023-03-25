-- Create User (Database)
CREATE USER spring IDENTIFIED BY spring123;

-- Grant User
GRANT CREATE SESSION to spring;
GRANT CREATE TABLE to spring;
GRANT CREATE VIEW to spring;
GRANT UNLIMITED TABLESPACE TO spring;
GRANT CREATE SEQUENCE TO spring;
GRANT CREATE TRIGGER TO spring;

-- Create table Account
CREATE SEQUENCE acc_seq START WITH 1;

CREATE TABLE Account(
                        id NUMBER(10) DEFAULT acc_seq.nextval NOT NULL,
                        email VARCHAR2(200) NOT NULL,
                        password VARCHAR2(200) NOT NULL
);

ALTER TABLE Account ADD
    CONSTRAINT acc_pk PRIMARY KEY(id);