package com.zemrow.test.dsl.querydsl.dao;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.sql.RelationalPath;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.dml.DefaultMapper;
import com.querydsl.sql.dml.SQLDeleteClause;
import com.querydsl.sql.dml.SQLInsertClause;
import com.querydsl.sql.dml.SQLUpdateClause;
import com.zemrow.test.dsl.querydsl.PostgreSQL95Configuration;

import java.util.List;

/**
 * Универсальное DAO (data access object) реализующее базовые методы работы с БД
 * - Получить entity по id
 * - Получить список entity
 * - Определение количества записей удовлетворяющее условиям\
 * - Добавление записи в таблицу
 * - Обновление записи в таблице по id
 * - Удаление записи из таблицы
 * - Пометить запись как удаленная
 * <p>
 * Created on 14.03.2017.
 *
 * @author Alexandr Polyakov
 */
public abstract class AbstractDao<E extends AbstractEntity, Q extends RelationalPath> {

    /**
     * SQL констанда идентификатора записи
     */
    private SimpleExpression id;

    /**
     * Класс entity c которым работает DAO
     *
     * @return
     */
    public abstract Class<E> getEntityClass();

    /**
     * SQL константы для таблицы
     *
     * @return
     */
    public abstract Q getSqlPath();

    /**
     * SQL констанда идентификатора записи
     *
     * @return
     */
    public SimpleExpression getIdPath() {
        if (id == null) {
            id = (SimpleExpression) getSqlPath().getPrimaryKey().getLocalColumns().get(0);
        }
        return id;
    }

    /**
     * Получить entity по id
     *
     * @param session
     * @param id
     * @return
     */
    public E select(final SessionStorage session, long id) {
        final SQLQuery<E> query = new SQLQuery(session.connection, PostgreSQL95Configuration.INSTANCE);
        query.select(getSqlPath());
        query.from(getSqlPath());
        query.where(getIdPath().eq(id));
        final E result = query.fetchOne();
        return result;
    }

    /**
     * Получить список entity
     *
     * @param session
     * @param where
     * @param order
     * @param offset
     * @param limit
     * @return
     */
    public List<E> select(final SessionStorage session, Predicate where[], OrderSpecifier order[], long offset, long limit) {
        final SQLQuery<E> query = new SQLQuery(session.connection, PostgreSQL95Configuration.INSTANCE);
        query.select(getSqlPath());
        query.from(getSqlPath());
        if (where != null) {
            query.where(where);
        }
        if (order != null) {
            query.orderBy(order);
        }
        query.offset(offset);
        query.limit(limit);
        final List<E> result = query.fetch();
        return result;
    }

    /**
     * Определение количества записей удовлетворяющее условиям
     *
     * @param session
     * @param where
     * @return
     */
    public long selectCount(final SessionStorage session, Predicate where[]) {
        final SQLQuery<Long> query = new SQLQuery(session.connection, PostgreSQL95Configuration.INSTANCE);
        query.from(getSqlPath());
        if (where != null) {
            query.where(where);
        }
        final long result = query.fetchCount();
        return result;
    }

    /**
     * Добавление записи в таблицу
     *
     * @param session
     * @param entity
     */
    public void insert(final SessionStorage session, E entity) {
        entity.preInsert(session);
        final SQLInsertClause query = new SQLInsertClause(session.connection, PostgreSQL95Configuration.INSTANCE, getSqlPath());
        query.populate(entity);
        query.execute();
    }

    /**
     * Добавление записи в таблицу
     *
     * @param session
     * @param entityArray
     */
    public void insertBatch(final SessionStorage session, E... entityArray) {
        if (entityArray != null && entityArray.length > 0) {
            final SQLInsertClause query = new SQLInsertClause(session.connection, PostgreSQL95Configuration.INSTANCE, getSqlPath());
            for (E entity : entityArray) {
                entity.preInsert(session);
                query.populate(entity);
                query.addBatch();
            }
            query.execute();
        }
    }

    /**
     * Обновление записи в таблице по id
     *
     * @param session
     * @param entity
     */
    public void update(final SessionStorage session, E entity) {
        entity.preUpdate(session);
        final SQLUpdateClause query = new SQLUpdateClause(session.connection, PostgreSQL95Configuration.INSTANCE, getSqlPath());
        query.populate(entity, DefaultMapper.WITH_NULL_BINDINGS);
        query.where(getIdPath().eq(entity.id));
        query.execute();
    }

    /**
     * Удаление записи из таблицы
     *
     * @param session
     * @param id
     */
    public void delete(final SessionStorage session, long id) {
        final SQLDeleteClause query = new SQLDeleteClause(session.connection, PostgreSQL95Configuration.INSTANCE, getSqlPath());
        query.where(getIdPath().eq(id));
        query.execute();
    }


    /**
     * Пометить запись как удаленная (работает медленно)
     *
     * @param session
     * @param id
     */
    public void markAsDeleted(final SessionStorage session, long id) {
        final E entity = select(session, id);
        markAsDeleted(session, entity);
    }

    /**
     * Пометить запись как удаленная (работает медленно)
     *
     * @param session
     * @param entity
     */
    public void markAsDeleted(final SessionStorage session, E entity) {
        entity.preDelete(session);
        update(session, entity);
    }
}
