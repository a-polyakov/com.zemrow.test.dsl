package com.zemrow.test.dsl.querydsl;

import com.google.common.base.Function;
import com.mysema.codegen.CodeWriter;
import com.mysema.codegen.model.Parameter;
import com.mysema.codegen.model.SimpleType;
import com.mysema.codegen.model.TypeCategory;
import com.querydsl.codegen.EntityType;
import com.querydsl.codegen.Property;
import com.querydsl.codegen.Serializer;
import com.querydsl.codegen.SerializerConfig;
import com.querydsl.sql.codegen.OrdinalPositionComparator;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.sql.Timestamp;
import java.util.*;

/**
 * Генератор entity
 * <p>
 * Created on 15.03.2017.
 *
 * @author Alexandr Polyakov
 */
public class PojoSerializer implements Serializer {

    private static final Function<Property, Parameter> propertyToParameter = new Function<Property, Parameter>() {
        @Override
        public Parameter apply(Property input) {
            return new Parameter(input.getName(), input.getType());
        }
    };

    /**
     * true, to serialize property annotations
     */
    private final boolean propertyAnnotations;

    public PojoSerializer() {
        this.propertyAnnotations = false;
    }

    @Override
    public void serialize(EntityType model, SerializerConfig serializerConfig,
                          CodeWriter writer) throws IOException {
        String simpleName = model.getSimpleName();

        // package
        if (!model.getPackageName().isEmpty()) {
            writer.packageDecl(model.getPackageName());
        }

        // imports
        Set<String> importedClasses = getAnnotationTypes(model);
        importedClasses.add("com.zemrow.test.dsl.querydsl.dao.SessionStorage");
        importedClasses.add("com.zemrow.test.dsl.querydsl.dao.AbstractEntity");
        if (model.hasLists()) {
            importedClasses.add(List.class.getName());
        }
        if (model.hasCollections()) {
            importedClasses.add(Collection.class.getName());
        }
        if (model.hasSets()) {
            importedClasses.add(Set.class.getName());
        }
        if (model.hasMaps()) {
            importedClasses.add(Map.class.getName());
        }
        if (hasPropertyWithType(model, TypeCategory.DATETIME)) {
            importedClasses.add(Timestamp.class.getName());
        }
        writer.importClasses(importedClasses.toArray(new String[importedClasses.size()]));

        // javadoc
        writer.javadoc("Класс сгенегирован автоматически, для таблицы " + simpleName + " из БД");

        // header
        for (Annotation annotation : model.getAnnotations()) {
            writer.annotation(annotation);
        }

        writer.beginClass(model, new SimpleType("AbstractEntity"));

        boolean hasCreate_time = false;
        boolean hasCreated_by = false;
        boolean hasUpdate_time = false;
        boolean hasUpdated_by = false;
        boolean hasDelete_time = false;
        boolean hasDeleted_by = false;
        // fields
        for (Property property : getSortProperties(model)) {
            if (!"id".equals(property.getEscapedName())) {
                if ("create_time".equals(property.getEscapedName())) {
                    hasCreate_time = true;
                } else if ("created_by".equals(property.getEscapedName())) {
                    hasCreated_by = true;
                } else if ("update_time".equals(property.getEscapedName())) {
                    hasUpdate_time = true;
                } else if ("updated_by".equals(property.getEscapedName())) {
                    hasUpdated_by = true;
                } else if ("delete_time".equals(property.getEscapedName())) {
                    hasDelete_time = true;
                } else if ("deleted_by".equals(property.getEscapedName())) {
                    hasDeleted_by = true;
                }
                if (propertyAnnotations) {
                    for (Annotation annotation : property.getAnnotations()) {
                        writer.annotation(annotation);
                    }
                }
                writer.publicField(property.getType(), property.getEscapedName());
            }
        }

        // empty constructor, constructor by all fileds
        addFullConstructor(model, writer);

        // preInsert, preUpdate, preDelete
        addPreMethod(model, writer,
                hasCreate_time,
                hasCreated_by,
                hasUpdate_time,
                hasUpdated_by,
                hasDelete_time,
                hasDeleted_by);

        writer.end();
    }

