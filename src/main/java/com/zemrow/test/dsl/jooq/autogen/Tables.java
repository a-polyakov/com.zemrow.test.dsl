/*
 * This file is generated by jOOQ.
*/
package com.zemrow.test.dsl.jooq.autogen;


import com.zemrow.test.dsl.jooq.autogen.tables.AuthEntryPoint;
import com.zemrow.test.dsl.jooq.autogen.tables.AuthSession;
import com.zemrow.test.dsl.jooq.autogen.tables.AuthSessionFail;
import com.zemrow.test.dsl.jooq.autogen.tables.AuthUser;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in auth
 */
@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.9.0"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Tables {

    /**
     * Способы авторизации пользователя
     */
    public static final AuthEntryPoint AUTH_ENTRY_POINT = com.zemrow.test.dsl.jooq.autogen.tables.AuthEntryPoint.AUTH_ENTRY_POINT;

    /**
     * Сессия пользователя
     */
    public static final AuthSession AUTH_SESSION = com.zemrow.test.dsl.jooq.autogen.tables.AuthSession.AUTH_SESSION;

    /**
     * Неудачные попытки войти в систему
     */
    public static final AuthSessionFail AUTH_SESSION_FAIL = com.zemrow.test.dsl.jooq.autogen.tables.AuthSessionFail.AUTH_SESSION_FAIL;

    /**
     * Пользователь
     */
    public static final AuthUser AUTH_USER = com.zemrow.test.dsl.jooq.autogen.tables.AuthUser.AUTH_USER;
}
