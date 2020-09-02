package com.shisandao.web.core.generator.component;

import com.shisandao.web.core.generator.dto.FileInfo;
import com.shisandao.web.core.generator.dto.TemplateType;

/**
 * 代码工厂
 * Created by 士三刀 on 2020-08-27 10:42:24
 */
public class CodeFactory {

    public static CodeGenerator getCodeGenerator(String tableName, FileInfo fileInfo) {
        if (TemplateType.DTO.name().equals(fileInfo.getType())) {
            return new DtoCodeGenerator(tableName, fileInfo);
        } else if (TemplateType.MAPPER.name().equals(fileInfo.getType())) {
            return new MapperCodeGenerator(tableName, fileInfo);
        } else if (TemplateType.MAPPER_XML.name().equals(fileInfo.getType())) {
            return new MapperXmlCodeGenerator(tableName, fileInfo);
        } else if (TemplateType.SERVICE.name().equals(fileInfo.getType())) {
            return new ServiceCodeGenerator(tableName, fileInfo);
        } else if (TemplateType.SERVICE_IMPL.name().equals(fileInfo.getType())) {
            return new ServiceImplCodeGenerator(tableName, fileInfo);
        } else if (TemplateType.CONTROLLER.name().equals(fileInfo.getType())) {
            return new ControllerCodeGenerator(tableName, fileInfo);
        }
        return null;
    }

}
