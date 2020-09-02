package com.shisandao.web.core.mybatis.mapper;

import com.shisandao.web.core.mybatis.provider.BaseSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BaseMapper<T> {

    //新增一条数据
    @InsertProvider(method = "insert",type= BaseSqlProvider.class)
    int insert(T bean);

    //根据主键删除一条数据
    @DeleteProvider(method = "deleteByPrimaryKey",type=BaseSqlProvider.class)
    int deleteByPrimaryKey(T bean);

    //根据主键更新一条数据
    @UpdateProvider(method = "updateByPrimaryKey",type=BaseSqlProvider.class)
    int updateByPrimaryKey(T bean);

    //根据主键更新一条数据，不更新null的字段
    @UpdateProvider(method = "updateByPrimaryKeySelective",type=BaseSqlProvider.class)
    int updateByPrimaryKeySelective(T bean);

    //根据主键查询数据
    @SelectProvider(method = "selectByPrimaryKey",type=BaseSqlProvider.class)
    T selectByPrimaryKey(Long id);

    //根据不为null的字段查询
    @SelectProvider(method = "select",type=BaseSqlProvider.class)
    List<T> select(T bean);

}
