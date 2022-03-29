//Name: Courtney Woods
//Date: February 24, 2022
//Course: CS 3331
//Instructor: Daniel Mejia
//Assignment Name: Programming Assignment 3
//Lab Description: Program to maintain customer's money in bank.
/*Honesty Statement: This work is to be done individually. It is not permitted to share, reproduce, or alter any
part of this assignment for any purpose. Students are not permitted from sharing code,
uploading this assignment online in any form, or viewing/receiving/modifying code
written from anyone else. This assignment is part of an academic course at The
University of Texas at El Paso and a grade will be assigned for the work produced
individually by the student*/
import java.io.*;

/**
 * <strong>Credit Account Class</strong>:
 * 
 * This is the class that holds the credit account information.
 * 
 * @author Courtney Woods
 * @version 3.0.0 March 13, 2022
 */

public class Credit {

    /**
     * Object that holds the credit account number
     */
    private String accNumCr;

    /**
     * object that holds the credit account balance
     */
    private double creditBal;

    /**
     * object that holds the credit score
     */
    private int creditScore;

    /**
     * object that holds the credit limit
     */
    private int creditLimit;

    Credit(){}

    
    /** 
     * Returns the credit account number
     * @return String
     */
    public String getAccNumCr(){
        return this.accNumCr;
    }

    
    /** 
     * Returns the credit account balance
     * @return double
     */
    public double getCreditBal(){
        return this.creditBal;
    }

    
    /** 
     * returns the credit score
     * @return int
     */
    public int getCreditScore(){
        return this.creditScore;
    }

    
    /** 
     * returns the credit limit
     * @return int
     */
    public int getCreditLimit(){
        return this.creditLimit;
    }

    
    /** 
     * sets the credit account number
     * @param accNumCr - credit account number
     */
    public void setAccNumCr(String accNumCr){
        this.accNumCr = accNumCr;
    }

    
    /** 
     * Sets the credit account balance
     * @param creditBal - credit account balance
     */
    public void setCreditBal(double creditBal){
        this.creditBal = creditBal;
    }

    
    /** 
     * Sets the credit score
     * @param creditScore - customer credit score
     */
    public void setCreditScore(int creditScore){
        this.creditScore = creditScore;
    }

    
    /** 
     * Sets the credit limit
     * @param creditLimit - customer credit limit
     */
    public void setCreditLimit(int creditLimit){
        this.creditLimit = creditLimit;
    }

    
    /**
     * Deposits into the credit account
     * @param amount - amount to deposit into credit account
     * @param customer - array list of customer
     * @param x - location of customer in the array list
     * @throws IOException - throws IOException
     */
    public void depositCredit(double amount, Customer2 customer, int x)throws IOException{

        //initialize file writer
        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";

        try(
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bankLog= new BufferedWriter(fw);){

            //cannot deposit negative amounts
            if (amount < 0){

                System.out.println("Invalid");

                //put into bank log
                bankLog.write(customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL() + " unsuccessfully received $" + String.format("%.2f",amount) + " to Credit-" + getAccNumCr() + ". Reason: Invalid input.");
                bankLog.newLine();
            }
            else if(this.creditBal > this.creditLimit){

                System.out.println("Transaction Failed. Reason: Cannot go over credit limit");

                //put into bank log
                bankLog.write(customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL() + " unsuccessfully received " + String.format("%.2f", amount )+ " to Credit-" + getAccNumCr() + ". Reason: Cannot exceed credit limit");
                bankLog.newLine();
            }
            else{

                //deposit to checking
                System.out.println("Success! Transaction Complete!");
                double newCrBal = this.creditBal -= amount;

                //update amount
                setCreditBal(newCrBal);

                //put into bank log
                bankLog.write(customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL() + " deposited $" + String.format("%.2f",amount) + " to Credit-" + getAccNumCr() + ". "+ customer.customers().get(x).getNameF() + " "+ customer.customers().get(x).getNameL() + "'s new balance: $"+ String.format("%.2f",this.creditBal));
                bankLog.newLine();

                customer.customers().get(x).addHist("Debit", String.valueOf(String.format("%.2f",amount)), "Credit", "Deposit");

            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    
    /** 
     * Transfers into the credit account
     * @param amount - amount to transfer into credit account
     * @param customer - Array list of customer account
     * @param x - location of customer in the array list
     * @throws IOException - throws exception
     */
    public void transferToCredit(double amount, Customer2 customer, int x)throws IOException{
        //initialize file writer
        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";

        try(
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bankLog = new BufferedWriter(fw);){


            //cannot deposit negative amounts
            if (amount < 0){

                System.out.println("Invalid");

                //put into log
                bankLog.write(customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + " unsuccessfully transferred $" + String.format("%.2f", amount) + " to Credit-" + getAccNumCr() + ". Reason: Invalid input.");
                bankLog.newLine();
            }
            else{

                //deposit to checking
                double newCrBal = this.creditBal -= amount;

                //update amount
                setCreditBal(newCrBal);

                //put into log
                bankLog.write(customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL() + " transferred $" + String.format("%.2f", amount) + " to Credit-" + getAccNumCr()+ ". " + customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + "'s balance for Credit-" + getAccNumCr() +": $"+ String.format("%.2f", this.creditBal));
                bankLog.newLine();

                customer.customers().get(x).addHist("Debit", String.valueOf(String.format("%.2f",amount)), "Credit", "Transfer");

            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    

    /** 
     * Pays using credit account balance
     * @param amount - amount to pay from credit account
     * @param customer - array list of customer account
     * @param x - location of customer in the array list
     * @throws IOException - throws exception
     */
    public void payCredit(double amount, Customer2 customer, int x) throws IOException{

         //initialize file writer
        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";

        try(
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bankLog = new BufferedWriter(fw);){

            //cannot withdraw negative amounts
            if (amount < 0){

                System.out.println("Invalid");

                //put into log
                bankLog.write(customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + " unsuccessfully paid $" + String.format("%.2f", amount) + " from Credit-" + getAccNumCr()+ ". Reason: Invalid input.");
                bankLog.newLine();
            }
            else if(this.creditBal > this.creditLimit){

                System.out.println("Cannot accept payment");

                //put in bank log
                bankLog.write(customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL() + " unsuccessfully paid $" + String.format("%.2f", amount) + " from Credit-" + getAccNumCr()+ ". Reason: Negative balance.");
                bankLog.newLine();
            }
            else{

                // add from credit
                System.out.println("Success! Transaction Complete!");
                double newCrBal = this.creditBal += amount;

                //update amount
                setCreditBal(newCrBal);

                //put into log
                bankLog.write(customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + " paid $" + String.format("%.2f", amount) + " from Credit-" + getAccNumCr() + ". "+ customer.customers().get(x).getNameF()+ " "+ customer.customers().get(x).getNameL()+ "'s new balance: $" + String.format("%.2f", this.creditBal));
                bankLog.newLine();

                customer.customers().get(x).addHist("Credit", String.valueOf(String.format("%.2f",amount)), "Credit", "Purchase from MinerMall");

            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    
    /** 
     * Pays using Credit account balance
     * @param amount - amount to pay using credit account
     * @param customer - array list of customer accounts
     * @param loc - location of customer in array list
     * @return boolean
     * @throws IOException - throws IOException
     */
    public boolean CreditPay(double amount, Customer2 customer, int loc) throws IOException{
        boolean paid = false;
        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";

        try(FileWriter fw = new FileWriter(fileName, true);
        BufferedWriter bankLog = new BufferedWriter(fw);){

            //if amount is negative
            if (amount < 0){

                //print error
                System.out.println("Invalid: Reason: Negative Amount");

                //write in bankLog
                bankLog.write(customer.customers().get(loc).getNameF()+ " " + customer.customers().get(loc).getNameL()+ " unsuccessfully paid $" + String.format("%.2f", amount) + " from Credit-" + getAccNumCr());
                bankLog.newLine();

            }
            //if the credit balance exceeds credit limit
            else if(this.creditBal > this.creditLimit){

                //cannot take funds
                System.out.println("Cannot exceed credit limit. Transaction failed.");

                //put in bankLog
                bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " unsuccessfully paid $" + String.format("%.2f", amount) + " from Credit-" + getAccNumCr());
                bankLog.newLine();

            }

            else{

                paid = true;

                //execute transaction
                System.out.println("Success! Transaction complete!");
                this.creditBal += amount;

                //put into log
                bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " successfully paid $" + String.format("%.2f", amount) + " from Credit-" + getAccNumCr() + "." + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + "'s new Credit-" + getAccNumCr() + " balance is: $" + String.format("%.2f", this.creditBal));
                bankLog.newLine();

                customer.customers().get(loc).addHist("Credit", String.valueOf(String.format("%.2f",amount)), "Credit", "Payment");
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
        return paid;
    }


}