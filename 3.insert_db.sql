insert into users (name, e_mail, password, premium) values ('Andrey', 'and@mail.ru', 'zxc', 1);
insert into users (name, e_mail, password) values ('Viktoriya', 'vika@mail.ru', 'asd');

insert into currency (name_currency) values ('₸');
insert into currency (name_currency) values ('$');
insert into currency (name_currency) values ('€');

insert into category (name_category, id_user) values ('Зарплата', 1);
insert into category (name_category, id_user) values ('Развлечения', 1);
insert into category (name_category, id_user) values ('Автомобиль', 1);
insert into category (name_category, id_user) values ('Зарплата', 2);
insert into category (name_category, id_user) values ('Благотоворительность', 2);
insert into category (name_category, id_user) values ('Фитнес', 2);

insert into wallet (name_wallet, currency, user_id, amount) values ('Наличные', 1, 1, 22568);
insert into wallet (name_wallet, currency, user_id, amount) values ('MasterCard', 1, 1, 68062);
insert into wallet (name_wallet, currency, user_id, amount) values ('Наличные', 1, 2, 15698);
insert into wallet (name_wallet, currency, user_id, amount) values ('Visa', 2, 2, 626);


insert into transaction (user_id, date, category, price, wallet, description, type_transaction) 
values (1, '2019-09-05', 1, 145000, 1, 'Хорошая премия', 1);
insert into transaction (user_id, date, category, price, wallet, description, type_transaction) 
values (1, '2019-09-15', 2, 2500, 2, 'В кино', 0);
insert into transaction (user_id, date, category, price, wallet, description, type_transaction) 
values (1, '2019-09-27', 3, 7526, 2, 'Заправка', 0);
insert into transaction (user_id, date, category, price, wallet, type_transaction) 
values (2, '2019-09-07', 4, 125000, 3, 1);
insert into transaction (user_id, date, category, price, wallet, description, type_transaction) 
values (2, '2019-09-18', 6, 9500, 4, 'Абонемент на месяц', 0);