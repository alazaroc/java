package com.example.swagger2;

import io.swagger.annotations.SwaggerDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
//                .apis(RequestHandlerSelectors.basePackage("com.example.consumingrest"))
                .paths(regex("/v.*"))
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .consumes(getConsumesProduces())
                .produces(getConsumesProduces())
                .globalResponseMessage(RequestMethod.GET, responseMessageBuilderList());
//                .securitySchemes(Arrays.asList(securityScheme()))
//                .securityContexts(Arrays.asList(securityContext()));
    }

    private Set getConsumesProduces(){
        Set list = new HashSet();
        list.add("application/json");
//        list.add("application/xml");
        return list;
    }


    private Set getProtocols(){
        Set list = new HashSet();
        list.add(SwaggerDefinition.Scheme.HTTP);
        list.add(SwaggerDefinition.Scheme.HTTPS);
        return list;
    }

    private ArrayList responseMessageBuilderList() {
        ArrayList responseMessageBuilderList = new ArrayList();
        responseMessageBuilderList.add(new ResponseMessageBuilder()
                .code(401)
                .message("You are not authorized to view the resource")
                //.responseModel(new ModelRef("Error"))
                .build());
        responseMessageBuilderList.add(new ResponseMessageBuilder()
                .code(403)
                .message("Accessing the resource you were trying to reach is forbidden")
                //.responseModel(new ModelRef("Error"))
                .build());
        responseMessageBuilderList.add(new ResponseMessageBuilder()
                .code(404)
                .message("The resource you were trying to reach is not found")
                //.responseModel(new ModelRef("Error"))
                .build());
        return responseMessageBuilderList;
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "My REST API",
                "Some custom description of API.",
                "1.0",
                null,
                null, null, null,
                Collections.emptyList());
    }
}
