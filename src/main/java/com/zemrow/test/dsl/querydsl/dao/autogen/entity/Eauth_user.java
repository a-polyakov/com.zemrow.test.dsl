package com.zemrow.test.dsl.querydsl.dao.autogen.entity;

import com.zemrow.test.dsl.querydsl.dao.AbstractEntity;
import com.zemrow.test.dsl.querydsl.dao.SessionStorage;

/**
 * Класс сгенегирован автоматически, для таблицы Eauth_user из БД
 */
public class Eauth_user extends AbstractEntity {

    public String label;

    public Long create_time;

    public java.util.UUID created_by;

    public Long update_time;

    public java.util.UUID updated_by;

    public Long delete_time;

    public java.util.UUID deleted_by;

    public Eauth_user() {
    }

    public Eauth_user(java.util.UUID id, String label, Long create_time, java.util.UUID created_by, Long update_time, java.util.UUID updated_by, Long delete_time, java.util.UUID deleted_by) {
        super(id);
        this.label = label;
        this.create_time = create_time;
        this.created_by = created_by;
        this.update_time = update_time;
        this.updated_by = updated_by;
        this.delete_time = delete_time;
        this.deleted_by = deleted_by;
    }

    @Override
    public void preInsert(SessionStorage session) {
        super.preInsert(session);
        if (create_time == null) {
            create_time = System.currentTimeMillis();
        }
        if (created_by == null) {
            created_by = session.userId;
        }
        update_time = System.currentTimeMillis();
        updated_by = session.userId;
    }

    @Override
    public void preUpdate(SessionStorage session) {
        super.preUpdate(session);
        update_time = System.currentTimeMillis();
        updated_by = session.userId;
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

