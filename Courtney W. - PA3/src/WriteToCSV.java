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

/**
 * <strong>WriteToCSV Class</strong>:
 * 
 * This class is in charge of writing the bank information to the cvs file.
 * @author Courtney Woods
 * @version 3.0.0 March 13, 2022
 */

public class WriteToCSV{

    /**
     * initialize file writer as null
     */
    FileWriter writeToCSV = null;

    /** 
     * Sets input file destination
     */
    String file = ("C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankCustomerUpdate2.csv");

    /**
     * initialize scanner
     */
    Scanner bf = new Scanner(file);

    /**
     * writes all the customer account information to the CVS file
     * @param customer - array list of customer accounts
     * @throws IOException - throws exception
     */
    public void writeCSV(Customer2 customer) throws IOException{
        writeToCSV = new FileWriter(file);

        try{

            writeToCSV.append("ID, First Name, Last Name, Address, City, State, Zip, Phone Number, Date of Birth, Checking Account Number, Checking Balance, Savings Account Number, Savings Balance, Credit Account Number, Credit Balance, Credit Score, Credit Limit");
            writeToCSV.append("\n");

            for(Customer cus: customer.customers()){

                writeToCSV.append(cus.getID() + "," + cus.getNameF() + "," + cus.getNameL() + "," + cus.getAddress() + "," + cus.getCity() + "," + cus.getState() + "," + cus.getZip() + "," + cus.getPhoneNum() + "," + cus.getDOB() + "," + cus.check.getCheckingAccNum() + "," + String.format("%.2f",cus.check.getCheckingBal()) + "," + cus.save.getSavingAccNum() + "," + String.format("%.2f",cus.save.getSavingBal()) + "," + cus.credit.getAccNumCr() + "," + String.format("%.2f",cus.credit.getCreditBal()) + "," + cus.credit.getCreditScore() + "," + cus.credit.getCreditScore());
                writeToCSV.append("\n");
            }
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                writeToCSV.flush();
                writeToCSV.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    
    /** 
     * writes all the customer account information to the CVS file and changes the most recent peron's information
     * @param customer - array list of customer accounts
     * @param x - location of customer in array list
     * @throws IOException - throws exception 
     */
    public void writeCSV(Customer2 customer, int x) throws IOException{
        writeToCSV = new FileWriter(file);

        try{

            writeToCSV.append("ID, First Name, Last Name, Address, City, State, Zip, Phone Number, Date of Birth, Checking Account Number, Checking Balance, Savings Account Number, Savings Balance, Credit Account Number, Credit Balance, Credit Score, Credit Limit");
            writeToCSV.append("\n");

            for(Customer cus: customer.customers()){
                writeToCSV.append(cus.getID() + "," + cus.getNameF() + "," + cus.getNameL() + "," + cus.getAddress() + "," + cus.getCity() + "," + cus.getState() + "," + cus.getZip() + "," + cus.getPhoneNum() + "," + cus.getDOB() + "," + cus.check.getCheckingAccNum() + "," + String.format("%.2f",cus.check.getCheckingBal()) + "," + cus.save.getSavingAccNum() + "," + String.format("%.2f",cus.save.getSavingBal()) + "," + cus.credit.getAccNumCr() + "," + String.format("%.2f",cus.credit.getCreditBal()) + "," + cus.credit.getCreditScore() + "," + cus.credit.getCreditScore());
                writeToCSV.append("\n");
                if(Integer.valueOf(cus.getID()) == x){

                    writeToCSV.append(cus.getID() + "," + cus.getNameF() + "," + cus.getNameL() + "," + cus.getAddress() + "," + cus.getCity() + "," + cus.getState() + "," + cus.getZip() + "," + cus.getPhoneNum() + "," + cus.getDOB() + "," + cus.check.getCheckingAccNum() + "," + String.format("%.2f",cus.check.getCheckingBal()) + "," + cus.save.getSavingAccNum() + "," + String.format("%.2f",cus.save.getSavingBal()) + "," + cus.credit.getAccNumCr() + "," + String.format("%.2f",cus.credit.getCreditBal()) + "," + cus.credit.getCreditScore() + "," + cus.credit.getCreditScore());
                    writeToCSV.append("\n");
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                writeToCSV.flush();
                writeToCSV.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}

