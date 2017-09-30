package com.zemrow.test.dsl.querydsl;

import com.mysema.codegen.model.TypeCategory;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.types.EnumByNameType;
import com.querydsl.sql.types.UtilUUIDType;
import com.zemrow.test.dsl.querydsl.dao.EntryPointTypeEnum;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.UUID;

/**
 * Created on 20.01.2017.
 *
 * @author Alexandr Polyakov
 */
public class PostgreSQL95Configuration {

    public static final Configuration INSTANCE = new Configuration(PostgreSQL95Templates.INSTANCE);

    static {
        // По умолчанию нет поддержки UUID, исправляем
        INSTANCE.register(new UtilUUIDType());

        // По умолчанию нет возможности сортировать по UUID, исправляем
        try {
            final Field field = TypeCategory.class.getDeclaredField("types");
            field.setAccessible(true);
            final Object o = field.get(TypeCategory.COMPARABLE);
            if (o instanceof Set) {
                ((Set) o).add(UUID.class.getName());
            }
        } catch (Exception e) {
        }
        // Регистрация enum
        INSTANCE.register("auth_entry_point", "entry_point_type", new EnumByNameType<>(EntryPointTypeEnum.class));
    }
}
