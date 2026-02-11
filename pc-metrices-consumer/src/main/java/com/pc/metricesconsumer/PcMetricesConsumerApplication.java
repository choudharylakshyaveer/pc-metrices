package com.pc.metricesconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class PcMetricesConsumerApplication {

  static void main(String[] args) {
    SpringApplication.run(PcMetricesConsumerApplication.class, args);
  }
}
