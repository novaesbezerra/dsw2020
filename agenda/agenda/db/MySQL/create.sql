drop database IF EXISTS agenda;

create database agenda;

use agenda;


create table Admin(
	id bigint not null auto_increment, 
	login varchar(128) not null,
	senha varchar(64) not null,
	primary key(id)
);


create table Medico(
	id bigint not null auto_increment, 
	crm bigint not null unique, 
	nome varchar(256) not null,  
	senha varchar(64) not null, 
	email varchar(128) not null, 
	primary key(id),
	especialidade varchar(256) not null);

insert into Admin(login, senha)
	values ('admin', 'admin');

insert into Medico(crm, nome, senha, email, especialidade) 
	values (12345678, 'José Maria', '123abc', 'jose_maria@gmail.com', 'Clinico Geral');

insert into Medico(crm, nome, senha, email, especialidade) 
	values (87654321, 'João Ciniro', '123abc', 'ciniro@gmail.com', 'Ginecologista');

insert into Medico(crm, nome, senha, email, especialidade) 
	values (98765432, 'Joaquim Zagatti', '123abc', 'zagatti@gmail.com', 'Cardiologista');

create table Paciente(
	id bigint not null auto_increment, 
	nome varchar(256) not null,
	email varchar(128) not null, 
	senha varchar(64) not null, 
	cpf varchar(15) not null,  /*aumentei para varchar(15) max 14 carateres*/
	telefone varchar(20), 
	sexo varchar(20), 
	nascimento varchar(10), 
	primary key (id));

insert into Paciente(nome, email, senha, cpf, telefone, sexo, nascimento) 
	values ('Juraci Carvalho', 'juraci@gmail.com', '123456', '12345678900', '', 'FEM', '01/05/1900');

insert into Paciente(nome, email, senha, cpf, telefone, sexo, nascimento) 
	values ('Juarez', 'juarez@gmail.com', '123456', '12345678901', '', 'MASC', '01/06/1900');

create table Consulta(
	id bigint not null auto_increment, 
	data varchar(20) not null, 
	valor float not null, 
	medico_id bigint not null, 
	paciente_id bigint not null, 
	primary key (id), 
	foreign key (medico_id) references Medico(id), 
	foreign key (paciente_id) references Paciente(id));

insert into Consulta(data, valor, medico_id, paciente_id) 
	values ('30/11/2020', 188, 1, 2);

insert into Consulta(data, valor, medico_id, paciente_id) 
	values ('20/12/2020', 100, 1, 1);

insert into Consulta(data, valor, medico_id, paciente_id) 
	values ('27/11/2020', 200, 2, 2);