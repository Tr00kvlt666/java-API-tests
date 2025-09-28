package api.quality;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TestMetricsCollector {
    private static TestMetricsCollector instance;
    private ConcurrentHashMap<String, TestMetricResult> results = new ConcurrentHashMap<>();
    private AtomicLong totalResponseTime = new AtomicLong(0);
    private AtomicLong totalTests = new AtomicLong(0);
    private AtomicLong passedTests = new AtomicLong(0);

    public static synchronized TestMetricsCollector getInstance() {
        if (instance == null) {
            instance = new TestMetricsCollector();
        }
        return instance;
    }

    public void recordTestResult(String testName, boolean passed, long responseTime, String errorMessage) {
        TestMetricResult result = new TestMetricResult(testName, passed, responseTime, errorMessage);
        results.put(testName, result);

        totalTests.incrementAndGet();
        totalResponseTime.addAndGet(responseTime);
        if (passed) {
            passedTests.incrementAndGet();
        }

        System.out.println("📊 Test recorded: " + testName + " | Passed: " + passed + " | Response time: " + responseTime + "ms");
    }

    public double calculateSuccessRate() {
        if (totalTests.get() == 0) return 0.0;
        return (double) passedTests.get() / totalTests.get();
    }

    public long getAverageResponseTime() {
        if (totalTests.get() == 0) return 0;
        return totalResponseTime.get() / totalTests.get();
    }

    public List<TestMetricResult> getFailedTests() {
        return results.values().stream()
                .filter(result -> !result.isPassed())
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public List<TestMetricResult> getAllResults() {
        return new ArrayList<>(results.values());
    }

    public int getTotalTestCount() {
        return totalTests.intValue();
    }

    public int getPassedTestCount() {
        return passedTests.intValue();
    }

    public int getFailedTestCount() {
        return totalTests.intValue() - passedTests.intValue();
    }

    public void reset() {
        results.clear();
        totalResponseTime.set(0);
        totalTests.set(0);
        passedTests.set(0);
    }
}