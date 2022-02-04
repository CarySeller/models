package caryseller;

// Illustrates rolling over an extra ROLLOVER_AMOUNT N years early as opposed to leaving the
// ROLLOVER_AMOUNT in the regular IRA.  Rolling it over early could cause a one year spike in income
// with IRMAA consequences and possible a higher tax bracket.  What's printed out is what the balance in
// a ROTH account would be each year with the ROLLOVER_AMOUNT moved at the start of the N year vs leaving
// that amount in a taxable account and paying taxes at the capital gains rate and regular (marginal) rate.
public class RolloverTaxSavingsAfterNYearsByUsingTaxableMoneyToPayTax {
    static double STATE_TAX_RATE = 4.99;
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("1 argument (tax rate at time of withdrawal) is needed.");
            System.exit(1);
        }
        double widTR = Double.parseDouble(args[0]);
        double marginalTaxRate = (widTR + STATE_TAX_RATE) / 100;
        double capitalGainsRate = (15 + 3.8 + STATE_TAX_RATE) / 100;
        double ROLLOVER_AMOUNT = 100000;
        double TAX_LIABILITY_FOR_ROLLOVER = ROLLOVER_AMOUNT * marginalTaxRate;
        double balanceWhenKeptInTaxableAtMarginalRate = TAX_LIABILITY_FOR_ROLLOVER;
        double balanceWhenKeptInTaxableAtCapitalGainsRate = TAX_LIABILITY_FOR_ROLLOVER;
        double balanceWhenRolledIntoRoth  = TAX_LIABILITY_FOR_ROLLOVER;
        double ROI = .05;
        int N = 10;

        System.out.printf("Savings of single transfer of %.0f from a taxable account to roth account over %d years:%n", ROLLOVER_AMOUNT, N);
        for (int i = 1; i <= N; i++) {
            balanceWhenRolledIntoRoth *= (1.0 + ROI);

            double taxableEarningsAfterTax = balanceWhenKeptInTaxableAtMarginalRate * ROI * (1-marginalTaxRate);
            balanceWhenKeptInTaxableAtMarginalRate += taxableEarningsAfterTax;

            taxableEarningsAfterTax = balanceWhenKeptInTaxableAtCapitalGainsRate * ROI * (1-capitalGainsRate);
            balanceWhenKeptInTaxableAtCapitalGainsRate += taxableEarningsAfterTax;
            System.out.printf("After year %2d, balances:  Roth: %.2f, Taxable at marginal rate: %.2f, Taxable at c g rate: %.2f%n", i, balanceWhenRolledIntoRoth, balanceWhenKeptInTaxableAtMarginalRate, balanceWhenKeptInTaxableAtCapitalGainsRate);
            System.out.printf(""); // on a separate line so that one can break in the debugger
        }
    }
}
