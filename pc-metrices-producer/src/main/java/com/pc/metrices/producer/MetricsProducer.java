package com.pc.metrices.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class MetricsProducer {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final SystemMetricsSerializer serializer;

    public MetricsProducer(
            KafkaTemplate<String, byte[]> kafkaTemplate,
            SystemMetricsSerializer serializer
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.serializer = serializer;
    }

    public void publish(SystemMetrics metrics)
            throws ExecutionException, InterruptedException {

        byte[] payload = serializer.serialize(metrics);

        SendResult<String, byte[]> result =
                kafkaTemplate
                        .send("system-metrics", "pc-metrics", payload)
                        .get();

        log.info("Topic: {}", result.getRecordMetadata().topic());
        log.info("Partition: {}", result.getRecordMetadata().partition());
        log.info("Offset: {}", result.getRecordMetadata().offset());
    }
}
