package com.mytaxi.core;

import com.mytaxi.MytaxiServerApplicantTestApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(MytaxiServerApplicantTestApplication.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(generateApiInfo()).securitySchemes(Arrays.asList(new BasicAuth("basicAuth")));
    }


    private ApiInfo generateApiInfo() {
        return new ApiInfo("mytaxi Server Applicant Test Service", "This service is to check the technology knowledge of a server applicant for mytaxi.", "Version 1.0 - mw",
                "urn:tos", "career@mytaxi.com", "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
    }
}
