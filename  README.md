# SQL_Homework

## Для проверки
- Выполняем команду docker-compose up -d
- Запускаем наш сервис java -jar app-deadline.jar -P:jdbc.url=jdbc:mysql://localhost:3306/app -P:jdbc.user=app -P:jdbc.password=pass
- Проверяем таблицы в БД с помозью клиента docker-compose exec mysql mysql -u app -D app -p (пароль pass)
- Запускаем тесты 