package com.javamsdt.hibernatespring.model.annotationconfig;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("com.javamsdt.hibernatespring")
@EnableTransactionManagement(proxyTargetClass = true)
@Slf4j
public class ApplicationContextConfig {

    // InternalResourceViewResolver Info
    private static final String PREFIX = "/WEB-INF/views/";
    private static final String SUFFIX = ".jsp";

    // Data Source Info
    private final static String DATA_SOURCE_PROPERTIES = "/dataSource.properties";

    // Beans names
    private static final String VIEW_RESOLVER_BEAN = "viewResolver";
    private static final String DATA_SOURCE_BEAN = "dataSource";
    private static final String TRANSACTION_MANAGER_BEAN = "transactionManager";
    private static final String ENTITY_MANAGER_FACTORY_BEAN = "entityManagerFactory";

    // @Bean(VIEW_RESOLVER_BEAN)
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(PREFIX);
        viewResolver.setSuffix(SUFFIX);
        return viewResolver;
    }

    @Bean(DATA_SOURCE_BEAN)
    public DataSource dataSource() {
        log.info("ConnectionConfigurator DataSource is running");
        HikariConfig config = new HikariConfig(DATA_SOURCE_PROPERTIES);
        return new HikariDataSource(config);
    }

    @Bean(ENTITY_MANAGER_FACTORY_BEAN)
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.javamsdt.hibernatespring");
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setJpaProperties(getHibernateProperties());
        localContainerEntityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        localContainerEntityManagerFactoryBean.afterPropertiesSet();
        return localContainerEntityManagerFactoryBean;
    }

    @Bean(TRANSACTION_MANAGER_BEAN)
    public JpaTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        return properties;
    }
}
