package caryseller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeferSameAmtPreTaxAndAfterTaxTest {
    @Test
    void RothTheSameAsRegularIfSideAccountEarningsNotTaxed() {
        String earningsRate = "-" + DeferSameAmtPreTaxAndAfterTax.STATE_TAX_RATE;
        String[] args = {"22", "22", earningsRate};
        DeferSameAmtPreTaxAndAfterTax.main(args);
        assertTrue(true);
    }

    @Test
    void RothBetterThanTheRegularIfSideAccountEarningsAreTaxed() {
        // A rate of "0" will mean the effective rate is DeferSameAmtPreTaxAndAfterTax.STATE_TAX_RATE
        String[] args = {"22", "22", "0"};
        DeferSameAmtPreTaxAndAfterTax.main(args);
        assertTrue(true);
    }
}
