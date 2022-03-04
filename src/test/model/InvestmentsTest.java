package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// test class for Investments
public class InvestmentsTest {
    private Investments i1;

    @BeforeEach
    void setUpMethod() {
        i1 = new Investments("testName");
    }

    @Test
    void testConstructor() {
        assertEquals("testName",i1.getName());
    }

    @Test
    void testGetName() {
        Investments i2 = new Investments("NameTest");
        assertEquals("NameTest",i2.getName());
    }

    @Test
    void testGetAmt() {
        Investments i3 = new Investments("Testi");
        i3.setAmt(40);
        assertEquals(40,i3.getAmt());
    }
}
