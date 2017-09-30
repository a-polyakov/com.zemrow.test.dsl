package com.zemrow.test.dsl.querydsl.dao.autogen.constants;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.ComparablePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.ColumnMetadata;
import com.zemrow.test.dsl.querydsl.dao.autogen.entity.Erout_log;

import javax.annotation.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * Qrout_log is a Querydsl query type for Erout_log
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class Qrout_log extends com.querydsl.sql.RelationalPathBase<Erout_log> {

    private static final long serialVersionUID = 1650751641;

    public static final Qrout_log rout_log = new Qrout_log("rout_log");

    public final StringPath action = createString("action");

    public final NumberPath<Long> end_invoke = createNumber("end_invoke", Long.class);

    public final StringPath error_stack_trace = createString("error_stack_trace");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath service_id = createString("service_id");

    public final NumberPath<Long> start_invoke = createNumber("start_invoke", Long.class);

    public final StringPath token = createString("token");

    public final com.querydsl.sql.PrimaryKey<Erout_log> pk_rout_log = createPrimaryKey(id);

    public Qrout_log(String variable) {
        super(Erout_log.class, forVariable(variable), "log", "rout_log");
        addMetadata();
    }

    public Qrout_log(String variable, String schema, String table) {
        super(Erout_log.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public Qrout_log(String variable, String schema) {
        super(Erout_log.class, forVariable(variable), schema, "rout_log");
        addMetadata();
    }

    public Qrout_log(Path<? extends Erout_log> path) {
        super(path.getType(), path.getMetadata(), "log", "rout_log");
        addMetadata();
    }

    public Qrout_log(PathMetadata metadata) {
        super(Erout_log.class, metadata, "log", "rout_log");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.OTHER).withSize(2147483647).notNull());
        addMetadata(token, ColumnMetadata.named("token").withIndex(2).ofType(Types.VARCHAR).withSize(255));
        addMetadata(service_id, ColumnMetadata.named("service_id").withIndex(3).ofType(Types.VARCHAR).withSize(255));
        addMetadata(action, ColumnMetadata.named("action").withIndex(4).ofType(Types.VARCHAR).withSize(255));
        addMetadata(start_invoke, ColumnMetadata.named("start_invoke").withIndex(5).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(end_invoke, ColumnMetadata.named("end_invoke").withIndex(6).ofType(Types.BIGINT).withSize(19));
        addMetadata(error_stack_trace, ColumnMetadata.named("error_stack_trace").withIndex(7).ofType(Types.VARCHAR).withSize(2147483647));
    }

}

