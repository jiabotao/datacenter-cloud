package org.datacenter.kafka.manager.domain;

/**
 * @author liuxiaoxi
 */

public class PartitionInfo {

    private Integer partition;

    private String host;

    private Integer port;

    private Boolean isr;

    private Long offset;

    private Long endOffset;

    public Integer getPartition() {
        return partition;
    }

    public void setPartition(Integer partition) {
        this.partition = partition;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Boolean getIsr() {
        return isr;
    }

    public void setIsr(Boolean isr) {
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
