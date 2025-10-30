package com.jjang051.jpa.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeRenameStrategy implements FileRenameStrategy {
    @Override
    public String rename(String originalFileName) {
        String fileName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));;
        return fileName + "_"+now + "."+extension;
    }
}
