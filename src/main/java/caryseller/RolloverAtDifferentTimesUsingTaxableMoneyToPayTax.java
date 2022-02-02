package caryseller;

public class RolloverAtDifferentTimesUsingTaxableMoneyToPayTax {
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
        double ROLLOVER_TAXES = YEARLY_ROLLOVER * cashoutMarginalTaxRate;
        double planBal = STARTING_BALANCE; // the balance in the regular 401k plan
        double taxableBal = STARTING_BALANCE; // assume same balance the taxable account - as long it can cover
                                              // taxes it should not matter what it is since we're
                                              // curious about differences in when money is withdrawan.
        double rothBal = 0; // the balance in the Roth 401k plan
        double ROI = .05;
        int N = 10;
        for (int i = 1; i <= N; i++) {
            // after year i, assuming withdrawal and taxes at the end of the year
            planBal *= (1.0 + ROI);
            rothBal *= (1.0 + ROI);
            planBal -= YEARLY_ROLLOVER;
            rothBal += YEARLY_ROLLOVER;
            double taxableBalanceEarningsAfterTax = taxableBal * ROI * (1-cashoutMarginalTaxRate);
            taxableBal += taxableBalanceEarningsAfterTax;
            taxableBal -= ROLLOVER_TAXES;
            double planAfterTaxValue = planBal * (1-cashoutMarginalTaxRate);
            System.out.printf("After year %2d:  Balance in reg 401K: %.2f, Balance in Roth 401K: %.2f, Balance in taxable acct: %.2f, Total after tax value: %.2f%n", i, planBal, rothBal, taxableBal, planAfterTaxValue + rothBal + taxableBal);
            System.out.printf(""); // on a separate line so that one can break in the debugger
        }

        System.out.printf("%nRollover all right at the start of the %d years: move all to Roth after paying taxes and then compound over 10 years:%n", N);
        // Rollover all right at the start of the N years: move all to Roth after paying taxes and then compound over 10 years
        rothBal = STARTING_BALANCE;
        taxableBal = STARTING_BALANCE * (1-cashoutMarginalTaxRate);
        for (int i = 1; i <= N; i++) {
            rothBal *= (1.0 + ROI);
            double taxableBalanceEarningsAfterTax = taxableBal * ROI * (1-cashoutMarginalTaxRate);
            taxableBal += taxableBalanceEarningsAfterTax;
            System.out.printf("After year %2d:  Balance in Roth 401K: %.2f, Balance in taxable acct: %.2f, Total after tax value: %.2f%n", i, rothBal, taxableBal, rothBal + taxableBal);
            System.out.printf(""); // on a separate line so that one can break in the debugger
        }

        System.out.printf("%nCompound in the regular for 10 years and then rollover all at the end:%n");
        // No rollover is done for the N years - the value if the rollover is done after 10 years is the same
        // as the balance after tax value shown after 10 years.
        planBal = STARTING_BALANCE;
        taxableBal = STARTING_BALANCE;
        for (int i = 1; i <= N; i++) {
            planBal *= (1.0 + ROI);
            double taxableBalanceEarningsAfterTax = taxableBal * ROI * (1-cashoutMarginalTaxRate);
            taxableBal += taxableBalanceEarningsAfterTax;
            double planAfterTaxValue = planBal * (1-cashoutMarginalTaxRate);
            System.out.printf("After year %2d:  Balance in reg 401K: %.2f, Balance in taxable acct: %.2f, Total after tax value: %.2f%n", i, planBal, taxableBal, planAfterTaxValue + taxableBal);
            System.out.printf(""); // on a separate line so that one can break in the debugger
        }

    }
}
