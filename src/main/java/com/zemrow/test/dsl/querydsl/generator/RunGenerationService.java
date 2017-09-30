package com.zemrow.test.dsl.querydsl.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Генератор сервисов
 * <p>
 *
 * @author Alexandr Polyakov
 */
public class RunGenerationService {

    public static void main(String[] args) {
        File project = new File(System.getProperty("user.dir") + "\\messenger-dao\\src\\main\\java\\ru\\orgunit\\messenger\\dao\\admin");
        forEach(project);


    }

    private static void forEach(File file) {
        List<File> files = Arrays.asList(file.listFiles());
        for (File fl : files) {
            if (fl.isFile()) {
                generationFile(fl);
            } else {
                forEach(fl);
            }
        }
    }

    private static void generationFile(File daoFile) {
        String name = daoFile.getName().substring(0, daoFile.getName().length() - 8);
        if (name.equals("Abstract")) {
            return;
        }
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        StringBuilder content = new StringBuilder()
                .append("package com.zemrow.test.dsl.querydsl.service;")
                .append("\n")
                .append("\n")
                .append("import com.zemrow.test.dsl.querydsl.dao.admin.AbstractDao;")
                .append("\n")
                .append("import com.zemrow.test.dsl.querydsl.dao.admin.").append(name).append("Dao;")
                .append("\n")
                .append("\n")
                .append("public class ").append(name).append("Service extends AbstractService {")
                .append("\n")
                .append("\n")
                .append("    public static final String SERVICE = ").append('"').append(name.toLowerCase()).append('"').append(";")
                .append("\n")
                .append("\n")
                .append("    public static final ").append(name).append("Service INSTANCE = new ").append(name).append("Service();")
                .append("\n")
                .append("\n")
                .append("    private ").append(name).append("Service() {")
                .append("\n")
                .append("        super();")
                .append("\n")
                .append("    }")
                .append("\n")
                .append("\n")
                .append("    @Override")
                .append("\n")
                .append("    protected AbstractDao getDao() {")
                .append("\n")
                .append("        return ").append(name).append("Dao.INSTANCE;")
                .append("\n")
                .append("    }")
                .append("\n")
                .append("}");

        File mkdirs = new File(System.getProperty("user.dir") + "\\messenger-web\\src\\main\\java\\ru\\orgunit\\messenger\\service\\" + daoFile.getParentFile().getName());
        if (!mkdirs.getName().equals("admin")) {
            mkdirs.mkdirs();

        } else {
            mkdirs = mkdirs.getParentFile();
        }
        File file = new File(mkdirs.getAbsolutePath() + "\\" + name + "Service.java");
        byte[] myBytes = content.toString().getBytes();
        try (FileOutputStream fooStream = new FileOutputStream(file, false)) {
            fooStream.write(myBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}