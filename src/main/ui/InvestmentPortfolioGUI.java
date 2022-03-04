package ui;

import exceptions.WrongInvestment;
import model.InvestmentPortfolio;
import model.Investments;
import persistence.JsonReader;
import persistence.JsonWriter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// Builds a GUI for Investment Portfolio application
public class InvestmentPortfolioGUI extends JFrame {

    private InvestmentPortfolio iportfolio =  new InvestmentPortfolio();
    JPanel myPanel = new JPanel();
    JFrame myFrame = new JFrame("I-Portfolio by Advitya");
    private static final String JSON_STORE = "./data/workroom.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    String input1;
    int input2;
    int input3;

    // CONSTRUCTOR
    // Builds the Investment Portfolio GUI
    public InvestmentPortfolioGUI() {
        myFrame.setSize(500, 700);
        myFrame.setDefaultCloseOperation(myFrame.EXIT_ON_CLOSE);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        myFrame.setLayout(new BorderLayout());

        initializeImage();

        initializeAddInvestment1();
        JTextField addI = initializeInvestment2();
        JTextField addIa = initializeInvestment3();
        JLabel num = new JLabel(String.valueOf(iportfolio.getInvestment().size()) + "                         ");
        initializeAddButton(addI, addIa, num);

        initializeRemove1();
        JTextField addRa = initializeRemove2();
        initializeRemoveButton(num, addRa);

        DefaultTableModel dtm = initializeTableModel();
        showInvestmentButton(dtm);

        initializeDis();
        initializeTracker();

        JButton save = initializeSaveButton(num);
        JButton load = initializeLoadButton(num);

        finalAdds(num, save, load);
    }

    //MODIFIES : this
    //EFFECTS : Adds an Image to the Panel of GUI
    private void initializeImage() {
        ImageIcon ig = new ImageIcon("./data/MainLogo.png");
        Image image = ig.getImage(); // transform it
        Image newig = image.getScaledInstance(300,200,  Image.SCALE_SMOOTH); // scale it the smooth way
        ImageIcon newii = new ImageIcon(newig);
        JLabel labelImage = new JLabel(ig);
        myPanel.add(labelImage);
    }

    //MODIFIES : this
    //EFFECTS : Adds a JLabel for Add Investment to panel for GUI
    private void initializeAddInvestment1() {
        JLabel l1 = new JLabel("Add Investment");
        l1.setHorizontalAlignment(JLabel.LEFT);
        l1.setBounds(10, 30, 165, 35);
        myPanel.add(l1);
    }

    //MODIFIES : this
    //EFFECTS : Adds a JTextField for Add Investment to panel for GUI to get Investment name
    private JTextField initializeInvestment2() {
        JTextField addI = new JTextField(10);
        Dimension size1 = addI.getPreferredSize();
        addI.setBounds(115, 30, size1.width, 35);
        myPanel.add(addI);
        return addI;
    }

    //MODIFIES : this
    //EFFECTS : Adds a JTextField for Add Investment to panel for GUI to get Investment amount
    private JTextField initializeInvestment3() {
        JTextField addIa = new JTextField(3);
        addIa.setBounds(170, 30, 180, 35);
        myPanel.add(addIa);
        return addIa;
    }

