package victor.santiago.lendico.model;

import lombok.Data;

@Data
public class GeneratePlanInput {
    private double loanAmount, nominalRate;
    private int duration;
    private String startDate;
}
