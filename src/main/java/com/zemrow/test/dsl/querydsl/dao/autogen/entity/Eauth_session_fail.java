package com.zemrow.test.dsl.querydsl.dao.autogen.entity;

import com.zemrow.test.dsl.querydsl.dao.AbstractEntity;
import com.zemrow.test.dsl.querydsl.dao.SessionStorage;

/**
 * Класс сгенегирован автоматически, для таблицы Eauth_session_fail из БД
 */
public class Eauth_session_fail extends AbstractEntity {

    public java.util.UUID auth_entry_point_id;

    public String ip_address;

    public String comment;

    public Long create_time;

    public Eauth_session_fail() {
    }

    public Eauth_session_fail(java.util.UUID id, java.util.UUID auth_entry_point_id, String ip_address, String comment, Long create_time) {
        super(id);
        this.auth_entry_point_id = auth_entry_point_id;
        this.ip_address = ip_address;
        this.comment = comment;
        this.create_time = create_time;
    }

    @Override
    public void preInsert(SessionStorage session) {
        super.preInsert(session);
        if (create_time == null) {
            create_time = System.currentTimeMillis();
        }
    }

}

