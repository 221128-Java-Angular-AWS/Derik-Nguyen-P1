
drop table if exists users;
drop table if exists tickets;




create table if not exists users (
	id SERIAL primary key,
	first_name VARCHAR(200),
	last_name VARCHAR(200),
	username VARCHAR(200) not null unique,
	"password" VARCHAR(200) not null,
	is_manager boolean default false
);



create table if not exists tickets (
	id SERIAL primary key,
	employee_id INT,
	manager_id INT,
	description VARCHAR(500) not null,
	request_amount numeric(100,2),
	pending_status boolean default false,
	approved boolean default null
);

select * from users where username = 'edisone@eagle.edu' and "password" = 'avemaria1';
select * from users;
select * from tickets;
select * from users where is_manager = true;

insert into users (first_name, last_name, username, "password") values ('dpaul', 'ngiu', 'derjljog,edu', 'password1');
insert into users (first_name, last_name, username, "password", is_manager) values ('jimmy', 'beans', 'edisone@eagle.edu', 'avemaria1', true); 
insert into users (first_name, last_name, username, "password") values ('kelvin', 'samsara', 'derjoohu,edu', 'password1');
insert into users (first_name, last_name, username, "password", is_manager) values ('timmy', 'beans', 'edison56agle.edu', 'avemaria1', true);



insert into tickets (employee_id, manager_id, description, request_amount, pending_status) values (1, 2,'This is a test', 108000.01, true);
insert into tickets (employee_id, manager_id, description, request_amount, pending_status) values (1, 4,'This is a test', 178000.01, true);

select * from tickets t
join users u on t.manager_id = u.id
join users ue on t.employee_id = ue.id;


update tickets 
set approved = true, pending_status = default
where manager_id = 2;

update tickets 
set approved = false, pending_status = default
where manager_id = 4;

 

select first_name, last_name from users 
where username = 'gjreuej@psu,edu'






