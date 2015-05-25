INSERT INTO roles VALUES(0, 'ADMIN');
INSERT INTO roles VALUES(1, 'MOD');
INSERT INTO roles VALUES(2, 'BASIC');

INSERT INTO countries VALUES (1, 'USA');
INSERT INTO countries VALUES (2, 'Canada');
INSERT INTO countries VALUES (3, 'United Kingdom');
INSERT INTO countries VALUES (4, 'France');
INSERT INTO countries VALUES (5, 'Germany');
INSERT INTO countries VALUES (6, 'Spain');
INSERT INTO countries VALUES (7, 'Poland');
INSERT INTO countries VALUES (8, 'Netherlands');
INSERT INTO countries VALUES (9, 'Ukraine');
INSERT INTO countries VALUES (10, 'China');
INSERT INTO countries VALUES (11, 'Japan');
INSERT INTO countries VALUES (12, 'South Korea');
INSERT INTO countries VALUES (13, 'Swiss');
INSERT INTO countries VALUES (14, 'Russia');
INSERT INTO countries VALUES (15, 'Nepal');
INSERT INTO countries VALUES (16, 'India');

INSERT INTO cities VALUES (null, 1, 'New York');
INSERT INTO cities VALUES (null, 1, 'Los Angeles');
INSERT INTO cities VALUES (null, 2, 'Montreal');
INSERT INTO cities VALUES (null, 3, 'London');
INSERT INTO cities VALUES (null, 3, 'Glasgow');
INSERT INTO cities VALUES (null, 6, 'Barcelona');

INSERT INTO addresses VALUES(1, 'Direccion Xelit3 1 2', 8080, 6);
INSERT INTO addresses VALUES(2, 'Direccion Adri 1 2', 8080, 6);

INSERT INTO users VALUES(1, 0, 'Xavi Rueda', 'Xelit3', '7c4a8d09ca3762af61e59520943dc26494f8941b', 'xelit2@mail.com', 1);
INSERT INTO users VALUES(2, 0, 'Adria Nieto', 'aNieto', '7c4a8d09ca3762af61e59520943dc26494f8941b', 'anieto@mail.com', 2);

INSERT INTO brands VALUES (1, 'Electronic Arts', 1);
INSERT INTO brands VALUES (2, 'Activision Blizzard', 1);
INSERT INTO brands VALUES (3, 'Microsoft', 1);
INSERT INTO brands VALUES (4, 'Ubisoft', 4);
INSERT INTO brands VALUES (5, 'Crytek', 5);
INSERT INTO brands VALUES (6, 'Nintendo', 11);
INSERT INTO brands VALUES (7, 'Tequila works', 6);
INSERT INTO brands VALUES (8, 'Valve', 1);
INSERT INTO brands VALUES (9, 'Arenanet', 1);
INSERT INTO brands VALUES (10, 'NC Soft', 1);
INSERT INTO brands VALUES (11, 'Tripwire Interactive', 1);
INSERT INTO brands VALUES (12, 'Iceberg Interactive', 8);
INSERT INTO brands VALUES (13, 'Irrational Games', 1);
INSERT INTO brands VALUES (14, '2K Games', 1);

INSERT INTO videogames VALUES (1, 'Medal of Honor', 1, 1, 2002, true);
INSERT INTO videogames VALUES (2, 'FIFA 15', 1, 1, 2014, true);
INSERT INTO videogames VALUES (3, 'World of Warcraft', 2, 2, 2004, true);
INSERT INTO videogames VALUES (4, 'Age of Empires', 3, 3, 1997, true);
INSERT INTO videogames VALUES (5, 'Age of Mythology', 3, 4, 2003, true);
INSERT INTO videogames VALUES (6, 'Assassins Creed', 4, 4, 2008, true);
INSERT INTO videogames VALUES (7, 'Deadlight', 7, 7, 2011, true);
INSERT INTO videogames VALUES (8, 'Crysis', 7, 5, 2008, true);
INSERT INTO videogames VALUES (9, 'Half Life', 8, 8, 1998, true);
INSERT INTO videogames VALUES (10, 'Half Life 2', 8, 8, 2004, true);
INSERT INTO videogames VALUES (11, 'Call of Duty 4 Modern Warfare', 1, 1, 2006, true);
INSERT INTO videogames VALUES (12, 'Battlefield 3', 1, 1, 2012, true);
INSERT INTO videogames VALUES (13, 'Guild Wars 2', 10, 9, 2012, true);
INSERT INTO videogames VALUES (14, 'Killing Floor 2', 12, 11, 2015, true);
INSERT INTO videogames VALUES (15, 'Bioshock', 14, 13, 2012, true);

INSERT INTO users_videogames VALUES (1, 1);
INSERT INTO users_videogames VALUES (1, 4);
INSERT INTO users_videogames VALUES (1, 5);
INSERT INTO users_videogames VALUES (1, 6);
INSERT INTO users_videogames VALUES (1, 7);
INSERT INTO users_videogames VALUES (1, 8);
INSERT INTO users_videogames VALUES (1, 11);
INSERT INTO users_videogames VALUES (1, 12);
INSERT INTO users_videogames VALUES (1, 15);

INSERT INTO users_videogames VALUES (2, 3);
INSERT INTO users_videogames VALUES (2, 4);
INSERT INTO users_videogames VALUES (2, 5);
INSERT INTO users_videogames VALUES (2, 7);
INSERT INTO users_videogames VALUES (2, 9);
INSERT INTO users_videogames VALUES (2, 10);
INSERT INTO users_videogames VALUES (2, 11);
INSERT INTO users_videogames VALUES (2, 13);
INSERT INTO users_videogames VALUES (2, 14);
INSERT INTO users_videogames VALUES (2, 15);

INSERT INTO users_followers VALUES(1, 2);
INSERT INTO users_followers VALUES(2, 1);

INSERT INTO users_followings VALUES(1, 2);
INSERT INTO users_followings VALUES(2, 1);