package org.datacenter.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = {"org.daracenter"})
public class DataCenterAuth {

    public static void main(String[] args){
        SpringApplication.run(DataCenterAuth.class,args);
    }
}
