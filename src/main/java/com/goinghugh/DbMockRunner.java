package com.goinghugh;

import com.goinghugh.dbmock.*;
import com.goinghugh.mysql.MysqlReader;
import com.goinghugh.mysql.MysqlWriter;

import java.sql.Connection;

/**
 * runner
 *
 * @author yongxu wang
 * @date 2019-05-21 15:10
 **/
public class DbMockRunner {
    public static void main(String[] args) {

        Connection connect = ConnectFactory.getConnect();
        DbReader dbReader = new MysqlReader(connect, "blog", "django_admin_log");
        DbWriter dbWriter = new MysqlWriter(dbReader.read().get(0));
        dbWriter.write(10);
    }
}
