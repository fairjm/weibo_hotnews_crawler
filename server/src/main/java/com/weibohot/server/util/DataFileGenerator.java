package com.weibohot.server.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

public final class DataFileGenerator {

    public static void write(final File file, final Map<String, String> content) throws IOException {
        final Path filePath = file.toPath();
        Files.deleteIfExists(filePath);
        final StringBuilder sb = new StringBuilder();
        sb.append("key\tvalue\r\n");
        for (final Map.Entry<String, String> e : content.entrySet()) {
            sb.append(e.getKey() + "\t" + e.getValue() + "\r\n");
        }
        Files.write(filePath, sb.toString().getBytes("utf-8"), StandardOpenOption.CREATE,
                StandardOpenOption.WRITE, StandardOpenOption.APPEND);
    }
}
