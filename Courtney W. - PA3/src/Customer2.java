//Name: Courtney Woods
//Date: February 24, 2022
//Course: CS 3331
//Instructor: Daniel Mejia
//Assignment Name: Programming Assignment 2
//Lab Description: Program to maintain customer's money in bank.

import java.security.SecureRandom;
import java.util.*;
import java.io.*;

/**
 * <strong>Customer2 Account Class</strong>:
 * 
 * this method creates the customer instance list. It is in charge of allocating information and verifying the identity of the customers. 
 * 
 * @author Courtney Woods
 * @version 3.0.0 March 13, 2022
 */

public class Customer2 {

    /** 
     *ArrayList object that holds the customer instance
     */
    private ArrayList<Customer> customer;

    /**
     * ArrayList object that holds the transaction reader instance
    */
    private ArrayList<TransactionReader> trans;

    /**
     * Object that holds the last ID used
     */
    private int lastID;

    /**
     * object that holds the last checking account number user
     */
    private String lastCheck;

    /**
     * object that holds the last saving account number used
     */
    private String lastSave;

    /**
     * object that holds the last credit account number used
     */
    private String lastCredit;

    Customer2(){
        customer = new ArrayList<Customer>();
        trans = new ArrayList<TransactionReader>();
    }

    /** 
     * allows access to the customer class
     * @return ArrayList
     */
    public ArrayList<Customer> customers(){
        return customer;
    }

    /** 
     * Returns the Transaction Reader array list
     * @return ArrayList
     */
    public ArrayList<TransactionReader> tran(){
        return trans;
    }

    /** 
     * adds an empty customer account to customer list.
     * @param customerAcc - account to add to customer array list
     */
    public void addAcc(Customer customerAcc){
        customer.add(customerAcc);
    }

    /** 
     * Adds to the trans array list
     * @param transaction - transaction to add to array list
     */
    public void addTrans(TransactionReader transaction){
        trans.add(transaction);
    }

    /** 
     * returns the last ID
     * @return int
     */
    public int getLastID() {
        return lastID;
    }

    /** 
     * sets the last ID
     * @param lastID - last ID used
     */
    public void setLastID(int lastID) {
        this.lastID = lastID;
    }

    /** 
     * returns the last Checking account number used
     * @return String
     */
    public String getLastCheck() {
        return lastCheck;
    }

    
    /** 
     * sets the last checking account number used
     * @param lastCheck - last checking account number used
     */
    public void setLastCheck(String lastCheck) {
        this.lastCheck = lastCheck;
    }

    /** 
     * Returns the last savings account number used
     * @return String
     */
    public String getLastSave() {
        return lastSave;
    }

    
    /** 
     * sets the last savings account number used
     * @param lastSave - last savings account number used
     */
    public void setLastSave(String lastSave) {
        this.lastSave = lastSave;
    }

    /** 
     * Returns the last credit account number used
     * @return String
     */
    public String getLastCredit() {
        return lastCredit;
    }

    /** 
     * sets the last credit account number used
     * @param lastCredit - last credit account number used
     */
    public void setLastCredit(String lastCredit) {
        this.lastCredit = lastCredit;
    }

    /**
     * this method finds and returns the position of the csv title in the array.
     * @param word - word to search for
     * @param arr - array list to search in
     * @return int
     */
    public int indexOfTitle(String word, String[] arr){
        int i = 0;

        // traverses through array list
        while(i < arr.length){

            //if the string matches the value in the array
            if (arr[i].equals(word)){

                //return i which is the index in the array list of matching word
                return i;
            }
            i++;
        }

        //if it be found return -1
        return -1;
    }

