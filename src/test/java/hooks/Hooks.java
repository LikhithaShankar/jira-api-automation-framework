package hooks;

import io.cucumber.java.Before;
import utils.BaseTest;

public class Hooks extends BaseTest {

    @Before
    public void beforeScenario() {
        setup();
    }
}