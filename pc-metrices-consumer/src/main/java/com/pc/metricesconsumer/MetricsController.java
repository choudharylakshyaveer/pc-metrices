package com.pc.metricesconsumer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/metrics")
public class MetricsController {

  private final LiveMetricsStore store;

  public MetricsController(LiveMetricsStore store) {
    this.store = store;
  }

  @GetMapping("/live")
  public ResponseEntity<SystemMetricsResponse> liveMetrics() {

    SystemMetrics metrics = store.getLatest();

    if (metrics == null) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(SystemMetricsResponse.from(metrics));
  }
}
