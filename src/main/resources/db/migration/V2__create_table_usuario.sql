create table usuario
(
    id    bigint auto_increment,
    nome  varchar(50) not null,
    email varchar(50) not null,
    primary key (id)
);

insert into usuario(id, nome, email)
values (1, 'Ana da Silva', 'ana@email.com');