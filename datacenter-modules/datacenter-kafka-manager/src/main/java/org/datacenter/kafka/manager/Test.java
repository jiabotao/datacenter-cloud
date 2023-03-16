package org.datacenter.kafka.manager;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String bootstrapServers=  "localhost:9092";
        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        AdminClient adminClient = null;

        adminClient = KafkaAdminClient.create(properties);
        NewTopic newTopic = new NewTopic("aaa", 1, (short) 1);
        CreateTopicsResult createTopicsResult = adminClient.createTopics(Lists.newArrayList(newTopic));


        System.out.println(createTopicsResult.all().get());

    }
}
