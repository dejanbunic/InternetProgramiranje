-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ipbaza
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ipbaza
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ipbaza` DEFAULT CHARACTER SET utf8 ;
USE `ipbaza` ;

-- -----------------------------------------------------
-- Table `ipbaza`.`administrator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipbaza`.`administrator` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(256) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ipbaza`.`call_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipbaza`.`call_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(1024) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ipbaza`.`call`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipbaza`.`call` (
  `name` VARCHAR(1024) NOT NULL,
  `time` DATETIME NOT NULL,
  `location` VARCHAR(1024) NOT NULL,
  `description` VARCHAR(1024) NOT NULL,
  `picture` VARCHAR(1024) NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `call_category_id` INT NOT NULL,
  `fake` TINYINT NOT NULL DEFAULT '0',
  `block` TINYINT NOT NULL DEFAULT '0',
  `creationTime` DATETIME NOT NULL,
  PRIMARY KEY (`id`, `call_category_id`),
  INDEX `fk_call_call_category1_idx` (`call_category_id` ASC) VISIBLE,
  CONSTRAINT `fk_call_call_category1`
    FOREIGN KEY (`call_category_id`)
    REFERENCES `ipbaza`.`call_category` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ipbaza`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipbaza`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  `userGrou` INT NULL DEFAULT NULL,
  `email` VARCHAR(45) NOT NULL,
  `firstName` VARCHAR(45) NULL DEFAULT NULL,
  `lastName` VARCHAR(45) NULL DEFAULT NULL,
  `country` VARCHAR(45) NULL DEFAULT NULL,
  `picture` VARCHAR(127) NULL DEFAULT NULL,
  `region` VARCHAR(127) NULL DEFAULT NULL,
  `city` VARCHAR(127) NULL DEFAULT NULL,
  `alpha2Code` VARCHAR(2) NULL DEFAULT NULL,
  `blocked` TINYINT NOT NULL DEFAULT '0',
  `logged` TINYINT NOT NULL DEFAULT '0',
  `registered` TINYINT NOT NULL DEFAULT '0',
  `mail_notification` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 424
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ipbaza`.`notification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipbaza`.`notification` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  `avatar` VARCHAR(1024) NOT NULL,
  `name` VARCHAR(1024) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Objava_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_Objava_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ipbaza`.`user` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
AUTO_INCREMENT = 147
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ipbaza`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipbaza`.`comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(1024) NULL DEFAULT NULL,
  `picture` VARCHAR(1024) NULL DEFAULT NULL,
  `notification_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `avatar` VARCHAR(1024) NOT NULL,
  PRIMARY KEY (`id`, `notification_id`, `user_id`),
  INDEX `fk_comment_notification1_idx` (`notification_id` ASC) VISIBLE,
  INDEX `fk_comment_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_notification1`
    FOREIGN KEY (`notification_id`)
    REFERENCES `ipbaza`.`notification` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ipbaza`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 532
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ipbaza`.`danger`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipbaza`.`danger` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `name` VARCHAR(1024) NOT NULL,
  `lat` FLOAT(10,6) NULL DEFAULT NULL,
  `lng` FLOAT(10,6) NULL DEFAULT NULL,
  `date` DATETIME NOT NULL,
  `description` VARCHAR(1024) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_danger_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_danger_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ipbaza`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 60
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ipbaza`.`danger_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipbaza`.`danger_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(1024) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ipbaza`.`danger_has_danger_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipbaza`.`danger_has_danger_category` (
  `danger_id` INT NOT NULL,
  `danger_category_id` INT NOT NULL,
  PRIMARY KEY (`danger_id`, `danger_category_id`),
  INDEX `fk_danger_has_danger_category_danger_category1_idx` (`danger_category_id` ASC) VISIBLE,
  INDEX `fk_danger_has_danger_category_danger1_idx` (`danger_id` ASC) VISIBLE,
  CONSTRAINT `fk_danger_has_danger_category_danger1`
    FOREIGN KEY (`danger_id`)
    REFERENCES `ipbaza`.`danger` (`id`),
  CONSTRAINT `fk_danger_has_danger_category_danger_category1`
    FOREIGN KEY (`danger_category_id`)
    REFERENCES `ipbaza`.`danger_category` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ipbaza`.`link`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipbaza`.`link` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `link` VARCHAR(1024) NOT NULL,
  `Notification_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Notification_id`),
  INDEX `fk_Link_Objava1_idx` (`Notification_id` ASC) VISIBLE,
  CONSTRAINT `fk_Link_Objava1`
    FOREIGN KEY (`Notification_id`)
    REFERENCES `ipbaza`.`notification` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 58
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ipbaza`.`log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipbaza`.`log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` DATETIME NULL DEFAULT NULL,
  `logout` DATETIME NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_table1_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_table1_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `ipbaza`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 496
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ipbaza`.`picture`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipbaza`.`picture` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `link` VARCHAR(1024) NULL DEFAULT NULL,
  `notification_id` INT NOT NULL,
  PRIMARY KEY (`id`, `notification_id`),
  INDEX `fk_picture_notification1_idx` (`notification_id` ASC) VISIBLE,
  CONSTRAINT `fk_picture_notification1`
    FOREIGN KEY (`notification_id`)
    REFERENCES `ipbaza`.`notification` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ipbaza`.`text`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipbaza`.`text` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `link` VARCHAR(1024) NULL DEFAULT NULL,
  `Notification_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Notification_id`),
  INDEX `fk_Text_Notification1_idx` (`Notification_id` ASC) VISIBLE,
  CONSTRAINT `fk_Text_Notification1`
    FOREIGN KEY (`Notification_id`)
    REFERENCES `ipbaza`.`notification` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 34
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ipbaza`.`video`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipbaza`.`video` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `link` VARCHAR(1024) NULL DEFAULT NULL,
  `Notification_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Notification_id`),
  INDEX `fk_Video_Objava1_idx` (`Notification_id` ASC) VISIBLE,
  CONSTRAINT `fk_Video_Objava1`
    FOREIGN KEY (`Notification_id`)
    REFERENCES `ipbaza`.`notification` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 49
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
