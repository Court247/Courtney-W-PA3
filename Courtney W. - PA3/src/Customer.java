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
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * <strong>Customer Account Class</strong> 
 * 
 * this class extends the Person class. It holds the whole customer's account information. 
 * 
 * @author Courtney Woods
 * @version 3.0.0 March 13, 2022
 */

public class Customer extends Person {
    
    /**
     * Object that holds customer ID
    */
    private String ID;

    /**
     * Object that holds amount of money spent so far in Miner Bank
    */
    private double spentSoFar = 0.0;

    /** 
     * Holds a list of items
    */
    private ArrayList<Item> items;

    /**
     * Holds a list of transaction history
     */
    private ArrayList<History> history;

    /**
     * Holds the local time
     */
    private LocalDateTime time = LocalDateTime.now();

    /**
     * Calls an instance of class Item
     */
    Item it = new Item();

    /**
     * Calls an instance of class Checking
     */
    Checking check = new Checking();

    /**
     * Calls an instance of class Saving
     */
    Savings save = new Savings();

    /**
     * Calls an instance of class Credit
     */
    Credit credit = new Credit();

    /**
     * Calls an instance of class History
     */
    History hist = new History();

    /** */
    BankStatement bank = new BankStatement();

    /**
     * object that holds the price of all items bought from miner bank
     */
    private double price = 0;

    /**
     * incrementing integer
     */
    int x = 0;

    Customer(){
        items= new ArrayList<>();
        history = new ArrayList<>();
    }

    /**
     * returns the time
     * @return String
     */
    public String getTime2() {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String form = time.format(timeFormat);
        return form;
    }

    
    /** 
     * returns the customer ID
     * @return String
     */
    public String getID() {
        return this.ID;
    }

    
    /**
     * sets the customer ID
     * @param ID - customer ID
     */
    public void setID(String ID){
        this.ID = ID;
    }

    
    /** 
     * Adds an item to the Items array list
     * @param item - Item to add into items array list
     */
    public void addItem(Item item){
        items.add(item);
    }

    
    /** 
     * Returns the price 
     * @return double
     */
    public double getPrice() {
        return price;
    }

    
    /** 
     * sets the price
     * @param price - total price of items in cart
     */
    public void setPrice(double price) {
        this.price = price;
    }

    
    /** 
     * returns the money spent so far
     * @return double
     */
    public double getSpentSoFar() {
        return spentSoFar;
    }

    
    /** 
     * sets the money spent so far
     * @param spentSoFar - money spent so far in miner mall
     */
    public void setSpentSoFar(double spentSoFar) {
        this.spentSoFar += spentSoFar ;
    }

    /**
     * resets the price in the miner bank
     */
    public void resetPrice(){
        this.price = 0;
    }

    /**
     * returns the Items array list
     * @return ArrayList
     */
    public ArrayList<Item> getItems(){return items;}

    /**
     * return the History array list
     * @return ArrayList
     */
    public ArrayList<History> getHistory(){return history;}

    
    /** 
     * Checks if an item is in the list
     * @param ID - Item ID
     * @return boolean
     */
    public boolean inList(String ID){
        for(int i = 0; i < items.size(); i++){
            if (items.get(i).getItemID().equals(ID)){
                return true;
            }
        }
        return false;
    }

    
    /** 
     * returns the index of where an item is
     * @param ID - Item ID
     * @return int
     */
    public int find(String ID){
        int i = 0;
        while(i < items.size()){
            if (items.get(i).getItemID().equals(ID)){
                return i;
            }
        }
        return -1;
    }

    /**
     * this method  takes in an ID, customer instance list, and a user ID. It is in charge of adding to the Item instance list for when the user is purchasing from Miner Mall
     * @param ID - customer ID
     * @param customer - Customer array list
     * @param loc - location of customer in the array list
     * @throws IOException - throws IOException
     */
    public void addToCart(String ID, Customer2 customer, int loc)throws IOException{

        //changes the format of the time to make it more understandable.
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String form = it.time.format(timeFormat);

        String fileName2 = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";

        try(FileWriter fw = new FileWriter(fileName2, true);
                BufferedWriter bankLog = new BufferedWriter(fw);){
            String[] titles;
            String[] itemsList;
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
                itemsList = sl.split(",");

                if (itemsList[it.indexOfTitle("ID", titles)].equals(ID)){

                    System.out.println(x);

                    if(!items.isEmpty() && inList(ID)){

                        int locF = find(ID);

                        if(!(items.get(locF).getItemQuantity()> items.get(locF).getItemMax())){

                            items.get(locF).setItemQuantity(items.get(locF).getItemQuantity()+1);
                            price += items.get(locF).getItemPrice();

                            //put in bank log
                            bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " added "+ items.get(locF).getItemName() + " to cart at " + form);
                            bankLog.newLine();
                        }
                    }
                    else{
                        items.add(new Item());

                        items.get(x).setItemID(itemsList[it.indexOfTitle("ID", titles)]);
                        items.get(x).setItemName(itemsList[it.indexOfTitle("Item", titles)]);
                        items.get(x).setItemMax(Integer.valueOf(itemsList[it.indexOfTitle("Max", titles)]));
                        items.get(x).setItemPrice(Double.valueOf(itemsList[it.indexOfTitle("Price", titles)]));
                        items.get(x).setItemQuantity(items.get(x).getItemQuantity()+1);
                        price += items.get(x).getItemPrice();

                        //put in bank log
                        bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " added "+ items.get(x).getItemName() + " to cart at " + form);
                        bankLog.newLine();

                        x++;
                    }
                }
            }
            bankInfo.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * this method empties the cart so that if the customer wants to go back to Miner Mall the cart will be empty
     */
    public void emptyCart(){
        items.removeAll(items);
        this.x = 0;
    }

    /**
     * method simply prints the list so that the customers can see what is in their cart and the date/time they're added into the cart.
     */
    public void printList(){

        for(int i=0;i<items.size();i++){
            System.out.println(items.get(i).getItemName()+" , "+ items.get(i).getTime() + " Quantity: "+ items.get(i).getItemQuantity());
        }
    }

    
    /** 
     * Adds to the history ArrayList
     * @param func - function being done
     * @param string - amount of money
     * @param acc - account that is being modified
     * @param des - description of transaction
     */
    public void addHist(String func, String string,String acc, String des){
        history.add(new History(func, string, acc, des));
        hist.setI(hist.getI() + 1);
        
    }

    /** 
     * Traverses the history array list
    */
    public void printList2(){

        for(int i=0;i<history.size();i++){
            System.out.println(history.get(i).getAccType() + " " + history.get(i).getAmount() + " " + history.get(i).getDescript() + " " + history .get(i).getFunct());
        }
    }
    


}