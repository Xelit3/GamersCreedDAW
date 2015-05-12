DROP DATABASE gamerscreed;

CREATE DATABASE gamerscreed;

USE gamerscreed;

CREATE USER gamerscreed@'localhost' IDENTIFIED BY 'gamerscreed';
GRANT ALL ON gamerscreed.* TO gamerscreed@'localhost' IDENTIFIED BY 'gamerscreed';

CREATE TABLE roles(
	id INT(3) PRIMARY KEY AUTO_INCREMENT,
	role_name VARCHAR(50)
)ENGINE=INNODB DEFAULT CHARSET UTF8;

CREATE TABLE countries(
	id INT(3) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50)
)ENGINE=INNODB DEFAULT CHARSET UTF8;

CREATE TABLE cities(
	id INT(3) PRIMARY KEY AUTO_INCREMENT,
	id_country INT(3),
	name VARCHAR(50)
)ENGINE=INNODB DEFAULT CHARSET UTF8;

CREATE TABLE addresses(
	id INT(3) PRIMARY KEY AUTO_INCREMENT,	
	street VARCHAR(100),
	cp INT(5),
	id_city INT(3)
)ENGINE=INNODB DEFAULT CHARSET UTF8;

CREATE TABLE users(
	id INT(3) PRIMARY KEY AUTO_INCREMENT,
	id_role INT(3),
	name VARCHAR(100),
	username VARCHAR(25),
	password VARCHAR(250),
	mail VARCHAR(50),
	id_address INT(3)
)ENGINE=INNODB DEFAULT CHARSET UTF8;

CREATE TABLE users_followers(
		id_user INT(3),
		id_follower INT(3),
	PRIMARY KEY (id_user, id_follower)
);

CREATE TABLE users_followings(
		id_user INT(3),
		id_following INT(3),
	PRIMARY KEY (id_user, id_following)
);

CREATE TABLE posts(
	id INT(3) PRIMARY KEY AUTO_INCREMENT,
	id_user INT(3),
	content VARCHAR(250),
	post_date DATETIME
)ENGINE=INNODB DEFAULT CHARSET UTF8;

CREATE TABLE brands(
	id INT(3) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100),
	id_country INT(3)
)ENGINE=INNODB DEFAULT CHARSET UTF8;

CREATE TABLE videogames(
	id INT(3) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100),
	id_publisher INT(3),
	id_developer INT(3),
	year INT(4),
	confirmed BOOLEAN
)ENGINE=INNODB DEFAULT CHARSET UTF8;

CREATE TABLE users_videogames(	
	id_user INT(3),
	id_videogame INT(3),
	PRIMARY KEY (id_user, id_videogame)
)ENGINE=INNODB DEFAULT CHARSET UTF8;

CREATE TABLE operations(
	id INT(3) PRIMARY KEY AUTO_INCREMENT,
	id_user_sender INT(3),
	id_user_receiver INT(3),
	id_videogame_sender INT(3),
	id_videogame_receiver INT(3),
	price FLOAT(4,2),
	date_sended DATETIME,
	date_accepted DATETIME,
	rejected BOOLEAN DEFAULT false
)ENGINE=INNODB DEFAULT CHARSET UTF8;

CREATE TABLE forum_sections(
	id INT(3) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50)
)ENGINE=INNODB DEFAULT CHARSET UTF8;

CREATE TABLE forum_threads(
	id INT(3) PRIMARY KEY AUTO_INCREMENT,
	id_section INT(3),
	id_user_creation INT(3),
	title VARCHAR(50),
	date DATETIME
)ENGINE=INNODB DEFAULT CHARSET UTF8;

CREATE TABLE forum_messages(
	id INT(3) PRIMARY KEY AUTO_INCREMENT,
	id_thread INT(3),
	id_user INT(3),
	date_wrote DATETIME
)ENGINE=INNODB DEFAULT CHARSET UTF8;

ALTER TABLE cities ADD FOREIGN KEY(id_country) REFERENCES countries(id);
ALTER TABLE addresses ADD FOREIGN KEY(id_city) REFERENCES cities(id);
ALTER TABLE users ADD FOREIGN KEY(id_role) REFERENCES roles(id);
ALTER TABLE users ADD FOREIGN KEY(id_address) REFERENCES addresses(id);
ALTER TABLE users_followers ADD FOREIGN KEY(id_user) REFERENCES users(id);
ALTER TABLE users_followers ADD FOREIGN KEY(id_follower) REFERENCES users(id);
ALTER TABLE users_followings ADD FOREIGN KEY(id_user) REFERENCES users(id);
ALTER TABLE users_followings ADD FOREIGN KEY(id_following) REFERENCES users(id);
ALTER TABLE posts ADD FOREIGN KEY(id_user) REFERENCES users(id);
ALTER TABLE brands ADD FOREIGN KEY(id_country) REFERENCES countries(id);
ALTER TABLE videogames ADD FOREIGN KEY(id_publisher) REFERENCES brands(id);
ALTER TABLE videogames ADD FOREIGN KEY(id_developer) REFERENCES brands(id);
ALTER TABLE operations ADD FOREIGN KEY(id_user_sender) REFERENCES users(id);
ALTER TABLE operations ADD FOREIGN KEY(id_user_receiver) REFERENCES users(id);
ALTER TABLE operations ADD FOREIGN KEY(id_videogame_sender) REFERENCES videogames(id);
ALTER TABLE operations ADD FOREIGN KEY(id_videogame_receiver) REFERENCES videogames(id);
ALTER TABLE forum_threads ADD FOREIGN KEY(id_section) REFERENCES forum_sections(id);
ALTER TABLE forum_threads ADD FOREIGN KEY(id_user_creation) REFERENCES users(id);
ALTER TABLE forum_messages ADD FOREIGN KEY(id_thread) REFERENCES forum_threads(id);
ALTER TABLE forum_messages ADD FOREIGN KEY(id_user) REFERENCES users(id);
ALTER TABLE users_videogames ADD FOREIGN KEY(id_user) REFERENCES users(id);
ALTER TABLE users_videogames ADD FOREIGN KEY(id_videogame) REFERENCES videogames(id);

ALTER TABLE roles ADD UNIQUE(role_name);
ALTER TABLE countries ADD UNIQUE(name);
ALTER TABLE users ADD UNIQUE(name);
ALTER TABLE brands ADD UNIQUE(name);
ALTER TABLE videogames ADD UNIQUE(name);
ALTER TABLE forum_sections ADD UNIQUE(name);