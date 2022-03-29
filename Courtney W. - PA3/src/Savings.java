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

/**
 * <strong>Savings Class</strong>:
 * 
 * This class is the Savings account class. it holds the balance as well as run methods pertaining to the savings account
 * 
 * @author Courtney Woods
 * @version 3.0.0 March 13, 2022
 */
public class Savings extends Account{

    /**
     * Holds the savings account balance
     */
    private double savingBal;

    Savings(){}

    
    /** 
     * Gets savings account balance
     * @return double
     */
    public double getSavingBal(){
        return this.savingBal;
    }

    
    /** 
     * Sets savings account balance
     * @param savingBal - savings account balance
     */
    public void setSavingBal(double savingBal){
        this.savingBal = savingBal;
    }

    
    /** 
     * This method does the actual withdrawing from the savings account. it takes in an amount, customer instance list, and an integer
     * @param amount - amount to withdraw from savings account
     * @param customer - array list of customer accounts
     * @param x - location of customer in array list
     * @throws IOException - throws exception
     */
    public void withdrawSavings(double amount, Customer2 customer,int x)throws IOException{

        //initialize file name
        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";

        try(
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bankLog = new BufferedWriter(fw);){

            //cannot withdraw negative amounts
            if (amount < 0){

                System.out.println("Invalid.");

                //put into bank log
                bankLog.write(customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL() + " unsuccessfully withdrew $" + String.format("%.2f",amount) + " from Saving-" + super.getSavingAccNum()+ ". Reason: Invalid input.");
                bankLog.newLine();
            }
            //cannot withdraw more than in account
            else if(amount > this.savingBal){

                System.out.println("Insufficient Funds. Failed to withdraw from Savings.");

                //put into bank log
                bankLog.write(customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + " unsuccessfully withdrew $" + String.format("%.2f",amount) + " from Saving-" + super.getSavingAccNum()+ ". Reason: Insufficient funds.");
                bankLog.newLine();
            }

            else{

                //withdraw from checking.
                System.out.println("Success! Transaction Complete!");
                double newSBal = this.savingBal -= amount;

                //set new total
                setSavingBal(newSBal);

                //put into bank log
                bankLog.write(customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + " withdrew $" + String.format("%.2f", amount) + " from Saving-" + super.getSavingAccNum() + ". "+ customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL()+ "'s new balance: $"+ String.format("%.2f",this.savingBal));
                bankLog.newLine();

                customer.customers().get(x).addHist("Credit", String.valueOf(String.format("%.2f",amount)), "Saving", "Withdraw");

            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    
    /** 
     * This method does the actual depositing to the savings account. it takes in an amount, customer instance list, and an integer
     * @param amount - amount to deposit into savings account
     * @param customer - array list of customer accounts
     * @param x - location of customer in array list
     * @throws IOException - throws exception
     */
    public void depositSavings(double amount, Customer2 customer, int x)throws IOException{

        //initialize file writer
        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";

        try(
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bankLog = new BufferedWriter(fw);){

            //cannot deposit negative amounts
            if (amount < 0){

                System.out.println("Invalid");

                //put into bank log
                bankLog.write(customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL() + " unsuccessfully received $" + String.format("%.2f",amount) + " to Saving-" + super.getSavingAccNum()+ ". Reason: Invalid input.");
                bankLog.newLine();
            }
            else{

                //deposit to checking
                System.out.println("Success! Transaction Complete!");
                double newSBal = this.savingBal += amount;

                //update amount
                setSavingBal(newSBal);

                //put into bank log
                bankLog.write(customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + " received $" + String.format("%.2f",amount) + " to Saving-" + super.getSavingAccNum()+ ". "+ customer.customers().get(x).getNameF()+ " "+ customer.customers().get(x).getNameL()+ "'s new balance: $"+ String.format("%.2f",this.savingBal));
                bankLog.newLine();

                customer.customers().get(x).addHist("Debit", String.valueOf(String.format("%.2f",amount)), "Saving", "Deposit");

            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    
    /** 
     * This method hands the actual transferring to the savings accounts. it takes in an amount, customer instance list, and an integer. It returns a boolean. 
     * @param amount - amount to transfer into savings
     * @param customer - array list of customer accounts
     * @param x - location of customer in array list
     * @throws IOException - throws exception
     */
    public void transferToSavings(double amount, Customer2 customer, int x)throws IOException{
        //initialize file writer
        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";

        try(
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bankLog = new BufferedWriter(fw);){


            //cannot deposit negative amounts
            if (amount < 0){

                System.out.println("Invalid");

                //put int log
                bankLog.write(customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + " unsuccessfully transferred $" + String.format("%.2f", amount) + " to Saving-" + super.getSavingAccNum() + ". Reason: Invalid input.");
                bankLog.newLine();
            }
            else{

                //deposit to saving
                System.out.println("Success! Transaction Complete!");
                double newSBal = this.savingBal += amount;

                //update amount
                setSavingBal(newSBal);

                //put into log
                bankLog.write(customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + " transferred $" + String.format("%.2f", amount) + " to Saving-" + super.getSavingAccNum()+ ". " + customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + "'s balance for Saving-" + super.getSavingAccNum()+": $"+ String.format("%.2f", this.savingBal));
                bankLog.newLine();

                customer.customers().get(x).addHist("Debit", String.valueOf(String.format("%.2f",amount)), "Saving", "Transfer");

            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    
    /** 
     * This method does the actual transferring from the savings accounts. it takes in an amount, customer instance list, and an integer. It returns a boolean
     * @param amount - amount to transfer from savings
     * @param customer - array list of customer accounts
     * @param x - location of customer in array list
     * @return boolean
     * @throws IOException - throws exception
     */
    public boolean transferFromSavings(double amount, Customer2 customer, int x)throws IOException{
        boolean complete = false;
        //initialize file name
        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";

        try(
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bankLog = new BufferedWriter(fw);){

            //cannot withdraw negative amounts
            if (amount < 0){

                System.out.println("Invalid.");

                //put into log
                bankLog.write(customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL() + " unsuccessfully transferred $" + String.format("%.2f", amount) + " from Saving-" + super.getSavingAccNum() + ". Reason: Invalid input.");
                bankLog.newLine();
            }
            //cannot withdraw more than in account
            else if(amount > this.savingBal){

                System.out.println("Insufficient Funds. Failed to transfer from Savings.");

                //put into log
                bankLog.write(customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL() + " unsuccessfully transferred $" + String.format("%.2f", amount) + " from Saving-" + super.getCheckingAccNum() + ". Reason: Insufficient funds.");
                bankLog.newLine();
            }
            else{
                complete = true;
                //withdraw from checking.
                System.out.println("Success! Transaction Complete!");
                double newCBal = this.savingBal -= amount;

                //set new total
                setSavingBal(newCBal);

                //put into log
                bankLog.write(customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + " transferred $" + String.format("%.2f", amount) + " from Saving-" + super.getSavingAccNum()+ ". " + customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + "'s balance for Saving-" + super.getSavingAccNum()+": $" + String.format("%.2f", this.savingBal));
                bankLog.newLine();

                customer.customers().get(x).addHist("Credit", String.valueOf(String.format("%.2f",amount)), "Saving", "Transfer");

            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return complete;
    }


}