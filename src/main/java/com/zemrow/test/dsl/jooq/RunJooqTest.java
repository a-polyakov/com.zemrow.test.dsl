package com.zemrow.test.dsl.jooq;

import com.zemrow.test.dsl.DBConst;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

import static com.zemrow.test.dsl.jooq.autogen.auth.SchemaAuthConst.AUTH;

/**
 * Created on 21.12.2016.
 *
 * @author Alexandr Polyakov
 */
public class RunJooqTest {
    private static final int COUNT = 2;

    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DBConst.DEFAULT_URL, DBConst.DEFAULT_USER, DBConst.DEFAULT_PASSWORD)) {
            long timeEngineStart = 0;
            long timeQueryBuild = 0;
            long timeQueryExec = 0;
            long timeQueryExtract = 0;
            for (int i = 0; i < COUNT; i++) {
                long time = System.currentTimeMillis();
                final DSLContext dsl = DSL.using(connection, SQLDialect.POSTGRES_9_5);
                time = System.currentTimeMillis() - time;
                timeEngineStart += time;
                System.out.println("Время запуска engine " + time + "ms");
                time = System.currentTimeMillis();

                final SelectSeekStep2<Record2<UUID, UUID>, UUID, UUID> query = dsl.select(AUTH.AUTH_USER.ID, AUTH.AUTH_SESSION.ID)
                        .from(AUTH.AUTH_USER)
                        .join(AUTH.AUTH_ENTRY_POINT).on(AUTH.AUTH_ENTRY_POINT.AUTH_USER_ID.eq(AUTH.AUTH_USER.ID))
                        .join(AUTH.AUTH_SESSION).on(AUTH.AUTH_SESSION.AUTH_ENTRY_POINT_ID.eq(AUTH.AUTH_ENTRY_POINT.ID))
                        .orderBy(AUTH.AUTH_USER.ID.asc(), AUTH.AUTH_SESSION.ID.asc());
                time = System.currentTimeMillis() - time;
                timeQueryBuild += time;
                System.out.println(query.toString());
                System.out.println("Время построения запроса " + time + "ms");
                time = System.currentTimeMillis();
                final Cursor<Record2<UUID, UUID>> result = query.fetchLazy();
                time = System.currentTimeMillis() - time;
                timeQueryExec += time;
                System.out.println("Время выполнения запроса " + time + "ms");
                time = System.currentTimeMillis();
                for (Record2 record : result) {
                    System.out.println(record.get(0) + "\t" + record.get(1));
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
