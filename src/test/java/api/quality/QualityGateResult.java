package api.quality;

import java.util.List;

public class QualityGateResult {
    private final boolean passed;
    private final List<String> failedGates;
    private final List<String> warnings;
    private final double successRate;
    private final long averageResponseTime;

    public QualityGateResult(boolean passed, List<String> failedGates, List<String> warnings,
                             double successRate, long averageResponseTime) {
        this.passed = passed;
        this.failedGates = failedGates;
        this.warnings = warnings;
        this.successRate = successRate;
        this.averageResponseTime = averageResponseTime;
    }

    // Getters
    public boolean isPassed() { return passed; }
    public List<String> getFailedGates() { return failedGates; }
    public List<String> getWarnings() { return warnings; }
    public double getSuccessRate() { return successRate; }
    public long getAverageResponseTime() { return averageResponseTime; }
}