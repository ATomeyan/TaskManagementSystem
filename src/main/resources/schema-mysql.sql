create table tb_change_log
(
    table_name varchar(255) null,
    action varchar(255) null,
    added timestamp null
);

create table tb_comment
(
    uuid varchar(36) not null primary key,
    created datetime(6) not null,
    updated datetime(6) null,
    content varchar(255) not null
);

create table tb_role
(
    name varchar(255) not null,
    uuid varchar(36) not null primary key
);

create table tb_task
(
    uuid varchar(36) not null primary key,
    created datetime(6) not null,
    updated datetime(6) null,
    description varchar(255) not null,
    due_date date not null,
    priority enum ('High', 'Normal', 'Low') not null,
    status enum ('Scheduled', 'In progress', 'Blocked', 'On test', 'Completed', 'Cancelled') not null,
    title varchar(255) not null
);

create table tb_task_comment
(
    uuid varchar(36) not null primary key,
    comment_uid varchar(36) null,
    task_uid varchar(36) null,
    constraint FKcubq2mm6qm1yd4brx82bagpif
        foreign key (comment_uid) references tb_comment (uuid),
    constraint FKte2if9cvmf20ghbi7m9160eyh
        foreign key (task_uid) references tb_task (uuid)
);

create table tb_user
(
    enabled bit not null,
    created datetime(6) not null,
    updated datetime(6) null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    password varchar(255) not null,
    username varchar(255) not null,
    uuid varchar(36)  not null primary key,
    constraint UK_4wv83hfajry5tdoamn8wsqa6x unique (username)
);

create table tb_task_user
(
    uuid varchar(36) not null primary key,
    task_uid varchar(36) null,
    user_uid varchar(36) null,
    constraint FK38qegcnqwvl2llsdh1n2tfbi4
        foreign key (user_uid) references tb_user (uuid),
    constraint FK77b3ui7nba86hwoow2c0gb53
        foreign key (task_uid) references tb_task (uuid)
);

create definer = root@localhost trigger new_row_trigger
    after insert
    on tb_task_user
    for each row
begin
    insert into tb_change_log (table_name, action, added) VALUE ('tb_task_user', 'INSERT', now());
end;

create table tb_user_comment
(
    uuid varchar(36) not null primary key,
    comment_uid varchar(36) null,
    user_uid varchar(36) null,
    constraint FKd5idcx624kvnt0hpfdfydw3bx
        foreign key (comment_uid) references tb_comment (uuid),
    constraint FKdoyg2rqtjtpflp2y9454dg7ae
        foreign key (user_uid) references tb_user (uuid)
);

create table tb_user_roles
(
    role_uid varchar(36) null,
    user_uid varchar(36) null,
    uuid varchar(36) not null primary key,
    constraint FKa5ce7noypketw1t2d66acnrtr
        foreign key (role_uid) references tb_role (uuid),
    constraint FKidvp6qm4mujyve5to2p1gugmb
        foreign key (user_uid) references tb_user (uuid)
);