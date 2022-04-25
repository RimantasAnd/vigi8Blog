 CREATE TABLE IF NOT EXISTS `vigi8blog`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nick_name` VARCHAR(64) NOT NULL COMMENT 'user nick name' ,
  `password` VARCHAR(512) NOT NULL COMMENT 'hashed' ,
  `last_ip` VARCHAR(15)  COMMENT 'last seen IP' ,
  `admin_lock` BOOLEAN NOT NULL DEFAULT FALSE COMMENT 'locked by admin' ,
  `last_login_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT 'user create time',
  UNIQUE `nick_name` (`nick_name`))
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `vigi8blog`.`post` (
`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 `title` VARCHAR(255) NOT NULL COMMENT 'post title' ,
 `post_text` TEXT NOT NULL COMMENT 'post text' ,
 `author_id` BIGINT NOT NULL COMMENT 'post author id' ,
 `post_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'post time' ,
 `posted_ip` VARCHAR(15) COMMENT 'post IP address',
 `admin_locked` BOOLEAN NOT NULL DEFAULT FALSE COMMENT 'post locked by Admin',
 `comments_locked` BOOLEAN NOT NULL DEFAULT FALSE COMMENT 'post comments is locked',
 FOREIGN KEY (`author_id`) REFERENCES `users`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
  ) ENGINE = InnoDB;

--  ALTER TABLE vigi8blog`.`post`
--  ADD FOREIGN KEY (PersonID) REFERENCES Persons(PersonID);

 CREATE TABLE IF NOT EXISTS`vigi8blog`.`comments` (
 `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 `temporary_nic` VARCHAR(16) NOT NULL COMMENT 'comment written as',
 `user_id` BIGINT NOT NULL ,
 `parent_post_id` BIGINT NOT NULL ,
 `comment_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT 'comment time',
 `comment_text` VARCHAR (2048) NOT NULL COMMENT 'comment text' ,
 `admin_locked` BOOLEAN NOT NULL DEFAULT FALSE COMMENT 'comment locked by Admin',
 `commented_ip` VARCHAR(15) COMMENT 'comment IP address',
 FOREIGN KEY (`parent_post_id`) REFERENCES `post`(`id`) ON DELETE CASCADE ON UPDATE CASCADE,
 FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
 ) ENGINE = InnoDB;






