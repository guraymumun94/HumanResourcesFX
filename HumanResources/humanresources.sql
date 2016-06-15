DROP DATABASE IF EXISTS `humanresources`;
CREATE DATABASE `humanresources`;

CREATE TABLE `humanresources`.`job`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(100) NOT NULL,
	`salary` INT NOT NULL,
	PRIMARY KEY(`id`)
) ENGINE = InnoDB;

CREATE TABLE `humanresources`.`vacancy`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`type` ENUM("Постоянна работа", "Временна работа", "Стаж") NOT NULL,
	`count` INT NOT NULL,
	`description` VARCHAR(255) NULL DEFAULT NULL,
	`job_id` INT NOT NULL,
	PRIMARY KEY(`id`),
	FOREIGN KEY(`job_id`) REFERENCES `humanresources`.`job`(`id`)
		ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE `humanresources`.`employee`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(100) NOT NULL,
	`type` ENUM("По трудово правоотношение", "По заместване", "Граждански договор") NOT NULL,
	`job_type` ENUM("Постоянна работа", "Временна работа", "Стаж") NOT NULL,
	`address` VARCHAR(100) NOT NULL,
	`shift` INT NULL DEFAULT NULL,
	`bonus` INT NULL DEFAULT NULL,
	`assessment` ENUM("1", "2", "3", "4", "5", "6", "7", "8", "9", "10") NULL DEFAULT NULL,
	`working_days` INT NULL DEFAULT NULL,
	`wage` INT NULL DEFAULT NULL,
	`UCN` BIGINT(10) NOT NULL NULL,
	`email` VARCHAR(100) NOT NULL,
	`phone_number` VARCHAR(100) NOT NULL,
	`start_date` DATE NOT NULL,
	`job_id` INT NOT NULL,
	PRIMARY KEY(`id`),
	FOREIGN KEY(`job_id`) REFERENCES `humanresources`.`job`(`id`)
		ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE `humanresources`.`former_employee`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(100) NOT NULL,
	`job_type` ENUM("Постоянна работа", "Временна работа", "Стаж") NOT NULL,
	`address` VARCHAR(100) NOT NULL,
	`email` VARCHAR(100) NOT NULL,
	`phone_number` VARCHAR(100) NOT NULL,
	`start_date` DATE NOT NULL,
	`end_date` DATE NOT NULL,
	`job_id` INT NOT NULL,
	PRIMARY KEY(`id`),
	FOREIGN KEY(`job_id`) REFERENCES `humanresources`.`job`(`id`) ON DELETE CASCADE ON UPDATE CASCADE) ENGINE = InnoDB;

CREATE TABLE `humanresources`.`candidate`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(100) NOT NULL,
	`address` VARCHAR(100) NOT NULL,
	`email` VARCHAR(100) NOT NULL,
	`phone_number` VARCHAR(100) NOT NULL,
/*`type` ENUM("По трудово правоотношение", "По заместване", "Граждански договор") NULL DEFAULT NULL,*/
	`vacancy_id` INT NOT NULL,
	PRIMARY KEY(`id`),
	FOREIGN KEY(`vacancy_id`) REFERENCES `humanresources`.`vacancy`(`id`) ON DELETE CASCADE ON UPDATE CASCADE) ENGINE = InnoDB;

CREATE TABLE `humanresources`.`application`(
	`id` INT NOT NULL UNIQUE,
	`cover_letter` VARCHAR(255) NULL DEFAULT NULL,
	`skills` VARCHAR(255) NULL DEFAULT NULL,
	`education` VARCHAR(255) NULL DEFAULT NULL,
	`experience` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY(`id`),
	FOREIGN KEY(`id`) REFERENCES `humanresources`.`candidate`(`id`) ON DELETE CASCADE ON UPDATE CASCADE) ENGINE = InnoDB;

CREATE TABLE `humanresources`.`absence`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`date` DATE NOT NULL,
	`type` ENUM("По болести", "Платени", "Неплатени") NOT NULL,
	`employee_id` INT NOT NULL,
	PRIMARY KEY(`id`),
	FOREIGN KEY(`employee_id`) REFERENCES `humanresources`.`employee`(`id`)
		ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

/*
Cannot delete or update a parent row: a foreign key constraint fails
SET FOREIGN_KEY_CHECKS=0; -- to disable them
SET FOREIGN_KEY_CHECKS=1; -- to re-enable them
*/

/*******************************************************************************************/
/*INSERTS*/
INSERT INTO `humanresources`.`job`(`id`, `title`, `salary`)
VALUES (NULL, 'Специализация1', 2000),
       (NULL, 'Специализация2', 1000), 
       (NULL, 'Специализация3', 800);
	   
