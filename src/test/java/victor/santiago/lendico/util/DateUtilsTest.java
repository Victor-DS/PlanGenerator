package victor.santiago.lendico.util;

import java.time.format.DateTimeParseException;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

    @Test
    public void shouldAddMonthsSuccessfully() {
        Assert.assertEquals("2018-02-01T00:00:01Z",
                DateUtils.addMonthsToDate("2018-01-01T00:00:01Z", 1));
        Assert.assertEquals("2019-01-01T00:00:01Z",
                DateUtils.addMonthsToDate("2018-01-01T00:00:01Z", 12));
    }

    @Test(expected = DateTimeParseException.class)
    public void shouldFailToParseInvalidDate() {
        DateUtils.addMonthsToDate("INVALID", 1);
    }
}
