package api.tests;

import api.quality.QualityGate;
import api.quality.QualityGateChecker;
import api.quality.QualityGateResult;
import api.quality.TestMetricsCollector;
import api.scenarios.MainTestHelper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MainTests extends BaseApiTest {
    private MainTestHelper mainTestHelper;

    @BeforeEach
    public void setUp() {
        mainTestHelper = new MainTestHelper();
    }

    @Order(1)
    @Test
    @DisplayName("Проверка запроса для получения пользователей")
    @QualityGate(maxResponseTime = 1500, critical = true)
    public void listUsers_test() {
        mainTestHelper.listUsersScenario();
    }

    @Order(2)
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("Проверка запроса для получения одного пользователя")
    @QualityGate(minSuccessRate = 0.90, maxResponseTime = 2300)
    public void listSingleUser_test(int number) {
        mainTestHelper.listSingleUserScenario(number);
    }
    @AfterAll
    public static void finalQualityCheck() {
        TestMetricsCollector collector = TestMetricsCollector.getInstance();
        QualityGateChecker checker = new QualityGateChecker();
        QualityGateResult result = checker.checkAllGates(collector);

        if (!result.isPassed()) {
            System.err.println("🔴 FINAL QUALITY CHECK FAILED");
        } else {
            System.out.println("🟢 FINAL QUALITY CHECK PASSED");
        }
    }

}