-----------------------------------------------------------------------
-- - reconstruction de la base de donnees
-----------------------------------------------------------------------
DROP DATABASE IF EXISTS jdbcdb;
CREATE DATABASE jdbcdb;
USE jdbcdb;

------------------------------------------------------------------------
-- -construction de la table des utilisateurs
------------------------------------------------------------------------
CREATE TABLE utilisateur(
    idUser               int(4) PRIMARY KEY AUTO_INCREMENT,
    login                varchar(20) NOT NULL,
    passworld            varchar(20) NOT NULL,
    nombreDeConnection   int(4)      NOT NULL DEFAULT 0 
);

INSERT INTO utilisateur(login,passworld) VALUES('toto','123toto');
INSERT INTO utilisateur(login,passworld) VALUES('titi','123titi');
INSERT INTO utilisateur(login,passworld) VALUES('tom','123tom');
INSERT INTO utilisateur(login,passworld) VALUES('jery','123jery');
