package com.zemrow.test.dsl.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.sql.postgresql.PostgreSQLQuery;
import com.querydsl.sql.postgresql.PostgreSQLQueryFactory;
import com.zemrow.test.dsl.DBConst;
import com.zemrow.test.dsl.querydsl.dao.autogen.constants.Qauth_entry_point;
import com.zemrow.test.dsl.querydsl.dao.autogen.constants.Qauth_session;
import com.zemrow.test.dsl.querydsl.dao.autogen.constants.Qauth_user;

import javax.inject.Provider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Created on 21.12.2016.
 * сложности:
 * <ul>
 * <li>По умолчанию нет поддержки UUID. Лечение: при генерации указать <code>configuration.register(new UtilUUIDType());</code></li>
 * <li>По умолчанию в запрос не включается имя схемы. Лечится созданием наследника от PostgreSQLTemplates с установкой setPrintSchema(true);</li>
 * <li>Нет возможности сортировать по UUID. Лечится рефлексией<br>
 * <code><br/>final Field field = TypeCategory.class.getDeclaredField("types");<br/>
 * field.setAccessible(true);<br/>
 * final Object o = field.get(TypeCategory.COMPARABLE);<br/>
 * if (o instanceof Set) {<br/>
 * ((Set) o).add(UUID.class.getName());<br/>
 * }</code>
 * </li>
 * </ul>
 *
 * @author Alexandr Polyakov
 */
public class RunQuerydslTest {
    private static final int COUNT = 2;

    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DBConst.DEFAULT_URL, DBConst.DEFAULT_USER, DBConst.DEFAULT_PASSWORD)) {
            long timeEngineStart = 0;
            long timeQueryBuild = 0;
            long timeQueryExec = 0;
            long timeQueryExtract = 0;
            for (int i = 0; i < COUNT; i++) {
                long time = System.currentTimeMillis();
                PostgreSQLQueryFactory queryFactory = new PostgreSQLQueryFactory(PostgreSQL95Configuration.INSTANCE
                        , new Provider<Connection>() {
                    @Override
                    public Connection get() {
                        return connection;
                    }
                });
                time = System.currentTimeMillis() - time;
                timeEngineStart += time;
                System.out.println("Время запуска engine " + time + "ms");
                time = System.currentTimeMillis();

                final PostgreSQLQuery<Tuple> query = queryFactory.select(Qauth_user.auth_user.id, Qauth_session.auth_session.id)
                        .from(Qauth_user.auth_user)
                        .join(Qauth_entry_point.auth_entry_point).on(Qauth_entry_point.auth_entry_point.auth_user_id.eq(Qauth_user.auth_user.id))
                        .join(Qauth_session.auth_session).on(Qauth_session.auth_session.auth_entry_point_id.eq(Qauth_entry_point.auth_entry_point.id))
                        .orderBy(Qauth_user.auth_user.id.asc());
                time = System.currentTimeMillis() - time;
                timeQueryBuild += time;
                System.out.println(query.toString());
                System.out.println("Время построения запроса " + time + "ms");
                time = System.currentTimeMillis();
                final List<Tuple> result = query.fetch();
                time = System.currentTimeMillis() - time;
                timeQueryExec += time;
                System.out.println("Время выполнения запроса " + time + "ms");
                time = System.currentTimeMillis();
                for (Tuple record : result) {
                    System.out.println(record.get(0, UUID.class) + "\t" + record.get(1, UUID.class));
                }
                time = System.currentTimeMillis() - time;
                timeQueryExtract += time;
                System.out.println("Время получения данных и вывода на экран " + time + "ms");
            }
            System.out.println("Среднее время запуска engine " + (timeEngineStart / COUNT) + "ms");
            System.out.println("Среднее время построения запроса " + (timeQueryBuild / COUNT) + "ms");
            System.out.println("Среднее время выполнения запроса " + (timeQueryExec / COUNT) + "ms");
            System.out.println("Среднее время получения данных и вывода на экран " + (timeQueryExtract / COUNT) + "ms");
        }
    }
}
