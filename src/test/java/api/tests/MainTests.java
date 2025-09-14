package api.tests;

import api.scenarios.MainTestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MainTests {
    private  MainTestHelper mainTestHelper;
    private int number;

    @Order(1)
    @Test
    @DisplayName("Проверка запроса для получения пользователей")
    public void listUsers_test() {
        mainTestHelper = new MainTestHelper();
        mainTestHelper.listUsersScenario();
    }
    @Order(2)
    @Test
    @DisplayName("Проверка запроса для получения одного пользователя")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 2, 6, 7, 8, 9, 10})
    public void listSingleUser_test(int number) {
        this.number = number;
        mainTestHelper = new MainTestHelper();
        mainTestHelper.listSingleUserScenario(number);
    }
}
