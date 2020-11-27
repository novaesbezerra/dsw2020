connect 'jdbc:derby:Livraria;create=true;user=root;password=root';

create table Especialidade(id bigint not null generated always as identity, cnpj varchar(18) not null, nome varchar(256) not null, constraint Especialidade_PK primary key (id));

create table Medico(id bigint not null generated always as identity, titulo varchar(256) not null, autor varchar(256) not null, ano integer not null, preco float not null, especialidade_id bigint not null, constraint Medico_PK primary key (id), constraint Especialidade_FK foreign key (especialidade_id) references Especialidade(id));

insert into Especialidade(cnpj, nome) values  ('55.789.390/0008-99', 'Companhia das Letras');

insert into Especialidade(cnpj, nome) values ('71.150.470/0001-40', 'Record');

insert into Especialidade(cnpj, nome) values ('32.106.536/0001-82', 'Objetiva');

insert into Medico(titulo, autor, ano, preco, especialidade_id) values ('Ensaio sobre a Cegueira', 'José Saramago', 1995, 54.9, 1);

insert into Medico(titulo, autor, ano, preco, especialidade_id) values  ('Cem anos de Solidão', 'Gabriel Garcia Márquez', 1977, 59.9, 2);

insert into Medico(titulo, autor, ano, preco, especialidade_id) values ('Diálogos Impossíveis', 'Luis Fernando Verissimo', 2012, 22.9, 3);

create table Paciente(id bigint not null generated always as identity (start with 1, increment by 1), nome varchar(256) not null, login varchar(20) not null unique, senha varchar(64) not null, papel varchar(10), constraint Paciente_PK primary key (id));

insert into Paciente(nome, login, senha, papel) values ('Administrador', 'admin', 'admin', 'ADMIN');

insert into Paciente(nome, login, senha, papel) values ('Paciente', 'user', 'user', 'USER');


create table Consulta(id bigint not null generated always as identity, data varchar(10) not null, valor float not null, medico_id bigint not null, paciente_id bigint not null, constraint Consulta_PK primary key (id), constraint LIVRO_FK foreign key (medico_id) references Medico(id), constraint USUARIO_FK foreign key (paciente_id) references Paciente(id));

insert into Consulta(data, valor, medico_id, paciente_id) values ('30/08/2020', 10.88, 1, 2);

disconnect;

quit;
