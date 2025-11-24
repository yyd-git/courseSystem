package com.zjsu.yyd.course;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced  // ✅ 关键：让 RestTemplate 支持通过服务名访问
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}