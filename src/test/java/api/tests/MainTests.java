package api.tests;

import api.scenarios.MainTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MainTests {
    private MainTestHelper mainTestHelper;

    @BeforeEach
    public void setUp() {
        mainTestHelper = new MainTestHelper();
    }

    @Order(1)
    @Test
    @DisplayName("Проверка запроса для получения пользователей")
    public void listUsers_test() {
        mainTestHelper.listUsersScenario();
    }

    @Order(2)
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 2, 6, 7, 8, 9, 10})
    @DisplayName("Проверка запроса для получения одного пользователя")
    public void listSingleUser_test(int number) {
        mainTestHelper.listSingleUserScenario(number);
    }

    @Order(3)
    @Test
    @DisplayName("Проверка списка вещей и получения одной вещи из списка")
    public void listUsers1_test() {
        mainTestHelper.listResources(3); //доработать куки
    }
}