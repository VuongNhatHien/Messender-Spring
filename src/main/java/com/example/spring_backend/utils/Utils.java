package com.example.spring_backend.utils;

import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    @SneakyThrows
    public static String saveToTempStorage(MultipartFile file) {
        String projectDir = System.getProperty("user.dir") + "/temp_file/";
        new File(projectDir).mkdirs();
        String fileName = UUID.randomUUID() + file.getOriginalFilename();

        Path path = Paths.get(projectDir, fileName);
        Files.write(path, file.getBytes());
        return path.toString();
    }

    public static String extractFirstUrl(String text) {
        String urlRegex = "(http|ftp|https):\\/\\/([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:\\/~+#-]*[\\w@?^=%&\\/~+#-])";
        Pattern pattern = Pattern.compile(urlRegex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}
