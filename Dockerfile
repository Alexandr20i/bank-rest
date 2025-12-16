# ===== СТАДИЯ СБОРКИ =====
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /build
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# ===== СТАДИЯ ЗАПУСКА =====
FROM eclipse-temurin:17-jdk

# Рабочая директория внутри контейнера
WORKDIR /app

# Копируем jar-файл
COPY --from=build /build/target/*.jar app.jar

# Открываем порт
EXPOSE 8080

# Запуск приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
