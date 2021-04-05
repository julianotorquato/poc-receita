package br.com.southsystem.pocreceita.service.util;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileUtil {

    public static final String DELIMITER = ";";

    public static CSVWriter createCSVWriter(String path)throws IOException{
        return new CSVWriter(new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8));
    }
    public static File getFile(String path, String nameFile) throws Exception {
        File pathBase = new File(path);
        if (!pathBase.exists()) {
            pathBase.mkdirs();
        }
        return new File(pathBase, nameFile);
    }

    public static void writeFile(String path, String nameFile, String body, boolean append) throws Exception {
        File file = getFile(path, nameFile);
        writeFile(file, body, append);
    }

    public static void writeFile(File file, String body, boolean append) throws Exception {
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter out = new FileWriter(file, append);
        out.write(body);
        out.flush();
        out.close();
    }


}
