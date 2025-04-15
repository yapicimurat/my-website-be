package com.yapicimurat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.util.HtmlUtils;

@SpringBootApplication
public class App {
    public static void main( String[] args )
    {
        System.out.println(HtmlUtils.htmlEscape("Mura"));
        SpringApplication.run(App.class, args);
    }
}