create table usuario_roles
(
    id         bigint auto_increment,
    usuario_id bigint not null,
    roles_id    bigint not null,
    primary key (id),
    foreign key (roles_id)    references role (id),
    foreign key (usuario_id) references usuario (id)
);

insert into usuario_roles(id, usuario_id, roles_id)
values ( 1, 1, 1);