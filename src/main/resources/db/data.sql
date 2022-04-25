INSERT INTO users(id,nick_name,password)VALUES (1,"Admin","password");
INSERT INTO users(id,nick_name,password)VALUES (2,"Administrator","password");
INSERT INTO users(nick_name,password)VALUES ("rimand","password");
INSERT INTO users(nick_name,password)VALUES ("andkmr","password");
INSERT INTO users(nick_name,password)VALUES ("manvlk","password");
INSERT INTO users(nick_name,password)VALUES ("taunvc","password");

INSERT INTO post(title,post_text,author_id,posted_ip)VALUES  ("Pirmasis blogo įrašas","Šis blogas BLA", 1,"192.168.1.1");
INSERT INTO post(title,post_text,author_id,posted_ip)VALUES  ("Antras blogo įrašas","Šis blogas BLA BLA", 1,"192.168.1.1");
INSERT INTO post(title,post_text,author_id,posted_ip)VALUES  ("Tračias blogo įrašas","Šis blogas BLA BLA BLA", 2,"192.168.1.2");
INSERT INTO post(title,post_text,author_id,posted_ip)VALUES  ("Ketvirtas blogo įrašas","Šis blogas BLA BLA BLA BLA", 2,"192.168.1.2");
INSERT INTO post(title,post_text,author_id,posted_ip)VALUES  ("Penktas blogo įrašas","Šis blogas BLA", 3,"192.168.1.3");
INSERT INTO post(title,post_text,author_id,posted_ip)VALUES  ("Šeštas blogo įrašas","Šis blogas BLA BLA", 3,"192.168.1.3");
INSERT INTO post(title,post_text,author_id,posted_ip)VALUES  ("Septintas blogo įrašas","Šis blogas BLA BLA BLA", 4,"192.168.1.4");
INSERT INTO post(title,post_text,author_id,posted_ip)VALUES  ("Aštuntas blogo įrašas","Šis blogas BLA BLA BLA BLA", 4,"192.168.1.4");

INSERT INTO comments (temporary_nic,user_id,parent_post_id,comment_text,commented_ip)VALUES("Palangos Juzė",1,1,"Autorius runkelis","192.168.1.1");
INSERT INTO comments (temporary_nic,user_id,parent_post_id,comment_text,commented_ip)VALUES("Vingių Jonas",1,2,"Autorius šaunuolis","192.168.1.1");
INSERT INTO comments (temporary_nic,user_id,parent_post_id,comment_text,commented_ip)VALUES("Kareivis Šveikas",1,3,"Išgerkim alaus","192.168.1.1");
INSERT INTO comments (temporary_nic,user_id,parent_post_id,comment_text,commented_ip)VALUES("Kindziulis",1,4,"Kokia netvarka","192.168.1.1");
INSERT INTO comments (temporary_nic,user_id,parent_post_id,comment_text,commented_ip)VALUES("Palangos Juzė",2,1,"Autorius runkelis","192.168.1.2");
INSERT INTO comments (temporary_nic,user_id,parent_post_id,comment_text,commented_ip)VALUES("Vingių Jonas",2,2,"Autorius šaunuolis","192.168.1.2");
INSERT INTO comments (temporary_nic,user_id,parent_post_id,comment_text,commented_ip)VALUES("Kareivis Šveikas",2,3,"Išgerkim alaus","192.168.1.2");
INSERT INTO comments (temporary_nic,user_id,parent_post_id,comment_text,commented_ip)VALUES("Kindziulis",2,4,"Kokia netvarka","192.168.1.2");