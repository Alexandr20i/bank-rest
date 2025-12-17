# Bank REST API

Система управления банковскими картами с поддержкой JWT, ролей, переводов и Liquibase.

---

##  Технологии

- Java 17+
- Spring Boot
- Spring Security + JWT
- Spring Data JPA
- PostgreSQL
- Liquibase
- Docker + Docker Compose
- Swagger / OpenAPI

---

## Быстрый старт (Docker)

1. Собрать jar:

`./mvnw clean package -DskipTests`

2. Запустить Docker Compose:

`docker-compose up --build`

3. Swagger UI:

http://localhost:8080/swagger-ui/index.html

4. Доступ к базе (PostgreSQL):

DB: bank_db

User: bank_user

Password: bank_pass

Port: 5432

## Локальный запуск без Docker

1. Настроить базу PostgreSQL

2. Настроить application.yml с параметрами подключения

3. Применить миграции Liquibase

4. Запустить:

`./mvnw spring-boot:run`

## База данных
Используется PostgreSQL, запускаемый в Docker.
Параметры по умолчанию:
* DB: bank_db
* User: bank_user
* Password: bank_pass
* Port: 5432

Миграции выполняются автоматически через Liquibase при старте приложения.

##  Аутентификация
* POST /api/auth/register — регистрация пользователя
* POST /api/auth/login — вход и получение JWT
* Для защищённых эндпоинтов передавать:
`Authorization: Bearer <JWT>
`

## API
* Swagger UI: /swagger-ui/index.html
* Endpoints:

`/api/cards — CRUD карты (USER)`

`/api/transfers — переводы (USER)`

`/api/admin/** — админские операции`

## Юнит-тесты
Все ключевые сервисы покрыты тестами
Запуск:

`./mvnw test`

## Структура проекта

src/main/java/com/bank/bankrest

├── controller   # REST-контроллеры

├── domain       # Entity и enum

├── dto          # Request / Response DTO

├── exception    # Обработка ошибок

├── mapper       # Мапперы Entity → DTO

├── repository   # JPA-репозитории

├── security     # JWT и Spring Security

├── service      # Бизнес-логика

└── config       # Конфигурации (Swagger, Security)

src/main/resources/db/migration

├── db.changelog-master.yaml

├── 001-create-roles.yaml

├── 002-create-users.yaml

├── 003-create-cards.yaml

└── 004-create-transfers.yaml

## Примечания
* Пароли хранятся в зашифрованном виде (BCrypt)
* Номера карт хранятся в зашифрованном виде и возвращаются с маской
* Доступ к API ограничен ролями
* Проект запускается одной командой через Docker Compose