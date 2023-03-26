create table usuario
(
    id    bigint auto_increment,
    nome  varchar(50) not null,
    email varchar(50) not null,
    password varchar(255) not null,
    primary key (id)
);

insert into usuario(id, nome, email, password)
values (1, 'Ana da Silva', 'ana@email.com', '$2a$12$d9Xgks9kKhfTvy1xzatt5eQ7/QCZrpdE2zVaXZRww.qyLEv5KvEFC');