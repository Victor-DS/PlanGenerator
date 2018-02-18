package victor.santiago.lendico.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeneratePlanOutput {
    private double borrowerPaymentAmount, initialOutstandingPrincipal, interest,
            principal, remainingOutstandingPrincipal;
    private String date;
}
