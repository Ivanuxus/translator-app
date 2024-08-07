# translator-app
A Spring Boot application for translating text using Yandex API.
# Translator App

	Клонируйте репозиторий:
git clone https://github.com/Ivanuxus/translator-app.git
	Перейдите в директорию проекта:
cd translator-app
	Код создания бд в PostgreSQL
	CREATE TABLE translations (
    id SERIAL PRIMARY KEY,
    ip_address VARCHAR(45) NOT NULL,
    input_text TEXT NOT NULL,
    translated_text TEXT NOT NULL,
    timestamp TIMESTAMP NOT NULL
);

	Добавьте в application.properties yandex.api.keyб,yandex.folder.id, реквизиты созданной бд в PostgreSQL
	Убедитесь, что у вас установлены все зависимости:
./mvnw clean install
	Запустите приложение:
./mvnw spring-boot:run
Использование
Откройте браузер и перейдите по адресу http://localhost:8080. Вы увидите интерфейс для перевода слов.

Информация о проекте
Этот проект использует Spring Boot для создания веб-приложения и JDBC для работы с базой данных PostgreSQL. Для перевода используется API Яндекс Переводчика.