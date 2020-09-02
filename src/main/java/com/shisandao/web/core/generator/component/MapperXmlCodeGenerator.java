package com.shisandao.web.core.generator.component;

import com.shisandao.web.core.generator.dto.CodeData;
import com.shisandao.web.core.generator.dto.FileInfo;

/**
 * mapper_xml代码生成器
 * Created by 士三刀 on 2020-08-27 10:42:24
 */
public class MapperXmlCodeGenerator extends CodeGenerator {

    public MapperXmlCodeGenerator(String tableName, FileInfo fileInfo) {
        super(tableName, fileInfo);
    }

    @Override
    void packCodeData(String tableName, String fileUrl, CodeData codeData) {
        codeData.setMapperClassName(mapperClassName(codeData.getClassName()));
        codeData.setMapperPackageName(mapperPackageName(codeData.getPackageName()));
    }

    @Override
    String packageUrl(String fileUrl) {
        return fileUrl.split("src/main/resources/")[1];
    }

    private String mapperClassName(String className) {
        return className.substring(0, className.length()-6) + "Mapper";
    }

    private String mapperPackageName(String packageName) {
        return packageName.substring(0, packageName.length()-6) + "mapper";
    }
}