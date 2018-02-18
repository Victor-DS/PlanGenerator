package victor.santiago.lendico.calculator;

import java.util.ArrayList;
import java.util.List;

import victor.santiago.lendico.model.GeneratePlanInput;
import victor.santiago.lendico.model.GeneratePlanOutput;
import victor.santiago.lendico.util.DateUtils;

public class PlanCalculator {

    private static final int DAYS_IN_MONTH = 30;
    private static final int MONTHS_IN_YEAR = 12;
    private static final int DAYS_IN_YEAR = DAYS_IN_MONTH * MONTHS_IN_YEAR;

    public List<GeneratePlanOutput> getPlan(GeneratePlanInput input) {
        List<GeneratePlanOutput> response = new ArrayList<>();

        double lastOutstandingPrincipal = input.getLoanAmount();

        GeneratePlanOutput output;
        for (int months = 0; months < input.getDuration(); months++) {
            output = getGeneratedPlan(input, lastOutstandingPrincipal, months);
            lastOutstandingPrincipal = output.getRemainingOutstandingPrincipal();
            response.add(output);
        }

        return response;
    }

    private GeneratePlanOutput getGeneratedPlan(GeneratePlanInput input, double lastOutstandingPrincipal, int months) {
        double annuity = getAnnuity(input.getNominalRate(), input.getLoanAmount(), input.getDuration());
        double interest = getInterest(input.getNominalRate(), DAYS_IN_MONTH, lastOutstandingPrincipal, DAYS_IN_YEAR);
        double principal = getPrincipal(annuity, interest, lastOutstandingPrincipal);

        return GeneratePlanOutput.builder()
                          .date(DateUtils.addMonthsToDate(input.getStartDate(), months))
                          .borrowerPaymentAmount(annuity)
                          .principal(principal)
                          .interest(interest)
                          .initialOutstandingPrincipal(Math.max(lastOutstandingPrincipal, 0.00))
                          .remainingOutstandingPrincipal(Math.max(lastOutstandingPrincipal - principal, 0.00))
                          .build();
    }

    private double getPrincipal(double annuity, double interest, double initialOutstandingPrincipal) {
        final double principal = getPrincipal(annuity, interest);
        return interest > initialOutstandingPrincipal ? initialOutstandingPrincipal : principal;
    }

    private double getPrincipal(double annuity, double interest) {
        return annuity - interest;
    }

    private double getInterest(double nominalRate, int daysInMonth,
                               double initialOutstandingPrincipal, int daysInYear) {
        final double rateInPercent = nominalRate / 100;
        return (rateInPercent * daysInMonth * initialOutstandingPrincipal) / daysInYear;
    }

    private double getAnnuity(double ratePerPeriod, double presentValue, int numberOfPeriods) {
        final double rateInPercent = ratePerPeriod / (100 * MONTHS_IN_YEAR);
        return (rateInPercent * presentValue) / (1 - Math.pow((1+rateInPercent), -numberOfPeriods));
    }
}
