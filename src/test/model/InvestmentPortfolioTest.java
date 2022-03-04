package model;

import exceptions.WrongInvestment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

// Test class for InvestmentPortfolio
class InvestmentPortfolioTest {
    private InvestmentPortfolio testP;

    @BeforeEach
    void setUpMethod() {
        testP = new InvestmentPortfolio();
    }

    @Test
    void testConstructor() {
        assertEquals(0,testP.getSize());
    }

    @Test
    void testNewInvestment() {
        testP.newInvestment("testS1");
        testP.newInvestment("testS2");
        assertEquals(2, testP.getSize());
    }

    @Test
    void testRemoveInvestmentNoException() {
        assertEquals(0,testP.getSize());
        testP.newInvestment("test1");
        testP.newInvestment("test2");
        assertEquals(2,testP.getSize());
        try {
            testP.removeInvestment(2);
        } catch (WrongInvestment wrongInvestment) {
            fail();
        }
        assertEquals(1,testP.getSize());
    }

    @Test
    void testRemoveInvestmentException1() {
        assertEquals(0,testP.getSize());
        testP.newInvestment("test1");
        testP.newInvestment("test2");
        assertEquals(2,testP.getSize());
        try {
            testP.removeInvestment(3);
        } catch (WrongInvestment wrongInvestment) {
            // expected
        }
        assertEquals(2,testP.getSize());
    }

    @Test
    void testRemoveInvestmentException2() {
        assertEquals(0,testP.getSize());
        testP.newInvestment("test1");
        testP.newInvestment("test2");
        assertEquals(2,testP.getSize());
        try {
            testP.removeInvestment(0);
        } catch (WrongInvestment wrongInvestment) {
            // expected
        }
        assertEquals(2,testP.getSize());
    }


    @Test
    void testGetInvestment() {
        testP.newInvestment("Test1");
        ArrayList<Investments> check = testP.getInvestment();
        assertEquals("Test1",check.get(0).getName());
    }

    @Test
    void testRemoveInvestmentSizeGreater() {
        testP.newInvestment("sample");
        assertFalse(testP.removeInvestmentSize(2));
    }

    @Test
    void testRemoveInvestmentSizeLesser() {
        testP.newInvestment("sample");
        assertTrue(testP.removeInvestmentSize(1));
    }


    @Test
    void testGetInvestmentCount() {
        assertEquals(0,testP.getInvestmentCount());
        testP.addInvestment(new Investments("sample1"));
        assertEquals(1,testP.getInvestmentCount());
        testP.addInvestment(new Investments("sample2"));
        assertEquals(2,testP.getInvestmentCount());

    }
}