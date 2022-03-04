package persistence;

import model.InvestmentPortfolio;
import model.Investments;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads Investment Portfolio from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public InvestmentPortfolio read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseInvestmentPortfolio(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private InvestmentPortfolio parseInvestmentPortfolio(JSONObject jsonObject) {
        InvestmentPortfolio ip = new InvestmentPortfolio();
        addInvestments(ip, jsonObject);
        return ip;
    }

    // MODIFIES: ip
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addInvestments(InvestmentPortfolio ip, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("names");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addThingy(ip, nextThingy);
        }
    }

    // MODIFIES: ip
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addThingy(InvestmentPortfolio ip, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int amt = jsonObject.getInt("amt");
        Investments i = new Investments(name);
        i.setAmt(amt);
        ip.addInvestment(i);
    }
}
