package com.zemrow.test.dsl.querydsl;

import com.querydsl.sql.Configuration;
import com.querydsl.sql.codegen.MetaDataExporter;
import com.querydsl.sql.codegen.OrdinalPositionComparator;
import com.querydsl.sql.codegen.OriginalNamingStrategy;
import com.zemrow.test.dsl.DBConst;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * актуализация констант
 * <p>
 * Created on 21.12.2016.
 *
 * @author Alexandr Polyakov
 */
public class RunQuerydslGenerateConst {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, NoSuchFieldException, IllegalAccessException {
        Class.forName(DBConst.DRIVER);
        try (Connection dbConn = DriverManager.getConnection(DBConst.DEFAULT_URL, DBConst.DEFAULT_USER, DBConst.DEFAULT_PASSWORD)) {
            long time = System.currentTimeMillis();

            final MetaDataExporter exporter = new MetaDataExporter();
            exporter.setSchemaPattern("auth,log"); // сканироваться будет только указаная схема
            exporter.setPackageName("com.zemrow.test.dsl.querydsl.dao.autogen.constants");
            exporter.setNamePrefix("Q");
            exporter.setBeanPrefix("E");
            exporter.setTargetFolder(new File("src\\main\\java"));
            final Configuration configuration = PostgreSQL95Configuration.INSTANCE;

            exporter.setConfiguration(configuration);
            exporter.setNamingStrategy(new OriginalNamingStrategy());
            final DatabaseMetaData metaData = dbConn.getMetaData();
            exporter.setExportForeignKeys(true);
            exporter.setExportDirectForeignKeys(true);
            exporter.setExportInverseForeignKeys(false);
            exporter.setColumnComparatorClass(OrdinalPositionComparator.class);
            exporter.setBeanPackageName("com.zemrow.test.dsl.querydsl.dao.autogen.entity");
            exporter.setBeanSerializer(new PojoSerializer());
            exporter.export(metaData);

            time = System.currentTimeMillis() - time;
            System.out.println("Время генерации констант " + time + "ms");
        }
    }
}
