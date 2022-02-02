package caryseller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RolloverAtDifferentTimesUsingTaxableMoneyToPayTaxTest {
    @Test
    void Test() {
        String[] args = {"23"}; // avg of 22 and 24
        RolloverAtDifferentTimesUsingTaxableMoneyToPayTax.main(args);
        assertTrue(true);
    }

}
