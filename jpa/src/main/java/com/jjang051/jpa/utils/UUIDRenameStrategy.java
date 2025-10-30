package com.jjang051.jpa.utils;

import java.util.UUID;

public class UUIDRenameStrategy implements FileRenameStrategy{
    @Override
    public String rename(String originalFileName) {
        String fileName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
        String uuid = UUID.randomUUID().toString();
        return fileName+"_"+uuid+"."+extension;
    }
}
