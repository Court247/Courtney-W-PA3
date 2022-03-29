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
/**
 * <strong>History Class</strong> 
 * 
 * This class holds the transaction history of a customer
 * 
 * @author Courtney Woods
 * @version 3.0.0 March 13, 2022
 */
public class History{
    /**
     * Holds either debit or credit
     */
    private String functionName;

    /**
     * transaction amount
    */
    private String amount;

    /**
     * Holds which account
     */
    private String accType;

    /** 
     * Holds if it's a transfer, withdraw, payment, deposit, or purchase
    */
    private String descript;

    /** 
     * increment
    */
    private int i =0;

    History(){    }

    /** 
     * Contructor 
     * @param function - debit or credit
     * @param Amount  - transaction amount
     * @param accT - account type
     * @param des - description of transaction
    */
    public History(String function, String Amount, String accT, String des){ 
        this.functionName = function;
        this.amount = Amount;
        this.accType = accT;
        this.descript = des;
    }   

    /**
     * 
     * @param i - sets increment
     */
    public void setI(int i){ this.i = i;}

    /**
     * returns i
     * @return int
     */
    public int getI(){return i;}

    /**
     * returns the function
     * @return String
     */
    public String getFunct(){return this.functionName;}
    
    /**
     * returns the transaction amount
     * @return String
     */
    public String getAmount(){return this.amount;}

    /**
     * Returns account type
     * @return String
     */
    public String getAccType(){return this.accType;}

    /**
     * Returns description
     * @return String
     */
    public String getDescript(){return this.descript;}
}