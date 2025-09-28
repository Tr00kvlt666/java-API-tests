package api.tests;

import api.quality.TestMetricsCollector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public class BaseApiTest {
    protected TestMetricsCollector metrics = TestMetricsCollector.getInstance();
    private long testStartTime;
    private String currentTestName;

    @BeforeEach
    public void startTestTimer(TestInfo testInfo) {
        testStartTime = System.currentTimeMillis();
        currentTestName = testInfo.getDisplayName();
        System.out.println("🚀 Starting test: " + currentTestName);
    }

    @AfterEach
    public void collectTestMetrics(TestInfo testInfo) {
        long responseTime = System.currentTimeMillis() - testStartTime;
        boolean testPassed = true;
        try {
            metrics.recordTestResult(
                    currentTestName,
                    testPassed,
                    responseTime,
                    testPassed ? null : "Test failed - check logs for details"
            );

            String status = testPassed ? "✅ PASSED" : "❌ FAILED";
            System.out.println(String.format("%s Test finished: %s | Time: %dms",
                    status, currentTestName, responseTime));

        } catch (Exception e) {
            System.err.println("Error recording test metrics: " + e.getMessage());
        }
    }
}