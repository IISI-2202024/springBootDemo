package com.kkk.demo.db1.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.kkk.demo.db1.*", entityManagerFactoryRef = "entityManagerFactoryDb1", transactionManagerRef = "transactionManagerDb1")
@EnableTransactionManagement
public class DbConfig1 {

	@Bean("DataSource1")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.db1")
	DataSource DataSource1() {
		return DataSourceBuilder.create().build();
	}

	@Bean("entityManagerFactoryDb1")
	@Primary
	LocalContainerEntityManagerFactoryBean EntityManagerFactoryDb1(@Qualifier("DataSource1") DataSource DataSource1,
			EntityManagerFactoryBuilder builder) {
		return builder.dataSource(DataSource1).packages("com.kkk.demo.db1.*").persistenceUnit("Ds1").build();
	}

	@Bean("transactionManagerDb1")
	@Primary
	PlatformTransactionManager TransactionManagerDb1(
			@Qualifier("entityManagerFactoryDb1") LocalContainerEntityManagerFactoryBean EntityManagerFactoryDb1) {
		return new JpaTransactionManager(EntityManagerFactoryDb1.getObject());
	}

	@Bean("primaryJdbcTemplate")
	@Primary
	JdbcTemplate jdbcTemplate1(@Qualifier("DataSource1") DataSource DataSource1) {
		return new JdbcTemplate(DataSource1);
	}

}