package api.scenarios;

import api.steps.MainSteps;
import io.qameta.allure.Step;

public class MainTestHelper {
    private final MainSteps mainSteps;

    public MainTestHelper() {
        this.mainSteps = new MainSteps();
    }

    @Step("Сценарий получения списка пользователей")
    public void listUsersScenario() {
        mainSteps.listUsers();
    }
    public void listSingleUserScenario(int number) {
        mainSteps.listSingleUser(number);
    }
    public void listResources(int id) {
        mainSteps.listResources();
        mainSteps.singleResource(id);
    }
    public void createAndUpdateStatusUser(int id) {
        mainSteps.createSingleUser();
        mainSteps.updateUser(id); //доработать регистрацию определенного юзера и его обновление
    }
    public void createAndPatchUpdateUser(int id) {
        mainSteps.createSingleUser();
        mainSteps.patchUpdaterUser(id); //доработать регистрацию определенного юзера и его обновление
    }
    public void createAndDeleteUser(int id) {
        mainSteps.createSingleUser();
        mainSteps.deleteSingleUser(id); //доработать регистрацию определенного юзера и его удаление
    }
    public void registerAndLoginUser() {
        mainSteps.registeringUser();
        mainSteps.getLoginToken(); //доработать регистрацию определенного юзера и его логирование
    }
    public void getListOfRegisteredUsers(int id) {
        mainSteps.delayedResponse(id); //попробовать взять зарегистрированного пользователя и снова зарегать
    }

}
