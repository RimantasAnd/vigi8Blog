CREATE DATABASE IF NOT EXISTS `vigi8blog`;
 CREATE TABLE IF NOT EXISTS `vigi8blog`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nick_name` VARCHAR(64) NOT NULL COMMENT 'user nick name' ,
  `password` VARCHAR(512) NOT NULL COMMENT 'hashed' ,
  `last_ip` VARCHAR(15)  COMMENT 'last seen IP' ,
  `name` VARCHAR(32)  comment 'user firs name',
  `surname` VARCHAR(32)  comment 'user family name',
  `email` VARCHAR(128)  comment 'user email',
  `admin_lock` BOOLEAN NOT NULL DEFAULT FALSE COMMENT 'locked by admin' ,
  `last_login_time` DATETIME  COMMENT 'user create time',
  `passwd_hashed` BOOLEAN NOT NULL DEFAULT FALSE COMMENT 'password is hashed' ,
  UNIQUE `nick_name` (`nick_name`))
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `vigi8blog`.`posts` (
`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 `title` VARCHAR(255) NOT NULL COMMENT 'post title' ,
 `post_text` TEXT NOT NULL COMMENT 'post text' ,
 `author_id` BIGINT NOT NULL COMMENT 'post author id' ,
 `post_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'post time' ,
 `posted_ip` VARCHAR(15) COMMENT 'post IP address',
 `admin_locked` BOOLEAN NOT NULL DEFAULT FALSE COMMENT 'post locked by Admin',
 `comments_locked` BOOLEAN NOT NULL DEFAULT FALSE COMMENT 'post comments is locked',
 `propose_to_delete` BOOLEAN NOT NULL DEFAULT FALSE COMMENT 'post proposed to delete',
 FOREIGN KEY (`author_id`) REFERENCES `users`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
  ) ENGINE = InnoDB;

--  ALTER TABLE vigi8blog`.`post`
--  ADD FOREIGN KEY (PersonID) REFERENCES Persons(PersonID);

 CREATE TABLE IF NOT EXISTS `vigi8blog`.`comments` (
 `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 `temporary_nic` VARCHAR(16) COMMENT 'comment written as',
 `user_id` BIGINT NOT NULL,
 `parent_post_id` BIGINT NOT NULL ,
 `comment_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'comment time',
 `comment_text` VARCHAR (2048) NOT NULL COMMENT 'comment text' ,
 `admin_locked` BOOLEAN NOT NULL DEFAULT FALSE COMMENT 'comment locked by Admin',
 `commented_ip` VARCHAR(15) COMMENT 'comment IP address',
 `propose_to_delete` BOOLEAN NOT NULL DEFAULT FALSE COMMENT 'comment proposed to delete',
 FOREIGN KEY (`parent_post_id`) REFERENCES `posts`(`id`) ON DELETE CASCADE ON UPDATE CASCADE,
 FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
 ) ENGINE = InnoDB;






