package com.example.footballmanager.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {
  @Bean
  public Docket api() {
    // @formatter:off
    // Register the controllers to swagger
    // Also it is configuring the Swagger Docket
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
        .build();
    // @formatter:on
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // enabling swagger-ui part for visual documentation
    registry
        .addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
    registry
        .addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  private ApiInfo metaData() {
    ApiInfo apiInfo =
        new ApiInfo(
            "FootballManager REST API",
            "Spring Boot REST API for FootballManager System",
            "1.0",
            "Terms of service",
            "Oleh Maliarchuk",
            "Apache License Version 2.0",
            "https://www.apache.org/licenses/LICENSE-2.0");
    return apiInfo;
  }
}
