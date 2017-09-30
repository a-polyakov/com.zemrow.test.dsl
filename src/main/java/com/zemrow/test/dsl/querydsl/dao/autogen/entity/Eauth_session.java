package com.zemrow.test.dsl.querydsl.dao.autogen.entity;

import com.zemrow.test.dsl.querydsl.dao.AbstractEntity;
import com.zemrow.test.dsl.querydsl.dao.SessionStorage;

/**
 * Класс сгенегирован автоматически, для таблицы Eauth_session из БД
 */
public class Eauth_session extends AbstractEntity {

    public java.util.UUID auth_entry_point_id;

    public String token;

    public Long create_time;

    public Long delete_time;

    public java.util.UUID deleted_by;

    public Eauth_session() {
    }

    public Eauth_session(java.util.UUID id, java.util.UUID auth_entry_point_id, String token, Long create_time, Long delete_time, java.util.UUID deleted_by) {
        super(id);
        this.auth_entry_point_id = auth_entry_point_id;
        this.token = token;
        this.create_time = create_time;
        this.delete_time = delete_time;
        this.deleted_by = deleted_by;
    }

    @Override
    public void preInsert(SessionStorage session) {
        super.preInsert(session);
        if (create_time == null) {
            create_time = System.currentTimeMillis();
        }
    }

    @Override
    public void preDelete(SessionStorage session) {
        super.preDelete(session);
        if (delete_time == null) {
            delete_time = System.currentTimeMillis();
        }
        if (deleted_by == null) {
            deleted_by = session.userId;
        }
    }

}

