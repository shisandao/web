package com.shisandao.web.core.mybatis.provider;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import com.shisandao.web.core.util.StrUtil;
import org.apache.ibatis.jdbc.SQL;

import javax.persistence.Id;
import javax.persistence.Transient;

public class BaseSqlProvider<T> {

    public String insert(T bean) throws IllegalAccessException {

        SQL sql = new SQL();

        Class<?> clazz = bean.getClass();
        String tableName = tableName(clazz);
        sql.INSERT_INTO(tableName);

        List<Field> fields = getFields(clazz);
        for (Field field : fields) {
            field.setAccessible(true);

            Id key = field.getAnnotation(Id.class);
            if (null != key && null == field.get(bean) ) {
                //主键为空时在数据库中自增，所有忽略
                continue;
            }

            sql.VALUES(StrUtil.humpToLine(field.getName()), "#{" + field.getName() + "}");
        }

        System.out.println("SQL: "+sql.toString());
        return sql.toString();
    }

    public String deleteByPrimaryKey(T bean) {

        SQL sql = new SQL();

        Class<?> clazz = bean.getClass();
        String tableName = tableName(clazz);
        sql.DELETE_FROM(tableName);

        addPrimaryKeyCondition(getFields(clazz), sql);

        System.out.println("SQL: "+sql.toString());
        return sql.toString();
    }

    public String updateByPrimaryKey(T bean) throws IllegalAccessException {
        SQL sql = new SQL();

        Class<?> clazz = bean.getClass();
        String tableName = tableName(clazz);
        sql.UPDATE(tableName);

        List<Field> fields = getFields(clazz);

        for (Field field : fields) {
            field.setAccessible(true);

            Id key = field.getAnnotation(Id.class);
            if (null != key && null == field.get(bean) ) {
                //主键为空时在数据库中自增，所有忽略
                continue;
            }

            sql.SET(paramTemplate(field.getName()));
        }

        addPrimaryKeyCondition(fields, sql);

        System.out.println("SQL: "+sql.toString());
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(T bean) throws IllegalAccessException {
        SQL sql = new SQL();

        Class<?> clazz = bean.getClass();
        String tableName = tableName(clazz);
        sql.UPDATE(tableName);

        List<Field> fields = getFields(clazz);

        for (Field field : fields) {
            field.setAccessible(true);

            if (null == field.get(bean) ) {
                //忽略null的字段
                continue;
            }

            sql.SET(paramTemplate(field.getName()));
        }

        addPrimaryKeyCondition(fields, sql);

        System.out.println("SQL: "+sql.toString());
        return sql.toString();
    }

    public String selectByPrimaryKey(Long id) {
        SQL sql = new SQL();

        Class<?> clazz = getTClass();
        String tableName = tableName(clazz);
        sql.SELECT(tableName);

        Field pkField = getPrimarkKeyField(getFields(clazz));
        if (null == pkField) {
            sql.WHERE(" 1 = 2");
            throw new RuntimeException("对象中未包含PrimaryKey属性");
        }
        pkField.setAccessible(true);
        sql.WHERE(StrUtil.humpToLine(pkField.getName()) + " = #{" + id + "}");

        System.out.println("SQL: "+sql.toString());
        return sql.toString();
    }

    public String select(T bean) throws IllegalAccessException {
        SQL sql = new SQL();

        Class<?> clazz = bean.getClass();
        String tableName = tableName(clazz);
        sql.SELECT(tableName);

        List<Field> fields = getFields(clazz);

        for (Field field : fields) {
            field.setAccessible(true);

            if (null == field.get(bean) ) {
                //忽略null的字段
                continue;
            }

            sql.WHERE(paramTemplate(field.getName()));
        }

        System.out.println("SQL: "+sql.toString());
        return sql.toString();
    }

    private Class<?> getTClass() {
        return (Class<?>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private String tableName(Class<?> clazz) {
        String className = clazz.getSimpleName();
        return StrUtil.humpToLine(className.substring(0, className.length()-3)).substring(1);
    }

    private Field getPrimarkKeyField(List<Field> fields) {
        for (Field field : fields) {
            field.setAccessible(true);
            Id key = field.getAnnotation(Id.class);
            if (null != key) {
                return field;
            }
        }
        return null;
    }

    private List<Field> getFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Transient key = field.getAnnotation(Transient.class);
            //排除被 @Transient 标注的临时字段
            if (null == key) {
                fieldList.add(field);
            }
        }
        return fieldList;
    }

    private String paramTemplate(String fieldName) {
        return StrUtil.humpToLine(fieldName) + " = #{" + fieldName + "}";
    }

    private void addPrimaryKeyCondition(List<Field> fields, SQL sql) {
        Field pkField = getPrimarkKeyField(fields);
        if (null == pkField) {
            sql.WHERE(" 1 = 2");
            throw new RuntimeException("对象中未包含PrimaryKey属性");
        }
        pkField.setAccessible(true);
        sql.WHERE(paramTemplate(pkField.getName()));
    }

}
