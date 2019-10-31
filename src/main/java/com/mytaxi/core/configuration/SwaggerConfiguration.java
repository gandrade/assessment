package com.mytaxi.core.configuration;

import com.mytaxi.MytaxiServerApplicantTestApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

/**
 * Configures Swagger2 configuration, including security also.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration
{

    @Bean
    public Docket docket()
    {
        BasicAuth basicAuth = new BasicAuth("basicAuth");
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage(MytaxiServerApplicantTestApplication.class.getPackage().getName()))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(generateApiInfo())
            .securityContexts(Arrays.asList(apiSecurityContext()))
            .securitySchemes(Arrays.asList(basicAuth));
    }


    private SecurityContext apiSecurityContext()
    {
        return SecurityContext.builder()
            .securityReferences(Arrays.asList(basicAuthReference()))
            .forPaths(PathSelectors.ant("/v1/**"))
            .build();
    }


    private SecurityReference basicAuthReference()
    {
        return new SecurityReference("basicAuth", new AuthorizationScope[0]);
    }


    private ApiInfo generateApiInfo()
    {
        return new ApiInfo(
            "mytaxi Server Applicant Test Service",
            "This service is to check the technology knowledge of a server applicant for mytaxi.",
            "Version 1.0 - mw",
            "urn:tos",
            new Contact(null, null, "career@mytaxi.com"),
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",
            Collections.emptyList());
    }
}
