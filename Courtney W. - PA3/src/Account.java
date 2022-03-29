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

/**
 * <strong>Account Class</strong>:
 * 
 * This is the class that holds the Accounts.
 * 
 * @author Courtney Woods
 * @version 3.0.0 March 13, 2022
 */
public class Account{
    /** 
     * object for checking account number
    */
    private String checkAccNum;
    
    /** 
     * object for saving account number
    */
    private String saveAccNum;

    Account(){}

    /** 
     * getter for getting the checking account number
     * @return String
     */
    public String getCheckingAccNum(){
        return this.checkAccNum;
    }

    /** 
     * getter for getting the saving account number
     * @return String
     */
    public String getSavingAccNum(){
        return this.saveAccNum;
    }

    /** 
     * setter for setting the checking account number
     * @param checkAccNum
     */
    public void setCheckingAccNum( String checkAccNum){
        this.checkAccNum = checkAccNum;
    }

    /** 
     * setter for getting the saving account number
     * @param saveAccNum
     */
    public void setSavingAccNum(String saveAccNum){
        this.saveAccNum = saveAccNum;
    }

}

