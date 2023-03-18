package org.datacenter.kafka.manager.controller;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.datacenter.common.core.domain.http.R;
import org.datacenter.kafka.manager.utils.KafkaUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
    public R deleteTopic(@RequestBody Map<String,Set<String>> params){
        String bootstrapServers =  "localhost:9092";
        Set<String> topicNameSet = params.getOrDefault("topicNames",new HashSet<>());

        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);

        AdminClient adminClient = null;
        boolean deleteResult = false;
        try {
            adminClient =  KafkaAdminClient.create(properties);
            DeleteTopicsResult deleted = adminClient.deleteTopics(topicNameSet);
            deleted.all().get();
            deleteResult = true;
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if(adminClient != null){
                adminClient.close();
            }
        }
        return R.ok(deleteResult);

    }


    @PostMapping("/createTopic")
    public R createTopic(@RequestBody Map<String,String> params){
        String default_bootstrap_servers=  "localhost:9092";
        String bootstrap_servers = params.getOrDefault("bootstrap.servers",default_bootstrap_servers);
        String topicName  = params.get("topicName");
        if(topicName==null){
            return R.fail("请填写topic名称");
        }
        boolean created = createTopic(bootstrap_servers,topicName,1,(short)3);
        return R.ok(created);

    }

    @PostMapping("/describeTopics")
    public R describeTopics(@RequestBody Map<String,String> params){
        String default_bootstrap_servers=  "localhost:9092";
        String bootstrapServers = params.getOrDefault("bootstrap.servers",default_bootstrap_servers);
        String topicName  = params.get("topicName");
        if(topicName==null){
            return R.fail("请填写topic名称");
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
            return R.ok(aa);
        }catch (Exception e){
            return R.fail(e.getMessage());
        }finally {
            if(adminClient!=null){
                adminClient.close();
            }
        }
    }




    public boolean createTopic(String bootstrapServers, String topicName, int partitions, short replication) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        AdminClient adminClient = null;
        try {
            adminClient = KafkaAdminClient.create(properties);
            NewTopic newTopic = new NewTopic(topicName, partitions, replication);
            CreateTopicsResult createTopicsResult = adminClient.createTopics(Lists.newArrayList(newTopic));
            createTopicsResult.all().get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (adminClient != null) {
                adminClient.close();
            }
        }
        return true;
    }



}
