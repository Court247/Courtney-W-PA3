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
 * <strong>Checking Account Class</strong>: 
 * 
 * This extends the Account class and is the class that holds the checking account information.
 * 
 * @author Courtney Woods
 * @version 3.0.0 March 13, 2022
 */
public class Checking extends Account{

    /**
     * holds the balance in the checking account
     */
    private double checkingBal;
    
    Checking(){}

    /** 
     * returns the checking balance
     * @return double
     */
    public double getCheckingBal() {
        return this.checkingBal;
    }

    /** 
     * Sets the checking balance
     * @param checkingBal - checking account balance
     */
    public void setCheckingBal(double checkingBal) {
        this.checkingBal = checkingBal;
    }

    /** 
     * Withdraws from the checking account
     * @param amount - amount to withdraw
     * @param customer - customer array list
     * @param x - location of customer in array list
     * @throws IOException - throws exception
     */
    public void withdrawChecking(double amount, Customer2 customer, int x)throws IOException{
        //initialize file name
        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";
        try(
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bankLog = new BufferedWriter(fw);){

            //cannot withdraw negative amounts
            if (amount < 0){

                System.out.println("Invalid.");

                //put into bank log
                bankLog.write(customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + " unsuccessfully withdrew $" + String.format("%.2f",amount) + " from Checking-" + super.getCheckingAccNum()+ ". Reason: Invalid input.");
                bankLog.newLine();
            }
            //cannot withdraw more than in account
            else if(amount > this.checkingBal){

                System.out.println("Insufficient Funds. Failed to withdraw from Checking.");

                //put into bank log
                bankLog.write(customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL() + " unsuccessfully withdrew $" + String.format("%.2f",amount) + " from Checking-" + super.getCheckingAccNum() + ". Reason: Insufficient funds.");
                bankLog.newLine();
            }
            else{
                //withdraw from checking.
                System.out.println("Success! Transaction from Checking complete!");
                double newCBal = this.checkingBal -= amount;

                //set new total
                setCheckingBal(newCBal);

                //put into bank log
                bankLog.write(customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL() + " withdrew $" + String.format("%.2f",amount) + " from Checking-" + super.getCheckingAccNum() + ". "+ customer.customers().get(x).getNameF()+ " "+ customer.customers().get(x).getNameL() + "'s new balance: $"+ String.format("%.2f",this.checkingBal));
                bankLog.newLine();

                customer.customers().get(x).addHist("Credit", String.valueOf(String.format("%.2f",amount)), "Checking", "Withdraw");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /** 
     * Deposits to the checking account
     * @param amount - amount to deposit
     * @param customer - customer array list
     * @param x - location of customer in array list
     * @throws IOException - throws exception
     */
    public void depositChecking(double amount,Customer2 customer, int x)throws IOException{

        //initialize file writer
        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";

        try(
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bankLog = new BufferedWriter(fw);){

            //cannot deposit negative amounts
            if (amount < 0){

                System.out.println("Invalid input.");

                //put into bank log
                bankLog.write(customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL() + " unsuccessfully deposited $" + String.format("%.2f",amount) + " to Checking-" + super.getCheckingAccNum() + ". Reason: Invalid input.");
                bankLog.newLine();
            }
            else{

                //deposit to checking
                System.out.println("Success! Transaction Complete!");
                double newCBal = this.checkingBal += amount;

                // set new Checking balance
                setCheckingBal(newCBal);

                //put into bank log
                bankLog.write(customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + " deposited $" + String.format("%.2f",amount) + " to Checking-" + super.getCheckingAccNum() + ". "+ customer.customers().get(x).getNameF() + " "+ customer.customers().get(x).getNameL() + "'s new balance: $"+ String.format("%.2f",this.checkingBal));
                bankLog.newLine();

                customer.customers().get(x).addHist("Debit", String.valueOf(String.format("%.2f",amount)), "Checking", "Deposit");

            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /** 
     * Transfers from checking account
     * @param amount - amount to transfer from checking account
     * @param customer - customer array list
     * @param x - location in the array list
     * @return boolean
     * @throws IOException - throws exception
     */
    public boolean transferFromChecking(double amount, Customer2 customer, int x) throws IOException{
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
                bankLog.write(customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL() + " unsuccessfully transferred $" + amount + " from Checking-" + super.getCheckingAccNum() + ". Reason: Invalid input.");
                bankLog.newLine();
            }
            //cannot withdraw more than in account
            else if(amount > this.checkingBal){

                System.out.println("Insufficient Funds. Failed to transfer from Checking.");

                //put into log
                bankLog.write(customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL() + " unsuccessfully transferred $" + amount + " from Checking-" + super.getCheckingAccNum() + ". Reason: Invalid input.");
                bankLog.newLine();
            }
            else{
                complete = true;

                double newCBal = this.checkingBal -= amount;

                //set new total
                setCheckingBal(newCBal);

                //withdraw from checking.
                System.out.println("Success! Transaction Complete!");

                //put into log
                bankLog.write(customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL() + " transferred $" + amount + " from Checking-" + super.getCheckingAccNum()+ ". " + customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + "'s balance for Checking-" + super.getCheckingAccNum()+": $"+ this.checkingBal);
                bankLog.newLine();

                customer.customers().get(x).addHist("Credit", String.valueOf(String.format("%.2f",amount)), "Checking", "Transfer");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return complete;
    }

    /** 
     * Transfers to checking account
     * @param amount - amount to transfer to checking account
     * @param customer - customer array list
     * @param x - location of customer in array list
     * @throws IOException - throws exception
     */
    public void transferToChecking(double amount, Customer2 customer, int x)throws IOException{
        //initialize file writer
        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";

        try(
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bankLog = new BufferedWriter(fw);){

            //cannot deposit negative amounts
            if (amount < 0){

                System.out.println("Invalid");

                //put into log
                bankLog.write(customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + " unsuccessfully transferred $" + String.format("%.2f",amount) + " to Checking-" + super.getCheckingAccNum() + ". Reason: Invalid input.");
                bankLog.newLine();
            }
            else{

                //deposit to checking
                System.out.println("Success! Transaction Complete!");
                double newCBal = this.checkingBal += amount;

                //update amount
                setCheckingBal(newCBal);

                //put into log
                bankLog.write(customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL() + " transferred $" + String.format("%.2f",amount) + " to Checking-" + super.getCheckingAccNum()+ ". " + customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + "'s balance for Checking-" + super.getCheckingAccNum()+": $"+ this.checkingBal);
                bankLog.newLine();

                customer.customers().get(x).addHist("Debit", String.valueOf(String.format("%.2f",amount)), "Checking", "Deposit");
            }
            

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /** 
     * Buys using checking account
     * @param amount - amount to pay from checking account
     * @param customer - customer array list
     * @param loc - location of customer in the array list
     * @throws IOException - throws exception
     */
    public void bMCheckingBuy(double amount, Customer2 customer, int loc) throws IOException{
        
        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";
        
        try(FileWriter fw = new FileWriter(fileName, true);
        BufferedWriter bankLog = new BufferedWriter(fw);){

            // if the amount is negative
            if (amount <0){

                //print error
                System.out.println("Invalid: Reason: Negative Amount");

                //put into log
                bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " failed to purchase using Checking account.");
                bankLog.newLine();
            }else if(amount > this.checkingBal){

                System.out.println("Transaction failed. Reason: Insufficient funds.");

                //put into log
                bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " failed to purchase using Checking account.");
                bankLog.newLine();
            }else{

                System.out.println("Success! Transaction complete!");
                this.checkingBal -= amount;

                //put into log
                bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " successfully purchased " + String.format("%.2f", amount) + " from Checking-" + customer.customers().get(loc).check.getCheckingAccNum() + ". " + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + "'s checking account balance is: $" + String.format("%.2f", amount));
                bankLog.newLine();

                customer.customers().get(loc).addHist("Credit", String.valueOf(String.format("%.2f",amount)), "Checking", "Purchase from MinerMall");
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /** 
     * Payment to another customer using checking account
     * @param amount - amount to pay from checking account
     * @param customer - customer array list
     * @param loc - location of customer in the array list
     * @return boolean
     * @throws IOException - throws exception
     */
    public boolean CheckingPay(double amount, Customer2 customer, int loc) throws IOException{
        boolean paid = false;
        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";

        try(FileWriter fw = new FileWriter(fileName, true);
        BufferedWriter bankLog = new BufferedWriter(fw);){

            // if the amount is negative
            if (amount <0){

                System.out.println("Invalid: Reason: Negative Amount");

                //put into log
                bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " failed to purchase using Checking account.");
                bankLog.newLine();

            }else if(amount > this.checkingBal){

                System.out.println("Transaction failed. Reason: Insufficient funds.");

                //put into log
                bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " failed to purchase using Checking account.");
                bankLog.newLine();
            }else{
                paid = true;

                System.out.println("Success! Transaction complete!");

                //remove from checking balance
                this.checkingBal -= amount;

                //put into log
                bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " successfully purchased " + String.format("%.2f", amount) + " from Checking-" + customer.customers().get(loc).check.getCheckingAccNum() + ". " + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + "'s checking account balance is: $" + String.format("%.2f", amount));
                bankLog.newLine();

                customer.customers().get(loc).addHist("Credit", String.valueOf(String.format("%.2f",amount)), "Checking", "Payment");
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
        return paid;
    }


}