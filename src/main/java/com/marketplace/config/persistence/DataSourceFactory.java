package com.marketplace.config.persistence;

import com.marketplace.model.dto.DatabaseWithCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DataSourceFactory {

    public static DataSource getDataSource() {
        SimpleDriverDataSource ds = new SimpleDriverDataSource();
        String database = DatabaseWithCredentials.getTypeOfDatabase();
        Class<Driver> driverClass;
        switch (database) {
            case "postgresql":
                ds.setUrl("jdbc:postgresql://localhost:5432/marketplace");
                try {
                    driverClass = (Class<Driver>) Class.forName("org.postgresql.Driver");
                    ds.setDriverClass(driverClass);
                } catch (ClassNotFoundException | ClassCastException e) {
                    e.printStackTrace();
                }
                ds.setUsername("postgres");
                ds.setPassword("admin");
                break;
            case "h2":
                ds.setUrl("jdbc:h2:mem:marketplace;DB_CLOSE_DELAY=-1");
                try {
                    driverClass = (Class<Driver>) Class.forName("org.h2.Driver");
                    ds.setDriverClass(driverClass);
                } catch (ClassNotFoundException | ClassCastException e) {
                    e.printStackTrace();
                }
                ds.setUsername("sa");
                ds.setPassword("");
                break;
            default:
                return null;
        }
        return ds;
    }
}
