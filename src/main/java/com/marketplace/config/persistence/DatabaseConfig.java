package com.marketplace.config.persistence;

import com.marketplace.model.dto.MySQLCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Driver;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class DatabaseConfig {

    @Resource
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(env.getRequiredProperty("spring.entity.package"));
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(getHibernateProperties());

        return em;
    }

    @Bean(name="transactionManager")
    public PlatformTransactionManager platformTransactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory().getObject());
        return manager;
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setUrl(env.getRequiredProperty("spring.datasource.url"));
        Class<Driver> driverClass;
        try {
            driverClass = (Class<Driver>) Class.forName(env.getRequiredProperty("spring.datasource.driverClassName"));
            ds.setDriverClass(driverClass);
        } catch (ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
        }
        ds.setUsername(env.getRequiredProperty("spring.datasource.username"));
        ds.setPassword(env.getRequiredProperty("spring.datasource.password"));
//        ds.setUsername(MySQLCredentials.getUserName());
//        ds.setPassword(MySQLCredentials.getPassword());
        return ds;
    }

    public Properties getHibernateProperties() {
        try {
            Properties properties = new Properties();
            InputStream is = getClass().getClassLoader().getResourceAsStream("application.properties");
            properties.load(is);

            return properties;
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't find 'application.properties' in classpath");
        }
    }
}
