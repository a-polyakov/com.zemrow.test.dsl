package com.zemrow.test.dsl.querydsl.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Генератор сервлетов
 * TODO
 *
 * @author Alexandr Polyakov
 */
public class RunGenerationServlet {

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
                .append("package com.zemrow.test.dsl.querydsl.web.servlet;")
                .append("\n")
                .append("\n")
                .append("import com.zemrow.test.dsl.querydsl.service.").append(name).append("Service;")
                .append("\n")
                .append("\n")
                .append("import javax.servlet.annotation.WebServlet;")
                .append("\n")
                .append("\n")
                .append("@WebServlet(\"/").append(name.toLowerCase()).append("/*\")")
                .append("\n")
                .append("public class ").append(name).append("Servlet extends AbstractServlet {")
                .append("\n")
                .append("\n")
                .append("    @Override")
                .append("\n")
                .append("    protected String getService() {")
                .append("\n")
                .append("        return ").append(name).append("Service.SERVICE;")
                .append("\n")
                .append("    }")
                .append("\n")
                .append("}");

        File mkdirs = new File(System.getProperty("user.dir") + "\\messenger-web\\src\\main\\java\\ru\\orgunit\\messenger\\web\\servlet\\" + daoFile.getParentFile().getName());
        if (!mkdirs.getName().equals("admin")) {
            mkdirs.mkdirs();

        } else {
            mkdirs = mkdirs.getParentFile();
        }
        File file = new File(mkdirs.getAbsolutePath() + "\\" + name + "Servlet.java");
        byte[] myBytes = content.toString().getBytes();
        try (FileOutputStream fooStream = new FileOutputStream(file, false)) {
            fooStream.write(myBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

