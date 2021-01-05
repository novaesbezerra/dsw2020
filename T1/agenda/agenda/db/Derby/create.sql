connect 'jdbc:derby:agenda;create=true;user=root;password=root';

create table Medico(id bigint not null auto_increment, crm integer not null unique, nome varchar(256) not null,  senha varchar(64) not null, email varchar(128) not null, especialidade varchar(256) not null);

insert into Medico(crm, nome, senha, email, especialidade) values (12345678, 'Jose Maria', '123abc', 'jose_maria@gmail.com', 'Clinico Geral');

insert into Medico(crm, nome, senha, email, especialidade) values (87654321, 'Joao Ciniro', '123abc', 'ciniro@gmail.com', 'Ginecologista');

insert into Medico(crm, nome, senha, email, especialidade) values (98765432, 'Joaquim Zagatti', '123abc', 'zagatti@gmail.com', 'Cardiologista');

create table Paciente(id bigint not null auto_increment, nome varchar(256) not null, email varchar(128) not null, senha varchar(64) not null, cpf varchar(12) not null, telefone varchar(20), sexo varchar(20), nascimento varchar(10), primary key (id));

insert into Paciente(nome, email, senha, cpf, telefone, sexo, nascimento) values ('Juraci Carvalho', 'juraci@gmail.com', '123456', '123.456.789-00', '12345-12345', 'FEM', '1900-12-01');

insert into Paciente(nome, email, senha, cpf, telefone, sexo, nascimento) values ('Juarez', 'juarez@gmail.com', '123456', '123.456.789-00', '12345-12345', 'MASC', '1900-12-02');

create table Consulta(id bigint not null auto_increment, data varchar(20) not null, valor float not null, medico_id bigint not null, paciente_id bigint not null, primary key (id), foreign key (medico_id) references Medico(id), foreign key (paciente_id) references Paciente(id));

insert into Consulta(data, valor, medico_id, paciente_id) values ('2020-12-01/1:30', 188, 1, 2);

insert into Consulta(data, valor, medico_id, paciente_id) values ('2020-12-02/1:30', 200, 2, 2);

disconnect;

quit;