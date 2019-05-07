package com.goinghugh.dbmock;

import com.goinghugh.dbmock.model.Column;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class DataBaseReaderTest {

    private static final Logger logger = LoggerFactory.getLogger(DataBaseReaderTest.class);

    private Connection conn;

    @Before
    public void init() {
        conn = DataBaseReader.getConn();
    }

    @After
    public void tearDown() throws SQLException {
        conn.close();
    }

    @Test
    public void getTableStructure() throws SQLException {
        DataBaseReader dataBaseReader = new DataBaseReader();
        List<Column> allColumn = dataBaseReader.getAllColumn(conn, "blog", "student");

        allColumn.forEach(column -> logger.info("行为: {}", column));
        allColumn.forEach(column -> ColumnUtils.autoGenerator(column));
        allColumn.forEach(column -> logger.info("行为: {}", column));
    }

    @Test
    public void getAllColumn() {
    }
}