/*
 * This file is generated by jOOQ.
*/
package com.zemrow.test.dsl.jooq.autogen.tables;


import com.zemrow.test.dsl.jooq.autogen.Auth;
import com.zemrow.test.dsl.jooq.autogen.Keys;
import com.zemrow.test.dsl.jooq.autogen.tables.records.AuthUserRecord;
import org.jooq.*;
import org.jooq.impl.TableImpl;

import javax.annotation.Generated;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


/**
 * Пользователь
 */
@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.9.0"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class AuthUser extends TableImpl<AuthUserRecord> {

    private static final long serialVersionUID = -846928614;

    /**
     * The reference instance of <code>auth.auth_user</code>
     */
    public static final AuthUser AUTH_USER = new AuthUser();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AuthUserRecord> getRecordType() {
        return AuthUserRecord.class;
    }

    /**
     * The column <code>auth.auth_user.id</code>. ID записи
     */
    public final TableField<AuthUserRecord, UUID> ID = createField("id", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "ID записи");

    /**
     * The column <code>auth.auth_user.label</code>. Состояние пользователя: Не в сети, В сети, Не беспокоить
     */
    public final TableField<AuthUserRecord, String> LABEL = createField("label", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "Состояние пользователя: Не в сети, В сети, Не беспокоить");

    /**
     * The column <code>auth.auth_user.create_time</code>. Дата создания записи
     */
    public final TableField<AuthUserRecord, Long> CREATE_TIME = createField("create_time", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "Дата создания записи");

    /**
     * The column <code>auth.auth_user.created_by</code>. Пользователь создавший запись
     */
    public final TableField<AuthUserRecord, UUID> CREATED_BY = createField("created_by", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "Пользователь создавший запись");

    /**
     * The column <code>auth.auth_user.update_time</code>. Дата обновления записи
     */
    public final TableField<AuthUserRecord, Long> UPDATE_TIME = createField("update_time", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "Дата обновления записи");

    /**
     * The column <code>auth.auth_user.updated_by</code>. Пользователь обновивший запись
     */
    public final TableField<AuthUserRecord, UUID> UPDATED_BY = createField("updated_by", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "Пользователь обновивший запись");

    /**
     * The column <code>auth.auth_user.delete_time</code>. Дата удаления записи
     */
    public final TableField<AuthUserRecord, Long> DELETE_TIME = createField("delete_time", org.jooq.impl.SQLDataType.BIGINT, this, "Дата удаления записи");

    /**
     * The column <code>auth.auth_user.deleted_by</code>. Пользователь удаливший запись
     */
    public final TableField<AuthUserRecord, UUID> DELETED_BY = createField("deleted_by", org.jooq.impl.SQLDataType.UUID, this, "Пользователь удаливший запись");

    /**
     * Create a <code>auth.auth_user</code> table reference
     */
    public AuthUser() {
        this("auth_user", null);
    }

    /**
     * Create an aliased <code>auth.auth_user</code> table reference
     */
    public AuthUser(String alias) {
        this(alias, AUTH_USER);
    }

    private AuthUser(String alias, Table<AuthUserRecord> aliased) {
        this(alias, aliased, null);
    }

    private AuthUser(String alias, Table<AuthUserRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "Пользователь");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Auth.AUTH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<AuthUserRecord> getPrimaryKey() {
        return Keys.PK_AUTH_USER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<AuthUserRecord>> getKeys() {
        return Arrays.<UniqueKey<AuthUserRecord>>asList(Keys.PK_AUTH_USER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<AuthUserRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<AuthUserRecord, ?>>asList(Keys.AUTH_USER__AUTH_USER__CREATED_BY__FK, Keys.AUTH_USER__AUTH_USER__UPDATED_BY__FK, Keys.AUTH_USER__AUTH_USER__DELETED_BY__FK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthUser as(String alias) {
        return new AuthUser(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public AuthUser rename(String name) {
        return new AuthUser(name, null);
    }
}
