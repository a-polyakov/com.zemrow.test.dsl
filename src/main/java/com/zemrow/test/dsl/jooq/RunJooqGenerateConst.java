package com.zemrow.test.dsl.jooq;

import com.zemrow.test.dsl.DBConst;
import org.jooq.util.*;
import org.jooq.util.jaxb.Catalog;
import org.jooq.util.jaxb.Schema;
import org.jooq.util.postgres.PostgresDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author Alexandr Polyakov
 */
public class RunJooqGenerateConst {
    public static void main(String[] args) throws Exception {
        Class.forName(DBConst.DRIVER);
        try (Connection dbConn = DriverManager.getConnection(DBConst.DEFAULT_URL, DBConst.DEFAULT_USER, DBConst.DEFAULT_PASSWORD)) {

            // переопределяем стратегию наименования java классов
            final GeneratorStrategy strategy = new DefaultGeneratorStrategy() {
                @Override
                public String getJavaPackageName(Definition definition, Mode mode) {
                    if (definition instanceof TableDefinition && mode == Mode.DEFAULT) {
                        return getTargetPackage() + "." + getJavaIdentifier(definition.getSchema()).toLowerCase() + ".dao.constants";
                    } else if (definition instanceof TableDefinition && mode == Mode.POJO) {
                        return getTargetPackage() + "." + getJavaIdentifier(definition.getSchema()).toLowerCase() + ".dao.entity";
                    } else {
                        return super.getJavaPackageName(definition, mode);
                    }
                }

                @Override
                public String getJavaClassName(Definition definition, Mode mode) {
                    final String name = super.getJavaClassName(definition, mode);
                    if (definition instanceof CatalogDefinition && mode == Mode.DEFAULT) {
                        return "MainConst";
                    } else if (definition instanceof SchemaDefinition && mode == Mode.DEFAULT) {
                        return "Schema" + name + "Const";
                    } else if (definition instanceof TableDefinition && mode == Mode.DEFAULT) {
                        return name + "Const";
                    } else if (definition instanceof TableDefinition && mode == Mode.POJO) {
                        return name + "Entity";
                    } else {
                        return name;
                    }
                }
            };
            strategy.setTargetPackage("com.zemrow.test.dsl.jooq.autogen");
            strategy.setTargetDirectory("src\\main\\java");

            final PostgresDatabase database = new PostgresDatabase();
            database.setConnection(dbConn);
            database.setIncludes(new String[]{".*"});
            database.setExcludes(new String[]{""});

            final Schema schemaAuth = new Schema();
            schemaAuth.setInputSchema("auth");
            schemaAuth.setOutputSchema(schemaAuth.getInputSchema());
            schemaAuth.setOutputSchemaToDefault(true);

            final Schema schemaLog = new Schema();
            schemaLog.setInputSchema("log");
            schemaLog.setOutputSchema(schemaLog.getInputSchema());
            schemaLog.setOutputSchemaToDefault(true);

            final Catalog catalog = new Catalog();
            catalog.setSchemata(Arrays.asList(schemaAuth, schemaLog));
            catalog.setOutputCatalog(catalog.getInputCatalog());

            database.setConfiguredCatalogs(Arrays.asList(catalog));
            database.setConfiguredSchemata(Collections.EMPTY_LIST);
            database.setSchemaVersionProvider(VersionProvider.INSTANCE);
            database.setCatalogVersionProvider(VersionProvider.INSTANCE);
            database.setConfiguredCustomTypes(Collections.EMPTY_LIST);
            database.setConfiguredEnumTypes(Collections.EMPTY_LIST);

            final JavaGenerator generator = new JavaGenerator() {
                @Override
                protected void generateTableReferences(SchemaDefinition schema) {
                    // не генерировать отдельный класс Tables
                }

                @Override
                public boolean generateRelations() {
                    return false; // не генерировать отдельный класс Keys
                }

//                @Override
//                protected void generateRelations(SchemaDefinition schema) {
//                     не генерировать отдельный класс Keys
//                }
            };
            generator.setStrategy(strategy);
            generator.setGenerateGeneratedAnnotation(false); // удалить из сгенерированого кода аннотацию javax.annotation.Generated
            generator.setGenerateEmptyCatalogs(false);
            generator.setGenerateGlobalCatalogReferences(false);
            generator.setGenerateEmptySchemas(false);
            generator.setGenerateGlobalSchemaReferences(true); // в Catalog добавить список схем
            generator.setGenerateSequences(false);

            generator.setGenerateGlobalObjectReferences(true);
            generator.setGenerateGlobalTableReferences(true); // В константу схемы включить список таблиц
//            generator.setGenerateGlobalLinkReferences(false);
//            generator.setGenerateLinks(false);
//            generator.setGenerateRelations(false); // не генерировать отдельный класс Keys
            generator.setGenerateTables(true); // Constant
            generator.setGenerateRecords(false);
            generator.setGeneratePojos(true); // Entity
            generator.setGeneratePojosEqualsAndHashCode(true);
            generator.generate(database);
        }
    }
}