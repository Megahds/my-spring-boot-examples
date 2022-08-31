-- MYSQL
-- mydb.person definition

CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `age` int(11) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;


-- POSTGRESQL
-- public.person definition

-- Drop table

-- DROP TABLE public.person;

CREATE TABLE public.person (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	"name" varchar NOT NULL,
	age int4 NOT NULL,
	address varchar NULL,
	CONSTRAINT person_pkey PRIMARY KEY (id)
);


-- SQL SERVER
-- mydb.dbo.person definition

-- Drop table

-- DROP TABLE mydb.dbo.person;

CREATE TABLE mydb.dbo.person (
	id int IDENTITY(1,1) NOT NULL,
	name varchar(40) COLLATE Latin1_General_CI_AS NOT NULL,
	age int NOT NULL,
	address varchar(100) COLLATE Latin1_General_CI_AS DEFAULT NULL NULL,
	CONSTRAINT PK__person__3213E83F452A2FB8 PRIMARY KEY (id)
);