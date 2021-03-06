package caryseller;

public class RolloverAtDifferentTimesWithNoTaxableMoneyAvailableToPayTax {
    static double STATE_TAX_RATE = 4.99;
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("1 argument (tax rate at time of withdrawal) is needed.");
            System.exit(1);
        }
        double widTR = Double.parseDouble(args[0]);
        double cashoutMarginalTaxRate = (widTR + STATE_TAX_RATE) / 100;
        double STARTING_BALANCE = 1000000;
        double YEARLY_ROLLOVER = 100000;
        double YEARLY_ROLLOVER_AFTER_TAXES = YEARLY_ROLLOVER * (1-cashoutMarginalTaxRate);
        double planBal = STARTING_BALANCE; // the balance in the regular 401k plan
        double rothBal = 0; // the balance in the Roth 401k plan
        double ROI = .05;
        int N = 10;
        for (int i = 1; i <= N; i++) {
            // after year i, assuming withdrawal at the end of the year and tax paid at the same time
            planBal *= (1.0 + ROI);
            rothBal *= (1.0 + ROI);
            planBal -= YEARLY_ROLLOVER;
            rothBal += YEARLY_ROLLOVER_AFTER_TAXES;
            double planAfterTaxValue = planBal * (1-cashoutMarginalTaxRate);

            System.out.printf("After year %2d:  Balance in reg 401K: %.2f, Balance in Roth 401K: %.2f, Total after tax value: %.2f%n", i, planBal, rothBal, planAfterTaxValue + rothBal);
        }

        System.out.printf("%nRollover all right at the start: move all to Roth after paying taxes and then compound over %d years:%n", N);
        // Rollover all right at the start: move all to Roth after paying taxes and then compound over 10 years
        rothBal = STARTING_BALANCE * (1-cashoutMarginalTaxRate);
        for (int i = 1; i <= N; i++) {
            rothBal *= (1.0 + ROI);
            System.out.printf("After year %2d:  Balance in Roth 401K: %.2f%n", i, rothBal);
        }

        System.out.printf("%nCompound in the regular for %d years and then rollover all at the end:%n", N);
        // Compound in the regular for 10 years and then rollover all at the end
        planBal = STARTING_BALANCE;
        for (int i = 1; i <= N; i++) {
            planBal *= (1.0 + ROI);
            double planAfterTaxValue = planBal * (1-cashoutMarginalTaxRate);
            System.out.printf("After year %2d:  After tax value: %.2f%n", i, planAfterTaxValue);
        }

    }
}
