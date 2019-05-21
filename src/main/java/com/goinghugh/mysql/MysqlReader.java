package com.goinghugh.mysql;

import com.goinghugh.dbmock.DbReader;
import com.goinghugh.dbmock.config.BeanConfig;
import com.goinghugh.dbmock.constant.DatabaseConst;
import com.goinghugh.dbmock.model.Column;
import com.goinghugh.dbmock.model.TableStructure;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * mysql 表结构读取
 *
 * @author yongxu wang
 * @date 2019-05-21 11:56
 **/
public class MysqlReader implements DbReader {

    private Connection conn;

    private String schemaNamePattern;

    private String tableNamePattern;

    public MysqlReader(String schemaNamePattern, String tableNamePattern) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        try {
            this.conn = context.getBean(DataSource.class).getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.schemaNamePattern = schemaNamePattern;
        this.tableNamePattern = tableNamePattern;
    }

    @Override
    public List<TableStructure> read() {
        return merge();
    }

    private List<TableStructure> merge() {
        MultiValueMap<String, Column> multiValueMap = new LinkedMultiValueMap<>();
        List<TableStructure> structures = new ArrayList<>();
        try {
            List<Column> allColumn = getAllColumns();
            allColumn.forEach(column -> multiValueMap.add(column.getTableName(), column));
            for (Map.Entry<String, List<Column>> entry : multiValueMap.entrySet()) {
                structures.add(new TableStructure(entry.getValue().get(0).getTableCat(), entry.getKey(), entry.getValue()));
            }
            return structures;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Column> getAllColumns() throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet rs = metaData.getColumns(null, schemaNamePattern, tableNamePattern, null);
        List<Column> columns = new ArrayList<>();
        Column column;
        while (rs.next()) {
            column = new Column();
            column.setTableCat(rs.getString(DatabaseConst.TABLE_CAT));
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
            columns.add(column);
        }
        return columns;
    }
}
