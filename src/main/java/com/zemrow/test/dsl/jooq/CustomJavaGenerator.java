package com.zemrow.test.dsl.jooq;

import org.jooq.tools.StringUtils;
import org.jooq.util.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


class CustomJavaGenerator extends JavaGenerator {
    @Override
    protected void generateTableReferences(SchemaDefinition schema) {
        // не генерировать отдельный класс Tables
    }

    @Override
    public boolean generateRelations() {
        return false; // не генерировать отдельный класс Keys
    }

    @Override
    protected void generatePojo(TableDefinition table, JavaWriter out) {
        final String className = getStrategy().getJavaClassName(table, GeneratorStrategy.Mode.POJO);
        final String superName = out.ref("com.zemrow.test.dsl.jooq.impl.AbstractEntity");
        final List<String> interfaces = out.ref(getStrategy().getJavaClassImplements(table, GeneratorStrategy.Mode.POJO));

        printPackage(out, table, GeneratorStrategy.Mode.POJO);

        generatePojoClassJavadoc(table, out);

        out.println("public class %s[[before= extends ][%s]][[before= implements ][%s]] {", Collections.singletonList(className), superName, interfaces);
        out.printSerial();
        out.println();

        int maxLength = 0;
        for (TypedElementDefinition<?> column : table.getColumns()) {
            if (!"id".equals(column.getName())) {
                maxLength = Math.max(maxLength, out.ref(getJavaType(column.getType(), GeneratorStrategy.Mode.POJO)).length());
            }
        }

        for (TypedElementDefinition<?> column : table.getColumns()) {
            if (!"id".equals(column.getName())) {
                out.tab(1).println("/**");
                out.tab(1).println(" * %s", column.getComment());
                out.tab(1).println(" */");
                out.tab(1).println("private %s %s;",
                        StringUtils.rightPad(out.ref(getJavaType(column.getType(), GeneratorStrategy.Mode.POJO)), maxLength),
                        getStrategy().getJavaMemberName(column, GeneratorStrategy.Mode.POJO));
            }
        }

        // Constructors
        // ---------------------------------------------------------------------

        // Default constructor
        out.println();
        out.tab(1).println("public %s() {}", className);

        // [#1363] copy constructor
        out.println();
        out.tab(1).println("public %s(%s value) {", className, className);
        out.tab(2).println("super(value);");
        for (TypedElementDefinition<?> column : table.getColumns()) {
            if (!"id".equals(column.getName())) {
                out.tab(2).println("this.%s = value.%s;",
                        getStrategy().getJavaMemberName(column, GeneratorStrategy.Mode.POJO),
                        getStrategy().getJavaMemberName(column, GeneratorStrategy.Mode.POJO));
            }
        }

        out.tab(1).println("}");

        // Multi-constructor
        if (table.getColumns().size() > 0 &&
                table.getColumns().size() < 256) {
            out.println();
            out.tab(1).print("public %s(", className);
            String separator1 = "";
            for (TypedElementDefinition<?> column : table.getColumns()) {

                out.println(separator1);

                out.tab(2).print("%s %s",
                        StringUtils.rightPad(out.ref(getJavaType(column.getType(), GeneratorStrategy.Mode.POJO)), maxLength),
                        getStrategy().getJavaMemberName(column, GeneratorStrategy.Mode.POJO));
                separator1 = ",";
            }

            out.println();
            out.tab(1).println(") {");
            out.tab(2).println("super(id);");
            for (TypedElementDefinition<?> column : table.getColumns()) {
                if (!"id".equals(column.getName())) {
                    final String columnMember = getStrategy().getJavaMemberName(column, GeneratorStrategy.Mode.POJO);

                    out.tab(2).println("this.%s = %s;", columnMember, columnMember);
                }
            }

            out.tab(1).println("}");
        }

        final List<? extends TypedElementDefinition<?>> elements = table.getColumns();
        for (int i = 0; i < elements.size(); i++) {
            final TypedElementDefinition<?> column = elements.get(i);
            if (!"id".equals(column.getName())) {

                generatePojoGetter(column, i, out);

                generatePojoSetter(column, i, out);
            }
        }

        generatePojoToString(table, out);
        generatePojoEqualsAndHashCode(table, out);

        if (generateInterfaces() && !generateImmutablePojos()) {
            printFromAndInto(out, table);
        }

        out.println("}");
        closeJavaWriter(out);
    }

