# Используем образ с Java
FROM openjdk:20

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем JAR-файл вашего приложения в контейнер
COPY target/POIS-0.0.1-SNAPSHOT.jar app.jar

# Запускаем приложение при старте контейнера
CMD ["java", "-jar", "app.jar"]
