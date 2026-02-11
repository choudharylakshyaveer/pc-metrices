package com.pc.metricesconsumer;

public record SystemMetrics(
        double cpuUsage,
        long totalMemory,
        long usedMemory,
        long availableMemory,
        long timestamp
) {}

