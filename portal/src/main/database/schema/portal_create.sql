SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `portal` ;
USE `portal` ;

-- -----------------------------------------------------
-- Table `portal`.`table_adress`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `portal`.`table_adress` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `city` VARCHAR(45) NULL ,
  `country` VARCHAR(45) NULL ,
  `number` VARCHAR(45) NULL ,
  `street` VARCHAR(45) NULL ,
  `zip` VARCHAR(45) NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portal`.`table_geo_position`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `portal`.`table_geo_position` (
  `ID` INT NOT NULL ,
  `gp_latitude` DOUBLE NOT NULL ,
  `gp_longitude` DOUBLE NOT NULL ,
  `gp_timestamp` DATETIME NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portal`.`table_user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `portal`.`table_user` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `version` VARCHAR(45) NULL ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(255) NULL ,
  `firstname` VARCHAR(45) NULL ,
  `lastname` VARCHAR(45) NULL ,
  `email` VARCHAR(45) NULL ,
  `status` VARCHAR(45) NULL ,
  `ADRESS_ID` INT NULL ,
  `REGISTRATIONGEOPOSITION_ID` INT NULL ,
  PRIMARY KEY (`ID`) ,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) ,
  INDEX `fk_table_user_table_adress` (`ADRESS_ID` ASC) ,
  INDEX `fk_table_user_table_geo_position1` (`REGISTRATIONGEOPOSITION_ID` ASC) ,
  CONSTRAINT `fk_table_user_table_adress`
    FOREIGN KEY (`ADRESS_ID` )
    REFERENCES `portal`.`table_adress` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table_user_table_geo_position1`
    FOREIGN KEY (`REGISTRATIONGEOPOSITION_ID` )
    REFERENCES `portal`.`table_geo_position` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portal`.`table_series`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `portal`.`table_series` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `version` VARCHAR(45) NULL ,
  `name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portal`.`table_conference`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `portal`.`table_conference` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `version` VARCHAR(45) NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `startdate` DATE NOT NULL ,
  `enddate` DATE NOT NULL ,
  `description` VARCHAR(45) NULL ,
  `location` VARCHAR(45) NULL ,
  `venue` VARCHAR(45) NULL ,
  `accomodation` VARCHAR(45) NULL ,
  `howtofind` VARCHAR(45) NULL ,
  `CREATOR_ID` INT NULL ,
  `SERIES_ID` INT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_table_conference_table_user1` (`CREATOR_ID` ASC) ,
  INDEX `fk_table_conference_table_series1` (`SERIES_ID` ASC) ,
  CONSTRAINT `fk_table_conference_table_user1`
    FOREIGN KEY (`CREATOR_ID` )
    REFERENCES `portal`.`table_user` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table_conference_table_series1`
    FOREIGN KEY (`SERIES_ID` )
    REFERENCES `portal`.`table_series` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portal`.`table_category`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `portal`.`table_category` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `version` VARCHAR(45) NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `PARENT_ID` INT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_table_category_table_category1` (`PARENT_ID` ASC) ,
  CONSTRAINT `fk_table_category_table_category1`
    FOREIGN KEY (`PARENT_ID` )
    REFERENCES `portal`.`table_category` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portal`.`table_conference_has_category`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `portal`.`table_conference_has_category` (
  `table_conference_ID` INT NOT NULL ,
  `table_category_ID` INT NOT NULL ,
  PRIMARY KEY (`table_conference_ID`, `table_category_ID`) ,
  INDEX `fk_table_conference_has_category_table_category1` (`table_category_ID` ASC) ,
  CONSTRAINT `fk_table_conference_has_category_table_conference1`
    FOREIGN KEY (`table_conference_ID` )
    REFERENCES `portal`.`table_conference` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table_conference_has_category_table_category1`
    FOREIGN KEY (`table_category_ID` )
    REFERENCES `portal`.`table_category` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portal`.`table_series_has_user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `portal`.`table_series_has_user` (
  `table_series_ID` INT NOT NULL ,
  `table_user_ID` INT NOT NULL ,
  PRIMARY KEY (`table_series_ID`, `table_user_ID`) ,
  INDEX `fk_table_series_has_user_table_user1` (`table_user_ID` ASC) ,
  CONSTRAINT `fk_table_series_has_user_table_series1`
    FOREIGN KEY (`table_series_ID` )
    REFERENCES `portal`.`table_series` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table_series_has_user_table_user1`
    FOREIGN KEY (`table_user_ID` )
    REFERENCES `portal`.`table_user` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portal`.`sequence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `portal`.`sequence` (
  `SEQ_NAME` VARCHAR(45) NOT NULL ,
  `SEQ_COUNT` INT NULL ,
  PRIMARY KEY (`SEQ_NAME`) )
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
