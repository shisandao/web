package com.shisandao.web.core.generator.dto;

/**
 * 枚举文件模板类型
 * Created by 士三刀 on 2020-08-27 10:42:24
 */
public enum TemplateType {
    DTO("/dto.template"),
    MAPPER("/mapper.template"),
    SERVICE("/service.template"),
    SERVICE_IMPL("/service_impl.template"),
    CONTROLLER("/controller.template"),
    MAPPER_XML("/mapper_xml.template");

    private final String template;

    TemplateType (String template) {
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }

    public static String getTemplateByType(String type) {
        for (TemplateType templateType : TemplateType.values()) {
            if (type.equals(templateType.name())) {
                return templateType.getTemplate();
            }
        }
        return null;
    }
}
