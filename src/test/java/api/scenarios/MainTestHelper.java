package api.scenarios;

import api.steps.MainSteps;

public class MainTestHelper {
    private final MainSteps mainSteps;

    public MainTestHelper() {
        this.mainSteps = new MainSteps();
    }
    public void listUsersScenario() {
        mainSteps.listUsers();
    }
}
