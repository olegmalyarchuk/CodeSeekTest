-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema FootballManager
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema FootballManager
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `FootballManager` DEFAULT CHARACTER SET utf8 ;
USE `FootballManager` ;

-- -----------------------------------------------------
-- Table `FootballManager`.`Team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FootballManager`.`Team` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `wallet` INT NOT NULL,
  `commisionTeam` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idTeam_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `nameTeam_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FootballManager`.`Player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FootballManager`.`Player` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `age` INT NOT NULL,
  `expreienceMonths` INT NOT NULL,
  `teamId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idPlayer_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `idTeam0_idx` (`teamId` ASC) VISIBLE,
  CONSTRAINT `idTeam0`
    FOREIGN KEY (`teamId`)
    REFERENCES `FootballManager`.`Team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO footballmanager.team VALUES(1, "Liverpool", 100000, 10);
INSERT INTO footballmanager.team VALUES(2, "Flyde", 200000, 15);
INSERT INTO footballmanager.team VALUES(3, "Darwen", 125000, 5);
INSERT INTO footballmanager.team VALUES(4, "Stanley", 250000, 20);

INSERT INTO footballmanager.player VALUES(1, "Joe", "Gomez", 25, 24, 1);
INSERT INTO footballmanager.player VALUES(2, "Andrew", "Robertson", 28, 20, 1);
INSERT INTO footballmanager.player VALUES(3, "Joel", "Matip", 31, 5, 1);
INSERT INTO footballmanager.player VALUES(4, "Calvin", "Ramsav", 19, 1, 1);
INSERT INTO footballmanager.player VALUES(5, "Arthur", "Melto", 26, 48, 1);
INSERT INTO footballmanager.player VALUES(6, "James", "Milner", 36, 12, 1);
INSERT INTO footballmanager.player VALUES(7, "Chris", "Neal", 36, 24, 2);
INSERT INTO footballmanager.player VALUES(8, "Luke", "Bruke", 24, 12, 2);
INSERT INTO footballmanager.player VALUES(9, "Emeka", "Obi", 21, 2, 2);
INSERT INTO footballmanager.player VALUES(10, "Alex", "Whitmore", 27, 48, 2);
INSERT INTO footballmanager.player VALUES(11, "Sam", "Osborne", 23, 6, 2);
INSERT INTO footballmanager.player VALUES(12, "Danny", "Philliskirk", 31, 4, 2);
INSERT INTO footballmanager.player VALUES(13, "Andry", "Bell", 24, 24, 3);
INSERT INTO footballmanager.player VALUES(14, "Gary", "Brown", 31, 2, 3);
INSERT INTO footballmanager.player VALUES(15, "Dajour", "Buffonge", 27, 47, 3);
INSERT INTO footballmanager.player VALUES(16, "Peter", "Devine", 19, 45, 3);
INSERT INTO footballmanager.player VALUES(17, "Mark", "Patterson", 31, 20, 3);
INSERT INTO footballmanager.player VALUES(18, "James", "Orr", 34, 18, 3);
INSERT INTO footballmanager.player VALUES(19, "Lukas", "Jensen", 19, 10, 4);
INSERT INTO footballmanager.player VALUES(20, "Toby", "Savin", 21, 15, 4);
INSERT INTO footballmanager.player VALUES(21, "Ryan", "Astley", 36, 1, 4);
INSERT INTO footballmanager.player VALUES(22, "Mitch", "Clark", 24, 48, 4);
INSERT INTO footballmanager.player VALUES(23, "Harry", "Perritt", 27, 24, 4);
INSERT INTO footballmanager.player VALUES(24, "Doug", "Tharme", 22, 32, 4);
