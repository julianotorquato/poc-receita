package br.com.southsystem.pocreceita.service.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StreamUtil {

    public static Stream<String> of(String path) throws IOException {
        Path pathStorageInput = Paths.get(path);
        return Files.lines(pathStorageInput).parallel();
    }
}
