package org.datacenter.kafka.manager.utils;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.TopicPartition;

import java.util.*;

public class KafkaUtil {

    public static Set<String> listTopics(String bootstrapServers) {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
        AdminClient adminClient = AdminClient.create(props);
        ListTopicsResult listTopicsResult = adminClient.listTopics();
        KafkaFuture<Set<String>> kafkaFutureNames = listTopicsResult.names();
        Set<String> topicNameSet = null;
        try {
            topicNameSet = kafkaFutureNames.get();
        } catch (Exception e) {

        } finally {
            if (adminClient != null) {
                adminClient.close();
            }
        }
        return topicNameSet;
    }

    public static boolean createTopic(String bootstrapServers,String topicName,int partitions, short replication){
        boolean created = false;
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
            created = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (adminClient != null) {
                adminClient.close();
            }
        }
        return created;
    }

    public static boolean deleteTopic(String bootstrapServers,String topicName){
        boolean deleted = false;
        Set<String> topicNameSet = new HashSet<>();
        topicNameSet.add(topicName);

        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);
        AdminClient adminClient = KafkaAdminClient.create(properties);
        try{
            DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(topicNameSet);
            deleteTopicsResult.all().get();
            deleted = true;
        }catch (Exception e){

        }
        return deleted;
    }

    public static long getLogEndOffset(String bootstrapServers, TopicPartition topicPartition){

        Properties props = new Properties();

        // 必须设置的属性
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer= new KafkaConsumer<>(props);
        consumer.assign(Arrays.asList(topicPartition));
        consumer.seekToEnd(Arrays.asList(topicPartition));
        long endOffset = consumer.position(topicPartition);
        return endOffset;
    }


}
