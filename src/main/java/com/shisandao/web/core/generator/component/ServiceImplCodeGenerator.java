package com.shisandao.web.core.generator.component;

import com.shisandao.web.core.generator.dto.CodeData;
import com.shisandao.web.core.generator.dto.FileInfo;

/**
 * service_impl代码生成器
 * Created by 士三刀 on 2020-08-27 10:42:24
 */
public class ServiceImplCodeGenerator extends CodeGenerator {

    public ServiceImplCodeGenerator(String tableName, FileInfo fileInfo) {
        super(tableName, fileInfo);
    }

    @Override
    void packCodeData(String tableName, String fileUrl, CodeData codeData) {
        codeData.setServiceClassName(serviceClassName(codeData.getClassName()));
        codeData.setServicePackageName(servicePackageName(codeData.getPackageName()));
    }

    @Override
    String packageUrl(String fileUrl) {
        return fileUrl.split("src/main/java/")[1];
    }

    private String serviceClassName(String className) {
        return className.substring(0, className.length()-4);
    }

    private String servicePackageName(String packageName) {
        return packageName.substring(0, packageName.length()-5);
    }
}
