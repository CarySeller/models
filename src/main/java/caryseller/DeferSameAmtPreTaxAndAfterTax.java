package caryseller;

public class DeferSameAmtPreTaxAndAfterTax {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("3 arguments (tax rates at time of deferral, withdrawal and for earnigns) are needed.");
            System.exit(1);
        }
        double defTR = Double.parseDouble(args[0]);
        double widTR = Double.parseDouble(args[1]);
        double earTR = Double.parseDouble(args[2]); // tax rate for the earnings (may be subject to the dividend rate and the NIIT)
        double STATE_TAX_RATE = 4.99;
        double marginalTaxRate = (defTR + STATE_TAX_RATE) / 100.0;
        double cashoutmarginalTaxRate = (widTR + STATE_TAX_RATE) / 100;
        double earningsTaxRate = (earTR + STATE_TAX_RATE) / 100;
        double yearlyContribution = 26000;
        double preTaxAmountCorrespondingToTaxDeferredYearlyContribution = yearlyContribution / (1.0 - marginalTaxRate);
        // If doing a Roth one pays tax on all of preTaxAmountCorrespondingToTaxDeferredYearlyContribution - what's left after tax is the yearlyContribution.
        // If doing a regular deferral one pays tax on preTaxAmountCorrespondingToTaxDeferredYearlyContribution less yearlyContribution which is tax deferred
        // ... so what's left to contribute to the side account would be less.
        double yearlyTaxSavingsContributedToTaxableAcct = (preTaxAmountCorrespondingToTaxDeferredYearlyContribution - yearlyContribution) * (1.0 - marginalTaxRate);
        double taxableAcctBal = 0;
        // yearlyContribution is either in a regular 401k or a Roth 401k
        // planBal is the balance in the plan, a regular 401k or a Roth 401k
        // if the plan is a regular 401k the tax savings is invested in a taxable account at the same ROI.
        double planBal = 0; // the balance in the plan
        double ROI = .05;
        int N = 10;
        for (int i = 1; i <= N; i++) {
            // after year i, assuming contribution at the start of the year
            planBal = planBal + yearlyContribution;
            planBal = planBal * (1.0 + ROI);

            taxableAcctBal = taxableAcctBal + yearlyTaxSavingsContributedToTaxableAcct;
            double earnings = taxableAcctBal * ROI; // calc the earnings for that year
            earnings = earnings * (1 - earningsTaxRate); // adjust earnings by subtracting tax
            taxableAcctBal += earnings; // add in net earnings to the accumulated taxableAcctBal

            System.out.printf("After year %d:%n   Balance in plan: %.2f, taxableAcctBal: %.2f%n", i, planBal, taxableAcctBal);
            double regular401KCashout = planBal * (1 - cashoutmarginalTaxRate) + taxableAcctBal;
            System.out.printf("   Cashing out: Roth: %.2f, Regular: %.2f%n", planBal, regular401KCashout);
        }
    }
}
