import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

public class ExecuteWithRunListener {

    private void runOne() {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectClass(ParametersTest.class))
                .build();
        Launcher launcher = LauncherFactory.create();
        launcher.registerTestExecutionListeners(new ExecutionListener());
        launcher.execute(request);
    }

    public static void main(String[] args) {
        ExecuteWithRunListener runner = new ExecuteWithRunListener();
        runner.runOne();
    }
}
