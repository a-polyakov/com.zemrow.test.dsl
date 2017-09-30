##Пробуем DSL для работы с SQL
DSL (Domain-specific language) - Предметно-ориентированный язык. Запрос к базе можно стоить вызовом функций, например 
> select(person.name).from(person).where(person.id.eq(10)).orderBy(person.lastName.asc())

При этом уменьшается возможность ошибиться (опечатка в имени не возможна - программа не скомпилируется), идет контроль типов...

### Рассматриваются                                                                                                                        
* [querydsl](http://www.querydsl.com/)
* [jooq](https://www.jooq.org/)  

### Подготовка к работе
* Работа с БД требует наличие postgres и выполнить скрипты из _init.sql_ (настройки соединения см. _com.zemrow.test.dsl.DBConst.java_)
* затем необходимо выполнить _com.zemrow.test.dsl.liquibase.RunLiquibase_ он создаст таблицы
* querydsl
  * запустить _com.zemrow.test.dsl.querydsl.RunQuerydslGenerateConst_ будут сгенерированы константы и entity в пакет _com.zemrow.test.dsl.querydsl.dao.autogen_
  * использование в _com.zemrow.test.dsl.querydsl.RunQuerydslTest_
* jooq
  * запустить _com.zemrow.test.dsl.jooq.RunJooqGenerateConst_ будут сгенерированы константы и entity в пакет _com.zemrow.test.dsl.jooq.autogen_
  * использование в _com.zemrow.test.dsl.jooq.RunJooqTest_

Основная задумка: база является основой
* изменения вносятся в схему БД (используя liquibase)
* после изменения автоматически вносятся в код (SQL константы и DSL классы)
* программа перестанет компилироваться если где-то в SQL ошибка
* поверх легко написать генераторы кода для других слоев: DAO, Service, Controller, Servlet