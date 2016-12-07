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
public class MainListGui extends JFrame{
    private JPanel rootPanel;
    private JButton addButton;
    private JTextField addItemTextField;
    private JList<Item> MainList1;
    private JComboBox<Integer> priorityCB;
    
    
    private JLabel priorityTextField;

    private  DefaultListModel<Item> mainlistModel;

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
    
                System.out.println("ACTION PERFORMED");

               // int assignNewPriority = priorityCB.getSelectedIndex()+1;
                
                if (priorityCB.getSelectedIndex() != -1 ) {
    
                    int assignNewPriority = (int) priorityCB.getSelectedItem();
                    
                    Item selectedItem= MainList1.getSelectedValue();
    
                    System.out.println("selectedItem " + selectedItem);
    
                    if (selectedItem != null ){
                        int currentPriority = selectedItem.getPriority();
                        
                        //set the selected item to have priority from the JComboBox
                        //move other items to have greater or less priority
                        
                        //Assume list is sorted in priority order - todo sort list as items are added - support future adding of items in any priority order
                        
                        //Example: list with 7 items, 1-7. If item with current priority 5 is moved to priority 3, then item 3 needs to move to 4, item 4 needs to move to 5
                        //Example: list with 7 items, 1-7. If item with current priority 3 is moved to priority 5, then item 4 needs to move to 3, item 5 needs to move to 4
    
                        // Once priorities are changed, re-sort list.
                        
                        selectedItem.setPriority(assignNewPriority);
    
                        System.out.println("assignNewPriority " + assignNewPriority);
                        System.out.println("currentPriority = " + currentPriority);
    
                        ///todo test
                        if (currentPriority > assignNewPriority) {     //priority value decreased (e.g (current 5 to assignNew 3),
                                                            // need to increase priority value of intermediate items with priorities 3 and 4
                                                            //which can be found at element 2 and 3 in the list....
                            
                            for (int i = assignNewPriority-1 ; i < currentPriority-1 ; i++) {
                                mainlistModel.getElementAt(i).changePriority(1);
                            }
                        }
                        
                        //todo test
                        if (currentPriority < assignNewPriority) {      //priority value (e.g. 3 to 5) , need to decrease value of priority 4, 5
                                                                        //which can be found at element 3 and 4 in the list....
    
                            for (int i = assignNewPriority ; i < currentPriority ; i++) {
                                mainlistModel.getElementAt(i).changePriority(-1);
                            }
                        }
                        
                        //sort by removing all items, sorting, and putting them back. There's probably a much nicer way to do this. 
                        
                        ArrayList<Item> all = new ArrayList<Item>();
                        for (int x = 0 ; x < mainlistModel.size(); x++) {
                            all.add(mainlistModel.get(x));
                        }
    
                        Collections.sort(all);
                        
                        mainlistModel.clear();
    
                        for (Item i : all) {
                            mainlistModel.addElement(i);
                        }




//                        for(int i =0; i < mainlistModel.getSize(); i++){
//                            if( mainlistModel.getElementAt(i).getPriority()==assignNewPriority ){
//                                mainlistModel.getElementAt(i).setPriority(selectedItem.getPriority());
//                                MainList1.getSelectedValue().setPriority(assignNewPriority);
                        
                              }
//                        }
                        
                     

                    
    
                    
    
                }
                
               
            }
        });
    }
}
