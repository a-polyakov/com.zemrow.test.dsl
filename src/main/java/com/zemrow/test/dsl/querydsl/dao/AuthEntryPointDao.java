package com.zemrow.test.dsl.querydsl.dao;

import com.querydsl.sql.SQLQuery;
import com.zemrow.test.dsl.querydsl.PostgreSQL95Configuration;
import com.zemrow.test.dsl.querydsl.dao.autogen.constants.Qauth_entry_point;
import com.zemrow.test.dsl.querydsl.dao.autogen.entity.Eauth_entry_point;


/**
 * Created on 16.03.2017.
 *
 * @author Alexandr Polyakov
 */
public class AuthEntryPointDao extends AbstractDao<Eauth_entry_point, Qauth_entry_point> {

    public static final AuthEntryPointDao INSTANCE = new AuthEntryPointDao();
    private static final Qauth_entry_point aep = Qauth_entry_point.auth_entry_point;

    private AuthEntryPointDao() {
    }

    /**
     * Точка входа c данным типом и данным индификатором точки входа
     *
     * @param session  - SessionStorage
     * @param type     - тип точки входа
     * @param clientId - индификатор точки входа
     * @return
     */
    public Eauth_entry_point selectBy(final SessionStorage session, final EntryPointTypeEnum type, final String clientId) {
        final SQLQuery<Eauth_entry_point> query = new SQLQuery(session.connection, PostgreSQL95Configuration.INSTANCE);
        query.select(aep)
                .from(aep)
                .where(aep.delete_time.isNull())
                .where(aep.deleted_by.isNull())
                .where(aep.entry_point_type.eq(type))
                .where(aep.client_id.eq(clientId));
        final Eauth_entry_point result = query.fetchOne();
        return result;
    }

    @Override
    public final void delete(SessionStorage session, long id) {
        throw new UnsupportedOperationException();
    }


    @Override
    public Class<Eauth_entry_point> getEntityClass() {
        return Eauth_entry_point.class;
    }

    @Override
    public Qauth_entry_point getSqlPath() {
        return aep;
    }
}
