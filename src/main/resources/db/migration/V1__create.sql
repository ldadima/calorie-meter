create table if not exists users
(
    login text not null
        constraint user_pk
            primary key,
    height integer,
    weight integer,
    birthday date not null,
    password text not null,
    gender text not null
);

create table if not exists food
(
    id serial not null
        constraint food_pk
            primary key,
    name text,
    calories integer
);

create table if not exists users_foods
(
    user_login text not null
        constraint users_calories_users_login_fk
            references users
            on update cascade on delete cascade,
    food integer not null
        constraint users_foods_food_id_fk
            references food,
    weight integer not null,
    constraint users_foods_pk
        primary key (user_login, food)
);

create sequence if not exists food_id_seq
    as integer;