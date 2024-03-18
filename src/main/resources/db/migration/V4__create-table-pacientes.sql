create table pacientes (
id int not null auto_increment,
nome varchar(100) not null,
email varchar(100) not null,
telefone varchar(20) not null,
cpf varchar(10) not null,
logradouro varchar(100) not null,
numero varchar(100),
complemento varchar(100),
bairro varchar(100) not null,
cidade varchar(100) not null,
uf varchar(100) not null,
cep varchar(100) not null,

primary key (id)
);