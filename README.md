# Baby billing system. Трек - Тестирование
![Screenshot_1](https://github.com/user-attachments/assets/e1d95ad5-aa40-4251-93bb-e0129630c2a4)

## Структура репозитория

📁 autotests_v1-main
    📁 1
        📄 BrtApiTest.java
        📄 CdrApiTest.java
        📄 CrmApiTest.java
        📄 HrsApiTest.java
        📄 pom.xml
        📄 run
    📁 2
        📁 cdr
            📄 CdrAuthTest.java
            📄 CdrTestUtils.java
        📁 resources
            📄 application-test.yml.yaml
            📄 test-cdr-invalid.csv
            📄 test-cdr-valid.csv
        📄 run
    📁 3
        📄 ClassicTariffTest.java
        📄 MonthlyTariffTest.java
        📄 TariffTestBase.java
        📄 application-test.yml.yaml
        📄 pom.xml
        📄 run
    📁 4
        📄 4.ipynb
        📄 4.py
        📄 link.txt
    📄 README.md
    📁 postman
        📄 microservices_api_tests.postman_collection.json
    📁 allure-main
        📄 microservices_api_tests.postman_collection.json

# [Тест-план для тестирования системы тарификации](https://docs.google.com/document/d/1jI9S5phXTAnhaIBqtQijtmLmj8IBig_Es1HsV9AKkBs/edit?tab=t.0)

# [ Отчет о тестировании системы тарификации](https://docs.google.com/document/d/1JkS57-5XGUrQ1RFn8vW-zl0jUhNdXGZoXGsj0D9Optk/edit?usp=sharing)


# Описание автотестов из тест-плана

## 1 - CDR API – Интеграционные автотесты

### Назначение
Набор интеграционных автотестов для проверки REST API сервиса CDR (Call Detail Records). Тестируются основные функции генерации, получения и удаления CDR-файлов.

### Описание тестов

| №            | Название теста                              | Endpoint              | Описание |
|--------------|---------------------------------------------|-----------------------|----------|
| ATC-CDR-001  | `generateCdrFile_ShouldReturnAccepted`      | `POST /api/cdr/generate` | Проверка успешного запуска генерации CDR-файла. Ожидается статус `202 Accepted`. |
| ATC-CDR-002  | `getLastCdrFile_ShouldReturnValidStructure` | `GET /api/cdr/last`   | Проверка структуры ответа и статуса при запросе последнего CDR-файла. |
| ATC-CDR-003  | `getCdrFileByDate_ShouldReturnValidData`    | `GET /api/cdr/by-date?date=YYYY-MM-DD` | Проверка фильтрации CDR-файлов по дате. |
| ATC-CDR-004  | `getCdrFileByInvalidDate_ShouldReturnBadRequest` | `GET /api/cdr/by-date?date=invalid` | Проверка обработки некорректного параметра даты. Ожидается `400 Bad Request`. |
| ATC-CDR-005  | `deleteCdrFiles_ShouldReturnSuccess`        | `DELETE /api/cdr`     | Проверка удаления всех CDR-файлов. Ожидается успешный ответ со статусом `200 OK`. |

## 2 - BRT API – Интеграционные автотесты

### Назначение
Набор автотестов предназначен для проверки корректной работы BRT (Billing and Routing Table) API, включая регистрацию абонентов, смену тарифа и получение информации.

### Описание тестов

| №              | Название теста                               | Endpoint              | Описание |
|----------------|----------------------------------------------|-----------------------|----------|
| ATC-BRT-001    | `registerSubscriber_ShouldReturnCreated`     | `POST /api/brt/register` | Регистрирует нового абонента, ожидается статус `201 Created`. |
| ATC-BRT-002    | `changeTariff_ShouldReturnSuccess`           | `PUT /api/brt/tariff` | Меняет тариф абонента. Ожидается статус `200 OK`. |
| ATC-BRT-003    | `getSubscriberInfo_ShouldReturnCorrectData`  | `GET /api/brt/info?phone=...` | Возвращает информацию о тарифе по номеру телефона. |
| ATC-BRT-004    | `changeTariff_InvalidPhone_ShouldReturnNotFound` | `PUT /api/brt/tariff` | Пытается сменить тариф несуществующему абоненту. Ожидается `404`. |
| ATC-BRT-005    | `registerSubscriber_Duplicate_ShouldReturnConflict` | `POST /api/brt/register` | Пытается повторно зарегистрировать абонента. Ожидается `409 Conflict`. |

## 3 - HRS API – Интеграционные автотесты

### Назначение
Набор автотестов предназначен для проверки корректной работы сервиса HRS (Home Routing Service), ответственного за маршрутизацию вызовов между абонентами.

### Описание тестов

| №  | Название теста                              | Endpoint              | Описание |
|----|----------------------------------------------|-----------------------|----------|
| ATC-HRS-001  | `routeCall_ShouldReturnSuccess`              | `POST /api/hrs/route` | Корректная маршрутизация звонка между двумя абонентами. |
| ATC-HRS-002  | `routeCall_InvalidReceiver_ShouldReturnError`| `POST /api/hrs/route` | Ошибка при маршрутизации на несуществующего абонента. |
| ATC-HRS-003  | `getRoutingTable_ShouldReturnList`           | `GET /api/hrs/routes` | Получение текущей таблицы маршрутов. |
| ATC-HRS-004  | `clearRoutingTable_ShouldSucceed`            | `DELETE /api/hrs/routes` | Очистка всех маршрутов. |
| ATC-HRS-005  | `routeCall_WithoutReceiver_ShouldReturnBadRequest` | `POST /api/hrs/route` | Ошибка при отсутствии параметра `receiver`. |

## 4 - CRM API – Интеграционные автотесты

### Назначение
Набор автотестов предназначен для проверки корректной работы сервиса CRM (Customer Relationship Management), включая создание, получение, обновление и удаление клиентов.

### Описание тестов

| №  | Название теста                              | Endpoint                     | Описание |
|----|----------------------------------------------|------------------------------|----------|
| ATC-CRM-001  | `createCustomer_ShouldReturnCreated`         | `POST /api/crm/customers`     | Создание нового клиента с обязательными полями `name` и `email`. |
| ATC-CRM-002  | `getCustomer_ShouldReturnValidCustomer`     | `GET /api/crm/customers/{id}` | Получение информации о клиенте по ID. |
| ATC-CRM-003  | `getNonExistingCustomer_ShouldReturnNotFound` | `GET /api/crm/customers/{id}` | Попытка получить несуществующего клиента. |
| ATC-CRM-004  | `updateCustomer_ShouldReturnUpdatedCustomer` | `PUT /api/crm/customers/{id}` | Обновление данных клиента. |
| ATC-CRM-005  | `deleteCustomer_ShouldReturnNoContent`      | `DELETE /api/crm/customers/{id}` | Удаление клиента по ID. |











