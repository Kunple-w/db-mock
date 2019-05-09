package com.goinghugh.dbmock;

import com.goinghugh.dbmock.constant.DatabaseConst;
import com.goinghugh.dbmock.model.Column;
import com.goinghugh.dbmock.model.TableStructure;
import com.sun.tools.corba.se.idl.toJavaPortable.StringGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * db reader
 *
 * @author yongxu wang
 * @date 2019-05-05 20:07
 **/
public class DataBaseReader {
    private static final Logger logger = LoggerFactory.getLogger(DataBaseReader.class);

    static final String DB_URL = "jdbc:mysql://localhost:3306/blog";
    static final String USER = "root";
    static final String PASS = "qwe122";

    public TableStructure getTableStructure(Connection conn, String schemaNamePattern, String tableNamePattern) {
        try {
            List<Column> columns = getAllColumn(conn, schemaNamePattern, tableNamePattern);
            columns.forEach(column -> ColumnUtils.autoGenerator(column));
            return new TableStructure(schemaNamePattern, tableNamePattern, columns);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Column> getAllColumn(Connection conn, String schemaNamePattern, String tableNamePattern) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet rs = metaData.getColumns(null, schemaNamePattern, tableNamePattern, null);
        List<Column> columns = new ArrayList<>();
        Column column;
        while (rs.next()) {
            column = new Column();
            column.setTableName(rs.getString(DatabaseConst.TABLE_NAME));
            column.setColumnName(rs.getString(DatabaseConst.COLUMN_NAME));
            column.setJdbcType(rs.getString(DatabaseConst.DATA_TYPE));
            column.setTypeName((rs.getString(DatabaseConst.TYPE_NAME)));
            column.setDataType(Integer.valueOf(rs.getString(DatabaseConst.DATA_TYPE)));
            column.setColumnSize(Integer.valueOf(rs.getString(DatabaseConst.COLUMN_SIZE)));
            column.setDecimalDigit((rs.getString(DatabaseConst.DECIMAL_DIGITS) == null ? null : Integer.valueOf(rs.getString(DatabaseConst.DECIMAL_DIGITS))));
            column.setNullable("1".equals(rs.getString(DatabaseConst.NULLABLE)));
            column.setAutoincrement("YES".equals(rs.getString(DatabaseConst.IS_AUTOINCREMENT)));
            column.setRemarks(rs.getString(DatabaseConst.REMARKS));

            String colName = rs.getString("COLUMN_NAME");
            if (logger.isDebugEnabled()) {
                System.out.println("==============" + colName + "===============");
                System.out.println("Column Name: " + colName);
                System.out.println("Column Type: " + rs.getString("DATA_TYPE"));
                System.out.println("Column Type Name: " + rs.getString("TYPE_NAME"));
                System.out.println("Column Size: " + rs.getString("COLUMN_SIZE"));
                System.out.println("Column Decimal Digits: " + rs.getString("DECIMAL_DIGITS"));
                System.out.println("Column Nullable: " + rs.getString("NULLABLE"));
            }
            columns.add(column);
        }
        return columns;
    }

    public static Connection getConn() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
        DataBaseReader dataBaseReader = new DataBaseReader();
        TableStructure structure = dataBaseReader.getTableStructure(connection, "blog", "");
        logger.info("表结构为: {}", structure);
        logger.info("生成的创建sql为: {}", structure.getCreateSql());

    }
}
