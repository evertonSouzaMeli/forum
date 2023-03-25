create table topico
(
    id           bigint auto_increment,
    titulo       varchar(50)  not null,
    mensagem     varchar(300) not null,
    data_criacao datetime     not null,
    status       varchar(20)  not null,
    curso_id     bigint       not null,
    usuario_id   bigint       not null,
    primary key (id),
    foreign key (curso_id) references curso (id),
    foreign key (usuario_id) references usuario (id)
);