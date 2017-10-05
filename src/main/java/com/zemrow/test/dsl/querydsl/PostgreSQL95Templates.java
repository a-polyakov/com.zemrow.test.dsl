package com.zemrow.test.dsl.querydsl;

import com.querydsl.sql.PostgreSQLTemplates;

/**
 * Created on 19.01.2017.
 *
 * @author Alexandr Polyakov
 */
public class PostgreSQL95Templates extends PostgreSQLTemplates {

    public static final PostgreSQL95Templates INSTANCE = new PostgreSQL95Templates();

    private PostgreSQL95Templates() {
        super();
        // По умолчанию в запрос не включается имя схемы, исправляем
        setPrintSchema(true);
    }
}