    /**
     * this is the method that allocates the information in the instance list
     * @throws IOException - throws exception
     */
    public void allocate() throws IOException{
        String[] title;
        String[] custInfo;
        int c = 0;
        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\Bank Customers 3.csv";

        //read file
        File f = new File(fileName);
        Scanner bankInfo = new Scanner(f);

        //allocates title line into an array
        String titles = bankInfo.nextLine();
        title = titles.split(",");

        //adds the rest of the checking accounts into the customer list
        while(bankInfo.hasNextLine()){

            String sl = bankInfo.nextLine();
            custInfo = sl.split(",");

            //adds new instance of Customer into array list
            addAcc(new Customer());

            //fills in information
            customer.get(c).setID(custInfo[indexOfTitle("ID", title)]);
            customer.get(c).setNameF(custInfo[indexOfTitle("First Name", title)]);
            customer.get(c).setNameL(custInfo[indexOfTitle("Last Name", title)]);
            customer.get(c).setAddress(custInfo[indexOfTitle("Address", title)]);
            customer.get(c).setCity(custInfo[indexOfTitle("City", title)]);
            customer.get(c).setState(custInfo[indexOfTitle("State", title)]);
            customer.get(c).setZip(Integer.valueOf(custInfo[indexOfTitle("Zip", title)]));
            customer.get(c).setPhoneNum(custInfo[indexOfTitle("Phone Number", title)]);
            customer.get(c).setDOB(custInfo[indexOfTitle("Date of Birth", title)]);
            customer.get(c).check.setCheckingAccNum(custInfo[indexOfTitle("Checking Account Number", title)]);
            customer.get(c).check.setCheckingBal(Double.valueOf(custInfo[indexOfTitle("Checking Balance", title)]));
            customer.get(c).save.setSavingAccNum(custInfo[indexOfTitle("Savings Account Number", title)]);
            customer.get(c).save.setSavingBal(Double.valueOf(custInfo[indexOfTitle("Savings Balance", title)]));
            customer.get(c).credit.setAccNumCr(custInfo[indexOfTitle("Credit Account Number", title)]);
            customer.get(c).credit.setCreditBal(Double.valueOf(custInfo[indexOfTitle("Credit Balance", title)]));
            customer.get(c).credit.setCreditScore(Integer.valueOf(custInfo[indexOfTitle("Credit Score", title)]));
            customer.get(c).credit.setCreditLimit(Integer.valueOf(custInfo[indexOfTitle("Credit Limit", title)]));
            customer.get(c).bank.setInitialCheckBalance(customer.get(c).check.getCheckingBal());
            customer.get(c).bank.setInitialSaveBalance(customer.get(c).save.getSavingBal());
            customer.get(c).bank.setInitialCreditBalance(customer.get(c).credit.getCreditBal());

            c++;
        }
        bankInfo.close();
    }

    /**
     * this method is the initial verifying method for when the user logs in for the first time. it reads the ID, first and last name and compares it to the information stored, based on the outcome it returns a boolean.
     * @param uID - customer ID
     * @param fName - customer first name
     * @param lName - customer last name
     * @return boolean
     * @throws IOException - throws exception
     */
    public boolean verifyIdent(String uID, String fName, String lName)throws IOException {

        String tempID;
        String tempFN;
        String tempLN;
        boolean found = false;

        //search instance list and compare user input to what is on file.
        for(int i =0; i < customer.size();i++){
            tempID = customer.get(i).getID();
            tempFN = customer.get(i).getNameF();
            tempLN = customer.get(i).getNameL();

            //if the information is correct and the person is found 
            if (tempID.equals(uID) && tempFN.equals(fName) && tempLN.equals(lName)){

                //change found to true
                found = true;
            }
        }

        //return the value of found
        return found;
    }

    /**
    * this is the method that is used for when the bank manager is looking for an account via the first and last name.it does the same as the first verify ident method, but it does not require the ID.    * @param nameF
    * @param nameF - customer first name to search
    * @param nameL - customer last name to search
    * @return boolean
    * @throws IOException - throws exception
    */
    public boolean verifyIdent(String nameF, String nameL )throws IOException{
        boolean found = false;
        int i = 0;

        //search through customer instances and compares user input
        while (i < customer.size()){

            //if they all match
            if (nameF.equals(customer.get(i).getNameF()) && nameL.equals(customer.get(i).getNameL())){

                //change found to true
                found = true;
            }
            i++;
        }
        
        //return value of found
        return found;
    }

