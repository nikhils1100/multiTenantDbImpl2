package com.multiTenantDbImpl2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@ComponentScan(basePackages = { "com.multiTenantDbImpl2.*" })
@EntityScan("com.multiTenantDbImpl2.*")
@EnableJpaRepositories(basePackages = "com.multiTenantDbImpl2.*",
        entityManagerFactoryRef = "userEntityManager",
        transactionManagerRef = "userTransactionManager")
public class MultiTenantConfig {
    @Autowired
    private Environment env;

    public MultiTenantConfig() {
        super();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean userEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(userDataSource()); // Option to switch ds
        em.setPackagesToScan("com.multiTenantDbImpl2.repository.model");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public DataSource userDataSource() {
        String tenantName = env.getProperty("run-environment");
        if(tenantName==null)
            tenantName = "tenant_book_dev";
        String tenantPrefix = "persistence-".concat(tenantName);

        return DataSourceBuilder.create()
                .driverClassName(env.getProperty("spring.datasource.driver-class-name"))
                .url(env.getProperty(tenantPrefix.concat(".url")))
                .username(env.getProperty(tenantPrefix.concat(".username")))
                .password(env.getProperty(tenantPrefix.concat(".password")))
                .build();
    }

    @Bean
    public PlatformTransactionManager userTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(userEntityManager().getObject());
        return transactionManager;
    }
}
