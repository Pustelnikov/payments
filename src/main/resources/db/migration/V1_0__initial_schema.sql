CREATE SCHEMA IF NOT EXISTS payments;

CREATE TABLE payments.users
(
    user_id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_name varchar(64) UNIQUE NOT NULL,
    user_password varchar(128) NOT NULL,
    user_type varchar(32) NOT NULL
);

CREATE TABLE payments.roles
(
    user_role varchar(32) NOT NULL,
    user_user_id bigint NOT NULL REFERENCES payments.users (user_id)
);

CREATE TABLE payments.accounts
(
    account_id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    account_number varchar(64) UNIQUE NOT NULL,
    account_currency varchar(32) NOT NULL,
    account_balance numeric NOT NULL,
    account_status varchar(32) NOT NULL,
    users_user_id bigint NOT NULL REFERENCES payments.users (user_id)
);

CREATE TABLE payments.cards
(
    card_id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    card_number varchar(32) UNIQUE NOT NULL,
    card_expiration_date date NOT NULL,
    card_cvv integer NOT NULL,
    card_status varchar(32) NOT NULL,
    accounts_account_id bigint NOT NULL REFERENCES payments.accounts (account_id)
);

CREATE TABLE payments.transactions
(
    transaction_id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    transaction_uuid varchar(128) UNIQUE NOT NULL,
    transaction_type varchar(32) NOT NULL,
    opposite_account_number varchar(64) NOT NULL,
    transaction_amount numeric NOT NULL,
    transaction_timestamp timestamp without time zone NOT NULL,
    transaction_status varchar(32) NOT NULL,
    accounts_account_id bigint NOT NULL REFERENCES payments.accounts (account_id)
);