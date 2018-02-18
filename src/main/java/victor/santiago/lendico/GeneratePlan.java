package victor.santiago.lendico;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

import victor.santiago.lendico.calculator.PlanCalculator;
import victor.santiago.lendico.model.GeneratePlanInput;
import victor.santiago.lendico.model.GeneratePlanOutput;

public class GeneratePlan implements RequestHandler<GeneratePlanInput, List<GeneratePlanOutput>> {

    public List<GeneratePlanOutput> handleRequest(GeneratePlanInput input, Context context) {
        PlanCalculator calculator = new PlanCalculator();
        return calculator.getPlan(input);
    }
}
