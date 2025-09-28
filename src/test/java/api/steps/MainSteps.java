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

    @Step("Получаем список вещей")
    public void listResources() {
        LOG.info(">>listSingleUsers");
        Response response = getListResources();
        debugLogResponseAndCheckStatus(response);
        LOG.debug("Ответ получен");
        LOG.info("<<listSingleUsers");
    }

    @Step("Получаем одну вещь по id")
    public void singleResource(int id) {
        LOG.info(">>singleResource");
        Response response = getSingleResource(id);
        debugLogResponseAndCheckStatus(response);
        LOG.debug("Ответ получен");
        LOG.info("<<singleResource");
    }

    @Step("Создаем пользователя")
    public void createSingleUser() {
        LOG.info(">>createSingleUser");
        Response response = postCreateUser();
        debugLogResponseAndCheckStatus(response);
        LOG.debug("Ответ получен");
        LOG.info("<<createSingleUser");
    }

    @Step("Обновляем статус пользователя")
    public void updateUser(int userId) {
        LOG.info(">>updateUser");
        Response response = putUpdateUser(userId);
        debugLogResponseAndCheckStatus(response);
        LOG.debug("Ответ получен");
        LOG.info("<<updateUser");
    }

    @Step("Частичное обновление статуса пользователя")
    public void patchUpdaterUser(int userId) {
        LOG.info(">>patchUpdaterUser");
        Response response = patchUpdateUser(userId);
        debugLogResponseAndCheckStatus(response);
        LOG.debug("Ответ получен");
        LOG.info("<<patchUpdaterUser");
    }

    @Step("Удаление пользователя")
    public void deleteSingleUser(int userId) {
        LOG.info(">>deleteSingleUser");
        Response response = deleteUser(userId);
        debugLogResponseAndCheckStatus(response);
        LOG.debug("Ответ получен");
        LOG.info("<<deleteSingleUser");
    }

    @Step("Регистрация пользователя")
    public void registeringUser() {
        LOG.info(">>registeringUser");
        Response response = registerUser();
        debugLogResponseAndCheckStatus(response);
        LOG.debug("Ответ получен");
        LOG.info("<<registeringUser");
    }

    @Step("Логирование пользователя")
    public void getLoginToken() {
        LOG.info(">>getLoginToken");
        Response response = postAndGetLoginToken();
        debugLogResponseAndCheckStatus(response);
        LOG.debug("Ответ получен");
        LOG.info("<<getLoginToken");
    }

    @Step("Получение отложенного запроса и списка пользователей")
    public void delayedResponse(int userId) {
        LOG.info(">>delayedResponse");
        Response response = getDelayedResponse(userId);
        debugLogResponseAndCheckStatus(response);
        LOG.debug("Ответ получен");
        LOG.info("<<delayedResponse");
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
        return request.when().get("/users/" + Integer.toString(number));
    }

    private Response getListResources() {
        RequestSpecification request = requestBaseBuilder
                .buildCollectingSpecification();
        if (LOG.isDebugEnabled()) {
            request.log().all();
        }
        return request.when().get("/unknown");
    }

    private Response getSingleResource(int id) {
        RequestSpecification request = requestBaseBuilder
                .buildCollectingSpecification();
        if (LOG.isDebugEnabled()) {
            request.log().all();
        }
        return request.when().get("/unknown/" + id);
    }

    private Response postCreateUser() {
        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        RequestSpecification request = requestBaseBuilder
                .buildCollectingSpecification();
        if (LOG.isDebugEnabled()) {
            request.log().all();
        }
        return request.body(body).when().post("/users");
    }

    private Response putUpdateUser(int userId) {
        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";
        RequestSpecification request = requestBaseBuilder
                .buildCollectingSpecification();
        if (LOG.isDebugEnabled()) {
            request.log().all();
        }
        return request.body(body).when().put("/users/" + userId);
    }

    private Response patchUpdateUser(int userId) {
        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";
        RequestSpecification request = requestBaseBuilder
                .buildCollectingSpecification();
        if (LOG.isDebugEnabled()) {
            request.log().all();
        }
        return request.body(body).when().patch("/users/" + userId);
    }

    private Response deleteUser(int userId) {
        RequestSpecification request = requestBaseBuilder
                .buildCollectingSpecification();
        if (LOG.isDebugEnabled()) {
            request.log().all();
        }
        return request.when().delete("/users/" + userId);
    }

    private Response registerUser() {
        String body = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";
        RequestSpecification request = requestBaseBuilder
                .buildCollectingSpecification();
        if (LOG.isDebugEnabled()) {
            request.log().all();
        }
        return request.body(body).when().post("/register");
    }

    private Response postAndGetLoginToken() {
        String body = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";
        RequestSpecification request = requestBaseBuilder
                .buildCollectingSpecification();
        if (LOG.isDebugEnabled()) {
            request.log().all();
        }
        return request.body(body).when().post("/login");
    }

    private Response getDelayedResponse(int userId) {
        RequestSpecification request = requestBaseBuilder
                .buildCollectingSpecification();
        if (LOG.isDebugEnabled()) {
            request.log().all();
        }
        return request.when().get("/users?delay=" + userId);
    }

    public void debugLogResponseAndCheckStatus(Response response) {
        if (LOG.isDebugEnabled()) {
            response.then().log().body().statusCode(200);
        }
        assertEquals(200, response.statusCode(), "Статус код не равен 200");
    }
}
