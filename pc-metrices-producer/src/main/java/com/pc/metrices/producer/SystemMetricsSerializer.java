package com.pc.metrices.producer;

import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class SystemMetricsSerializer {

    private final ObjectMapper objectMapper;

    public SystemMetricsSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public byte[] serialize(SystemMetrics metrics) {
        try {
            return objectMapper.writeValueAsBytes(metrics);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to serialize SystemMetrics", e);
        }
    }
}
