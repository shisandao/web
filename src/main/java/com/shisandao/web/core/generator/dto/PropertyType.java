package com.shisandao.web.core.generator.dto;

/**
 * 枚举字段的数据类型
 * Created by 士三刀 on 2020-08-27 10:42:24
 */
public enum PropertyType {
    INT("Integer", ""),
    BIGINT("Long", ""),
    VARCHAR("String", ""),
    DECIMAL("BigDecimal", "java.math.BigDecimal"),
    DATE("Date", "java.util.Date")
    ;

    private String type;
    private String packageName;

    PropertyType(String type, String packageName) {
        this.type = type;
        this.packageName = packageName;
    }

    public String getType() {
        return type;
    }

    public String getPackageName() {
        return packageName;
    }

    public static String getTypeByName(String name) {
        for (PropertyType propertyType : PropertyType.values()) {
            if (name.equals(propertyType.name())) {
                return propertyType.getType();
            }
        }
        return name;
    }

    public static String getPackageNameByType(String type) {
        for (PropertyType propertyType : PropertyType.values()) {
            if (type.equals(propertyType.getType())) {
                return propertyType.getPackageName();
            }
        }
        return "";
    }
}
