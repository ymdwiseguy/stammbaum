package com.ymdwiseguy.configuration;


import com.github.jknack.handlebars.Handlebars;
import com.ymdwiseguy.FamilyTreeController;
import com.ymdwiseguy.FamilyTreeRepo;
import com.ymdwiseguy.FamilyTreeService;
import com.ymdwiseguy.FamilyTreeView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
public class FamilyTreeConfiguration {

    @Value("${liquibase.change-log}")
    private String liquibaseChangelog;

    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    Handlebars handlebars(){
        return new Handlebars();
    }

    @Bean
    JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    FamilyTreeRepo familyTreeRepo() {
        return new FamilyTreeRepo(jdbcTemplate());
    }

    @Bean
    public FamilyTreeController familyTreeController() {
        return new FamilyTreeController();
    }


    @Bean
    public FamilyTreeService familyTreeService(){
        return new FamilyTreeService(familyTreeRepo());
    }

    @Bean
    public FamilyTreeView familyTreeView() {
        return new FamilyTreeView(handlebars());
    }

//    @Bean
//    public SpringLiquibase liquibase() {
//        SpringLiquibase liquibase = new SpringLiquibase();
//        liquibase.setChangeLog(liquibaseChangelog);
//        liquibase.setDataSource(dataSource());
//        return liquibase;
//    }

}
