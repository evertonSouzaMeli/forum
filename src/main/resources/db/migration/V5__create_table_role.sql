create table role
(
    id   bigint auto_increment,
    nome varchar(50) not null,
    primary key (id)
);

insert into role(id, nome)
values ( 1, 'LEITURA_ESCRITA');