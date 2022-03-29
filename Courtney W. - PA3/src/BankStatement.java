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
import java.util.*;
import java.awt.*;

/**
 * <strong>BankStatement Class</strong>:
 * 
 * This is the class that holds the checking account information.
 * 
 * @author Courtney Woods
 * @version 3.0.0 March 13, 2022
 */
public class BankStatement  {

    /** 
     * Hash map for writing to txt file
    */
    private HashMap<Point, String> bankStatement = new HashMap<Point, String>();

    /**
     * object for columns
     */
    private int col;

    /**
     * object for rows
     */
    private int row;

    /**
     * object for the initial checking balance
     */
    private double initialCheckBalance;

    /**
     * object for the initial saving balance
     */
    private double initialSaveBalance;

    /**
     * object for initial credit balance
     */
    private double initialCreditBalance;

    /**
     * object for after transactions checking balance
     */
    private double afterCheckBalance;

    /**
     * object for after transactions saving balance
     */
    private double afterSaveBalance;

    /**
     * object for after transactions credit balance
     */
    private double afterCreditBalance;

    /**
     * returns col
     * @return int
     */
    public int getCol(){return this.col;}

    /**
     * returns row
     * @return int
     */
    public int getRow(){ return this.row; }

    /** 
     * returns the initial checking balance
     * @return double
    */
    public double getInitialCheckBalance(){return initialCheckBalance;}

    /**
     * returns initial saving balance
     * @return double
     */
    public double getInitialCSaveBalance(){return initialSaveBalance;}

    /**
     * returns initial credit balance
     * @return double
     */
    public double getInitialCreditBalance(){return initialCreditBalance;}

    /**
     * sets the initial checking balance
     * @param initialBalance - initial checking balance
     */
    public void setInitialCheckBalance(double initialBalance){this.initialCheckBalance = initialBalance;}

    /**
     * sets the initial saving balance
     * @param initialBalance - initial saving balance
     */
    public void setInitialSaveBalance(double initialBalance){this.initialSaveBalance = initialBalance;}

    /**
     * sets the initial credit balance
     * @param initialBalance - initial credit balance
     */
    public void setInitialCreditBalance(double initialBalance){this.initialCreditBalance = initialBalance;}

    /**
     * returns after checking balance
     * @return double
     */
    public double getAfterCheckBalance(){return afterCheckBalance;}

    /**
     * returns after saving balance
     * @return double
     */
    public double getAfterSaveBalance(){return afterSaveBalance;}

    /**
     * returns after credit balance
     * @return double
     */
    public double getAfterCreditBalance(){return afterCreditBalance;}

    /**
     * sets the after checking balance
     * @param afterBalance - after transactions checking balance
     */
    public void setAfterCheckBalance(double afterBalance){this.afterCheckBalance = afterBalance;}

    /**
     * sets the after saving balance
     * @param afterBalance after transactions saving balance
     */
    public void setAfterSaveBalance(double afterBalance){this.afterSaveBalance = afterBalance;}

    /**
     * sets the after credit balance
     * @param afterBalance - after transaction credit balance
     */
    public void setAfterCreditBalance(double afterBalance){this.afterCreditBalance = afterBalance;}

    /** 
     * this method gets the info in the hash map
     * @param col - column number
     * @param row - row number
     * @return String
     */
    public String getInfo(int col, int row){
        
        // set data string to empty
        String data = "";

        //set a new point with integers column and row
        Point info = new Point(col, row);

        //if the bank statement file contains information
        if(bankStatement.containsKey(info)){

            //set data to that key
            data = bankStatement.get(info);
        }

        //return string
        return data;
    }

    /** 
     * this method adds the info into the hash map
     * @param col - column number
     * @param row - row number
     * @param data - information to add to location
     * @throws IOException - throws exception
     */
    public void addInfo(int col, int row, String data) throws IOException{

        //create a new point at integers col and row and set the data you want to insert at this point
        bankStatement.put(new Point(col, row), data);

        //sets this.col as the max between this.col and col +1
        this.col = Math.max(this.col, col+1);

        //sets this.row as the max between this.row and row +1
        this.row = Math.max(this.row, row+1);
    }

    /**
     * this method clears the info in the hash map
     */
    public void clearInfo(){

        //clears bankStatement
        bankStatement.clear();

        //sets col to 0
        this.col = 0;

        //sets row to 0
        this.row = 0;
    }

    /** 
     * this method saves the file
     * @param file - file location
     * @throws IOException - throws exception
     */
    public void saveInfo(File file) throws IOException {

        //selects the file and the delimiter value
        saveInfo(file, ',');
    }

    /** 
     * this method saves the hash map info
     * @param file- file location
     * @param delim - delimiter
     * @throws IOException - throws exception
     */
    public void saveInfo(File file, char delim)throws IOException{

        //initializes a file writer
        FileWriter f = new FileWriter(file);

        //initializes a buffered writer
        BufferedWriter bw = new BufferedWriter(f);

        //row 
        for (int row = 0; row < this.row; row++) {

            //for columns
            for (int col = 0; col < this.col; col++) {

                //initializes a point as a new point at col and row
                Point data = new Point(col, row);

                // if bankStatement contains data
                if (bankStatement.containsKey(data)) {

                    // write into data that is saved there
                    bw.write(bankStatement.get(data));
                }

                // col is less than this.col
                if ((col + 1) < this.col) {

                    //write the delimiter
                    bw.write(delim);
                }
            }

            //create new line
            bw.newLine();
        }

        //flush bw
        bw.flush();

        //close bw
        bw.close();

    }

}
