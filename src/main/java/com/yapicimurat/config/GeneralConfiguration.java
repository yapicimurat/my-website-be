package com.yapicimurat.config;

import com.yapicimurat.dto.ArticleDTO;
import com.yapicimurat.dto.Pageable;
import com.yapicimurat.dto.PageableDTO;
import com.yapicimurat.model.Article;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        //Modelmapper configurations...
        ModelMapper modelMapper = new ModelMapper();




        return modelMapper;
    }
}