    /**
     *this method is used for when the bank manager is looking for an account via the account number, it compares the account number to the savings, checking and credit account number.then it returns a boolean
     * @param accNumber - account number to search
     * @return boolean
     * @throws IOException - throws IOException
     */
    public boolean verifyIdent(String accNumber)throws IOException{
        boolean found = false;

        //search through customer instances and compares user input
        for(int i =0; i < customer.size(); i++){

            //if they match
            if(accNumber.equals(customer.get(i).check.getCheckingAccNum()) || accNumber.equals(customer.get(i).save.getSavingAccNum()) || accNumber.equals(customer.get(i).credit.getAccNumCr())){
                //change found to true
                found = true;
            }
        }
        //return the value of found
        return found;
    }

    /**
     * this method is used to find the ID for when the bank manager is searching for the account via first and last name. it was made so that the it could make searching for the customer account easier.
     * @param nameF - customer first name
     * @param nameL - customer last name
     * @return int
     */
    public int findLoc(String nameF, String nameL){
        int i = 0;

        //search through customer accounts
        while (i < customer.size()){

            //if input name and last name matches the name in the customer array list instance
            if (nameF.equals(customer.get(i).getNameF()) && nameL.equals(customer.get(i).getNameL())){

                //if found return their location in the list
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * this method is used to find the ID for when the bank manager is searching for the account via account number. it was made so that the it could make searching for the customer account easier.
     * @param accN - account number 
     * @return int
     */
    public int findLoc(String accN){
    
        int i = 0;

        //search through customer accounts
        while(i < customer.size()){

            //compare the user inputs to what is on file
            if(accN.equals(customer.get(i).check.getCheckingAccNum()) || accN.equals(customer.get(i).save.getSavingAccNum())|| accN.equals(customer.get(i).credit.getAccNumCr())){
                
                //if the person is found return their location
                return i;
            }
            i++;
        }
        return 0;
    }

    /**
     * this method is for adding a new customer that is not in the Bank Customer file.
     * @param first - first name of new customer
     * @param last - last name of new customer
     */
    public void newCustomer(String first, String last){

        Random randCred = new SecureRandom();
        String address;
        String city;
        String state;
        int zip;
        String phone;
        String dob;
        double checkingBal = 0.0;
        double savingsBal = 0.0;
        double creditBal = 0.0;
        int creditScore = randCred.nextInt(950);
        int creditLimit = 0;


        int s = customer.size();
        Scanner newC = new Scanner(System.in);

        //adds an empty instance to the array list
        addAcc(new Customer());

        //finds the most recently used account number and id
        findNext();

        //has the new user input address
        System.out.println("Input address");
        address = newC.nextLine();

        //has the new user input city
        System.out.println("Input city");
        city = newC.nextLine();

        //has the new user input state
        System.out.println("Input state");
        state = newC.nextLine();

        //has the new user input zip
        System.out.println("Input zip");
        zip = newC.nextInt();

        //has the new user input phone number
        System.out.println("Input phone number");
        phone = newC.next();

        //has the new user input birth date
        System.out.println("Input date of birth (mm/dd/yy)");
        dob = newC.next();

        //allocate all the given information
        customer.get(s).setID(String.valueOf(lastID));
        customer.get(s).setNameF(first);
        customer.get(s).setNameL(last);
        customer.get(s).setAddress(address);
        customer.get(s).setCity(city);
        customer.get(s).setState(state);
        customer.get(s).setZip(zip);
        customer.get(s).setPhoneNum(phone);
        customer.get(s).setDOB(dob);

        //generates new acc number
        customer.get(s).check.setCheckingAccNum(lastCheck);
        customer.get(s).check.setCheckingBal(checkingBal);
        customer.get(s).save.setSavingAccNum(lastSave);
        customer.get(s).save.setSavingBal(savingsBal);
        customer.get(s).credit.setAccNumCr(lastCredit);
        customer.get(s).credit.setCreditBal(creditBal);
        customer.get(s).credit.setCreditScore(creditScore);
        customer.get(s).bank.setInitialCheckBalance(customer.get(s).check.getCheckingBal());
        customer.get(s).bank.setInitialSaveBalance(customer.get(s).save.getSavingBal());
        customer.get(s).bank.setInitialCreditBalance(customer.get(s).credit.getCreditBal());

        //generates credit limit for new user
        if (creditScore < 580){
            int min = 100;
            int max = 699;

            //random generated between 100 and 699
            creditLimit = randCred.nextInt(((max-min)+1) +min);

        }else if (creditScore > 580 && creditScore < 669){
            int min = 700;
            int max = 4999;

             //random generated between 700 and 4999
            creditLimit = randCred.nextInt(((max-min)+1) +min);

        }else if (creditScore > 670 && creditScore < 739){
            int min = 5000;
            int max = 7499;

             //random generated between 5000 and 74999
            creditLimit = randCred.nextInt(((max-min)+1) +min);

        }else if ( creditScore > 740 && creditScore < 799){
            int min = 7500;
            int max = 15999;

            //random generated between 7500 and 15999
            creditLimit = randCred.nextInt(((max-min)+1) +min);
        }else{
            int min = 16000;
            int max = 25000;

             //random generated between 16000 and 25000
            creditLimit = randCred.nextInt(((max-min)+1) +min);
        }

        //set the random generated credit limit
        customer.get(s).credit.setCreditLimit(creditLimit);

    }

    /**
     * this method is for  finding the most recently used to generate and keep track of the new customer ID, Checking/Saving/Credit account numbers.
     */
    public void findNext(){
        int i = 0;
        int id = 1;
        int checkAcc = 1000001;
        int saveAcc = 2000001;
        int credAcc = 3000001;

        while(i < customer.size()-1){

            //if the id is used do nothing
            if (id == Integer.valueOf(customer.get(i).getID())){
            }

            //if the check acc number is used, do nothing
            else if(checkAcc == Integer.valueOf(customer.get(i).check.getCheckingAccNum())){
            }

            //if the savings acc number is used, do nothing
            else if(saveAcc == Integer.valueOf(customer.get(i).save.getSavingAccNum())){
            }
            
            // the credit account number is used, do nothing
            else if(credAcc == Integer.valueOf(customer.get(i).credit.getAccNumCr())){
            }
            i++;
            id++;
            checkAcc++;
            saveAcc++;
            credAcc++;
        }
        //sets the number that is NOT used
        setLastID(id);
        setLastCheck(String.valueOf(checkAcc));
        setLastSave(String.valueOf(saveAcc));
        setLastCredit(String.valueOf(credAcc));

    }

    /**
     *  this method simply reads and allocates information in the actions.csv file to the transaction reader instances
     * @throws IOException - throws an IOException
     */
    public void read() throws IOException{

        String file = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\actions.csv";

        String[] title;
        String[] actions;
        int c = 0;
        File f = new File(file);
        Scanner info = new Scanner(f);

        String t = info.nextLine();
        title = t.split(",");

        while(info.hasNextLine()){

            String sl = info.nextLine();
            actions = sl.split(",");

            //add new TR instance
            addTrans(new TransactionReader());

            //allocates information from correct place in the actions array
            trans.get(c).setNameF(actions[indexOfTitle("Source First Name", title)]);
            trans.get(c).setNameL(actions[indexOfTitle("Source Last Name", title)]);
            trans.get(c).setAccount(actions[indexOfTitle("Source Account", title)]);
            trans.get(c).setAction(actions[indexOfTitle("Action", title)]);
            trans.get(c).setNameFD(actions[indexOfTitle("Destination First Name", title)]);
            trans.get(c).setNameLD(actions[indexOfTitle("Destination Last Name", title)]);
            trans.get(c).setAccountD(actions[indexOfTitle("Destination Account", title)]);
            trans.get(c).setAmount(Double.valueOf(actions[indexOfTitle("Amount", title)]));
            trans.get(c).setMmID(actions[indexOfTitle("Miner Mall ID", title)]);
            trans.get(c).setMmDes(actions[indexOfTitle("Miner Mall Description", title)]);

            c++;

        }

        info.close();
    }
}
