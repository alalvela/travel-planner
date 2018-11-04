package com.example.travelplanner.configuration;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket projectApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                    .apis(basePackage("com.example.travelplanner.controller"))
                    .paths(or(regex("/trip.*"), regex("/admin.*"), regex("/auth.*")))
                    .build()
                .alternateTypeRules(AlternateTypeRules
                        .newRule(typeResolver.resolve(ResponseEntity.class, WildcardType.class),
                                    typeResolver.resolve(WildcardType.class)));

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                    .title("Travel planner REST API")
                    .description("Reference documentation for Travel planner project.")
                    .build();
    }
}
