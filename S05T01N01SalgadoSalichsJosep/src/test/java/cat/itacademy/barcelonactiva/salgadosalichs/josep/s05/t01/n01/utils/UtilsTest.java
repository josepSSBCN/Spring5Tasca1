package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.utils;

import jdk.jshell.execution.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void checkTypeCountry() {
        assertEquals("UE", Utils.checkTypeCountry("Spain"));
        assertEquals("No UE", Utils.checkTypeCountry("EEUU"));
        assertEquals("-1", Utils.checkTypeCountry("   "));
        assertEquals("-1", Utils.checkTypeCountry(""));
        assertEquals("No UE", Utils.checkTypeCountry("asdfg sdgrt sdgsdfg"));
        ////*assertEquals("-1", Utils.checkTypeCountry(null));

    }
}