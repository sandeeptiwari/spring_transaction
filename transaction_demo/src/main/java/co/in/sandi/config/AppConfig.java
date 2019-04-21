package co.in.sandi.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@ComponentScan(basePackages = {"co.in.sandi.dao.impl", "co.in.sandi.tx.impl", "co.in.sandi.tx.test.Impl"})
@Configuration
@EnableTransactionManagement
public class AppConfig {
    private static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //MySQL database we are using
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/jpa");//change url
        dataSource.setUsername("root");//change userid
        dataSource.setPassword("root");//change pwd

        //H2 database
        /*
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
        dataSource.setUsername("sa");
        dataSource.setPassword("");*/
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        System.out.println("Creating entity Manager");
        logger.info("DATASOURCE :"+dataSource());
        LocalSessionFactoryBean factoryBean=new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan(new String[]{"co.in.sandi.model"});
        factoryBean.setHibernateProperties(additionalProperties());
        return factoryBean;
    }

    private Properties additionalProperties() {
        Properties properties=new Properties();
        properties.put("hibernate.dialect", org.hibernate.dialect.MySQL5Dialect.class);
        properties.put("hibernate.show_sql", Boolean.TRUE);
        properties.put("hibernate.format_sql", Boolean.TRUE);
        properties.put("use_sql_comments", Boolean.TRUE);
        properties.put("default_batch_fetch_size", 10);
        properties.put("hibernate.hbm2ddl.auto", "update");
        //properties.put("hibernate.cache.use_second_level_cache", Boolean.TRUE);
        //properties.put("hibernate.cache.use_query_cache", Boolean.TRUE);
        //properties.put("hibernate.cache.region.factory_class",EhCacheRegionFactory.class);
        return properties;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {

        HibernateTransactionManager txManager = new HibernateTransactionManager();
        logger.info("Obj :"+sessionFactory());
        txManager.setSessionFactory(sessionFactory().getObject());
        return txManager;
    }
}
