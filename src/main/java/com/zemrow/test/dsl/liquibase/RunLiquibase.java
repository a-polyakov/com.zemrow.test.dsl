package com.zemrow.test.dsl.liquibase;

import com.zemrow.test.dsl.DBConst;
import liquibase.integration.commandline.Main;

import java.io.PrintStream;
import java.util.Properties;

/**
 * Обновление БД
 * <p>
 * Created on 13.03.2017.
 *
 * @author Alexandr Polyakov
 */
public class RunLiquibase {

    public static void main(String[] args) throws Exception {
        final PrintStream out = System.out;
        out.println("Обновление БД, актуализация констант, актуализация сущностей");
        final Properties properties = System.getProperties();
        final String url = properties.getProperty("dataSourceUrl", DBConst.DEFAULT_URL);
        final String username = properties.getProperty("dataSourceUsername", DBConst.DEFAULT_USER);
        final String password = properties.getProperty("dataSourcePassword", DBConst.DEFAULT_PASSWORD);
        out.println("URL " + url);
        out.println("username " + username);
        out.println("Запуск liquibase");
        // Обновление БД
        Main.run(new String[]{
                "--logLevel=debug",
                "--url=" + url,
                "--driver=" + DBConst.DRIVER,
                "--username=" + username,
                "--password=" + password,
                "--currentDateTimeFunction=CURRENT_TIMESTAMP(3)",
                "--changeLogFile=script/auth.xml",
                "update"
        });
    }
}
