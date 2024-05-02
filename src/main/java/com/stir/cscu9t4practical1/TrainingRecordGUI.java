// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

public class TrainingRecordGUI extends JFrame implements ActionListener {

    private JTextField name = new JTextField(30);
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField hours = new JTextField(2);
    private JTextField mins = new JTextField(2);
    private JTextField secs = new JTextField(2);
    private JTextField dist = new JTextField(4);
    private JLabel labn = new JLabel(" Name:");
    private JLabel labd = new JLabel(" Day:");
    private JLabel labm = new JLabel(" Month:");
    private JLabel laby = new JLabel(" Year:");
    private JLabel labh = new JLabel(" Hours:");
    private JLabel labmm = new JLabel(" Mins:");
    private JLabel labs = new JLabel(" Secs:");
    private JLabel labdist = new JLabel(" Distance (km):");
    private JButton addR = new JButton("Add");
    private JButton lookUpByDate = new JButton("Look Up");
    private JButton FindAllByDate = new JButton("Find All");
    private JComboBox<String> entries = new JComboBox<>(new String[]{"General" , "Sprinting", "Cycling", "Swimming"});

    private JLabel repetition = new JLabel("Repetitions: ");
    private JTextField reps = new JTextField(3);

    private JLabel recovery = new JLabel("Recovery time: ");
    private JTextField rec = new JTextField(3);
    private JPanel sprintPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));


    private JLabel tempo = new JLabel("Tempo: ");
    private JTextField temp = new JTextField(10);

    private JLabel terrain = new JLabel("Terrain: ");
    private JTextField ter = new JTextField(10);
    private JPanel cyclePanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));


    private JLabel where = new JLabel("Where: ");
    private JTextField whereText = new JTextField(10);
    private JPanel swimPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));

    private JButton delete = new JButton("Delete");



    private TrainingRecord myAthletes = new TrainingRecord();

    private JTextArea outputArea = new JTextArea(6, 50);

    public static void main(String[] args) {
        TrainingRecordGUI applic = new TrainingRecordGUI();
    } // main

    // set up the GUI 
    public TrainingRecordGUI() {
        super("Training Record");
        setLayout(new FlowLayout());
        add(entries);
        add(labn);
        add(name);
        name.setEditable(true);
        add(labd);
        add(day);
        day.setEditable(true);
        add(labm);
        add(month);
        month.setEditable(true);
        add(laby);
        add(year);
        year.setEditable(true);
        add(labh);
        add(hours);
        hours.setEditable(true);
        add(labmm);
        add(mins);
        mins.setEditable(true);
        add(labs);
        add(secs);
        secs.setEditable(true);
        add(labdist);
        add(dist);
        dist.setEditable(true);
        sprintPanel.add(repetition);
        sprintPanel.add(reps);
        sprintPanel.add(recovery);
        sprintPanel.add(rec);
        add(sprintPanel);
        sprintPanel.setVisible(false);

        cyclePanel.add(terrain);
        cyclePanel.add(ter);
        cyclePanel.add(tempo);
        cyclePanel.add(temp);
        add(cyclePanel);
        cyclePanel.setVisible(false);

        swimPanel.add(where);
        swimPanel.add(whereText);
        add(swimPanel);
        swimPanel.setVisible(false);

        add(addR);
        addR.addActionListener(this);
        add(lookUpByDate);
        lookUpByDate.addActionListener(this);
        FindAllByDate.addActionListener(this);
        add(FindAllByDate);
        add(delete);
        delete.addActionListener(this);
        entries.addActionListener(this);
        add(outputArea);
        outputArea.setEditable(false);
        setSize(720, 250);
        setVisible(true);
        blankDisplay();

        // To save typing in new entries while testing, uncomment
        // the following lines (or add your own test cases)
        
    } // constructor

    // listen for and respond to GUI events 
    public void actionPerformed(ActionEvent event) {
        String message = "";
        if (event.getSource() == addR) {
            message = addEntry("generic");
        }
        if (event.getSource() == lookUpByDate) {
            message = lookupEntry();
        }
        if (event.getSource() == FindAllByDate)
        {
            message = FindAllEntries();
        }
        if(event.getSource() == entries)
        {
            if(entries.getSelectedItem().equals("Sprinting"))
            {
                swimPanel.setVisible(false);
                cyclePanel.setVisible(false);
                sprintPanel.setVisible(true);
            }
            else if (entries.getSelectedItem().equals("Cycling"))
            {
                sprintPanel.setVisible(false);
                swimPanel.setVisible(false);
                cyclePanel.setVisible(true);

            }
            else if (entries.getSelectedItem().equals("Swimming"))
            {
                sprintPanel.setVisible(false);
                cyclePanel.setVisible(false);
                swimPanel.setVisible(true);
            }
            else {
                sprintPanel.setVisible(false);
                cyclePanel.setVisible(false);
                swimPanel.setVisible(false);
            }
        }
        if(event.getSource()==delete)
        {
            message = deleted();
        }

        outputArea.setText(message);
        blankDisplay();
    } // actionPerformed

    public String addEntry(String what) {

        String message;
        System.out.println("Adding "+what+" entry to the records");
        String n = name.getText();
        if(name.getText().isBlank()) {
            message = "Name cannot be empty.";
        }
        else {
            try {
                int m = Integer.parseInt(month.getText());
                int d = Integer.parseInt(day.getText());
                int y = Integer.parseInt(year.getText());
                float km = java.lang.Float.parseFloat(dist.getText());
                int h = Integer.parseInt(hours.getText());
                int mm = Integer.parseInt(mins.getText());
                int s = Integer.parseInt(secs.getText());

                Entry e;

                if(entries.getSelectedItem().equals("Sprinting"))
                {
                    int rep = Integer.parseInt(reps.getText());
                    int recs = Integer.parseInt(rec.getText());

                    e = new SprintEntry(n, d, m, y, h, mm, s, km, rep, recs);
                }
                else if (entries.getSelectedItem().equals("Cycling"))
                {
                    String terr = ter.getText();
                    String tempo = temp.getText();

                    e = new CycleEntry(n, d, m, y, h, mm, s, km, terr, tempo);

                }
                else if (entries.getSelectedItem().equals("Swimming"))
                {
                    String where = whereText.getText();

                    e = new SwimEntry(n, d, m, y, h, mm, s, km, where);
                }

                else {
                    e = new Entry(n, d, m, y, h, mm, s, km);


                }


                message = myAthletes.addEntry(e);
            } catch (Exception e) {
                message = "Enter appropriate values.";
            }
        }

        return message;
    }
    
    public String lookupEntry() {
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up record ...");
        String message = myAthletes.lookupEntry(d, m, y);
        return message;
    }

    public String FindAllEntries()
    {
//        return "Not implemented yet.";
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up record ...");
        String message = myAthletes.lookupEntries(d, m, y);
        return message;
    }

    public String deleted()
    {
        String message;
        try {
            String n = name.getText();
            int m = Integer.parseInt(month.getText());
            int d = Integer.parseInt(day.getText());
            int y = Integer.parseInt(year.getText());

            message = myAthletes.deleteEntry(n, m, d, y);
        }
        catch (Exception e) {
            message = "Enter appropriate values.";
        }
        return message;
    }

    public void blankDisplay() {
        name.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        hours.setText("");
        mins.setText("");
        secs.setText("");
        dist.setText("");
        temp.setText("");
        ter.setText("");
        whereText.setText("");
        rec.setText("");
        reps.setText("");

    }// blankDisplay
    // Fills the input fields on the display for testing purposes only
    public void fillDisplay(Entry ent) {
        name.setText(ent.getName());
        day.setText(String.valueOf(ent.getDay()));
        month.setText(String.valueOf(ent.getMonth()));
        year.setText(String.valueOf(ent.getYear()));
        hours.setText(String.valueOf(ent.getHour()));
        mins.setText(String.valueOf(ent.getMin()));
        secs.setText(String.valueOf(ent.getSec()));
        dist.setText(String.valueOf(ent.getDistance()));
    }


} // TrainingRecordGUI

