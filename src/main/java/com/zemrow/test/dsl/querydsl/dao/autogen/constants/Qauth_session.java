package com.zemrow.test.dsl.querydsl.dao.autogen.constants;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.ComparablePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.ColumnMetadata;
import com.zemrow.test.dsl.querydsl.dao.autogen.entity.Eauth_session;

import javax.annotation.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * Qauth_session is a Querydsl query type for Eauth_session
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class Qauth_session extends com.querydsl.sql.RelationalPathBase<Eauth_session> {

    private static final long serialVersionUID = -1459604105;

    public static final Qauth_session auth_session = new Qauth_session("auth_session");

    public final ComparablePath<java.util.UUID> auth_entry_point_id = createComparable("auth_entry_point_id", java.util.UUID.class);

    public final NumberPath<Long> create_time = createNumber("create_time", Long.class);

    public final NumberPath<Long> delete_time = createNumber("delete_time", Long.class);

    public final ComparablePath<java.util.UUID> deleted_by = createComparable("deleted_by", java.util.UUID.class);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath token = createString("token");

    public final com.querydsl.sql.PrimaryKey<Eauth_session> pk_auth_session = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<com.zemrow.test.dsl.querydsl.dao.autogen.entity.Eauth_entry_point> auth_session__auth_entry_point_id__fk = createForeignKey(auth_entry_point_id, "id");

    public final com.querydsl.sql.ForeignKey<com.zemrow.test.dsl.querydsl.dao.autogen.entity.Eauth_user> auth_session__deleted_by__fk = createForeignKey(deleted_by, "id");

    public Qauth_session(String variable) {
        super(Eauth_session.class, forVariable(variable), "auth", "auth_session");
        addMetadata();
    }

    public Qauth_session(String variable, String schema, String table) {
        super(Eauth_session.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public Qauth_session(String variable, String schema) {
        super(Eauth_session.class, forVariable(variable), schema, "auth_session");
        addMetadata();
    }

    public Qauth_session(Path<? extends Eauth_session> path) {
        super(path.getType(), path.getMetadata(), "auth", "auth_session");
        addMetadata();
    }

    public Qauth_session(PathMetadata metadata) {
        super(Eauth_session.class, metadata, "auth", "auth_session");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.OTHER).withSize(2147483647).notNull());
        addMetadata(auth_entry_point_id, ColumnMetadata.named("auth_entry_point_id").withIndex(2).ofType(Types.OTHER).withSize(2147483647).notNull());
        addMetadata(token, ColumnMetadata.named("token").withIndex(3).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(create_time, ColumnMetadata.named("create_time").withIndex(4).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(delete_time, ColumnMetadata.named("delete_time").withIndex(5).ofType(Types.BIGINT).withSize(19));
        addMetadata(deleted_by, ColumnMetadata.named("deleted_by").withIndex(6).ofType(Types.OTHER).withSize(2147483647));
    }

}

