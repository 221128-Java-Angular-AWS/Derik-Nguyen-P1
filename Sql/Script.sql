
drop table if exists users;
drop table if exists ticket;




create table if not exists users (
	id SERIAL primary key,
	first_name VARCHAR(200),
	last_name VARCHAR(200),
	username VARCHAR(200) not null unique,
	"password" VARCHAR(200) not null,
	is_manager boolean default false
);



create table if not exists ticket (
	id SERIAL primary key,
	employee_id INT,
	manager_id INT,
	description VARCHAR(500) not null,
	request_amount numeric(100,2),
	pending_status boolean default false,
	approved boolean default null
);

select * from users;

insert into users (username, "password") values ('derik.nguyen@wsu,edu', 'password1');
insert into users (username, "password", is_manager) values ('edisone@eagle.edu', 'avemaria1', true); 

insert into ticket (employee_id, manager_id, description, request_amount, pending_status) values (1, 2,'gjhfjgh', 10000.09, true);

update ticket 
set approved = true
where employee_id = 1 and manager_id = 1;

select * from ticket t
join users u on t.manager_id = u.id; 







