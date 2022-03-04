package persistence;

import model.InvestmentPortfolio;
import model.Investments;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Test class for JsonReader
public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            InvestmentPortfolio ip = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderInvestmentPortfolio() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyInvestmentPortfolio.json");
        try {
            InvestmentPortfolio ip = reader.read();
            assertEquals(0, ip.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralInvestmentPortfolio() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralInvestmentPortfolio.json");
        try {
            InvestmentPortfolio ip = reader.read();
            List<Investments> Invests = ip.getInvestment();
            assertEquals(2, Invests.size());
            checkInvestment("Stock", Invests.get(0));
            checkInvestment("Trade", Invests.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
