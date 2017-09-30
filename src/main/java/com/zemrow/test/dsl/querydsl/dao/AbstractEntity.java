package com.zemrow.test.dsl.querydsl.dao;

import java.util.UUID;

/**
 * Универсальная запись БД
 * Created on 15.03.2017.
 *
 * @author Alexandr Polyakov
 */
public abstract class AbstractEntity {
    public UUID id;

    public AbstractEntity() {
        this(null);
    }

    public AbstractEntity(UUID id) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;

        AbstractEntity that = (AbstractEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
