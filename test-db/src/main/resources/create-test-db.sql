DROP TABLE IF EXISTS APARTMENT;
CREATE TABLE APARTMENT
(
    APARTMENT_ID         INTEGER PRIMARY KEY AUTO_INCREMENT,
    APARTMENT_NUMBER     INTEGER NOT NULL UNIQUE ,
    COUNT_OF_PLACES INTEGER NOT NULL,
    APARTMENT_CLASS      VARCHAR(30) NOT NULL
);