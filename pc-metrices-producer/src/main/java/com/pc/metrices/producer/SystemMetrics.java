package com.pc.metrices.producer;

public record SystemMetrics(
        double cpuUsage,
        long totalMemory,
        long usedMemory,
        long availableMemory,
        long timestamp
) {}
