package com.mytaxi.core.configuration;

import com.mytaxi.util.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configures application interceptor.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer
{
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new LoggingInterceptor())
            .addPathPatterns("/**");
    }
}
