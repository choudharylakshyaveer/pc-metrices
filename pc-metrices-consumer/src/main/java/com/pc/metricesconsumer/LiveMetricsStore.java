package com.pc.metricesconsumer;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

@Component
public class LiveMetricsStore {

    private final AtomicReference<SystemMetrics> latest =
            new AtomicReference<>();

    public void update(SystemMetrics metrics) {
        latest.set(metrics);
    }

    public SystemMetrics getLatest() {
        return latest.get();
    }
}
