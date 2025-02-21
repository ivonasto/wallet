create database database;

use database;

create table transactions
(
    transaction_id   int auto_increment
        primary key,
    sender           varchar(35)    not null,
    recipient        varchar(35)    not null,
    amount           decimal(30, 8) not null,
    currency         char(3)        not null,
    date_of_creation timestamp      not null
);

create table users
(
    user_id  int auto_increment
        primary key,
    username varchar(25) not null,
    password varchar(30) not null,
    enabled  tinyint(1)  null
);

create table wallets
(
    wallet_id        int auto_increment
        primary key,
    iban             varchar(35)                       not null,
    owner            int                               not null,
    currency         char(3)                           not null,
    date_of_creation timestamp                         not null,
    balance          decimal(30, 8) default 0.00000000 not null,
    constraint table_name_users_user_id_fk
        foreign key (owner) references users (user_id)
);

