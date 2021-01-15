# Grupo 7
## Disciplina de Desenvolvimento Web

Documento de Requisitos - B
Sistema para agendamento de consultas médicas

## Integrantes

726534 Gustavo Nakagawa

619612 Jessica Antunes

594954 Lucas Novaes


## Roteiro de execução

SGBD utilizado: MySql

Nome do banco de dados: agenda

Scripts sql para executar: create.sql (dentro da pasta agenda/db/MySql)

## Usuários e papéis

Médicos

> email: 'jose_maria@gmail.com', senha: 123abc

> email: 'ciniro@gmail.com', senha: 123abc

> email: 'zagatti@gmail.com', senha: 123abc

Pacientes

> email: 'juraci@gmail.com', senha: 123456

> email: 'juarez@gmail.com', senha: 123456


## Requisitos

O sistema deve incorporar os seguintes requisitos.

Lucas (50%), Jéssica (50%)

### REST API -- CRUD de pacientes

> POST http://localhost:8081/pacientes

> GET http://localhost:8081/pacientes

> GET http://localhost:8081/pacientes/{id}

> PUT http://localhost:8081/pacientes/{id}

> DELETE http://localhost:8081/pacientes/{id}

### REST API -- CRUD de médicos

> POST http://localhost:8081/medicos

> GET http://localhost:8081/medicos

> GET http://localhost:8081/medicos/{id}

> GET http://localhost:8081/medicos/especialidades/{nome}

> PUT http://localhost:8081/medicos/{id}

> DELETE http://localhost:8081/medicos/{id}

### REST API -- CRUD de consultas

> GET http://localhost:8081/consultas

> GET http://localhost:8081/consultas/{id}

> GET http://localhost:8081/consultas/pacientes/{id}

> GET http://localhost:8081/consultas/medicos/{id}