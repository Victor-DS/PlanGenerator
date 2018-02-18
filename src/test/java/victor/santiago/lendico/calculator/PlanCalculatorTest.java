package victor.santiago.lendico.calculator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import victor.santiago.lendico.model.GeneratePlanInput;
import victor.santiago.lendico.model.GeneratePlanOutput;

public class PlanCalculatorTest {

    private static final double DELTA = 0.01;

    private PlanCalculator toTest;

    @Before
    public void setup() {
        toTest = new PlanCalculator();
    }

    @Test
    public void shouldGetListOfOutputPlans() {
        GeneratePlanInput input = new GeneratePlanInput();
        input.setDuration(24);
        input.setStartDate("2018-01-01T00:00:00Z");
        input.setNominalRate(5.0);
        input.setLoanAmount(5000);

        List<GeneratePlanOutput> outputList = toTest.getPlan(input);

        Assert.assertEquals(24, outputList.size());

        Assert.assertEquals("2018-01-01T00:00:00Z", outputList.get(0).getDate());
        Assert.assertEquals("2018-02-01T00:00:00Z", outputList.get(1).getDate());

        Assert.assertEquals(198.53, outputList.get(0).getPrincipal(), DELTA);
        Assert.assertEquals(199.36, outputList.get(1).getPrincipal(), DELTA);

        Assert.assertEquals(20.83, outputList.get(0).getInterest(), DELTA);
        Assert.assertEquals(20, outputList.get(1).getInterest(), DELTA);

        Assert.assertEquals(219.36, outputList.get(0).getBorrowerPaymentAmount(), DELTA);
        Assert.assertEquals(219.36, outputList.get(1).getBorrowerPaymentAmount(), DELTA);

        Assert.assertEquals(5000, outputList.get(0).getInitialOutstandingPrincipal(), DELTA);
        Assert.assertEquals(4801.47, outputList.get(1).getInitialOutstandingPrincipal(), DELTA);
    }
}
