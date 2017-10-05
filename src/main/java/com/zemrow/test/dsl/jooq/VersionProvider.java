package com.zemrow.test.dsl.jooq;

import org.jooq.util.CatalogDefinition;
import org.jooq.util.CatalogVersionProvider;
import org.jooq.util.SchemaDefinition;
import org.jooq.util.SchemaVersionProvider;

/**
 * <p>TODO description
 *
 * @author Alexandr Polyakov on 04.10.2017
 */
public class VersionProvider implements CatalogVersionProvider, SchemaVersionProvider {

    public static final VersionProvider INSTANCE = new VersionProvider();

    private VersionProvider() {
    }

    @Override
    public String version(CatalogDefinition catalog) {
        //TODO from sql
        return null;
    }

    @Override
    public String version(SchemaDefinition schema) {
        //TODO from sql
        return null;
    }
}
