/*
 * This file is generated by jOOQ.
*/
package com.zemrow.test.dsl.jooq.autogen;


import com.zemrow.test.dsl.jooq.autogen.auth.SchemaAuthConst;
import com.zemrow.test.dsl.jooq.autogen.log.SchemaLogConst;
import org.jooq.Schema;
import org.jooq.impl.CatalogImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class MainConst extends CatalogImpl {

    private static final long serialVersionUID = 2057757327;

    /**
     * The reference instance of <code></code>
     */
    public static final MainConst DEFAULT_CATALOG = new MainConst();

    /**
     * The schema <code>auth</code>.
     */
    public final SchemaAuthConst AUTH = com.zemrow.test.dsl.jooq.autogen.auth.SchemaAuthConst.AUTH;

    /**
     * The schema <code>log</code>.
     */
    public final SchemaLogConst LOG = com.zemrow.test.dsl.jooq.autogen.log.SchemaLogConst.LOG;

    /**
     * No further instances allowed
     */
    private MainConst() {
        super("");
    }

    @Override
    public final List<Schema> getSchemas() {
        List result = new ArrayList();
        result.addAll(getSchemas0());
        return result;
    }

    private final List<Schema> getSchemas0() {
        return Arrays.<Schema>asList(
                SchemaAuthConst.AUTH,
                SchemaLogConst.LOG);
    }
}
