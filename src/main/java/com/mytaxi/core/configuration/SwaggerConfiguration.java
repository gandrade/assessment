package com.mytaxi.core.configuration;

import com.mytaxi.MytaxiServerApplicantTestApplication;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.stream.Stream;

/**
 * Configures Swagger2 configuration, including security also.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration
{

    @Bean
    public Docket docket(@Value("${security.roles}") String[] roles)
    {
        String packageName = MytaxiServerApplicantTestApplication.class.getPackage().getName();
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage(packageName))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(generateApiInfo())
            .securityContexts(Arrays.asList(generateSecurityContext(roles)))
            .securitySchemes(Arrays.asList(generateBasicAuth()));
    }


    private BasicAuth generateBasicAuth()
    {
        return new BasicAuth("basicAuth");
    }


    private SecurityContext generateSecurityContext(String[] roles)
    {
        return SecurityContext.builder()
            .securityReferences(Arrays.asList(generateSecurityReference(roles)))
            .forPaths(PathSelectors.ant("/v1/**"))
            .build();
    }


    private SecurityReference generateSecurityReference(String[] roles)
    {
        AuthorizationScope[] authorizationScopes = Stream.of(roles)
            .map(role -> new AuthorizationScope(role, role))
            .toArray(AuthorizationScope[]::new);
        return new SecurityReference("basicAuth", authorizationScopes);
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
