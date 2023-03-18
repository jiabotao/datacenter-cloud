package org.datacenter.kafka.manager.utils;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;

import java.util.Collection;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

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
}
