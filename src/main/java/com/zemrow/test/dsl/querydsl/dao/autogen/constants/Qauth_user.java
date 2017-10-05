package com.zemrow.test.dsl.querydsl.dao.autogen.constants;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.ComparablePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.ColumnMetadata;
import com.zemrow.test.dsl.querydsl.dao.autogen.entity.Eauth_user;

import javax.annotation.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * Qauth_user is a Querydsl query type for Eauth_user
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class Qauth_user extends com.querydsl.sql.RelationalPathBase<Eauth_user> {

    private static final long serialVersionUID = 931794058;

    public static final Qauth_user auth_user = new Qauth_user("auth_user");

    public final NumberPath<Long> create_time = createNumber("create_time", Long.class);

    public final ComparablePath<java.util.UUID> created_by = createComparable("created_by", java.util.UUID.class);

    public final NumberPath<Long> delete_time = createNumber("delete_time", Long.class);

    public final ComparablePath<java.util.UUID> deleted_by = createComparable("deleted_by", java.util.UUID.class);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath label = createString("label");

    public final NumberPath<Long> update_time = createNumber("update_time", Long.class);

    public final ComparablePath<java.util.UUID> updated_by = createComparable("updated_by", java.util.UUID.class);

    public final com.querydsl.sql.PrimaryKey<Eauth_user> pk_auth_user = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<Eauth_user> auth_user__updated_by__fk = createForeignKey(updated_by, "id");

    public final com.querydsl.sql.ForeignKey<Eauth_user> auth_user__deleted_by__fk = createForeignKey(deleted_by, "id");

    public final com.querydsl.sql.ForeignKey<Eauth_user> auth_user__created_by__fk = createForeignKey(created_by, "id");

    public Qauth_user(String variable) {
        super(Eauth_user.class, forVariable(variable), "auth", "auth_user");
        addMetadata();
    }

    public Qauth_user(String variable, String schema, String table) {
        super(Eauth_user.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public Qauth_user(String variable, String schema) {
        super(Eauth_user.class, forVariable(variable), schema, "auth_user");
        addMetadata();
    }

    public Qauth_user(Path<? extends Eauth_user> path) {
        super(path.getType(), path.getMetadata(), "auth", "auth_user");
        addMetadata();
    }

    public Qauth_user(PathMetadata metadata) {
        super(Eauth_user.class, metadata, "auth", "auth_user");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.OTHER).withSize(2147483647).notNull());
        addMetadata(label, ColumnMetadata.named("label").withIndex(2).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(create_time, ColumnMetadata.named("create_time").withIndex(3).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(created_by, ColumnMetadata.named("created_by").withIndex(4).ofType(Types.OTHER).withSize(2147483647).notNull());
        addMetadata(update_time, ColumnMetadata.named("update_time").withIndex(5).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(updated_by, ColumnMetadata.named("updated_by").withIndex(6).ofType(Types.OTHER).withSize(2147483647).notNull());
        addMetadata(delete_time, ColumnMetadata.named("delete_time").withIndex(7).ofType(Types.BIGINT).withSize(19));
        addMetadata(deleted_by, ColumnMetadata.named("deleted_by").withIndex(8).ofType(Types.OTHER).withSize(2147483647));
    }

}

