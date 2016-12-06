package com.miked;

/**
 * Created by miked on 12/5/2016.
 */
public class Item {

    private static int IDcounter = 1;
    private int priority = 1;
    private String item;
    protected int itemIDnum;


    Item(String item){
        //this.priority = priority +1;
        this.item = item;
        //this.itemIDnum = IDcounter;

        IDcounter++;
    }


    Item( String item, int ID){
        //this.priority = priority;
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
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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
        String priorityFF = Integer.toString(this.priority);
        return (IDFF + ";" + this.item + ";" + priorityFF);

    }

}
