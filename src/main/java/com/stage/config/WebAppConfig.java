package com.stage.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stage.dao.RoleDAO;
import com.stage.dao.RoleDAOImpl;
import com.stage.dao.UserDAO;
import com.stage.dao.UserDAOImpl;
import com.stage.model.Role;
import com.stage.model.User;
import com.stage.service.RoleServiceImpl;
import com.stage.service.UserServiceImpl;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by wital on 30.01.2017.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.stage")
@EnableTransactionManagement
public class WebAppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private Environment environment;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
    }

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
        return converter;
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/usersandroles");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");

        return dataSource;
    }
   /* private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.id.new_generator_mappings", "false");
        return properties;
    }*/
    @Autowired
    @Bean(name = "sessionFactory")

    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
       // sessionBuilder.addProperties(getHibernateProperties());

        sessionBuilder.addAnnotatedClasses(User.class);
        sessionBuilder.addAnnotatedClasses(Role.class);
        return sessionBuilder.buildSessionFactory();
    }

@Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(
            SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(
                sessionFactory);

        return transactionManager;
    }




    @Autowired
    @Bean(name = "userDao")
    public UserDAOImpl getSession(SessionFactory sessionFactory) {
        return new UserDAOImpl(sessionFactory);
    }
    @Autowired
    @Bean(name = "userService")
    public UserServiceImpl getDao(UserDAO userDao) {
        return new UserServiceImpl(userDao);
    }

    @Autowired
    @Bean(name = "roleDao")
    public RoleDAOImpl getSessionR(SessionFactory sessionFactory) {
        return new RoleDAOImpl(sessionFactory);
    }
    @Autowired
    @Bean(name = "roleService")
    public RoleServiceImpl getDaoR(RoleDAO roleDAO) {
        return new RoleServiceImpl(roleDAO);
    }
}