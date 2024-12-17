INSERT INTO payments.users (user_name, user_password)
VALUES ('payments', '$2a$12$M.PX8izRwZO7le6n168LYO2BTp8FItI5z6WVYSBsBKiggidBBSokq'),
       ('java', '$2a$12$f1a2Q3XvCmJsW0DM/wnh8eqzmlnZuG2r0VdcI.KDflY4y6r.ea0dy'),
       ('spring', '$2a$12$e88yr1u0pYJEsarbqZllt.A7vdglk8ePwidM5l0tQHH76OkALCW6i'),
       ('admin', '$2a$10$wn/OIrmp1YV.2LOaExEw/.cYugHc0Dm7VHyOgng4rIp9Q045mKHMS'),
       ('user', '$2a$12$EysLwygGcI32VdaPwphx0enC4xD5kQb603ohTDYMcj/P8zs6TxfoO');

INSERT INTO payments.roles (user_role, users_user_id)
VALUES ('ROLE_ADMIN', 1),
       ('ROLE_CUSTOMER', 1),
       ('ROLE_CUSTOMER', 2),
       ('ROLE_CUSTOMER', 3),
       ('ROLE_ADMIN', 4),
       ('ROLE_CUSTOMER', 5);

INSERT INTO payments.accounts (account_number, account_currency, account_balance, account_status, users_user_id)
VALUES ('AccNo65827647148812', 'BYN', 800, 'LOCKED', 1),
       ('AccNo02046614305685', 'RUB', 100, 'ACTIVE', 1),
       ('AccNo44512291105731', 'USD', 300, 'ACTIVE', 1),
       ('AccNo30835714230230', 'EUR', 400, 'ACTIVE', 1),
       ('AccNo94836701173487', 'BYN', 700, 'ACTIVE', 2),
       ('AccNo19415712205877', 'BYN', 500, 'ACTIVE', 3),
       ('AccNo40318911090276', 'USD', 900, 'ACTIVE', 4),
       ('AccNo22071048978566', 'EUR', 600, 'ACTIVE', 4);

INSERT INTO payments.transactions (transaction_uuid, transaction_type, opposite_account_number, transaction_amount, transaction_timestamp, transaction_status, accounts_account_id)
VALUES ('ca8a22fb-7831-432b-b637-b3c5cbb3c7cb', 'TRANSFER', 'AccNo94836701173487', 100, '2024-10-06 18:44:03.787905', 'SUCCESSFUL', 1),
       ('eaedbdb8-8bcc-47fb-a54b-db022c28b0df', 'TRANSFER', 'AccNo65827647148812', 100, '2024-10-06 18:44:03.807406', 'SUCCESSFUL', 5),
       ('2f031eea-a6f2-4b99-a3f4-368749ba836a', 'TRANSFER', 'AccNo19415712205877', 100, '2024-10-06 18:44:21.745041', 'SUCCESSFUL', 1),
       ('8bfb6378-42dd-4f28-8842-a4a2112382ff', 'TRANSFER', 'AccNo65827647148812', 100, '2024-10-06 18:44:21.750951', 'SUCCESSFUL', 6),
       ('98642efb-cbff-4ff5-9c26-6a0b237cda9e', 'TRANSFER', 'AccNo40318911090276', 100, '2024-10-06 18:44:47.320817', 'SUCCESSFUL', 3),
       ('bcac3052-487f-4cef-831e-238a2d7dbd1c', 'TRANSFER', 'AccNo44512291105731', 100, '2024-10-06 18:44:47.326745', 'SUCCESSFUL', 7),
       ('bd1560c2-155e-41fb-a55e-a3a898a0f6b5', 'PAYMENT', 'AccNo44512291105731', 300, '2024-10-06 18:45:40.30767', 'SUCCESSFUL', 7),
       ('9bf9ddc3-5191-476a-92cc-d3b1a2d91253', 'PAYMENT', 'AccNo40318911090276', 300, '2024-10-06 18:45:40.313561', 'SUCCESSFUL', 3),
       ('be9a2e84-8a81-4b95-8178-ab7631fd9b14', 'TRANSFER', 'AccNo30835714230230', 100, '2024-10-06 18:46:03.15998', 'SUCCESSFUL', 8),
       ('03799bfd-5bc9-4663-ab33-6be98831b75b', 'TRANSFER', 'AccNo22071048978566', 100, '2024-10-06 18:46:03.16498', 'SUCCESSFUL', 4);