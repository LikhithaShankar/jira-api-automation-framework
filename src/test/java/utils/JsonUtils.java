package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T readJson(String fileName, Class<T> clazz) {
        try {
            File file = new File("src/test/resources/testdata/" + fileName);
            return mapper.readValue(file, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file: " + fileName, e);
        }
    }
}