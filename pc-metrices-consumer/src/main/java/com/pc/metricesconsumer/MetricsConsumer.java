package com.pc.metricesconsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class MetricsConsumer {

  private final ObjectMapper objectMapper;
  private final LiveMetricsStore store;

  public MetricsConsumer(ObjectMapper objectMapper, LiveMetricsStore store) {
    this.objectMapper = objectMapper;
    this.store = store;
  }

  @KafkaListener(topics = "${metrics.topic}")
  public void consume(String message) {
    try {
      SystemMetrics metrics = objectMapper.readValue(message, SystemMetrics.class);
      store.update(metrics);
    } catch (Exception e) {
      throw new RuntimeException("Failed to consume metrics", e);
    }
  }
}
