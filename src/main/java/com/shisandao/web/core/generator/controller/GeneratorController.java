package com.shisandao.web.core.generator.controller;

import com.shisandao.web.core.common.RestHandler;
import com.shisandao.web.core.generator.component.CodeFactory;
import com.shisandao.web.core.generator.component.CodeGenerator;
import com.shisandao.web.core.generator.dto.FileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
* controller类
* Created by 士三刀 on 2020-08-27 10:42:24
*/
@Controller
public class GeneratorController {

    private Logger logger = LoggerFactory.getLogger(GeneratorController.class);

    @RequestMapping(value = "/codeService", method = RequestMethod.POST)
    @ResponseBody
    public void generator(String tableName, List<FileInfo> fileInfoList) {

        RestHandler handler = jsonResponse -> {
            CodeGenerator codeGenerator;
            for (FileInfo fileInfo : fileInfoList) {
                codeGenerator = CodeFactory.getCodeGenerator(tableName, fileInfo);
                if (null != codeGenerator) {
                    codeGenerator.execute();
                }
            }
        };
        handler.handle(logger);

    }

}