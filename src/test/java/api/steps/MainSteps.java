package api.steps;

import api.specification.RequestSpecificationBuilder;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainSteps {

    private static final Logger LOG = LoggerFactory.getLogger(MainSteps.class);
    private final RequestSpecificationBuilder requestBaseBuilder = new RequestSpecificationBuilder();

    @Step("Получаем список пользователей")
    public void listUsers() {
        LOG.info(">>listUsers");
        LOG.debug("Получаем список пользователей");
        Response response = getListUsers();
        debugLogResponseAndCheckStatus(response);
        LOG.debug("Ответ получен");
        LOG.info("<<listUsers");
    }
    @Step("Получаем одного определенного пользователя")
    public void listSingleUser(int number) {
        LOG.info(">>listSingleUser");
        Response response = getSingleUser(number);
        debugLogResponseAndCheckStatus(response);
        LOG.debug("Ответ получен");
        LOG.info("<<listSingleUser");
    }

    private Response getListUsers() {
        RequestSpecification request = requestBaseBuilder
                .buildCollectingSpecification();
        if (LOG.isDebugEnabled()) {
            request.log().all();
        }
        return request.when().get("/users?page=2");
    }
    private Response getSingleUser(int number) {
        RequestSpecification request = requestBaseBuilder
                .buildCollectingSpecification();
        if (LOG.isDebugEnabled()) {
            request.log().all();
        }
        return request.when().get("/users/" + number);
    }

    public void debugLogResponseAndCheckStatus(Response response) {
        if (LOG.isDebugEnabled()) {
            response.then().log().body().statusCode(200);
        }
        assertEquals(200, response.statusCode(), "Статус код не равен 200");
    }
}
