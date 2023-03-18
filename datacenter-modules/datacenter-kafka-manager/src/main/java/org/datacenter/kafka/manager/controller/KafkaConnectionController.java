package org.datacenter.kafka.manager.controller;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import org.apache.kafka.clients.admin.*;
import org.datacenter.common.core.domain.http.R;
import org.datacenter.kafka.manager.utils.KafkaUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/kafka/topic")
public class KafkaConnectionController {

    @PostMapping("/listTopics")
    public R listTopics(@RequestBody Map<String,String> params){
        String default_bootstrap_servers=  "localhost:9092";
        String bootstrap_servers = params.getOrDefault("bootstrap.servers",default_bootstrap_servers);
        Set<String> topicNameSet  = KafkaUtil.listTopics(bootstrap_servers);
        return R.ok(topicNameSet);
    }

    @PostMapping("/deleteTopic")
    public R deleteTopic(@RequestBody Map<String,String> params) throws ExecutionException, InterruptedException {
        String defaultBootstrapServers =  "localhost:9092";
        String bootstrapServers = params.getOrDefault("bootstrap.servers",defaultBootstrapServers);
        String topicName = params.get("topicName");
        boolean deleted = KafkaUtil.deleteTopic(bootstrapServers,topicName);
        return R.ok(deleted);

    }


    @PostMapping("/createTopic")
    public R createTopic(@RequestBody Map<String,String> params){
        String default_bootstrap_servers=  "localhost:9092";
        String bootstrap_servers = params.getOrDefault("bootstrap.servers",default_bootstrap_servers);
        String topicName  = params.get("topicName");
        if(topicName==null){
            return R.fail("请填写topic名称");
        }
        boolean created = KafkaUtil.createTopic(bootstrap_servers,topicName,1,(short)1);
        return R.ok(created);

    }

    @PostMapping("/describeTopics")
    public Map<String, TopicDescription> describeTopics(@RequestBody Map<String,String> params){
        String default_bootstrap_servers=  "localhost:9092";
        String bootstrapServers = params.getOrDefault("bootstrap.servers",default_bootstrap_servers);
        String topicName  = params.get("topicName");
        if(topicName==null){
          //  return R.fail("请填写topic名称");
        }
        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        AdminClient adminClient = null;
        try {
            adminClient = KafkaAdminClient.create(properties);
            DescribeTopicsResult describeTopicsResult = adminClient.describeTopics(Lists.newArrayList(topicName));
            Map<String, TopicDescription> aa = describeTopicsResult.all().get();
            return aa;
        }catch (Exception e){
           // return R.fail(e.getMessage());
        }finally {
            if(adminClient!=null){
                adminClient.close();
            }
        }
        return null;
    }








}
