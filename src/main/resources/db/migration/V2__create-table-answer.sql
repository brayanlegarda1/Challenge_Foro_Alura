CREATE TABLE answers(
    id bigint not null auto_increment,
    message varchar(1000) not null,
    topic_id bigint not null,
    creationDate date not null,
    user_id bigint not null,
    solution boolean not null,

    primary key(id)
);