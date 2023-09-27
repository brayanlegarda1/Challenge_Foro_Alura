CREATE TABLE topics(
    id bigint not null auto_increment,
    title varchar(100) not null,
    message varchar(1000) not null,
    creationDate date not null,
    status varchar(15) not null,
    user_id bigint not null,
    course_id bigint not null,

    primary key(id)
);