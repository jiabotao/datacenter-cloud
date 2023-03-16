package org.datacenter.kafka.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartKafkaManager {

    public static void main(String[] args){
        SpringApplication.run(StartKafkaManager.class,args);
    }
}
