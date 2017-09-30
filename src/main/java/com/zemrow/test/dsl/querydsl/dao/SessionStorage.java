package com.zemrow.test.dsl.querydsl.dao;

import java.sql.Connection;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Объект хранящий сессию пользователя в рамках обработки запроса
 * инициализируется после проверки token, и передается между слояви как параметр
 * <p>
 * Created on 16.03.2017.
 *
 * @author Alexandr Polyakov
 */
public class SessionStorage implements Cloneable {
    /**
     * Соединение с базой данных, создается в сервелете для проверки сессии пользователя и держится до завершения обработки запроса
     * транзакция по умолчанию начата (для длительных запросов предусмотреть механизм закрытия транзакции или всего соединения)
     */
    public Connection connection;
    /**
     * Уникальный идентификатор сессии, сложный для подбора
     */
    public String token;
    /**
     * ID пользователя
     */
    public UUID userId;
    /**
     * Локаль с которой работает пользователя
     */
    //TODO добавить поле в пользователя
    public Locale locale;
    /**
     * Временная зона пользователя
     */
    //TODO добавить поле в пользователя
    public TimeZone timeZone;

    @Override
    public SessionStorage clone() {
        SessionStorage copy = new SessionStorage();
        copy.token = token;
        copy.userId = userId;
        copy.locale = locale;
        copy.timeZone = timeZone;
        return copy;
    }

//TODO раскоментировать переменные сделать final
//    public SessionStorage(Connection connection, String token, Long userId, UserTypeEnum userType, Locale locale, TimeZone timeZone) {
//        this.connection = connection;
//        this.token = token;
//        this.userId = userId;
//        this.userType = userType;
//        this.locale = locale;
//        this.timeZone = timeZone;
//    }
}
