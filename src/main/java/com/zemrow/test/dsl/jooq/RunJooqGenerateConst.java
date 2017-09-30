package com.zemrow.test.dsl.jooq;

import com.zemrow.test.dsl.DBConst;
import org.jooq.util.GenerationTool;
import org.jooq.util.jaxb.*;

/**
 * @author Alexandr Polyakov
 */
public class RunJooqGenerateConst {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");

        final Configuration conf = new Configuration();
        final Jdbc jdbc = new Jdbc();
        jdbc.setDriver(DBConst.DRIVER);
        jdbc.setUrl(DBConst.DEFAULT_URL);
        jdbc.setUser(DBConst.DEFAULT_USER);
        jdbc.setPassword(DBConst.DEFAULT_PASSWORD);
        conf.setJdbc(jdbc);
        final Generator generator = new Generator();
        generator.setName(org.jooq.util.JavaGenerator.class.getName());
        final Database database = new Database();
        database.setName(org.jooq.util.postgres.PostgresDatabase.class.getName());
        database.setInputSchema("auth");
        database.setIncludes(".*");
        database.setExcludes(null);
        generator.setDatabase(database);
        final Target target = new Target();
        target.setPackageName("com.zemrow.test.dsl.jooq.autogen");
        target.setDirectory("src\\main\\java");
        generator.setTarget(target);
        generator.setStrategy(new Strategy());
        conf.setGenerator(generator);
        GenerationTool.generate(conf);
    }
}
