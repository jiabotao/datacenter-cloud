package org.datacenter.kafka.manager;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.datacenter.kafka.manager.utils.KafkaUtil;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String bootstrapServers=  "localhost:9092";
        KafkaUtil.getKafkaInfo(bootstrapServers);

    }
}
