package model;

import exceptions.WrongInvestment;
import org.json.JSONObject;

import java.util.ArrayList;

// Represents a InvestmentPortfolio
public class InvestmentPortfolio {
    private int investmentCount;
    private ArrayList<Investments> iportfolio;

    // constructor
    // MODIFIES : this
    // EFFECTS : Creates Arraylist of Investments type
    public InvestmentPortfolio() {
        iportfolio = new ArrayList<Investments>();
    }

    // REQUIRES : A string that will be Investment name
    // MODIFIES : this
    // EFFECTS : Adds Investment of given name to ArrayList of Investments
    public void newInvestment(String iname) {
        iportfolio.add(new Investments(iname));
        investmentCount++;
    }

    // REQUIRES : Investment
    // MODIFIES : this
    // EFFECTS : Adds given Investment to ArrayList of Investments
    public void addInvestment(Investments i) {
        iportfolio.add(i);
        investmentCount++;
    }

    // MODIFIES : this
    // EFFECTS : Removes Investment from ArrayList of Investments,
    // throws WrongInvestment exception if input integer is invalid
    public void removeInvestment(int i) throws WrongInvestment {
        if (i < 1 || i > iportfolio.size()) {
            throw new WrongInvestment();
        }
        iportfolio.remove(i - 1);
        investmentCount--;
    }

    // REQUIRES : integer
    // EFFECTS : Returns true if given integer is less than size of portfolio,
    //           otherwise returns false
    public boolean removeInvestmentSize(int i) {
        if (i <= iportfolio.size()) {
            return true;
        } else {
            return false;
        }
    }

    // GETTERS

    public ArrayList<Investments> getInvestment() {
        return iportfolio;
    }

    public int getSize() {
        return iportfolio.size();
    }

    public int getInvestmentCount() {
        return investmentCount;
    }

    // EFFECTS : Adds iportfolio array of Investments to newly created JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("names", iportfolio);
        return json;
    }

}

