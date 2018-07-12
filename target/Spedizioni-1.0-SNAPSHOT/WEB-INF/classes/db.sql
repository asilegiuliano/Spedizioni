DROP DATABASE IF EXISTS `spedizioni`;
CREATE DATABASE `spedizioni` DEFAULT CHARSET = utf8;

USE `spedizioni`;

CREATE TABLE `merce` (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    codice VARCHAR(250),
    descrizione VARCHAR(250),
    peso DECIMAL(10,4)
) Engine=InnoDB;

CREATE TABLE `spedizione` (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    numero INTEGER NOT NULL,
    `data` DATE
) Engine=InnoDB;

CREATE TABLE `costo_mezzo_trasporto` (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome_mezzo VARCHAR(250) NOT NULL,
    peso_massimo DECIMAL (10,4) NOT NULL,
    costo DECIMAL (10,4) NOT NULL
) Engine=InnoDB;

CREATE TABLE `merce_spedizione` (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_merce INTEGER NOT NULL,
    id_spedizione INTEGER NOT NULL,
    quantita INTEGER NOT NULL,
    CONSTRAINT FOREIGN KEY (id_merce) REFERENCES merce(id),
    CONSTRAINT FOREIGN KEY (id_spedizione) REFERENCES spedizione(id),
    CONSTRAINT UNIQUE (id_merce, id_spedizione)
) Engine=InnoDB;

INSERT INTO `merce` (id, codice, descrizione, peso) VALUES 
    (1, 'PNDRV8', 'Pen drive USB 64G no brand', 0.15),
    (2, 'DCP-J715E', 'Stampante Brother DCP J715 W', 5.3),
    (3, 'LNVCX1', 'Notebook Lenovo Carbon X1', 1.9),
    (4, 'HUP20', 'Smartphone Huawei P20', 0.53),
    (5, 'BSHT1R', 'Ampli Combo valvolare BlackStar HT 1-R', 6);

INSERT INTO `spedizione` (id, numero, `data`) VALUES 
    (1, 34, NOW()),
    (2, 3, NOW()),
    (3, 4, NOW()),
    (4, 723, NOW()),
    (5, 23, NOW()),
    (6, 72, NOW()),
    (7, 73, NOW()),
    (8, 7, NOW()),
    (9, 31, NOW());
    
INSERT INTO `costo_mezzo_trasporto` (id, nome_mezzo, peso_massimo, costo) VALUES
    (1, 'A piedi', 1, 5.9),
    (2, 'Bicicletta', 5, 7.9),
    (3, 'Ape', 30, 12.9),
    (4, 'Drone piccolo', 1.5, 6.5),
    (5, 'Scooter', 7.5, 9.9),
    (6, 'Furgone', 40, 13.9),
    (7, 'Piccione viaggiatore', 0.7, 5.2),
    (8, 'Drone grande', 3, 6.9),
    (9, 'Automobile', 25, 11.5);

INSERT INTO `merce_spedizione` (id_spedizione, id_merce, quantita) VALUES
    (1, 3, 2),
    (2, 2, 1),
    (2, 3, 1),
    (3, 3, 1),
    (3, 4, 1),
    (4, 1, 2),
    (4, 4, 1),
    (5, 4, 2),
    (5, 1, 1),
    (6, 1, 3),
    (7, 2, 7),
    (8, 5, 1),
    (8, 3, 1),
    (9, 5, 6);