    private void addPreMethod(EntityType model, CodeWriter writer,
                              boolean hasCreate_time,
                              boolean hasCreated_by,
                              boolean hasUpdate_time,
                              boolean hasUpdated_by,
                              boolean hasDelete_time,
                              boolean hasDeleted_by) throws IOException {

        final Parameter parameterSessionStorage = new Parameter("session", new SimpleType("SessionStorage"));

        // preInsert
        if (hasCreate_time || hasCreated_by || hasUpdate_time || hasUpdated_by) {
            writer.line("@Override");
            writer.beginPublicMethod(new SimpleType("void"), "preInsert", parameterSessionStorage);
            writer.line("super.preInsert(session);");
            if (hasCreate_time) {
                writer.line("if (create_time == null) {");
                writer.line("    create_time = System.currentTimeMillis();");
                writer.line("}");
            }
            if (hasCreated_by) {
                writer.line("if (created_by == null) {");
                writer.line("    created_by = session.userId;");
                writer.line("}");
            }
            addSetUpdateTime(writer, hasUpdate_time);
            addSetUpdatedBy(writer, hasUpdated_by);
            writer.end();
        }

        // preUpdate
        if (hasUpdate_time || hasUpdated_by) {
            writer.line("@Override");
            writer.beginPublicMethod(new SimpleType("void"), "preUpdate", parameterSessionStorage);
            writer.line("super.preUpdate(session);");
            addSetUpdateTime(writer, hasUpdate_time);
            addSetUpdatedBy(writer, hasUpdated_by);
            writer.end();
        }

        // preDelete
        if (hasDelete_time || hasDeleted_by) {
            writer.line("@Override");
            writer.beginPublicMethod(new SimpleType("void"), "preDelete", parameterSessionStorage);
            writer.line("super.preDelete(session);");

            if (hasDelete_time) {
                writer.line("if (delete_time == null) {");
                writer.line("    delete_time = System.currentTimeMillis();");
                writer.line("}");
            }
            if (hasDeleted_by) {
                writer.line("if (deleted_by == null) {");
                writer.line("    deleted_by = session.userId;");
                writer.line("}");
            }

            writer.end();
        }
    }

    private void addSetUpdateTime(CodeWriter writer, boolean hasUpdate_time) throws IOException {
        if (hasUpdate_time) {
            writer.line("update_time = System.currentTimeMillis();");
        }
    }

    private void addSetUpdatedBy(CodeWriter writer, boolean hasUpdated_by) throws IOException {
        if (hasUpdated_by) {
            writer.line("updated_by = session.userId;");
        }
    }

    private boolean hasPropertyWithType(EntityType model, TypeCategory category) {
        for (Property property : model.getProperties()) {
            if (property.getType().getCategory() == category) {
                return true;
            }
        }
        return false;
    }

    private Set<String> getAnnotationTypes(EntityType model) {
        Set<String> imports = new HashSet<String>();
        for (Annotation annotation : model.getAnnotations()) {
            imports.add(annotation.annotationType().getName());
        }
        if (propertyAnnotations) {
            for (Property property : model.getProperties()) {
                for (Annotation annotation : property.getAnnotations()) {
                    imports.add(annotation.annotationType().getName());
                }
            }
        }
        return imports;
    }

    protected void addFullConstructor(EntityType model, CodeWriter writer) throws IOException {
        // public empty constructor
        writer.beginConstructor();
        writer.end();

        // full constructor
        final ArrayList<Property> sortProperties = getSortProperties(model);
        writer.beginConstructor(sortProperties, propertyToParameter);
        for (Property property : sortProperties) {
            if ("id".equals(property.getEscapedName())) {
                writer.line("super(id);");
            } else {
                writer.line("this.", property.getEscapedName(), " = ", property.getEscapedName(), ";");
            }
        }
        writer.end();
    }

    private ArrayList<Property> getSortProperties(EntityType model) {
        final ArrayList<Property> list = new ArrayList(model.getProperties());
        Collections.sort(list, new OrdinalPositionComparator());
        return list;
    }
}
