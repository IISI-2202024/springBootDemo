package com.kkk.demo.db2.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.kkk.demo.db2.*", entityManagerFactoryRef = "entityManagerFactoryDb2", transactionManagerRef = "transactionManagerDb2")
@EnableTransactionManagement
public class DbConfig2 {

	@Bean("DataSource2")
	@ConfigurationProperties(prefix = "spring.datasource.db2")
	DataSource DataSource1() {
		return DataSourceBuilder.create().build();
	}

	@Bean("entityManagerFactoryDb2")
	LocalContainerEntityManagerFactoryBean EntityManagerFactoryDb2(@Qualifier("DataSource2") DataSource DataSource1,
			EntityManagerFactoryBuilder builder) {
		return builder.dataSource(DataSource1).packages("com.kkk.demo.db2.*").persistenceUnit("Ds2").build();
	}

	@Bean("transactionManagerDb2")
	PlatformTransactionManager TransactionManagerDb2(
			@Qualifier("entityManagerFactoryDb2") LocalContainerEntityManagerFactoryBean EntityManagerFactoryDb2) {
		return new JpaTransactionManager(EntityManagerFactoryDb2.getObject());
	}

	@Bean("secondaryJdbcTemplate")
	JdbcTemplate jdbcTemplate2(@Qualifier("DataSource2") DataSource DataSource2) {
		return new JdbcTemplate(DataSource2);
	}

}