INSERT INTO `humanresources`.`vacancy`(`id`, `type`, `count`, `description`,`job_id`)
VALUES (NULL, "Стаж", 3, NULL, 3),
       (NULL, "Постоянна работа", 1, 'Description1', 1),
       (NULL, "Стаж", 4, NULL, 3),
       (NULL, "Постоянна работа", 2, 'Description4', 1),
       (NULL, "Временна работа", 2, NULL, 2),
	(NULL, "Постоянна работа", 1, NULL, 1),
	(NULL, "Постоянна работа", 2, NULL, 2),
	(NULL, "Временна работа", 1, NULL, 1),
       (NULL, "Стаж", 5, NULL, 3);

INSERT INTO `humanresources`.`employee`(`id`, `name`, `type`, `job_type`, `address`,`shift`, `bonus`, `assessment`, `working_days`, `wage`, `UCN`, `email`, `phone_number`, `start_date`, `job_id`)
VALUES (NULL, 'Employee1', "По трудово правоотношение", "Стаж", 'София', NULL, NULL, NULL, NULL, NULL, 929456, 'email1', '+359 01', '2006-12-15', 3),
       (NULL, 'Employee2', "Граждански договор", "Постоянна работа", 'София', NULL, NULL, NULL, NULL, NULL, 823456, 'email2', '+359 02', '2007-06-24', 2),
       (NULL, 'Employee3', "По трудово правоотношение", "Стаж", 'София', NULL, NULL, NULL, NULL, NULL, 884456, 'email3', '+359 03', '2008-03-01', 3),
       (NULL, 'Employee4', "Граждански договор", "Постоянна работа", 'София', NULL, NULL, NULL, NULL, NULL, 893456, 'email4', '+359 04', '2009-08-20', 1),
       (NULL, 'Employee5', "По трудово правоотношение", "Временна работа", 'София', NULL, NULL, NULL, NULL, NULL, 793456, 'email5', '+359 05', '2016-04-15', 2),
       (NULL, 'Employee6', "Граждански договор", "Стаж", 'София', NULL, NULL, NULL, NULL, NULL, 943456, 'email6', '+359 06', '2006-12-15', 3);

INSERT INTO `humanresources`.`former_employee`(`id`, `name`, `job_type`, `address`, `email`, `phone_number`, `start_date`, `end_date`, `job_id`)
VALUES (NULL, 'FormerEmployee1', "Постоянна работа", 'София', 'email1', '+359 01', '2000-12-15', '2003-12-15', 1),
       (NULL, 'FormerEmployee2', "Постоянна работа", 'София', 'email2', '+359 02', '2001-06-24', '2004-12-15', 1),
       (NULL, 'FormerEmployee3', "Временна работа", 'София', 'email3', '+359 03', '2002-03-01', '2005-12-15', 2),
       (NULL, 'FormerEmployee4', "Временна работа", 'София', 'email4', '+359 04', '2003-08-20', '2006-12-15', 2),
       (NULL, 'FormerEmployee5', "Постоянна работа", 'София', 'email5', '+359 05', '2004-04-15', '2007-12-15', 2),
       (NULL, 'FormerEmployee6', "Временна работа", 'София', 'email6', '+359 06', '1999-12-15', '2002-12-15', 1);

INSERT INTO `humanresources`.`candidate`(`id`, `name`, `address`, `email`, `phone_number`, `vacancy_id`)
VALUES (NULL, 'Candidate1', 'София', 'email1', '+359 01', 1),
       (NULL, 'Candidate2', 'София', 'email2', '+359 02', 5),
       (NULL, 'Candidate3', 'София', 'email3', '+359 03', 2),
       (NULL, 'Candidate4', 'София', 'email4', '+359 04', 4),
       (NULL, 'Candidate5', 'София', 'email5', '+359 05', 5),
       (NULL, 'Candidate6', 'София', 'email6', '+359 06', 1);

INSERT INTO `humanresources`.`application`(`id`, `cover_letter`, `skills`, `education`, `experience`)
VALUES (6, 'CoverLetter6', NULL, NULL, NULL),
(5, 'CoverLetter5', NULL, NULL, NULL),
(4, 'CoverLetter4', NULL, NULL, NULL),
(3, 'CoverLetter3', NULL, NULL, NULL),
(2, 'CoverLetter2', NULL, NULL, NULL),	
(1, 'CoverLetter1', NULL, NULL, NULL);

INSERT INTO `humanresources`.`absence`(`id`, `date`, `type`, `employee_id`)
VALUES (NULL, '2016-05-16', "По болести", 1),
       (NULL, '2016-05-17', "Платени", 1),
       (NULL, '2016-05-18', "Неплатени", 2),
       (NULL, '2016-05-19', "Платени", 3),
       (NULL, '2016-05-20', "Неплатени", 4),
       (NULL, '2016-05-13', "По болести", 5);


