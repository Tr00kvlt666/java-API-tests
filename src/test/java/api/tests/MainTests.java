package api.tests;

import api.scenarios.MainTestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class MainTests {
    private  MainTestHelper mainTestHelper;

    @Order(1)
    @Test
    @DisplayName("Проверка запроса для получения пользователей")
    public void listUsers_test() {
        mainTestHelper = new MainTestHelper();
        mainTestHelper.listUsersScenario();
    }
}
