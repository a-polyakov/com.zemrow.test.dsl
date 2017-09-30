/*
 * This file is generated by jOOQ.
*/
package com.zemrow.test.dsl.jooq.autogen;


import com.zemrow.test.dsl.jooq.autogen.tables.AuthEntryPoint;
import com.zemrow.test.dsl.jooq.autogen.tables.AuthSession;
import com.zemrow.test.dsl.jooq.autogen.tables.AuthSessionFail;
import com.zemrow.test.dsl.jooq.autogen.tables.AuthUser;
import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.9.0"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Auth extends SchemaImpl {

    private static final long serialVersionUID = 218907011;

    /**
     * The reference instance of <code>auth</code>
     */
    public static final Auth AUTH = new Auth();

    /**
     * Способы авторизации пользователя
     */
    public final AuthEntryPoint AUTH_ENTRY_POINT = com.zemrow.test.dsl.jooq.autogen.tables.AuthEntryPoint.AUTH_ENTRY_POINT;

    /**
     * Сессия пользователя
     */
    public final AuthSession AUTH_SESSION = com.zemrow.test.dsl.jooq.autogen.tables.AuthSession.AUTH_SESSION;

    /**
     * Неудачные попытки войти в систему
     */
    public final AuthSessionFail AUTH_SESSION_FAIL = com.zemrow.test.dsl.jooq.autogen.tables.AuthSessionFail.AUTH_SESSION_FAIL;

    /**
     * Пользователь
     */
    public final AuthUser AUTH_USER = com.zemrow.test.dsl.jooq.autogen.tables.AuthUser.AUTH_USER;

    /**
     * No further instances allowed
     */
    private Auth() {
        super("auth", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
                AuthEntryPoint.AUTH_ENTRY_POINT,
                AuthSession.AUTH_SESSION,
                AuthSessionFail.AUTH_SESSION_FAIL,
                AuthUser.AUTH_USER);
    }
}
