CREATE SCHEMA IF NOT EXISTS `blog`;

USE `blog`;

CREATE TABLE IF NOT EXISTS `users` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `encrypted_pass` VARCHAR(1000) NOT NULL,
  `create_date` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `email_UNIQUE` (`email`)
);

CREATE TABLE IF NOT EXISTS `posts` (
  `idPost` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  `user_id` INT NOT NULL,
  `create_date` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idPost`),
  INDEX `fk_user_id_post_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_id_post`
    FOREIGN KEY (`user_id`)
    REFERENCES `blog`.`users` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `tags` (
  `idTag` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `create_date` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idTag`),
  UNIQUE KEY `name_UNIQUE` (`name`)
);

CREATE TABLE IF NOT EXISTS `comments` (
  `idComment` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(255) NOT NULL,
  `user_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  `create_date` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idComment`),
  INDEX `fk_user_comment_idx` (`user_id` ASC),
  INDEX `fk_post_comment_idx` (`post_id` ASC),
  CONSTRAINT `fk_user_comment`
    FOREIGN KEY (`user_id`)
    REFERENCES `blog`.`users` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_comment`
    FOREIGN KEY (`post_id`)
    REFERENCES `blog`.`posts` (`idPost`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `post_tags` (
  `idPost_tags` INT NOT NULL AUTO_INCREMENT,
  `post_id` INT NOT NULL,
  `tag_id` INT NOT NULL,
  `create_date` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idPost_tags`),
  INDEX `fk_post_id_idx` (`post_id` ASC),
  INDEX `fk_tag_id_idx` (`tag_id` ASC),
  UNIQUE KEY `post_tag_unique` (`post_id`, `tag_id`),
  CONSTRAINT `fk_post_id`
    FOREIGN KEY (`post_id`)
    REFERENCES `blog`.`posts` (`idPost`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tag_id`
    FOREIGN KEY (`tag_id`)
    REFERENCES `blog`.`tags` (`idTag`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
);