package com.goinghugh.dbmock;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yongxu wang
 * @date 2019-05-07 11:41
 **/
public class SpringJdbcDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        String sql = "INSERT INTO blog.student (name,password,age) value (?,?,?);";
        List<Object[]> batchArgs = new ArrayList<>();
        batchArgs.add(new Object[]{"caoyc", 123, 6});
        batchArgs.add(new Object[]{"zhh", "1234", 8});
        batchArgs.add(new Object[]{"cjx", "98", 8});
        int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println(Arrays.toString(ints));
    }

    @Configuration
    public static class Config {
        @Bean
        public DataSource driverManagerDataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/blog");
            dataSource.setUsername("root");
            dataSource.setPassword("qwe122");
            return dataSource;
        }

        @Bean
        public JdbcTemplate jdbcTemplate() {
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(driverManagerDataSource());
            return jdbcTemplate;
        }

    }
}


