import org.helper.jdbctracer.TraceDriver;
import org.junit.Assert;
import org.junit.Test;

public class DriverTests {

    @Test
    public void getPrefix_has_fixed_value() {
       Assert.assertEquals("Must not change", "jdbctrace(", TraceDriver.getPrefix());
    }

    @Test
    public void setup_is_called_with_NONE_returns_unchanged() {
        String url = "jdbc:does_not:matter";

        Assert.assertEquals("Must not change", url, TraceDriver.setup(url, "NONE"));
    }

    @Test
    public void setup_is_called_with_NULL_returns_changed() {
        String url = "jdbc:does_not:matter";

        Assert.assertEquals("Must change", "jdbctrace()"+url, TraceDriver.setup(url, null));
    }

    @Test
    public void setup_is_called_with_INFO_returns_changed() {
        String url = "jdbc:does_not:matter";

        Assert.assertEquals("Must change", "jdbctrace(INFO)"+url, TraceDriver.setup(url, "INFO"));
    }

    @Test
    public void setup_is_called_with_NULL_URL_returns_null() {
        String url = "jdbc:does_not:matter";

        Assert.assertNull("Must change", TraceDriver.setup(null, "INFO"));
    }

    @Test
    public void setup_is_called_twice_returns_tracing_url() {
        String url = "jdbc:does_not:matter";

        Assert.assertEquals("Must change", "jdbctrace(INFO)"+url, TraceDriver.setup(TraceDriver.setup(url, "INFO"), "NONE"));
    }
}