    //REQUIRES : JTextField for name, JTextField for amount, JLabel num for tracker
    //MODIFIES : this
    //EFFECTS : Adds a button to panel for GUI, to create an Investment and adds it to
    //         Investment Portfolio, also updates no. of Investments appropriately
    private void initializeAddButton(JTextField addI, JTextField addIa, JLabel num) {
        JButton addInvB = new JButton("Add");
        addInvB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input1 = addI.getText();
                input2 = Integer.parseInt(addIa.getText());
                Investments i = new Investments(input1);
                i.setAmt(input2);
                iportfolio.addInvestment(i);
                num.setText(String.valueOf(iportfolio.getInvestment().size()) + "                         ");
            }
        });
        myPanel.add(addInvB);
    }

    //MODIFIES : this
    //EFFECTS : Adds a JLabel for Remove Investment to panel for GUI
    private void initializeRemove1() {
        JLabel l2 = new JLabel("Remove Investment (Enter #)");
        myPanel.add(l2);
    }

    //MODIFIES : this
    //EFFECTS : Adds a JTextField for Remove Investment to panel for GUI to get
    //          Investment number to remove, and returns JTextField
    private JTextField initializeRemove2() {
        JTextField addRa = new JTextField(3);
        addRa.setBounds(170, 30, 180, 35);
        myPanel.add(addRa);
        return addRa;
    }

    //REQUIRES : JTextField for no./position of Investment to remove, JLabel num for tracker
    //MODIFIES : this
    //EFFECTS : Adds a button to panel for GUI, to remove the Investment of given no. from
    //         Investment Portfolio, also updates no. of Investments appropriately
    private void initializeRemoveButton(JLabel num, JTextField addRa) {
        JButton revInvB = new JButton("Remove");
        revInvB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input3 = Integer.parseInt(addRa.getText());
                    try {
                        iportfolio.removeInvestment(input3);
                    } catch (WrongInvestment wrongInvestment) {
                        JOptionPane.showMessageDialog(null,
                                "Error: Please enter a valid Investment position", "Error Message",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    num.setText(String.valueOf(iportfolio.getInvestment().size()) + "                         ");
            }
        });
        myPanel.add(revInvB);
    }

    //EFFECTS : Builds a default table model to Show Investments output
    private DefaultTableModel initializeTableModel() {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Name");
        dtm.addColumn("Amount");
        return dtm;
    }

    //REQUIRES : DefaultTableModel dtm
    //MODIFIES : this
    //EFFECTS : Adds a button to panel for GUI, to show added Investments in a
    //          table using table model by adding the table to the panel
    private void showInvestmentButton(DefaultTableModel dtm) {
        JButton o = new JButton("  Show Investments   ");
        o.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dtm.setRowCount(0);
                ArrayList<Investments> ivp = iportfolio.getInvestment();
                for (int i = 0; i < iportfolio.getInvestmentCount(); i++) {
                    dtm.insertRow(i,new Object[]{ ivp.get(i).getName(), String.valueOf(ivp.get(i).getAmt())});
                }

                JTable tbl = new JTable(dtm);
                myPanel.add(new JScrollPane(tbl));
                myFrame.revalidate();
                myFrame.repaint();
            }
        });
        myPanel.add(o);
    }

    //MODIFIES : this
    //EFFECTS : Adds a JLabel to the panel for GUI, to remind user about clicking Show Investments
    private void initializeDis() {
        JLabel dis = new JLabel("(Remember to click above button to see updated Investments)");
        myPanel.add(dis);
    }

    //MODIFIES : this
    //EFFECTS : Adds a JLabel to the panel for GUI, for showing no. of added Investments
    private void initializeTracker() {
        JLabel track = new JLabel("                        No. of added Investments :      ");
        myPanel.add(track);
    }

    //REQUIRES : JLabel num to update tracker
    //EFFECTS : Saves no. of currently added Investments to a file,
    //          updates tracker accordingly and returns JButton
    private JButton initializeSaveButton(JLabel num) {
        JButton save = new JButton("Save File");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(iportfolio);
                    jsonWriter.close();
                    System.out.println("Saved " + "Your Portfolio" + " to " + JSON_STORE);
                    num.setText(String.valueOf(iportfolio.getInvestment().size()) + "                         ");
                } catch (FileNotFoundException e1) {
                    System.out.println("Unable to write to file: " + JSON_STORE);
                }
            }
        });
        return save;
    }

    //REQUIRES : JLabel num to update tracker
    //MODIFIES : this
    //EFFECTS : Loads file previously saved , updates tracker
    //          accordingly, and returns JButton
    private JButton initializeLoadButton(JLabel num) {
        JButton load = new JButton("Load file");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    iportfolio = jsonReader.read();
                    System.out.println("Loaded " + "Your Portfolio" + " from " + JSON_STORE);
                    num.setText(String.valueOf(iportfolio.getInvestment().size()) + "                         ");
                } catch (IOException e1) {
                    System.out.println("Unable to read from file: " + JSON_STORE);
                }
            }
        });
        return load;
    }

    //REQUIRES : JLabel num for tracker, JButton save for save Investments, JButton load for load Investments
    //MODIFIES : this
    //EFFECTS : Adds num, save and load to the panel with updated values,
    //          also adds panel to frame
    private void finalAdds(JLabel num, JButton save, JButton load) {
        myPanel.add(num);

        myPanel.add(save);
        myPanel.add(load);

        myFrame.add(myPanel);
        myFrame.setVisible(true);
        myFrame.revalidate();
        myFrame.repaint();
    }

}
