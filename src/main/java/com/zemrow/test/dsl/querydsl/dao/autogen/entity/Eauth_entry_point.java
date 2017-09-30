package com.zemrow.test.dsl.querydsl.dao.autogen.entity;

import com.zemrow.test.dsl.querydsl.dao.AbstractEntity;
import com.zemrow.test.dsl.querydsl.dao.SessionStorage;

/**
 * Класс сгенегирован автоматически, для таблицы Eauth_entry_point из БД
 */
public class Eauth_entry_point extends AbstractEntity {

    public java.util.UUID auth_user_id;

    public com.zemrow.test.dsl.querydsl.dao.EntryPointTypeEnum entry_point_type;

    public String client_id;

    public String credential;

    public Boolean validate;

    public Long create_time;

    public java.util.UUID created_by;

    public Long update_time;

    public java.util.UUID updated_by;

    public Long delete_time;

    public java.util.UUID deleted_by;

    public Eauth_entry_point() {
    }

    public Eauth_entry_point(java.util.UUID id, java.util.UUID auth_user_id, com.zemrow.test.dsl.querydsl.dao.EntryPointTypeEnum entry_point_type, String client_id, String credential, Boolean validate, Long create_time, java.util.UUID created_by, Long update_time, java.util.UUID updated_by, Long delete_time, java.util.UUID deleted_by) {
        super(id);
        this.auth_user_id = auth_user_id;
        this.entry_point_type = entry_point_type;
        this.client_id = client_id;
        this.credential = credential;
        this.validate = validate;
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

