package com.shisandao.web.core.generator.component;

import cn.hutool.core.date.DateUtil;
import com.shisandao.web.core.generator.dto.*;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * 代码生成器-抽象父类
 * Created by 士三刀 on 2020-08-27 10:42:24
 */
public abstract class CodeGenerator {

    String RESOURCES_URL = System.getProperty("user.dir")+ File.separator+"src/main/resources/";
    String AUTHOR = "士三刀";

    private final String tableName;
    private final FileInfo fileInfo;
    private final CodeData codeData = new CodeData();

    public CodeGenerator(String tableName, FileInfo fileInfo) {
        this.tableName = tableName;
        this.fileInfo = fileInfo;
    }

    public void execute() {
        initCodeData(this.tableName, this.fileInfo.getFileUrl(), this.codeData);
        saveAsFileWriter(this.fileInfo, this.codeData);
    }

    private void initCodeData(String tableName, String fileUrl, CodeData codeData) {
        codeData.setAuthor(AUTHOR);
        codeData.setCreateTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        codeData.setClassName(className(fileUrl));
        codeData.setPackageName(packageName(fileUrl));
        packCodeData(tableName, fileUrl, codeData);
    }

    abstract void packCodeData(String tableName, String fileUrl, CodeData codeData);

    abstract String packageUrl(String fileUrl);

    private String className(String fileUrl) {
        String result = packageUrl(fileUrl);
        int lastIndex = result.lastIndexOf("/");
        result = result.substring(lastIndex+1).split("\\.")[0];
        return result;
    }

    private String packageName(String fileUrl) {
        String result = packageUrl(fileUrl);
        int lastIndex = result.lastIndexOf("/");
        result = result.substring(0, lastIndex).replaceAll("/", ".");
        return result;
    }

    private void saveAsFileWriter(FileInfo fileInfo, CodeData codeData) {
        FileWriter fwriter = null;
        try {
            String path = fileInfo.getFileUrl().substring(0, fileInfo.getFileUrl().lastIndexOf("/"));
            File file = new File(path);
            if(!file.exists()&&!file.isDirectory()) {
                if (! file.mkdirs()) {
                    return;
                }
            }
            // false表示覆盖原来的内容，而不是加到文件的后面。
            fwriter = new FileWriter(fileInfo.getFileUrl(), false);
            fwriter.write(generatorCode(fileInfo.getTemplate(), codeData));
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (null != fwriter) {
                    fwriter.flush();
                    fwriter.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private String generatorCode(String template, CodeData codeData) throws IOException {
        String root = RESOURCES_URL + "com/shisandao/web/core/generator/template";//模板路径
        FileResourceLoader resourceLoader = new FileResourceLoader(root, "utf-8");
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        Template t = gt.getTemplate(template);
        t.binding("codeData", codeData);
        return t.render();
    }
}
