package com.shisandao.web.core.generator;

import com.shisandao.web.core.generator.dto.FileInfo;
import com.shisandao.web.core.generator.component.*;

import java.io.File;
import java.util.*;

public class MyGenerator {

    public static void main(String[] args) {
        List<FileInfo> fileInfoList = new ArrayList<>(16);
        FileInfo fileInfo = new FileInfo();
        fileInfo.setType("DTO");
        fileInfo.setFileUrl(System.getProperty("user.dir")+ File.separator+"src/main/java/com/shisandao/web/core/sys/dto/GemOrderDto.java");
        fileInfoList.add(fileInfo);

        FileInfo fileInfo2 = new FileInfo();
        fileInfo2.setType("MAPPER");
        fileInfo2.setFileUrl(System.getProperty("user.dir")+ File.separator+"src/main/java/com/shisandao/web/core/sys/mapper/GemOrderMapper.java");
        fileInfoList.add(fileInfo2);

        FileInfo fileInfo4 = new FileInfo();
        fileInfo4.setType("SERVICE_IMPL");
        fileInfo4.setFileUrl(System.getProperty("user.dir")+ File.separator+"src/main/java/com/shisandao/web/core/sys/service/impl/GemOrderServiceImpl.java");
        fileInfoList.add(fileInfo4);

        FileInfo fileInfo5 = new FileInfo();
        fileInfo5.setType("CONTROLLER");
        fileInfo5.setFileUrl(System.getProperty("user.dir")+ File.separator+"src/main/java/com/shisandao/web/core/sys/controller/GemOrderController.java");
        fileInfoList.add(fileInfo5);

        FileInfo fileInfo3 = new FileInfo();
        fileInfo3.setType("SERVICE");
        fileInfo3.setFileUrl(System.getProperty("user.dir")+ File.separator+"src/main/java/com/shisandao/web/core/sys/service/GemOrderService.java");
        fileInfoList.add(fileInfo3);

        FileInfo fileInfo6 = new FileInfo();
        fileInfo6.setType("MAPPER_XML");
        fileInfo6.setFileUrl(System.getProperty("user.dir")+ File.separator+"src/main/resources/com/shisandao/web/core/sys/mapper/GemOrderMapper.xml");
        fileInfoList.add(fileInfo6);

        String tableName = "gem_order";
        MyGenerator t = new MyGenerator();
        t.generator(tableName, fileInfoList);
    }

    private void generator(String tableName, List<FileInfo> fileInfoList) {

        CodeGenerator codeGenerator;
        for (FileInfo fileInfo : fileInfoList) {
            codeGenerator = CodeFactory.getCodeGenerator(tableName, fileInfo);
            if (null != codeGenerator) {
                codeGenerator.execute();
            }
        }

    }



}
