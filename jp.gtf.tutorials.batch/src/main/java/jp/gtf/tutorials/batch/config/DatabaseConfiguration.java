/*
 * © 2019 Gtf Software, Inc. All Rights Reserved
 * Mail fxd@hotmail.co.jp
 * More information please visit https://gtf.jp
 */
package jp.gtf.tutorials.batch.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * データベース基本
 *
 * @author F
 */
@PropertySource(value = "file:conf/database.properties")
@EnableTransactionManagement
public class DatabaseConfiguration {

    @Value("${datasource.driver-class-name}")
    private String dirverClassName;
    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.username}")
    private String username;
    @Value("${datasource.password}")
    private String password;

    /**
     * データソース作成
     *
     * @return データソース
     */
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(dirverClassName)
                .url(url)
                .username(username)
                .password(password)
                .build();
    }
}
