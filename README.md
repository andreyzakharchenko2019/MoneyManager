Требования к проекту 
 
1.	Code Convention 
1.	Допустимое по CC название пакетов 
2.	Допустимое по CC название классов 
3.	Допустимое по CC название полей, методов, констант 
4.	Единый стиль расположения в классах: методов, констант, переменных, переопределенных методов
5.	Не должно быть не использованного кода (исключение get, set методы) 
6.	Не должно быть за комментированного кода. 
7.	Не допускается в коде хардкор переменных request.setAtributte(“id”,smth); 
Должно быть: 
public  static  final String ID=”id”; 
request.setAtributte(ID,smth); 
*И потом использовать константу ID везде где она встречается. 
8.	Не использовать магические числа
someMethod(5); 
someVal=5; 
Должно быть вынесено в константы или описано комментариями, что за цифра и для чего используется 
 
2.	Мультиязычность 
1.	В системе должно быть реализовано минимум 2 языка  
2.	В системе должна быть возможность в любой момент добавить еще один язык  
 
3.	Валидация 
1.	Обязательна поддержка кириллицы 
2.	В поля не должны вводится недопустимые значения 
3.	Поля с вводом пароля должны экранироваться 
4.	Поле для ввода емайла должно проверять на корректность емайл 
5.	Проверка на заполнение всех обязательных полей 
6.	Проверка расширений файлов  
 
4.	Общие требования к проектам 
1.	Пароли должны хэшироваться  
2.	Обязательна реализация jsp –servlet 
3.	Сервлет должен быть один (исключение сервлет для обработки графических изображений)  
4.	Рекомендовано использование CSS библиотек  
5.	Необходимо использовать стандартное JDBС 
6.	Необходимо реализовать собственный ConnectionPool 
7.	Должно быть реализовано не менее трех паттернов проектирования 
8.	Необходимо реализовать хотя бы один фильтр 
9.	Обязательна реализация MVC  
10.	Использование всех парадигм ООП  
11.	Использование log4j 
12.	Запрещено использовать аннотации кроме @Override 
13.	Не использовать внешние фреймворки  
 
5.	Требования к бд 
1.	БД должна быть доведена до 3 нормальной формы 
2.	Все таблицы должны быть связаны между собой  
3.	Должно быть реализовано View 
4.	Все поля должны быть с ограничениями на количество символов и их содержание   
5.	Допустимые СУБД: MySQL, H2, PostgreSQL, Oracle
