package com.shisandao.web.core.generator.dto;

/**
 * 封装代码文件生成所需的文件信息
 * Created by 士三刀 on 2020-08-27 10:42:24
 */
public class FileInfo {

    private String type;
    private String template;
    private String fileUrl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        this.template = TemplateType.getTemplateByType(type);
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
