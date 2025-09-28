package api.quality;

public class TestResult {
    private String testName;
    private boolean passed;
    private long responseTime;
    private String errorMessage;
    private long timestamp;

    public TestResult(String testName, boolean passed, long responseTime, String errorMessage) {
        this.testName = testName;
        this.passed = passed;
        this.responseTime = responseTime;
        this.errorMessage = errorMessage;
        this.timestamp = System.currentTimeMillis();
    }

    // Getters
    public String getTestName() { return testName; }
    public boolean isPassed() { return passed; }
    public long getResponseTime() { return responseTime; }
    public String getErrorMessage() { return errorMessage; }
    public long getTimestamp() { return timestamp; }
}