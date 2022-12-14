Postman Collection: https://www.getpostman.com/collections/1728895f526bfcd3b66a
![swagger](https://user-images.githubusercontent.com/93388658/192115939-755b63d0-735d-49ad-bb7c-a2afce7e5ea5.jpg)

To run: Compile & java -jar FootballManager-0.0.1-SNAPSHOT.war

Тестове завдання: "Футбольний менеджер"

Реалізувати систему керування футбольними командами/гравцями з використанням Java 8 (мінімум), Spring Boot, Hibernate/JDBC Template .


Система повинна підтримувати такий функціонал:

базові CRUD операції для роботи з командами та гравцями (у відповідності до REST стилю)
операція трансферу гравця з однієї команди в іншу:
вартість трансферу = кількість місяців досвіду гравця * 100000 / вік гравця у роках
комісія зі сторони команди (від 0% до 10% від вартості трансферу) - вказується в інформації про команду
повна сума (вартість трансферу + комісія) повинна зніматись з рахунку команди, яка купує гравця, і переходити на рахунок команди, яка продає

Реалізувати початкове наповнення бази даних гравців та команд.

Операції, що проводяться з гравцями/командами повинні валідуватись зі сторони клієнта та сервера. Помилки повинні оброблятись з поверненням відповідного HTTP статусу.

Створити Postman колекцію з запитами відповідно до реалізованого REST API.

Проект слід розмістити у публічному GitHub репозиторії (чи будь-якому іншому Git репозиторії на вибір). Проект повинен запускатись однією командою.
