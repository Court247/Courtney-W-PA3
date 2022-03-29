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
 * <strong>Person Class</strong>:
 * 
 * This class holds the basic abstract information for a person. It just contains getters and setters for methods.
 * @author Courtney Woods
 * @version 3.0.0 March 13, 2022
 */
public class Person{
    
    /** 
     * First name of person
    */
    private String nameF;

    /** 
     * Last name of person
    */
    private String nameL;

    /**
     * Address of person
     */
    private String address;

    /**
     * Cist of person
     */
    private String city;

    /**
     * State of person
     */
    private String state;

    /**
     * Zip code of person
     */
    private int zip;

    /**
     * Phone number of person
     */
    private String phoneNum;

    /**
     * Birth date of person
     */
    private String DOB;

    Person(){

    }

    
    /** 
     * gets first name and returns a string
     * @return String
     */
    public String getNameF(){
        return this.nameF;
    }

    
    /** 
     * gets last name and returns a string
     * @return String
     */
    public String getNameL(){
        return this.nameL;
    }

    
    /** 
     * gets address and returns a string
     * @return String
     */
    public String getAddress(){
        return this.address;
    }

    
    /** 
     * gets City and returns a string
     * @return String
     */
    public String getCity(){
        return this.city;
    }

    
    /** 
     * gets State and returns a string
     * @return String
     */
    public String getState(){
        return this.state;
    }

    
    /** 
     * gets phone number and returns a string
     * @return String
     */
    public String getPhoneNum(){
        return phoneNum;
    }

    
    /** 
     * gets zip and returns an int
     * @return int
     */
    public int getZip(){
        return this.zip;
    }

    
    /** 
     * gets birth date and returns a string
     * @return String
     */
    public String getDOB(){
        return this.DOB;
    }

    
    /** 
     * sets first name
     * @param nameF - person first name
     */
    public void setNameF(String nameF) {
        this.nameF = nameF;
    }

    
    /** 
     * sets last name
     * @param nameL - person last name
     */
    public void setNameL(String nameL) {
        this.nameL = nameL;
    }

    
    /** 
     * sets address
     * @param address - person address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    
    /** 
     * sets city
     * @param city - person city
     */
    public void setCity(String city) {
        this.city = city;
    }

    
    /** 
     * sets state
     * @param state - person state
     */
    public void setState(String state) {
        this.state = state;
    }

    
    /** 
     * sets zip
     * @param zip - person zip
     */
    public void setZip(int zip) {
        this.zip = zip;
    }

    
    /** 
     * sets phone number
     * @param phoneNum - person phone number
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    
    /** 
     * sets birth date
     * @param dOB - person date of birth
     */
    public void setDOB(String dOB) {
        this.DOB = dOB;
    }

}