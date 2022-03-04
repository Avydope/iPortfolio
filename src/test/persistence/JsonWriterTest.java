package persistence;

import model.InvestmentPortfolio;
import model.Investments;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Test class for JsonWriter
public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            InvestmentPortfolio wr = new InvestmentPortfolio();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyInvestmentPortfolio() {
        try {
            InvestmentPortfolio ip = new InvestmentPortfolio();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyInvestmentPortfolio.json");
            writer.open();
            writer.write(ip);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyInvestmentPortfolio.json");
            ip = reader.read();
            assertEquals(0, ip.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralInvestmentPortfolio() {
        try {
            InvestmentPortfolio ip = new InvestmentPortfolio();
            ip.addInvestment(new Investments("Stock"));
            ip.addInvestment(new Investments("Futures"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralInvestmentPortfolio.json");
            writer.open();
            writer.write(ip);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralInvestmentPortfolio.json");
            ip = reader.read();
            List<Investments> Invests = ip.getInvestment();
            assertEquals(2, Invests.size());
            checkInvestment("Stock", Invests.get(0));
            checkInvestment("Futures", Invests.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
