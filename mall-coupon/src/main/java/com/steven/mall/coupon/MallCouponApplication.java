package com.steven.mall.coupon;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@NacosConfigurationProperties(dataId = "mall-coupon",autoRefreshed = true)
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.steven.mall.coupon.dao")
public class MallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallCouponApplication.class, args);
    }

}
