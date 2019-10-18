package com.stackroute.muzix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket productApi() {
        //Return a prepared Docket instance
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.regex("/api/.*"))
                .apis(RequestHandlerSelectors.basePackage("com.stackroute.muzix"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Muzix API",
                "Manage music track information",
                "1.0",
                "Free to  use",
                 new springfox.documentation.service.Contact("Sachin","http://stackroute.com","sachin@xyz.com"),
                "API License",
                "http://stackroute.com",
                 Collections.emptyList());
    }
}
