package com.zemrow.test.dsl.querydsl.dao.autogen.constants;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.ComparablePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.ColumnMetadata;
import com.zemrow.test.dsl.querydsl.dao.autogen.entity.Eauth_session_fail;

import javax.annotation.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * Qauth_session_fail is a Querydsl query type for Eauth_session_fail
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class Qauth_session_fail extends com.querydsl.sql.RelationalPathBase<Eauth_session_fail> {

    private static final long serialVersionUID = 945040614;

    public static final Qauth_session_fail auth_session_fail = new Qauth_session_fail("auth_session_fail");

    public final ComparablePath<java.util.UUID> auth_entry_point_id = createComparable("auth_entry_point_id", java.util.UUID.class);

    public final StringPath comment = createString("comment");

    public final NumberPath<Long> create_time = createNumber("create_time", Long.class);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath ip_address = createString("ip_address");

    public final com.querydsl.sql.PrimaryKey<Eauth_session_fail> pk_auth_session_fail = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<com.zemrow.test.dsl.querydsl.dao.autogen.entity.Eauth_entry_point> auth_session_fail__auth_entry_point_id__fk = createForeignKey(auth_entry_point_id, "id");

    public Qauth_session_fail(String variable) {
        super(Eauth_session_fail.class, forVariable(variable), "auth", "auth_session_fail");
        addMetadata();
    }

    public Qauth_session_fail(String variable, String schema, String table) {
        super(Eauth_session_fail.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public Qauth_session_fail(String variable, String schema) {
        super(Eauth_session_fail.class, forVariable(variable), schema, "auth_session_fail");
        addMetadata();
    }

    public Qauth_session_fail(Path<? extends Eauth_session_fail> path) {
        super(path.getType(), path.getMetadata(), "auth", "auth_session_fail");
        addMetadata();
    }

    public Qauth_session_fail(PathMetadata metadata) {
        super(Eauth_session_fail.class, metadata, "auth", "auth_session_fail");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.OTHER).withSize(2147483647).notNull());
        addMetadata(auth_entry_point_id, ColumnMetadata.named("auth_entry_point_id").withIndex(2).ofType(Types.OTHER).withSize(2147483647).notNull());
        addMetadata(ip_address, ColumnMetadata.named("ip_address").withIndex(3).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(comment, ColumnMetadata.named("comment").withIndex(4).ofType(Types.VARCHAR).withSize(2147483647).notNull());
        addMetadata(create_time, ColumnMetadata.named("create_time").withIndex(5).ofType(Types.BIGINT).withSize(19).notNull());
    }

}

