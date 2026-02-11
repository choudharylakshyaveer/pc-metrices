package com.pc.metricesconsumer;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public record SystemMetricsResponse(
    String cpuUsage,
    String totalMemory,
    String usedMemory,
    String availableMemory,
    String timestamp) {

  public static SystemMetricsResponse from(SystemMetrics m) {
    return new SystemMetricsResponse(
        formatCpu(m.cpuUsage()),
        formatBytes(m.totalMemory()),
        formatBytes(m.usedMemory()),
        formatBytes(m.availableMemory()),
        formatTime(m.timestamp()));
  }

  private static String formatCpu(double cpu) {
    return String.format("%.2f %%", cpu);
  }

  private static String formatBytes(long bytes) {
    double gb = bytes / (1024.0 * 1024 * 1024);
    return String.format("%.2f GB", gb);
  }

  private static String formatTime(long epochMillis) {
    return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        .withZone(ZoneId.systemDefault())
        .format(Instant.ofEpochMilli(epochMillis));
  }
}
