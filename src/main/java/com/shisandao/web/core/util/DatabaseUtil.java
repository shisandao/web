package com.shisandao.web.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseUtil.class);
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/gem?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    private static final String CATALOG = "gem";

//    @Value("${spring.datasource.url}")
//    private static String DATABASE_URL;
//    @Value("${spring.datasource.username}")
//    private static String USERNAME;
//    @Value("${spring.datasource.password}")
//    private static String PASSWORD;
//    @Value("${mydatabase.driver}")
//    private static String DRIVER;
//    @Value("${mydatabase.catalog}")
//    private static String CATALOG;

    private static final String SQL = "SELECT * FROM ";// 数据库操作	
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            logger.error("can not load jdbc driver", e);
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            logger.error("get connection failure", e);
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error("close connection failure", e);
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if(null != rs) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.error("close ResultSet failure", e);
            }
        }
    }

    public static List<String> getTableNames() {
        List<String> tableNames = new ArrayList <>();
        Connection conn = getConnection();
        ResultSet rs = null;
        try {
            //获取数据库的元数据	
            DatabaseMetaData db = conn.getMetaData();
            //从元数据中获取到所有的表名	
            rs = db.getTables(CATALOG, null, null, new String[] { "TABLE" });
            while(rs.next()) {
                tableNames.add(rs.getString(3));
            }
        } catch (SQLException e) {
            logger.error("getTableNames failure", e);
        } finally {
            closeResultSet(rs);
            closeConnection(conn);
        }
        return tableNames;
    }

    public static List<String> getColumnNames(String tableName) {
        List<String> columnNames = new ArrayList<>();
        //与数据库的连接	
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据	
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数	
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnNames.add(rsmd.getColumnName(i + 1));
            }
        } catch (SQLException e) {
            logger.error("getColumnNames failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                } catch (SQLException e) {
                    logger.error("getColumnNames close pstem failure", e);
                }
            }
            closeConnection(conn);
        }
        return columnNames;
    }

    public static List<String> getColumnTypes(String tableName) {
        List<String> columnTypes = new ArrayList<>();
        //与数据库的连接	
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据	
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数	
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnTypes.add(rsmd.getColumnTypeName(i + 1));
            }
        } catch (SQLException e) {
            logger.error("getColumnTypes failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    logger.error("getColumnTypes close pstem failure", e);
                }
            }
        }
        return columnTypes;
    }

    public static List<String> getColumnComments(String tableName) {
        //与数据库的连接	
        Connection conn = getConnection();
        PreparedStatement pStemt;
        String tableSql = SQL + tableName;
        List<String> columnComments = new ArrayList<>();//列名注释集合	
        ResultSet rs = null;
        try {
            pStemt = conn.prepareStatement(tableSql);
            rs = pStemt.executeQuery("show full columns from " + tableName);
            while (rs.next()) {
                columnComments.add(rs.getString("Comment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closeConnection(conn);
        }
        return columnComments;
    }

    public static String  getPKColumn(String tableName) {
        Connection conn = getConnection();
        ResultSet rs = null;
        String pkColumn="";
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();
            //从元数据中获取到主键
            rs = db.getPrimaryKeys(CATALOG, null, tableName);
            //rs.next();
            if(rs.next())
            {
                pkColumn=rs.getString("column_name");
            }
        } catch (SQLException e) {
            logger.error("getTableNames failure", e);
        } finally {
            closeResultSet(rs);
            closeConnection(conn);
        }
        return pkColumn;
    }

    public static void main(String[] args) {


    }
}
