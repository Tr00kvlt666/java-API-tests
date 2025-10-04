package api.tests;

import api.scenarios.MainTestHelper;
import api.quality.QualityGate;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Epic("API Тесты для reqres.in")
@Feature("Управление пользователями и ресурсами")
@DisplayName("API Тесты для сервиса reqres.in")
public class MainTests extends BaseApiTest {
    private MainTestHelper mainTestHelper;

    @BeforeEach
    public void setUp() {
        mainTestHelper = new MainTestHelper();
    }

    @Order(1)
    @Test
    @DisplayName("Проверка запроса для получения пользователей")
    @QualityGate(
            name = "List Users Performance",
            maxResponseTime = 1000,
            maxIndividualResponseTime = 2000,
            critical = true
    )
    public void listUsers_test() {
        mainTestHelper.listUsersScenario();
    }

    @Order(2)
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("Проверка запроса для получения одного пользователя")
    @QualityGate(
            name = "Single User Performance",
            maxResponseTime = 800,
            maxIndividualResponseTime = 1500
    )
    public void listSingleUser_test(int number) {
        mainTestHelper.listSingleUserScenario(number);
    }
}