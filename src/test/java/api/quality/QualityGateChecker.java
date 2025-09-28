package api.quality;

import java.util.ArrayList;
import java.util.List;

public class QualityGateChecker {
    private static final double MIN_SUCCESS_RATE = 0.80; // 80% минимум
    private static final long MAX_AVG_RESPONSE_TIME = 3000; // 3 секунды
    private static final long MAX_INDIVIDUAL_RESPONSE_TIME = 5000; // 5 секунд

    public QualityGateResult checkAllGates(TestMetricsCollector collector) {
        List<String> failedGates = new ArrayList<>();
        List<String> warnings = new ArrayList<>();

        double successRate = collector.calculateSuccessRate();
        long avgResponseTime = collector.getAverageResponseTime();

        System.out.println("🔍 Checking quality gates...");
        System.out.println("   Success Rate: " + String.format("%.2f", successRate * 100) + "%");
        System.out.println("   Avg Response Time: " + avgResponseTime + "ms");

        // Gate 1: Success Rate
        if (successRate < MIN_SUCCESS_RATE) {
            String error = String.format("Success rate too low: %.2f%% (minimum: %.2f%%)",
                    successRate * 100, MIN_SUCCESS_RATE * 100);
            failedGates.add(error);
            System.out.println("   ❌ " + error);
        } else {
            System.out.println("   ✅ Success rate gate passed");
        }

        // Gate 2: Average Response Time
        if (avgResponseTime > MAX_AVG_RESPONSE_TIME) {
            String error = String.format("Average response time too high: %dms (maximum: %dms)",
                    avgResponseTime, MAX_AVG_RESPONSE_TIME);
            failedGates.add(error);
            System.out.println("   ❌ " + error);
        } else {
            System.out.println("   ✅ Response time gate passed");
        }

        // Gate 3: Check for very slow individual tests
        List<TestMetricResult> slowTests = collector.getAllResults().stream()
                .filter(result -> result.getResponseTime() > MAX_INDIVIDUAL_RESPONSE_TIME)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        if (!slowTests.isEmpty()) {
            String warning = String.format("Found %d tests with response time > %dms",
                    slowTests.size(), MAX_INDIVIDUAL_RESPONSE_TIME);
            warnings.add(warning);
            System.out.println("   ⚠️  " + warning);
        }

        // Gate 4: Check for failed tests
        List<TestMetricResult> failedTests = collector.getFailedTests();
        if (!failedTests.isEmpty()) {
            String warning = String.format("Found %d failed tests", failedTests.size());
            warnings.add(warning);
            System.out.println("   ⚠️  " + warning);

            // Логируем информацию о неудачных тестах
            failedTests.forEach(failedTest ->
                    System.out.println("      - " + failedTest.getTestName() + ": " + failedTest.getErrorMessage()));
        }

        // Gate 5: Minimum test count
        if (collector.getTotalTestCount() < 3) {
            warnings.add("Low test count: " + collector.getTotalTestCount() + " tests executed");
        }

        return new QualityGateResult(failedGates.isEmpty(), failedGates, warnings, successRate, avgResponseTime);
    }
}