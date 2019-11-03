package com.spring.restful.orders.app.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySource(value = { "classpath:db.properties" })
public class AppConfig {
	@Autowired
	private Environment env;
	@Bean
	public DataSource getDataSource() throws IllegalStateException, PropertyVetoException {
		ComboPooledDataSource cpds=new ComboPooledDataSource();
		cpds.setDriverClass(env.getRequiredProperty("jdbc.driverClassName"));
		cpds.setJdbcUrl(env.getRequiredProperty("jdbc.url"));
		cpds.setUser(env.getRequiredProperty("jdbc.username"));
		cpds.setPassword(env.getRequiredProperty("jdbc.password"));
		cpds.setInitialPoolSize(10);
		cpds.setMaxPoolSize(30);
		return cpds;
	}
	@Bean
	public JdbcTemplate jdbcTemplate() throws IllegalStateException, PropertyVetoException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate;
	}
}
