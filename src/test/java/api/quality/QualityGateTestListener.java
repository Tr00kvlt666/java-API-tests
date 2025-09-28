// QualityGateTestListener.java
package api.quality;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class QualityGateTestListener implements TestExecutionListener {
    private AtomicInteger testsStarted = new AtomicInteger(0);
    private AtomicInteger testsFinished = new AtomicInteger(0);

    @Override
    public void testPlanExecutionStarted(TestPlan testPlan) {
        System.out.println("🎯 Test execution started. Total tests: " +
                testPlan.countTestIdentifiers(TestIdentifier::isTest));
        TestMetricsCollector.getInstance().reset();
    }

    @Override
    public void executionStarted(TestIdentifier testIdentifier) {
        if (testIdentifier.isTest()) {
            testsStarted.incrementAndGet();
        }
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if (testIdentifier.isTest()) {
            testsFinished.incrementAndGet();

            // Здесь можно записывать результат теста более точно
            TestExecutionResult.Status status = testExecutionResult.getStatus();
            boolean passed = status == TestExecutionResult.Status.SUCCESSFUL;

            // Логируем результат теста
            System.out.println("📝 Test finished: " + testIdentifier.getDisplayName() +
                    " | Status: " + status);
        }
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
        TestMetricsCollector collector = TestMetricsCollector.getInstance();
        QualityGateChecker checker = new QualityGateChecker();

        System.out.println("\n📋 Test execution completed:");
        System.out.println("   Tests started: " + testsStarted.get());
        System.out.println("   Tests finished: " + testsFinished.get());
        System.out.println("   Total recorded in metrics: " + collector.getTotalTestCount());

        QualityGateResult result = checker.checkAllGates(collector);

        printQualityGateReport(result);

        if (!result.isPassed()) {
            System.err.println("❌ QUALITY GATES FAILED - CHECK THE REPORT ABOVE");
            // В CI/CD можно завершать с ошибкой:
            // System.exit(1);
        } else {
            System.out.println("✅ ALL QUALITY GATES PASSED");
        }
    }

    private void printQualityGateReport(QualityGateResult result) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📊 QUALITY GATES REPORT");
        System.out.println("=".repeat(60));
        System.out.printf("Overall Success Rate: %.2f%%\n", result.getSuccessRate() * 100);
        System.out.printf("Average Response Time: %dms\n", result.getAverageResponseTime());
        System.out.printf("Quality Gates Passed: %s\n", result.isPassed() ? "YES" : "NO");

        if (!result.getFailedGates().isEmpty()) {
            System.out.println("\n❌ FAILED GATES:");
            result.getFailedGates().forEach(gate -> System.out.println("  - " + gate));
        }

        if (!result.getWarnings().isEmpty()) {
            System.out.println("\n⚠️  WARNINGS:");
            result.getWarnings().forEach(warning -> System.out.println("  - " + warning));
        }

        System.out.println("=".repeat(60));
    }
}