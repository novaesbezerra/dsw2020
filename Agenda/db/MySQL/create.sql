drop database Livraria;

create database Livraria;

use Livraria;

create table Especialidade(id bigint not null auto_increment, cnpj varchar(18) not null, nome varchar(256) not null, primary key (id));

create table Medico(id bigint not null auto_increment, titulo varchar(256) not null, autor varchar(256) not null, ano integer not null, preco float not null, especialidade_id bigint not null, primary key (id), foreign key (especialidade_id) references Especialidade(id));

insert into Especialidade(cnpj, nome) values  ('55.789.390/0008-99', 'Companhia das Letras');

insert into Especialidade(cnpj, nome) values ('71.150.470/0001-40', 'Record');

insert into Especialidade(cnpj, nome) values ('32.106.536/0001-82', 'Objetiva');

insert into Medico(titulo, autor, ano, preco, especialidade_id) values ('Ensaio sobre a Cegueira', 'José Saramago', 1995, 54.9, 1);

insert into Medico(titulo, autor, ano, preco, especialidade_id) values  ('Cem anos de Solidão', 'Gabriel Garcia Márquez', 1977, 59.9, 2);

insert into Medico(titulo, autor, ano, preco, especialidade_id) values ('Diálogos Impossíveis', 'Luis Fernando Verissimo', 2012, 22.9, 3);

create table Paciente(id bigint not null auto_increment, nome varchar(256) not null, login varchar(20) not null unique, senha varchar(64) not null, papel varchar(10), primary key (id));

insert into Paciente(nome, login, senha, papel) values ('Administrador', 'admin', 'admin', 'ADMIN');

insert into Paciente(nome, login, senha, papel) values ('Paciente', 'user', 'user', 'USER');

create table Consulta(id bigint not null auto_increment, data varchar(10) not null, valor float not null, medico_id bigint not null, paciente_id bigint not null, primary key (id), foreign key (medico_id) references Medico(id), foreign key (paciente_id) references Paciente(id));

insert into Consulta(data, valor, medico_id, paciente_id) values ('30/08/2020', 10.88, 1, 2);

