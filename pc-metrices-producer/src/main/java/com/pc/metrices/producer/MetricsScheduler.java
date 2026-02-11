package com.pc.metrices.producer;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@EnableScheduling
public class MetricsScheduler {

  private final SystemMetricsCollector collector;
  private final MetricsProducer producer;

  public MetricsScheduler(SystemMetricsCollector collector, MetricsProducer producer) {
    this.collector = collector;
    this.producer = producer;
  }

  @Scheduled(fixedRate = 1000)
  public void publishMetrics() throws ExecutionException, InterruptedException {
    SystemMetrics metrics = collector.collect();
    producer.publish(metrics);
  }
}
