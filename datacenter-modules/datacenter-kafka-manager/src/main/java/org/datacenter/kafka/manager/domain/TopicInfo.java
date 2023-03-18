package org.datacenter.kafka.manager.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author liuxiaoxi
 */
public class TopicInfo implements Serializable {

    private String Name;

    private ArrayList<PartitionInfo> partitionInfoArrayList;

    public String getTopicName() {
        return Name;
    }

    public void setTopicName(String topicName) {
        this.Name = topicName;
    }

    public ArrayList<PartitionInfo> getPartitionInfoArrayList() {
        return partitionInfoArrayList;
    }

    public void setPartitionInfoArrayList(ArrayList<PartitionInfo> partitionInfoArrayList) {
        this.partitionInfoArrayList = partitionInfoArrayList;
    }
}
