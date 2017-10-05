package com.zemrow.test.dsl.querydsl.dao.autogen.constants;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import com.querydsl.sql.ColumnMetadata;
import com.zemrow.test.dsl.querydsl.dao.autogen.entity.Eauth_entry_point;

import javax.annotation.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * Qauth_entry_point is a Querydsl query type for Eauth_entry_point
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class Qauth_entry_point extends com.querydsl.sql.RelationalPathBase<Eauth_entry_point> {

    private static final long serialVersionUID = -330859036;

    public static final Qauth_entry_point auth_entry_point = new Qauth_entry_point("auth_entry_point");

    public final ComparablePath<java.util.UUID> auth_user_id = createComparable("auth_user_id", java.util.UUID.class);

    public final StringPath client_id = createString("client_id");

    public final NumberPath<Long> create_time = createNumber("create_time", Long.class);

    public final ComparablePath<java.util.UUID> created_by = createComparable("created_by", java.util.UUID.class);

    public final StringPath credential = createString("credential");

    public final NumberPath<Long> delete_time = createNumber("delete_time", Long.class);

    public final ComparablePath<java.util.UUID> deleted_by = createComparable("deleted_by", java.util.UUID.class);

    public final EnumPath<com.zemrow.test.dsl.querydsl.dao.EntryPointTypeEnum> entry_point_type = createEnum("entry_point_type", com.zemrow.test.dsl.querydsl.dao.EntryPointTypeEnum.class);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final NumberPath<Long> update_time = createNumber("update_time", Long.class);

    public final ComparablePath<java.util.UUID> updated_by = createComparable("updated_by", java.util.UUID.class);

    public final BooleanPath validate = createBoolean("validate");

    public final com.querydsl.sql.PrimaryKey<Eauth_entry_point> pk_auth_entry_point = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<com.zemrow.test.dsl.querydsl.dao.autogen.entity.Eauth_user> auth_entry_point__deleted_by__fk = createForeignKey(deleted_by, "id");

    public final com.querydsl.sql.ForeignKey<com.zemrow.test.dsl.querydsl.dao.autogen.entity.Eauth_user> auth_entry_point__updated_by__fk = createForeignKey(updated_by, "id");

    public final com.querydsl.sql.ForeignKey<com.zemrow.test.dsl.querydsl.dao.autogen.entity.Eauth_user> auth_entry_point__auth_user_id__fk = createForeignKey(auth_user_id, "id");

    public final com.querydsl.sql.ForeignKey<com.zemrow.test.dsl.querydsl.dao.autogen.entity.Eauth_user> auth_entry_point__created_by__fk = createForeignKey(created_by, "id");

    public Qauth_entry_point(String variable) {
        super(Eauth_entry_point.class, forVariable(variable), "auth", "auth_entry_point");
        addMetadata();
    }

    public Qauth_entry_point(String variable, String schema, String table) {
        super(Eauth_entry_point.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public Qauth_entry_point(String variable, String schema) {
        super(Eauth_entry_point.class, forVariable(variable), schema, "auth_entry_point");
        addMetadata();
    }

    public Qauth_entry_point(Path<? extends Eauth_entry_point> path) {
        super(path.getType(), path.getMetadata(), "auth", "auth_entry_point");
        addMetadata();
    }

    public Qauth_entry_point(PathMetadata metadata) {
        super(Eauth_entry_point.class, metadata, "auth", "auth_entry_point");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.OTHER).withSize(2147483647).notNull());
        addMetadata(auth_user_id, ColumnMetadata.named("auth_user_id").withIndex(2).ofType(Types.OTHER).withSize(2147483647).notNull());
        addMetadata(entry_point_type, ColumnMetadata.named("entry_point_type").withIndex(3).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(client_id, ColumnMetadata.named("client_id").withIndex(4).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(credential, ColumnMetadata.named("credential").withIndex(5).ofType(Types.VARCHAR).withSize(255));
        addMetadata(validate, ColumnMetadata.named("validate").withIndex(6).ofType(Types.BIT).withSize(1));
        addMetadata(create_time, ColumnMetadata.named("create_time").withIndex(7).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(created_by, ColumnMetadata.named("created_by").withIndex(8).ofType(Types.OTHER).withSize(2147483647).notNull());
        addMetadata(update_time, ColumnMetadata.named("update_time").withIndex(9).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(updated_by, ColumnMetadata.named("updated_by").withIndex(10).ofType(Types.OTHER).withSize(2147483647).notNull());
        addMetadata(delete_time, ColumnMetadata.named("delete_time").withIndex(11).ofType(Types.BIGINT).withSize(19));
        addMetadata(deleted_by, ColumnMetadata.named("deleted_by").withIndex(12).ofType(Types.OTHER).withSize(2147483647));
    }

}

