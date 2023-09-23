package com.yapicimurat.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        //Modelmapper configurations...

        return new ModelMapper();
    }
}
