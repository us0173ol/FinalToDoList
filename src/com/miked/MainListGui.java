package com.miked;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by miked on 12/5/2016.
 */
public class MainListGui extends JFrame{
    private JPanel rootPanel;
    private JButton addButton;
    private JTextField addItemTextField;
    private JList<Item> MainList1;
    private JComboBox<Integer> priorityCB;
    private JLabel priorityTextField;

    private  DefaultListModel<Item> mainlistModel;


    public MainListGui() {

        super("To Do List");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(700, 600);

        mainlistModel = new DefaultListModel<Item>();
        MainList1.setModel(mainlistModel);

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
                }else {
                    String itemToAdd = addItemTextField.getText();
                    int priority = priorityCB.getSelectedIndex();

                    Item i = new Item(itemToAdd);

                    mainlistModel.addElement(i);

                    for (int j = 1; j <= mainlistModel.getSize(); j++) {
                        priorityCB.removeItem(j);
                        priorityCB.addItem(j);
                    }
                    addItemTextField.setText("");
                }
            }
        });


        priorityCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int assignNewPriority = priorityCB.getSelectedIndex()+1;
                Item selectedItem= MainList1.getSelectedValue();
                if (selectedItem != null){
                    selectedItem.getPriority();
                }

                for(int i =0; i < mainlistModel.getSize(); i++){
                    if( mainlistModel.getElementAt(i).getPriority()==assignNewPriority ){
                        mainlistModel.getElementAt(i).setPriority(selectedItem.getPriority());
                        MainList1.getSelectedValue().setPriority(assignNewPriority);
                    }


                }
            }
        });
    }
}
