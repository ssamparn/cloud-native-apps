create table `address` (
    `id` serial PRIMARY KEY,
    `house_number` INTEGER(10) NOT NULL,
    `street_name` VARCHAR(30) NOT NULL,
    `city` VARCHAR(20) NOT NULL,
    `state` VARCHAR(15) NOT NULL,
    `employee_id` INTEGER(20) NOT NULL
);

insert into `address` (`house_number`, `street_name`, `city`, `state`, `employee_id`) values (523, 'Neuweg', 'Hilversum', 'North Holland', 50882);
insert into `address` (`house_number`, `street_name`, `city`, `state`, `employee_id`) values (254, 'United Highlands', 'Bengaluru', 'Karnataka', 50883);