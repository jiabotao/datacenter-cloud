package org.datacenter.kafka.manager.domain;

import java.util.List;

/**
 * @author liuxiaoxi
 */

public class PartitionInfo {

    private Integer partition;


    private String leaderHost;

    private Integer leaderPort;


    private List<String> isr;

    private Long offset;

    private Long endOffset;

    public String getLeaderHost() {
        return leaderHost;
    }

    public void setLeaderHost(String leaderHost) {
        this.leaderHost = leaderHost;
    }

    public Integer getLeaderPort() {
        return leaderPort;
    }

    public void setLeaderPort(Integer leaderPort) {
        this.leaderPort = leaderPort;
    }

    public Integer getPartition() {
        return partition;
    }

    public void setPartition(Integer partition) {
        this.partition = partition;
    }

    public List<String> getIsr() {
        return isr;
    }

    public void setIsr(List<String> isr) {
        this.isr = isr;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getEndOffset() {
        return endOffset;
    }

    public void setEndOffset(Long endOffset) {
        this.endOffset = endOffset;
    }
}
