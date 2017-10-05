/*
 * This file is generated by jOOQ.
*/
package com.zemrow.test.dsl.jooq.autogen.tables.records;


import com.zemrow.test.dsl.jooq.autogen.tables.AuthSession;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;

import javax.annotation.Generated;
import java.util.UUID;


/**
 * Сессия пользователя
 */
@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.9.0"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class AuthSessionRecord extends UpdatableRecordImpl<AuthSessionRecord> implements Record6<UUID, UUID, String, Long, Long, UUID> {

    private static final long serialVersionUID = 1132375638;

    /**
     * Setter for <code>auth.auth_session.id</code>. ID записи
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>auth.auth_session.id</code>. ID записи
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>auth.auth_session.auth_entry_point_id</code>. Точка входа пользователя
     */
    public void setAuthEntryPointId(UUID value) {
        set(1, value);
    }

    /**
     * Getter for <code>auth.auth_session.auth_entry_point_id</code>. Точка входа пользователя
     */
    public UUID getAuthEntryPointId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>auth.auth_session.token</code>. Уникальный идентификатор сессии, сложный для подбора
     */
    public void setToken(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>auth.auth_session.token</code>. Уникальный идентификатор сессии, сложный для подбора
     */
    public String getToken() {
        return (String) get(2);
    }

    /**
     * Setter for <code>auth.auth_session.create_time</code>. Дата создания записи
     */
    public void setCreateTime(Long value) {
        set(3, value);
    }

    /**
     * Getter for <code>auth.auth_session.create_time</code>. Дата создания записи
     */
    public Long getCreateTime() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>auth.auth_session.delete_time</code>. Дата удаления записи
     */
    public void setDeleteTime(Long value) {
        set(4, value);
    }

    /**
     * Getter for <code>auth.auth_session.delete_time</code>. Дата удаления записи
     */
    public Long getDeleteTime() {
        return (Long) get(4);
    }

    /**
     * Setter for <code>auth.auth_session.deleted_by</code>. Пользователь удаливший запись
     */
    public void setDeletedBy(UUID value) {
        set(5, value);
    }

    /**
     * Getter for <code>auth.auth_session.deleted_by</code>. Пользователь удаливший запись
     */
    public UUID getDeletedBy() {
        return (UUID) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<UUID, UUID, String, Long, Long, UUID> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<UUID, UUID, String, Long, Long, UUID> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UUID> field1() {
        return AuthSession.AUTH_SESSION.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UUID> field2() {
        return AuthSession.AUTH_SESSION.AUTH_ENTRY_POINT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return AuthSession.AUTH_SESSION.TOKEN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field4() {
        return AuthSession.AUTH_SESSION.CREATE_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field5() {
        return AuthSession.AUTH_SESSION.DELETE_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UUID> field6() {
        return AuthSession.AUTH_SESSION.DELETED_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UUID value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UUID value2() {
        return getAuthEntryPointId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getToken();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value4() {
        return getCreateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value5() {
        return getDeleteTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UUID value6() {
        return getDeletedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthSessionRecord value1(UUID value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthSessionRecord value2(UUID value) {
        setAuthEntryPointId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthSessionRecord value3(String value) {
        setToken(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthSessionRecord value4(Long value) {
        setCreateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthSessionRecord value5(Long value) {
        setDeleteTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthSessionRecord value6(UUID value) {
        setDeletedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthSessionRecord values(UUID value1, UUID value2, String value3, Long value4, Long value5, UUID value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AuthSessionRecord
     */
    public AuthSessionRecord() {
        super(AuthSession.AUTH_SESSION);
    }

    /**
     * Create a detached, initialised AuthSessionRecord
     */
    public AuthSessionRecord(UUID id, UUID authEntryPointId, String token, Long createTime, Long deleteTime, UUID deletedBy) {
        super(AuthSession.AUTH_SESSION);

        set(0, id);
        set(1, authEntryPointId);
        set(2, token);
        set(3, createTime);
        set(4, deleteTime);
        set(5, deletedBy);
    }
}