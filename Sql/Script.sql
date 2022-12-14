
drop table if exists employees;
drop table if exists managers;
drop table if exists managers_employees;
drop table if exists ticket;



create table if not exists employees (
	employee_id serial primary key,
	email VARCHAR(200) not null unique,
	"password" VARCHAR(200) not null
);

create table if not exists managers (
	manager_id SERIAL primary key,
	email VARCHAR(200) not null unique,
	"password" VARCHAR(200) not null
);

CREATE TABLE managers_employees (
	id SERIAL PRIMARY KEY,
	manager_id INT,
	employee_id INT,
	CONSTRAINT fk_manager_employees_employees FOREIGN KEY (employee_id) REFERENCES employees (employee_id),
	CONSTRAINT fk_manager_employees_managers FOREIGN KEY (manager_id) REFERENCES managers (manager_id)
);

create table if not exists ticket (
	id SERIAL primary key,
	employee_id INT,
	manager_id INT,
	description VARCHAR(500) not null,
	request_amount numeric(100,2),
	pending_status boolean default false,
	approved boolean default null,
	CONSTRAINT fk_ticket_employees_employees FOREIGN KEY (employee_id) REFERENCES employees (employee_id),
	CONSTRAINT fk_ticket_employees_managers FOREIGN KEY (manager_id) REFERENCES managers (manager_id)
);

select * from employees;

insert into employees (email, "password") values ('derik.nguyen@wsu,edu', 'password1');
insert into managers (email, "password") values ('edisone', 'ave'); 

insert into ticket (employee_id, manager_id, description, request_amount, pending_status) values (1, 1,'gjhfjgh', 10000.09, true);

update ticket 
set approved = true
where employee_id = 1 and manager_id = 1;

select * from ticket t
join managers m on m.manager_id = t.manager_id; 







