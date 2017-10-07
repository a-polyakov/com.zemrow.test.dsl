package com.zemrow.test.dsl.jooq.impl;

import com.zemrow.test.dsl.querydsl.dao.SessionStorage;

import java.util.UUID;

/**
 * Универсальная запись БД
 * Created on 15.03.2017.
 *
 * @author Alexandr Polyakov
 */
public abstract class AbstractEntity {

    /**
     * ID записи
     */
    protected UUID id;

    public AbstractEntity() {
    }

    public AbstractEntity(AbstractEntity value) {
        this.id = value.id;
    }

    public AbstractEntity(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void preInsert(final SessionStorage session) {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    public void preUpdate(final SessionStorage session) {
    }

    public void preDelete(final SessionStorage session) {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AbstractEntity)) return false;
        final AbstractEntity that = (AbstractEntity) obj;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
