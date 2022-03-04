package model;

// Represents a Investment class having a name
public class Investments {
    private String iname;
    private int amt;

    // constructor
    // EFFECTS : Creates Investment of given name
    public Investments(String iname) {
        this.iname = iname;
    }

    // GETTER

    public String getName() {
        return iname;
    }

    public int getAmt() {
        return this.amt;
    }

    // SETTER

    public void setAmt(int amt) {
        this.amt = amt;
    }

}
