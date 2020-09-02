package com.shisandao.web.core.generator.dto;

import com.shisandao.web.core.util.StrUtil;

/**
 * 封装数据库字段信息
 * Created by 士三刀 on 2020-08-27 10:42:24
 */
public class Property {

    private String name;
    private String type;
    private String comment;
    private String dbName;
    private String dbType;
    private Boolean pkFlag = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
        this.name = StrUtil.lineToHump(dbName);
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
        this.type = PropertyType.getTypeByName(dbType);
    }

    public Boolean getPkFlag() {
        return pkFlag;
    }

    public void setPkFlag(Boolean pkFlag) {
        this.pkFlag = pkFlag;
    }

    public void checkPk(String pkDbName) {
        if (this.dbName.equals(pkDbName)) {
            this.pkFlag = true;
        }
    }
}
