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

O sistema deve possuir um cadastro de pacientes, com os seguintes dados: e-mail, senha, CPF, nome, telefone, sexo e data de nascimento.

O sistema deve possuir um cadastro de médicos, com os seguintes dados: e-mail, senha, CRM, nome e especialidade.

O sistema deve possuir um cadastro de consultas, com os seguintes dados: CPF do paciente, CRM do médico e data/hora da consulta. Assume-se que a duração da consulta é de 30 minutos e sempre inicia-se em “hora cheia” (14h 00min etc) ou “hora meia” (14h 30min etc).

O sistema deve atender aos seguintes requisitos:

> R1: CRUD 1 de médicos (requer login de administrador)

(X) Implementado

Lucas (50%), Jéssica (50%)

> R2: CRUD de pacientes (requer login de administrador)

(X) Implementado

Lucas (50%), Jéssica (50%)

> R3: Listagem de todos os médicos em uma única página (não requer login)

(X) Parcialmente Implementado

Lucas (50%), Jéssica (50%)

> R4: Listagem de todos os médicos por especialidade (não requer login)

(X) Não Implementado

> R5: Agendamento de consulta com um médico (requer login do paciente via email + senha). Depois de fazer login, o paciente pode cadastrar uma consulta. Para isso, deve escolher um médico (escolhendo a partir de uma lista), uma data/horário, e deve ser gravado a consulta na base de dados.

(X) Implementado

Lucas (50%), Jéssica (50%)

> R6: Listagem de todas as consultas de um paciente (requer login do paciente via e-mail + senha). Depois de fazer login, o paciente pode visualizar todas as suas consultas gravadas.

(X) Implementado

Lucas (50%), Jéssica (50%)

> R7: O sistema não deve permitir o cadastro de consultas de um mesmo médico ou de um mesmo paciente em uma mesma data/horário.

(X) Parcialmente Implementado

Lucas (50%), Jéssica (50%)

> R8: Listagem de todas as consultas de um médico (requer login do médico via e-mail + senha). Depois de fazer login, o médico pode visualizar todas as suas consultas gravadas.

(X) Implementado

Lucas (50%), Jéssica (50%)

> R9: O sistema deve ser internacionalizado em pelo menos dois idiomas: português + outro de sua escolha. O sistema deve tratar todos os erros possíveis (cadastros duplicados, problemas técnicos, etc) mostrando uma página de erros amigável ao usuário e registrando o erro no console, em arquivo ou na base de dados.

(X) Parcialmente Implementado

Lucas (50%), Jéssica (50%)