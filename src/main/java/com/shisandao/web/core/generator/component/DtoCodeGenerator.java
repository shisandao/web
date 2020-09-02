package com.shisandao.web.core.generator.component;

import com.shisandao.web.core.generator.dto.CodeData;
import com.shisandao.web.core.generator.dto.FileInfo;
import com.shisandao.web.core.generator.dto.Property;
import com.shisandao.web.core.generator.dto.PropertyType;
import com.shisandao.web.core.util.DatabaseUtil;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * dto代码生成器
 * Created by 士三刀 on 2020-08-27 10:42:24
 */
public class DtoCodeGenerator extends CodeGenerator {

    public DtoCodeGenerator(String tableName, FileInfo fileInfo) {
        super(tableName, fileInfo);
    }

    @Override
    public void packCodeData(String tableName, String fileUrl, CodeData codeData) {
        packProperty(tableName, codeData);
        packImportPackage(codeData);
    }

    @Override
    public String packageUrl(String fileUrl) {
        return fileUrl.split("src/main/java/")[1];
    }

    private void packProperty(String tableName, CodeData codeData) {
        String pkDbName = DatabaseUtil.getPKColumn(tableName);

        List<String> nameList = DatabaseUtil.getColumnNames(tableName);
        List<String> typeList = DatabaseUtil.getColumnTypes(tableName);
        List<String> commentList = DatabaseUtil.getColumnComments(tableName);
        List<Property> list = new ArrayList<>();
        for (int i=0; i<nameList.size(); i++) {
            Property p = new Property();
            p.setDbName(nameList.get(i));
            p.setDbType(typeList.get(i));
            p.setComment(commentList.get(i));
            p.checkPk(pkDbName);
            list.add(p);
        }
        codeData.setPropertyList(list);
    }

    private void packImportPackage(CodeData codeData) {
        Set<String> importPackageSet = new HashSet<>(16);
        String packageName;
        for (Property p : codeData.getPropertyList()) {
            packageName = PropertyType.getPackageNameByType(p.getType());
            if (! StringUtils.isEmpty(packageName)) {
                importPackageSet.add(packageName);
            }
        }
        codeData.setImportPackageSet(importPackageSet);
    }
}
