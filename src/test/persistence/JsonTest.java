package persistence;

import model.Investments;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkInvestment(String name, Investments i) {
        assertEquals(name, i.getName());
    }
}
