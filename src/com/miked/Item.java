package com.miked;

import java.util.Comparator;

/**
 * Created by miked on 12/5/2016.
 */
public class Item implements Comparable<Item>{

    private static int IDcounter = 1;
    private static int priority = 1;
    private String item;
    protected int itemIDnum;
    protected int currentPriority;


    Item(String item){
        this.currentPriority = priority;
        this.item = item;
        this.itemIDnum = IDcounter;

        IDcounter++;
        priority++;
    }


    Item(int priority, String item, int ID){
        this.priority = priority;
        this.item = item;
        this.itemIDnum = ID;

        IDcounter++;
    }

    public static int getIDcounter() {
        return IDcounter;
    }

    public static void setIDcounter(int IDcounter) {
        Item.IDcounter = IDcounter;
    }

    public int getPriority() {
        return currentPriority;
    }

    public void setPriority(int priority) {
        this.currentPriority = priority;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getItemIDnum() {
        return itemIDnum;
    }

    public void setItemIDnum(int itemIDnum) {
        this.itemIDnum = itemIDnum;
    }

    public String lineForfile(){
        String IDFF = Integer.toString(this.itemIDnum);
        String priorityFF = Integer.toString(this.currentPriority);
        return (IDFF + ";" + this.item + ";" + priorityFF);

    }
    public String toString(){
        return ("Priority: " + this.currentPriority + " " + this.item + " ID: " + this.itemIDnum);
    }
    
    public void changePriority(int delta) {
        this.currentPriority += delta;
    }
    
    @Override
    public int compareTo(Item o) {
        return currentPriority - o.currentPriority;
    }
}
