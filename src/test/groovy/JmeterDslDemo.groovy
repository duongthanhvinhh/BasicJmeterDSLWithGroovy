import org.apache.http.entity.ContentType
import org.apache.jmeter.protocol.http.util.HTTPConstants
import org.junit.jupiter.api.Test
import us.abstracta.jmeter.javadsl.core.DslTestPlan
import us.abstracta.jmeter.javadsl.core.TestPlanStats

import static us.abstracta.jmeter.javadsl.JmeterDsl.*

class JmeterDslDemo {

    @Test
    void firstTest() {
        TestPlanStats testPlanStats = testPlan(threadGroup(2, 3,
                httpSampler("https://www.google.com").method(HTTPConstants.GET),
                httpSampler("https://www.google.com"),
                httpSampler("https://add-user").method(HTTPConstants.PATCH).contentType(ContentType.APPLICATION_JSON).body("provide your body here"),
                httpSampler("").method(HTTPConstants.DELETE)
        ),
                resultsTreeVisualizer()
        ).run()
    }

    @Test
    void generateJmxFile() {
        TestPlanStats testPlanStats = testPlan(threadGroup(2, 3,
                httpSampler("https://www.google.com"),
                httpSampler("https://www.google.com")
        )
        ).saveAsJmx("demo.jmx")

        DslTestPlan.fromJmx("demo.jmx").run()
    }
}
