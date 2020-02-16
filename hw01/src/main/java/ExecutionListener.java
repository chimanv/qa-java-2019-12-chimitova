import db.ResultSender;
import org.influxdb.dto.Point;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.support.descriptor.MethodSource;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

import java.util.concurrent.TimeUnit;

public class ExecutionListener implements TestExecutionListener {

    private long startTestTime;

    public void executionStarted(TestIdentifier testIdentifier) {
        if (testIdentifier.getType().isTest()) {
            startTestTime = System.currentTimeMillis();
        }
    }

    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if (testIdentifier.getType().isTest()) {
            Point point = Point.measurement("testmethod")
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .addField("testclass", ((MethodSource) testIdentifier.getSource().get()).getClassName())
                    .addField("name", testIdentifier.getDisplayName())
                    .addField("result", testExecutionResult.getStatus().name())
                    .addField("duration", (System.currentTimeMillis() - startTestTime))
                    .build();
            ResultSender.send(point);
        }
    }
}
