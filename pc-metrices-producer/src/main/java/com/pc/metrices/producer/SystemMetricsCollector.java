package com.pc.metrices.producer;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import org.springframework.stereotype.Component;

@Component
public class SystemMetricsCollector {

  private final CentralProcessor cpu;
  private final GlobalMemory memory;
  private long[] previousTicks;

  public SystemMetricsCollector() {
    SystemInfo systemInfo = new SystemInfo();
    this.cpu = systemInfo.getHardware().getProcessor();
    this.memory = systemInfo.getHardware().getMemory();
    this.previousTicks = cpu.getSystemCpuLoadTicks();
  }

  public SystemMetrics collect() {

    double cpuUsage = cpu.getSystemCpuLoadBetweenTicks(previousTicks) * 100;
    previousTicks = cpu.getSystemCpuLoadTicks();

    long total = memory.getTotal();
    long available = memory.getAvailable();
    long used = total - available;

    return new SystemMetrics(cpuUsage, total, used, available, System.currentTimeMillis());
  }
}
