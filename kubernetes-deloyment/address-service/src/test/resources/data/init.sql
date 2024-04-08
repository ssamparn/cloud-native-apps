create table `address` (
    `id` serial PRIMARY KEY,
    `city` VARCHAR(30) NOT NULL,
    `state` VARCHAR(20) NOT NULL
);

insert into `address` (`city`, `state`) values ('Hilversum', 'North Holland');
insert into `address` (`city`, `state`) values ('Bengaluru', 'Karnataka');