package org.datacenter.kafka.manager.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class TopicInfo implements Serializable {

    private String topicName;

    private ArrayList<PartitionInfo> partitionInfoArrayList;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public ArrayList<PartitionInfo> getPartitionInfoArrayList() {
        return partitionInfoArrayList;
    }

    public void setPartitionInfoArrayList(ArrayList<PartitionInfo> partitionInfoArrayList) {
        this.partitionInfoArrayList = partitionInfoArrayList;
    }
}
