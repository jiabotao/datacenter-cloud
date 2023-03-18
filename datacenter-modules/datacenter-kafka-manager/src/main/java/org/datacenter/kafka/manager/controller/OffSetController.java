package org.datacenter.kafka.manager.controller;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.datacenter.common.core.domain.http.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/kafka/offset")
public class OffSetController {

    @PostMapping("/getConsumerGroupList")
    public R  getConsumerGroupList(@RequestBody Map<String,String> params){
        String default_bootstrap_servers=  "localhost:9092";
        String bootstrap_servers = params.getOrDefault("bootstrap.servers",default_bootstrap_servers);
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
        props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
        AdminClient client = AdminClient.create(props);
        ListConsumerGroupsResult consumerGroupsResult = client.listConsumerGroups();
        ArrayList<String> list = new ArrayList<>();
        try{
            Collection<ConsumerGroupListing> consumerGroupList = consumerGroupsResult.all().get();
            for(ConsumerGroupListing group : consumerGroupList){
                System.out.println(group.toString());
                list.add(group.toString());
            }
            return R.ok(list);
        }catch (Exception e){
            return R.fail();
        }
    }

    @PostMapping("/getTopicDetail")
    public R getTopicDetail(@RequestBody Map<String,String> params){
        String default_bootstrap_servers=  "localhost:9092";
        String default_groupId = "testgroup";
        String bootstrap_servers = params.getOrDefault("bootstrap.servers",default_bootstrap_servers);
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
        props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
        AdminClient client = AdminClient.create(props);

        String groupId = params.getOrDefault("groupId",default_bootstrap_servers);

        ListConsumerGroupOffsetsResult listConsumerGroupOffsetsResult = client.listConsumerGroupOffsets(groupId);

        try {

            Map<String, Map<TopicPartition, OffsetAndMetadata>> consumerGroupOffsetsResultMap = listConsumerGroupOffsetsResult.all().get();

            for (String key : consumerGroupOffsetsResultMap.keySet()) {
                System.out.println(key);
                Map<TopicPartition, OffsetAndMetadata> topicPartitionMap = consumerGroupOffsetsResultMap.get(key);
                for(TopicPartition topicPartition:topicPartitionMap.keySet()){
                    System.out.println(getLogEndOffset(default_bootstrap_servers,topicPartition));
                }
            }

            return R.ok("aa");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
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
