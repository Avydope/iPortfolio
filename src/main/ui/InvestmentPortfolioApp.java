package ui;

import exceptions.WrongInvestment;
import model.InvestmentPortfolio;
import model.Investments;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Investment portfolio application
public class InvestmentPortfolioApp {
    private static final String JSON_STORE = "./data/workroom.json";
    private InvestmentPortfolio iportfolio;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS : runs the investment portfolio application
    public InvestmentPortfolioApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        iportfolio = new InvestmentPortfolio();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runPortfolio();
    }

    // MODIFIES : this
    // EFFECTS : processes user input
    private void runPortfolio() {
        boolean quit = false;
        String command = null;
        input = new Scanner(System.in);
        iportfolio = new InvestmentPortfolio();

        while (!quit) {
            appMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                quit = true;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThank you for using my application!");
    }

    // EFFECTS: displays menu of options to user
    private void appMenu() {
        System.out.println("\nWelcome to iPortfolio:");
        System.out.println("\tPress i to -> add investment");
        System.out.println("\tPress r to -> remove investment");
        System.out.println("\tPress v to -> view added investments in this session");
        System.out.println("\tPress s to -> save investment portfolio to file");
        System.out.println("\tPress l to -> load investment portfolio from file");
        System.out.println("\tPress q to -> quit");
    }

    // MODIFIES : this
    // EFFECTS : processes user command
    private void processCommand(String command) {
        if (command.equals("i")) {
            addInvestment();
        } else if (command.equals("r")) {
            removeInvestment();
        } else if (command.equals("v")) {
            viewInvestments();
        } else if (command.equals("s")) {
            saveInvestmentPortfolio();
        } else if (command.equals("l")) {
            loadInvestmentPortfolio();
        } else {
            System.out.println("Select a valid keyword");
        }
    }

    // MODIFIES : this
    // EFFECTS : Adds Investment to InvestmentPortfolio
    private void addInvestment() {
        System.out.println("Enter investment name: ");
        String iname = input.next();
        Investments i = new Investments(iname);
        System.out.println("Enter Amount: ");
        String amt = input.next();
        i.setAmt(Integer.parseInt(amt));
        iportfolio.addInvestment(i);
        System.out.println("Total no. of Investments " + iportfolio.getInvestmentCount());
    }

    // MODIFIES : this
    // EFFECTS : Removes Investment from InvestmentPortfolio
    private void removeInvestment() {
        System.out.println("Enter the no. of investment you want to remove: ");
        int i = input.nextInt();
        if (iportfolio.removeInvestmentSize(i)) {
            try {
                iportfolio.removeInvestment(i);
            } catch (WrongInvestment wrongInvestment) {
                wrongInvestment.printStackTrace();
            }
            System.out.println("Total no. of Investments " + iportfolio.getInvestmentCount());
        } else {
            System.out.println("Enter a valid no. between 0 and " + iportfolio.getInvestmentCount());
        }
    }

    // EFFECTS : Lists all added Investments
    private void viewInvestments() {
        System.out.println("Total no. of Investments " + iportfolio.getInvestmentCount());
        ArrayList<Investments> ivp = iportfolio.getInvestment();
        for (int i = 0; i < iportfolio.getInvestmentCount(); i++) {
            System.out.println((ivp.get(i).getName() + " : " + ivp.get(i).getAmt()));
        }
    }

    // EFFECTS: saves the investment portfolio to file
    private void saveInvestmentPortfolio() {
        try {
            jsonWriter.open();
            jsonWriter.write(iportfolio);
            jsonWriter.close();
            System.out.println("Saved " + "Your Portfolio" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads investment portfolio from file
    private void loadInvestmentPortfolio() {
        try {
            iportfolio = jsonReader.read();
            System.out.println("Loaded " + "Your Portfolio" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
