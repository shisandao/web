package com.shisandao.web.core.generator.dto;

import java.util.List;
import java.util.Set;

/**
 * 封装生成代码所需数据
 * Created by 士三刀 on 2020-08-27 10:42:24
 */
public class CodeData {

    private String author;
    private String createTime;
    private String className;
    private String packageName;
    private List<Property> propertyList;
    private String tableName;
    private String tableComment;
    private Set<String> importPackageSet;

    private String dtoClassName;
    private String dtoPackageName;
    private String mapperClassName;
    private String mapperPackageName;
    private String serviceClassName;
    private String servicePackageName;
    private String serviceImplClassName;
    private String serviceImplPackageName;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public Set<String> getImportPackageSet() {
        return importPackageSet;
    }

    public void setImportPackageSet(Set<String> importPackageSet) {
        this.importPackageSet = importPackageSet;
    }

    public String getDtoClassName() {
        return dtoClassName;
    }

    public void setDtoClassName(String dtoClassName) {
        this.dtoClassName = dtoClassName;
    }

    public String getDtoPackageName() {
        return dtoPackageName;
    }

    public void setDtoPackageName(String dtoPackageName) {
        this.dtoPackageName = dtoPackageName;
    }

    public String getMapperClassName() {
        return mapperClassName;
    }

    public void setMapperClassName(String mapperClassName) {
        this.mapperClassName = mapperClassName;
    }

    public String getMapperPackageName() {
        return mapperPackageName;
    }

    public void setMapperPackageName(String mapperPackageName) {
        this.mapperPackageName = mapperPackageName;
    }

    public String getServiceClassName() {
        return serviceClassName;
    }

    public void setServiceClassName(String serviceClassName) {
        this.serviceClassName = serviceClassName;
    }

    public String getServicePackageName() {
        return servicePackageName;
    }

    public void setServicePackageName(String servicePackageName) {
        this.servicePackageName = servicePackageName;
    }

    public String getServiceImplClassName() {
        return serviceImplClassName;
    }

    public void setServiceImplClassName(String serviceImplClassName) {
        this.serviceImplClassName = serviceImplClassName;
    }

    public String getServiceImplPackageName() {
        return serviceImplPackageName;
    }

    public void setServiceImplPackageName(String serviceImplPackageName) {
        this.serviceImplPackageName = serviceImplPackageName;
    }
}