    private void generatePojoToString(TableDefinition table, JavaWriter out) {
        final String className = getStrategy().getJavaClassName(table, GeneratorStrategy.Mode.POJO);

        out.println();

        out.tab(1).println("@Override");
        out.tab(1).println("public String toString() {");
        out.tab(2).println("final %s sb = new %s(\"%s (\");", StringBuilder.class, StringBuilder.class, className);
        out.tab(2).println();


        final List<ColumnDefinition> columns = table.getColumns();
        for (int i = 0; i < columns.size(); i++) {
            TypedElementDefinition<?> column = columns.get(i);
            final String columnMember = getStrategy().getJavaMemberName(column, GeneratorStrategy.Mode.POJO);
            final String columnType = getJavaType(column.getType());
            final boolean array = columnType.endsWith("[]");

            String separator = ".append(\"";
            if (i != 0) {
                separator += ", ";
            }
            separator += columnMember + ":\")";

            if (array && columnType.equals("byte[]")) {
                out.tab(2).println("sb%s.append(\"[binary...]\");", separator);
            } else if (array) {
                out.tab(2).println("sb%s.append(%s.toString(%s));", separator, Arrays.class, columnMember);
            } else {
                out.tab(2).println("sb%s.append(%s);", separator, columnMember);
            }
        }

        out.tab(2).println();
        out.tab(2).println("sb.append(\")\");");
        out.tab(2).println("return sb.toString();");
        out.tab(1).println("}");
    }

    protected void generatePojoEqualsAndHashCode(TableDefinition table, JavaWriter out) {
        final String className = getStrategy().getJavaClassName(table, GeneratorStrategy.Mode.POJO);

        out.println();

        out.tab(1).println("@Override");
        out.tab(1).println("public boolean equals(%s obj) {", Object.class);
        out.tab(2).println("if (!super.equals(obj)) {");
        out.tab(3).println("return false;");
        out.tab(2).println("}");
        out.tab(2).println("if (!(obj instanceof %s)) {", className);
        out.tab(3).println("return false;");
        out.tab(2).println("}");

        out.tab(2).println("final %s other = (%s) obj;", className, className);

        for (TypedElementDefinition<?> column : table.getColumns()) {
            if (!"id".equals(column.getName())) {
                final String columnMember = getStrategy().getJavaMemberName(column, GeneratorStrategy.Mode.POJO);

                out.tab(2).println("if (%s == null) {", columnMember);
                out.tab(3).println("if (other.%s != null) {", columnMember);
                out.tab(4).println("return false;");
                out.tab(3).println("}");
                out.tab(2).print("} else if (");

                if (getJavaType(column.getType()).endsWith("[]")) {
                    out.tab(2).println("!%s.equals(%s, other.%s)) {", Arrays.class, columnMember, columnMember);
                } else {
                    out.tab(2).println("!%s.equals(other.%s)) {", columnMember, columnMember);
                }
                out.tab(3).println("return false;");
                out.tab(2).println("}");
            }
        }

        out.tab(2).println("return true;");
        out.tab(1).println("}");

        out.println();

        out.tab(1).println("@Override");
        out.tab(1).println("public int hashCode() {");
        out.tab(2).println("int result = super.hashCode();");

        for (TypedElementDefinition<?> column : table.getColumns()) {
            if (!"id".equals(column.getName())) {
                final String columnMember = getStrategy().getJavaMemberName(column, GeneratorStrategy.Mode.POJO);

                if (getJavaType(column.getType()).endsWith("[]")) {
                    out.tab(2).println("result = 31 * result + ((%s == null) ? 0 : %s.hashCode(%s));", columnMember, Arrays.class, columnMember);
                } else {
                    out.tab(2).println("result = 31 * result + ((%s == null) ? 0 : %s.hashCode());", columnMember, columnMember);
                }
            }
        }

        out.tab(2).println("return result;");
        out.tab(1).println("}");
    }
}
