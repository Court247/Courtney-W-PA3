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
 * <strong>TransactionReader Class</strong>:
 * 
 * This class reads from the actions.csv file and allocates all the information
 * @author Courtney Woods
 * @version 3.0.0 March 13, 2022
 */
public class TransactionReader{

    /** 
     * Holds source account holder's first name
    */
    private String nameF;

    /**
     * Holds source account holder's last name
     */
    private String nameL;

    /**
     * holds source account type
     */
    private String accountS;

    /**
     * Holds action
     */
    private String action;

    /**
     * Holds destination account holder's first name
     */
    private String nameFD;

    /**
     * Holds destination account holder's last name
     */
    private String nameLD;

    /**
     * Holds destination account type
     */
    private String accountD;

    /**
     * Holds the amount
     */
    private double amount;

    /**
     * Holds miner mall item ID
     */
    private String mmID;

    /**
     * Holds miner mall item name
     */
    private String mmDes;


    
    /** 
     * Gets source account holder's first name
     * @return String
     */
    public String getNameF() {
        return nameF;
    }

    
    /** 
     * Sets source account holder's first name
     * @param nameF - Source First name
     */
    public void setNameF(String nameF) {
        this.nameF = nameF;
    }

    
    /** 
     * Gets source account holder's last name
     * @return String
     */
    public String getNameL() {
        return nameL;
    }

    
    /** 
     * Sets source account holder's last name
     * @param nameL - Source last name
     */
    public void setNameL(String nameL) {
        this.nameL = nameL;
    }

    
    /** 
     * Gets source account type
     * @return String
     */
    public String getAccountS() {
        return accountS;
    }

    
    /** 
     * Sets source account type
     * @param account - source account type
     */
    public void setAccount(String account) {
        this.accountS = account;
    }

    
    /** 
     * Gets action
     * @return String
     */
    public String getAction() {
        return action;
    }

    
    /** 
     * Sets action
     * @param action - action being taken
     */
    public void setAction(String action) {
        this.action = action;
    }

    
    /** 
     * Gets destination account holder's first name
     * @return String
     */
    public String getNameFD() {
        return nameFD;
    }

    
    /** 
     * Sets destination account holder's first name
     * @param nameFD - Destination first name
     */
    public void setNameFD(String nameFD) {
        this.nameFD = nameFD;
    }

    
    /** 
     * Gets destination account holder's last name
     * @return String
     */
    public String getNameLD() {
        return nameLD;
    }

    
    /** 
     * Sets destination account holder's last name'
     * @param nameLD - Destination last name
     */
    public void setNameLD(String nameLD) {
        this.nameLD = nameLD;
    }

    
    /** 
     * Gets destination account type
     * @return String
     */
    public String getAccountD() {
        return accountD;
    }

    
    /** 
     * Sets destination account type
     * @param accountD - Destination account type
     */
    public void setAccountD(String accountD) {
        this.accountD = accountD;
    }

    /** 
     * Gets amount
     * @return double
     */
    public double getAmount() {
        return amount;
    }

    /** 
     * Sets amount
     * @param amount - amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /** 
     * Gets MinerMall item ID
     * @return String
     */
    public String getMmID() {
        return mmID;
    }

    /** 
     * Sets MinerMall item ID
     * @param mmID - Item ID
     */
    public void setMmID(String mmID) {
        this.mmID = mmID;
    }

    /** 
     * Gets MinerMall item name
     * @return String
     */
    public String getMmDes() {
        return mmDes;
    }

    /** 
     * Sets MinerMall item name
     * @param mmDes - Item name
     */
    public void setMmDes(String mmDes) {
        this.mmDes = mmDes;
    }

}