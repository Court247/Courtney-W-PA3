//Name: Courtney Woods
//Date: February 24, 2022
//Course: CS 3331
//Instructor: Daniel Mejia
//Assignment Name: Programming Assignment 2
//Lab Description: Program to maintain customer's money in bank.
/*Honesty Statement: This work is to be done individually. It is not permitted to share, reproduce, or alter any
part of this assignment for any purpose. Students are not permitted from sharing code,
uploading this assignment online in any form, or viewing/receiving/modifying code
written from anyone else. This assignment is part of an academic course at The
University of Texas at El Paso and a grade will be assigned for the work produced
individually by the student*/
import java.io.*;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <strong>Item Account Class</strong>:
 * 
 * This method creates the customer instance list. It is in charge of allocating information and verifying the identity of the customers. 
 * 
 * @author Courtney Woods
 * @version 3.0.0 March 13, 2022
 */
public class Item{

    /**
     * String object that holds the item ID
     */
    private String itemID;

    /**
     * String object that holds the item name
     */
    private String itemName;

    /** 
     * Double object that holds the item price
    */
    private double itemPrice;

    /**
     * Int object that holds the item max
     */
    private int itemMax;

    /**
     * Int object that holds the item quantity
     */
    private int itemQuantity;
    
    /**
     * Double object that holds the total price
     */
    private double totalPrice = 0;

    /**
     * Local time object that holds the time and date
     */
    LocalDateTime time = LocalDateTime.now();

    Item(){}

    /**
     * Item constructor that takes in an item and the local time
     * @param item - Item ID
     * @param time - local time
     */
    public Item(String item, LocalDateTime time){
        this.itemName = item;
        this.time = time;
    }
    
    /** 
     * Gets the item ID
     * @return String
     */
    public String getItemID() {
        return itemID;
    }

    /** 
     * Sets the item ID
     * @param itemID - Item ID
     */
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    /** 
     * Gets the item name
     * @return String
     */
    public String getItemName() {
        return itemName;
    }

    /** 
     * gets the item max
     * @return int
     */
    public int getItemMax(){
        return itemMax;
    }

    /** 
     * gets the item quantity
     * @return int
     */
    public int getItemQuantity() {
        return itemQuantity;
    }

    /** 
     * sets the item quantity
     * @param itemQuantity - item quantity
     */
    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    /** 
     * sets the item name
     * @param itemName - item name
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /** 
     * gets the item price
     * @return double
     */
    public double getItemPrice() {
        return itemPrice;
    }

    /** 
     * sets the item price
     * @param itemPrice - price of item
     */
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    /** 
     * sets the item max
     * @param itemMax - max item
     */
    public void setItemMax(int itemMax){
        this.itemMax = itemMax;
    }

    /** 
     * gets the total price
     * @return double
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * gets the time and date and formats it to a readable string and returns the time and date
     * @return String
     */
    public String getTime() {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String form = time.format(timeFormat);
        return form;
    }

    /**
     * gets the time and date and formats it to a readable string and returns date
     * @return String
     */
    public String getTime2() {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String form = time.format(timeFormat);
        return form;
    }

    /** 
     * Sets the time
     * @param time - local time
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * this method returns an integer that is the index of a specific item in an array.
     * @param word - word to search for
     * @param arr - string array to search in
     * @return int
     */
    public  int indexOfTitle(String word, String[] arr){
        int i = 0;
        while(i < arr.length){

            //if the item in the array matches the word we're looking for
            if (arr[i].equals(word)){

                //return i which is the index of the word
                return i;
            }
            i++;
        }
        //else return 0
        return 0;
    }

    /**
     * this method just prints all the items available for purchase
     * @throws IOException - throws exception
     */
    public  void itemsListing()throws IOException{
        String[] titles;
        String[] items;

        int c = 0;


        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\Miner Mall.csv";

        //read file
        File fr = new File(fileName);
        Scanner bankInfo = new Scanner(fr);

        String t = bankInfo.nextLine();
        titles = t.split(",");

        c++;

        //Fill array
        while(bankInfo.hasNextLine()){

            String sl = bankInfo.nextLine();
            items = sl.split(",");

            //sets the information at the index of the header word that was stored in titles array
            itemID = items[indexOfTitle("ID",titles)];
            itemName = items[indexOfTitle("Item", titles)];
            itemPrice = Double.valueOf(items[indexOfTitle("Price", titles)]);
            itemMax = Integer.valueOf(items[indexOfTitle("Max", titles)]);

            System.out.println("Item ID: " +getItemID()+ " Item: "+getItemName()+ " Price: "+ getItemPrice());

            c++;
        }
        bankInfo.close();

    }

    
    /** 
     * verifies the if the item exists
     * @param iD - item ID
     * @param it - item name
     * @param price - price of item
     * @return boolean
     * @throws IOException - throws exception
     */
    public boolean verifyItem(String iD, String it, double price) throws IOException{
        boolean found = false;
        int c = 0;
        
        //initialize arrays
        String[] titles;
        String[] items;

        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\Miner Mall.csv";

        //read file
        File fr = new File(fileName);
        Scanner bankInfo = new Scanner(fr);

        // fill in titles in titles array
        String t = bankInfo.nextLine();
        titles = t.split(",");

        c++;
        //Fill items array in the position of where the titles are in the titles array
        while(bankInfo.hasNextLine()){

            String sl = bankInfo.nextLine();
            items = sl.split(",");

            itemID = items[indexOfTitle("ID",titles)];
            itemName = items[indexOfTitle("Item", titles)];
            itemPrice = Double.valueOf(items[indexOfTitle("Price", titles)]);
            itemMax = Integer.valueOf(items[indexOfTitle("Max", titles)]);

            //if the input ID, Name, and Price matches the id, name and price in the items array
            if(iD.equals(itemID) && it.equals(itemName) && price == itemPrice){

                //change found to true
                found = true;

            }

            c++;
        }
        bankInfo.close();

        //return whatever the value is of found
        return found;
    }
}