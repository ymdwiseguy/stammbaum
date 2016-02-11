package com.ymdwiseguy.configuration;

import com.ymdwiseguy.FamilyTreeController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {
    @Bean
    public FamilyTreeController familyTreeController() {
        return new FamilyTreeController();
    }
}
