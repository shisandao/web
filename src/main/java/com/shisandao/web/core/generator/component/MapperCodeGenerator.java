package com.shisandao.web.core.generator.component;

import com.shisandao.web.core.generator.dto.CodeData;
import com.shisandao.web.core.generator.dto.FileInfo;

/**
 * mapper代码生成器
 * Created by 士三刀 on 2020-08-27 10:42:24
 */
public class MapperCodeGenerator extends CodeGenerator {

    public MapperCodeGenerator(String tableName, FileInfo fileInfo) {
        super(tableName, fileInfo);
    }

    @Override
    void packCodeData(String tableName, String fileUrl, CodeData codeData) {

    }

    @Override
    String packageUrl(String fileUrl) {
        return fileUrl.split("src/main/java/")[1];
    }

}
