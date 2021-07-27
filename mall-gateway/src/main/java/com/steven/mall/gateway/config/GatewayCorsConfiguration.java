package com.steven.mall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @desc 处理跨域
 * @author steven
 * @date 2021/1/11 22:37
 */
@Configuration
public class GatewayCorsConfiguration {

    @Bean
    public CorsWebFilter webMvcConfig(){

        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //配置跨域
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOrigin("*");
//        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.setAllowCredentials(true);

        configurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(configurationSource);
    }
}
