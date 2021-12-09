package caryseller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RolloverAtDifferentTimesWithNoTaxableMoneyAvailableToPayTaxTest {
    @Test
    void Test() {
        String[] args = {"22"};
        RolloverAtDifferentTimesWithNoTaxableMoneyAvailableToPayTax.main(args);
        assertTrue(true);
    }

}
