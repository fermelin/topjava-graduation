INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User', 'user@gmail.com', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO USER_ROLES (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANT (name, address)
VALUES ('Ресторан 1', 'Адрес 1'),
       ('Ресторан 2', 'Адрес 2'),
       ('Ресторан 3', 'Адрес 3'),
       ('Ресторан 4', 'Адрес 4');

INSERT INTO VOTE (time_vote, date_vote, user_id, rest_id)
VALUES ('00:00', CURRENT_DATE, 1, 1),
       ('10:00', '2024-09-06', 1, 3),
       ('13:00', CURRENT_DATE, 2, 2),
       ('13:00', '2024-09-06', 2, 3);

INSERT INTO DISH (name, price, rest_id)
VALUES ('Блюдо 1', 330, 1),
       ('Блюдо 2', 1000, 1),
       ('Блюдо 3', 500, 1),
       ('Блюдо 4', 400, 2),
       ('Блюдо 5', 250, 2),
       ('Блюдо 6', 200, 2),
       ('Блюдо 7', 350, 3),
       ('Блюдо 8', 100, 3);

INSERT INTO MENU (menu_date, rest_id)
VALUES (CURRENT_DATE, 1),
       ('2024-08-31', 1),
       ('2024-08-29', 1),
       (CURRENT_DATE, 2),
       ('2024-08-31', 3);

INSERT INTO DISH_IN_MENU (menu_id, dish_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (4, 5),
       (4, 6),
       (5, 7),
       (5, 8);