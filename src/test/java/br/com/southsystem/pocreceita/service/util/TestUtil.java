package br.com.southsystem.pocreceita.service.util;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestUtil {

   public static boolean deleteIfExists(File dir) {
       String[]entries = dir.list();
       boolean deleted = false;
       for(String s: entries){
           File currentFile = new File(dir.getPath(),s);
           if (currentFile.delete()){
               deleted = true;
           }
       }
       return deleted;
    }

    public static String getPathSilence(String relativePath){
        try {
            return new ClassPathResource(relativePath).getFile().getPath();
        }catch (IOException e){
            return null;
        }
    }
}
