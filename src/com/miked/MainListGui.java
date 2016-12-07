package com.miked;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by miked on 12/5/2016.
 */
public class MainListGui extends JFrame {
    private JPanel rootPanel;
    private JButton addButton;
    private JTextField addItemTextField;
    private JList<Item> MainList1;
    private JComboBox<Integer> priorityCB;


    private JLabel priorityTextField;
    private JButton subListButton;
    private JList<Item> sublist1List;
    private JLabel subList1label;
    private JButton addToSublist1Button;
    private JButton exitButton;

    private DefaultListModel<Item> mainlistModel;
    private DefaultListModel<Item> subList1Model;

    //private ItemListModel listModel;

    public MainListGui() {

        super("To Do List");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(700, 600);

        mainlistModel = new DefaultListModel<Item>();
        MainList1.setModel(mainlistModel);
        subList1Model = new DefaultListModel<Item>();
        sublist1List.setModel(subList1Model);


//        try {
//            BufferedReader ML1br = new BufferedReader(new FileReader("mainList1.txt"));
//
//            String lineMain = ML1br.readLine();
//
//            while (lineMain != null) {
//
//                String[] splitLineMain = lineMain.split(";");
//                int ID = Integer.parseInt(splitLineMain[0]);
//                String item = splitLineMain[1];
//                int priority = Integer.parseInt(splitLineMain[2]);
//
//                Item item1 = new Item( item);
//
//                mainlistModel.addElement(item1);
//                lineMain = ML1br.readLine();
//            }
//            ML1br.close();
//
//    } catch (IOException ioe) {//Catch exceptions
//        System.out.println("Error reading file");
//    } catch (NumberFormatException nfe) {
//        System.out.println(nfe.toString());
//    } //catch (ParseException pe) {
        //System.out.println(pe.toString());
        //}


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addItemTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(MainListGui.this, "Please enter an item \nto add to the list");
                } else {
                    String itemToAdd = addItemTextField.getText();
                    int priority = priorityCB.getSelectedIndex();

                    Item i = new Item(itemToAdd);


                    mainlistModel.addElement(i);

                    for (int j = 1; j <= mainlistModel.getSize(); j++) {
                        priorityCB.removeItem(j);
                        priorityCB.addItem(j);
                    }
                    addItemTextField.setText("");
                    addItemTextField.grabFocus();
                }
            }
        });


        priorityCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("ACTION PERFORMED");

                // int assignNewPriority = priorityCB.getSelectedIndex()+1;

                if (priorityCB.getSelectedIndex() != -1) {

                    int assignNewPriority = (int) priorityCB.getSelectedItem();

                    Item selectedItem = MainList1.getSelectedValue();
                    Item subSelectedItem = sublist1List.getSelectedValue();

                    System.out.println("selectedItem " + selectedItem);

                    if (selectedItem != null) {
                        int currentPriority = selectedItem.getPriority();

                        selectedItem.setPriority(assignNewPriority);

                        System.out.println("assignNewPriority " + assignNewPriority);
                        System.out.println("currentPriority = " + currentPriority);

                        if (currentPriority > assignNewPriority) {     //priority value decreased (e.g (current 5 to assignNew 3),
                            // need to increase priority value of intermediate items with priorities 3 and 4
                            //which can be found at element 2 and 3 in the list....

                            for (int i = assignNewPriority - 1; i < currentPriority - 1; i++) {
                                mainlistModel.getElementAt(i).changePriority(1);
                            }
                        }
                        if (currentPriority < assignNewPriority) {      //priority value (e.g. 3 to 5) , need to decrease value of priority 4, 5
                            //which can be found at element 3 and 4 in the list....

                            for (int i = currentPriority; i < assignNewPriority; i++) {
                                mainlistModel.getElementAt(i).changePriority(-1);
                            }
                        }

                        //sort by removing all items, sorting, and putting them back. There's probably a much nicer way to do this. 

                        ArrayList<Item> all = new ArrayList<Item>();
                        for (int x = 0; x < mainlistModel.size(); x++) {
                            all.add(mainlistModel.get(x));
                        }

                        Collections.sort(all);

                        mainlistModel.clear();

                        for (Item i : all) {
                            mainlistModel.addElement(i);
                        }
                    }
                }
            }
        });
        subListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String subList1name = MainList1.getSelectedValue().getItem();
                subList1label.setText(subList1name);
                addToSublist1Button.setText("Add to \n"+subList1name);
            }
        });
        addToSublist1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addItemTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(MainListGui.this, "Please enter an item \nto add to the list");
                } else {
                    String itemToAdd = addItemTextField.getText();
                    int priority = priorityCB.getSelectedIndex();

                    Item i = new Item(itemToAdd);


                    subList1Model.addElement(i);

                    for (int j = 1; j <= subList1Model.getSize(); j++) {
                        priorityCB.removeItem(j);
                        priorityCB.addItem(j);
                    }
                    addItemTextField.setText("");
                    addItemTextField.grabFocus();
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //show dialog with options to make sure the user wants to quir
                int quit = JOptionPane.showConfirmDialog(MainListGui.this, "Are you sure you want to quit?",
                        "Quit?", JOptionPane.OK_CANCEL_OPTION);
                if (quit == JOptionPane.OK_OPTION) {
                    System.exit(0);//close
                }
            }
        });
    }
}

