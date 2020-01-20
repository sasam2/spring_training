package com.boraji.tutorial.spring.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.boraji.tutorial.spring.dao.PostJDBCTemplate;

@Configuration
@PropertySource("classpath:db.properties")
public class AppConfig {

  @Autowired
  private Environment env;
  
  @Autowired
  private DataSource dataSource;
  
  @Bean
  public DataSource getDataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(env.getProperty("mysql.dirver"));
    dataSource.setUrl(env.getProperty("mysql.jdbcUrl"));
    dataSource.setUsername(env.getProperty("mysql.username"));
    dataSource.setPassword(env.getProperty("mysql.password"));
    return dataSource;
  }
  
  @Bean
  public PostJDBCTemplate getPostJDBCTemplate() {
	PostJDBCTemplate postJDBCTemplate = new PostJDBCTemplate(dataSource);
    return postJDBCTemplate;
  }
  
  @Bean(name = "filterMultipartResolver") //multipartResolver
  public CommonsMultipartResolver multipartResolver() {
      CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
      multipartResolver.setMaxUploadSize(1000000);
      return multipartResolver;
  }
  
}
