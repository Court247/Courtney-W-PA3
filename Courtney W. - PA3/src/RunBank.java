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
import java.time.format.DateTimeFormatter;
import java.util.*;

/** 
 *  
 * RunBank Class
 * 
 * This method holds the bulk of the bank account and the main method.That runs the whole bank.
 * @author Courtney Woods
 * @version 3.0.0 March 13, 2022
 * 
*/
public class RunBank{
    /**
     * This calls an instance of the bank statement class
    */
    static BankStatement bank = new BankStatement();

    
    /** 
     * The main method that runs the bank
     * @param args - main method args
     * @throws IOException - throws exception
     */
    public static void main(String[] args) throws IOException{
        String userID = " ";
        String userNameF = " ";
        String userNameL = " ";
        int loc;
        Customer2 customer = new Customer2();
        int i = 0;
        boolean found = false;
        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";
        RunBank rb = new RunBank();

        //pre-fill all account information
        customer.allocate();

        //initialize file writer
        //initialize user input scanner for user information
        Scanner userInfo = new Scanner(System.in);

        try(FileWriter fw = new FileWriter(fileName, true);
                BufferedWriter bankLog = new BufferedWriter(fw);){

            System.out.println("Hello! Welcome to MinerBank! ");
            System.out.println("Are you a new user? Y or N?");
            String ans = userInfo.nextLine();

            if (ans.equals("Y")){
                System.out.println("Welcome New Customer! Please provide the following information.");

                System.out.println("Input first name.");
                userNameF = userInfo.nextLine();

                System.out.println("Input last name.");
                userNameL = userInfo.nextLine();

                customer.newCustomer(userNameF, userNameL);

                userID = customer.customers().get(customer.customers().size()-1).getID();
                found = true;
            }
            else if(ans.equals("N")){

                while( i< 3 && !found){

                    //Input user information
                    System.out.println("Please input user ID.");
                    userID = userInfo.nextLine();

                    System.out.println("Please input first name.");
                    userNameF = userInfo.nextLine();

                    System.out.println("Please input last name.");
                    userNameL = userInfo.nextLine();

                    //verifies customer
                    if(!customer.verifyIdent(userID, userNameF, userNameL)){
                        i++;
                        System.out.println("Attempt " +i+ ".");
                        System.out.println("Wrong user and/or ID. ");
                        if (i==3){
                            System.out.println("Too many wrong inputs. Goodbye! ");

                            //log end if too many wrong attempts
                            bankLog.write("Unsuccessful login attempt " + i + " of 3.");
                            bankLog.newLine();
                            bankLog.write("Too many unsuccessful login attempts. Force log out.");
                            bankLog.newLine();
                        }
                        else{
                            //log unsuccessful login attempts each time.
                            bankLog.write("Unsuccessful login attempt " + i + " of 3.");
                            bankLog.newLine();
                        }
                    }
                    else{
                        found = true;
                        loc = customer.findLoc(userNameF, userNameL);

                        //log successful login
                        bankLog.write(customer.customers().get(loc).getNameF()+ " " + customer.customers().get(loc).getNameL()+ " successfully logs in.");
                        bankLog.newLine();

                        
                    }
                }
            }else{

                System.out.println("Error");
            }
            loc = customer.findLoc(userNameF, userNameL);
            int num;
            if(found){
                do{//loop until someone presses 8

                    //Give options you want to choose
                    System.out.println("Welcome to MinerBank, " + customer.customers().get(loc).getNameF() +" "+ customer.customers().get(loc).getNameL()+ "! Please choose an option. ");

                    System.out.println("\t1. Check Balance.");
                    System.out.println("\t2. Withdraw Money.");
                    System.out.println("\t3. Deposit Money.");
                    System.out.println("\t4. Transfer Money.");
                    System.out.println("\t5. Make Payment.");
                    System.out.println("\t6. Buy from Miner Mall.");
                    System.out.println("\t7. Bank Manager.");
                    System.out.println("\t8. Exit.");

                    //input scanner for option choice
                    num = userInfo.nextInt();

                    switch(num){
                        case 1: // Check Balance
                            rb.checkBalanceUI(customer, loc);
                            break;
                        case 2: // Withdraw Money
                            rb.withdrawUI(customer, loc);
                            break;
                        case 3: // Deposit Money
                            rb.depositUI(customer, loc);
                            break;
                        case 4: //Transfer Money
                            rb.transferUI(customer, loc);
                            break;
                        case 5: //Make Payment
                            rb.makePaymentUI(customer, loc);
                            break;
                        case 6: // Buy from MinerMall
                            rb.buyFromMinerMallUI(customer, loc);
                            break;
                        case 7: //Bank Manager
                            int bM;

                            System.out.println("\t1.Search User.");
                            System.out.println("\t2.Transaction Manager.");
                            System.out.println("\t3.Bank Statement.");

                            bM = userInfo.nextInt();

                            if (bM == 1){

                                rb.bankManagerUI(customer, loc);
                            }
                            else if(bM == 2){

                                rb.bankManagerExtended(customer);
                            }
                            else if (bM == 3){

                                rb.bankStatement(customer, loc);
                            }
                            else{

                                System.out.println("invalid input");
                            }
                            break;

                        case 8: //Goodbye

                            System.out.println("Goodbye, " + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + "! Thank you for banking with MinerBank!");
                            //updates CSV file
                            rb.update(customer, loc);

                            //write to bank log
                            bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " logged out of MinerBank. ");
                            bankLog.newLine();
                            break;
                    }
                //stops when 8 is pushed
                }while (num != 8);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        userInfo.close();
    }


    
    /** 
     * This is the user interface for checking account balances
     * @param customer - customer array list
     * @param loc - location of customer in array list
     * @throws IOException - throws exception
     */
    public void checkBalanceUI(Customer2 customer, int loc)throws IOException{
        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";
        String file = ("C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankStatement.txt");
        Scanner userInfo = new Scanner(System.in);

        try(FileWriter fw = new FileWriter(fileName, true);
                BufferedWriter bankLog = new BufferedWriter(fw);){

            System.out.println("Which account would you like to customer about?");

            System.out.println("\t1.Checking Account");
            System.out.println("\t2.Savings Account");
            System.out.println("\t3.Credit Account");

            int checkBal = userInfo.nextInt();

            if (checkBal ==1){
                System.out.println("Checking account balance: $"  + String.format("%.2f",customer.customers().get(loc).check.getCheckingBal()) + ".");

                //input inquiry on checking acc.
                bankLog.write(customer.customers().get(loc).getNameF()+ " " + customer.customers().get(loc).getNameL() + " made a balance inquiry on Checking-"+ customer.customers().get(loc).check.getCheckingAccNum()+ "." + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + "'s balance for Checking-" +customer.customers().get(loc).check.getCheckingAccNum()+ ": $"+ String.format("%.2f", customer.customers().get(loc).check.getCheckingBal()));
                bankLog.newLine();

            }else if(checkBal ==2){
                System.out.println("Savings account balance: $" + String.format("%.2f",customer.customers().get(loc).save.getSavingBal()) + ".");

                //input inquiry on savings acc.
                bankLog.write(customer.customers().get(loc).getNameF()+ " " + customer.customers().get(loc).getNameL() + " made a balance inquiry on Saving-"+ customer.customers().get(loc).save.getSavingAccNum()+ "." + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + "'s balance for Saving-" +customer.customers().get(loc).save.getSavingAccNum()+ ": $"+ String.format("%.2f", customer.customers().get(loc).save.getSavingBal()));
                bankLog.newLine();

            }else if(checkBal == 3){
                System.out.println("Credit account balance: $" + String.format("%.2f", customer.customers().get(loc).credit.getCreditBal()) + "." );

                //input inquiry on checking acc.
                bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " made a balance inquiry on Credit-"+ customer.customers().get(loc).credit.getAccNumCr() + "." + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + "'s balance for Credit-" + customer.customers().get(loc).credit.getAccNumCr() + ": $"+ String.format("%.2f", customer.customers().get(loc).credit.getCreditBal()));
                bankLog.newLine();

            }else{
                System.out.println("Invalid input.");

                //input that user failed to check bank acc.
                bankLog.write(customer.customers().get(loc).getNameF()+ " " + customer.customers().get(loc).getNameL() + " failed to make an inquiry. Reason: ERROR! Invalid input.");
                bankLog.newLine();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    
    /** 
     * this method does the UI function of withdrawing from account.
     * @param customer - array list of customer accounts
     * @param loc - location of customer in array list
     * @throws IOException - throws IOException
     */
    public void withdrawUI(Customer2 customer, int loc) throws IOException{

        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";
        String file = ("C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankStatement.txt");
        Scanner userInfo = new Scanner(System.in);
        int x = loc;

        try(FileWriter fw = new FileWriter(fileName, true);
                BufferedWriter bankLog = new BufferedWriter(fw);){

            System.out.println("Which account would you like to withdraw from?");
            System.out.println("\t1.Checking Account");
            System.out.println("\t2.Savings Account");

            int accNum = userInfo.nextInt();

            if (accNum == 1){
                System.out.println("How much would you like to withdraw?");
                double wC = userInfo.nextDouble();

                //calls withdraw method for checking.
                customer.customers().get(x).check.withdrawChecking(wC,customer,x);

            }
            else if(accNum ==2){

                System.out.println("How much would you like to withdraw?");
                double wS = userInfo.nextDouble();

                //calls withdraw method for saving
                customer.customers().get(x).save.withdrawSavings(wS, customer, x);
            }
            else{
                System.out.println("Invalid input.");

                //failed withdraw
                bankLog.write(customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL() + " unsuccessfully withdrew from account. Reason: ERROR! Invalid input.");
                bankLog.newLine();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    
    /** 
     * this method does the UI function of depositing into accounts
     * @param customer - array list of customer accounts
     * @param loc - location of customer in array list
     * @throws IOException - throws IOException
     */
    public void depositUI(Customer2 customer, int loc) throws IOException{

        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";
        String file = ("C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankStatement.txt");

        Scanner userInfo = new Scanner(System.in);

        try(FileWriter fw = new FileWriter(fileName, true);
                BufferedWriter bankLog = new BufferedWriter(fw);){

            System.out.println("Which account would you like to deposit to?");
            System.out.println("\t1.Checking Account");
            System.out.println("\t2.Savings Account");
            System.out.println("\t3.Credit Account");

            int accNumD = userInfo.nextInt();
            
            //checking account
            if (accNumD ==1){

                System.out.println("How much would you like to deposit?");
                double dC = userInfo.nextDouble();

                //deposits into account
                customer.customers().get(loc).check.depositChecking(dC, customer, loc);
            }
            //savings account
            else if(accNumD ==2){

                System.out.println("How much would you like to deposit?");
                double dS = userInfo.nextDouble();

                //deposits into account
                customer.customers().get(loc).save.depositSavings(dS, customer, loc);

            }
            //credit account
            else if(accNumD == 3){

                System.out.println("How much would you like to deposit?");
                double dCr = userInfo.nextDouble();

                //deposits into account
                customer.customers().get(loc).credit.depositCredit(dCr, customer, loc);

            }
            // anything other than the 3 options are invalid
            else{
                System.out.println("Invalid input.");

                //failed deposit log input
                bankLog.write(customer.customers().get(loc).getNameF()+ " " + customer.customers().get(loc).getNameL() + " failed to make a deposit. Reason: ERROR! Invalid input.");
                bankLog.newLine();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    
    /** 
     * this method handles the transfer UI between accounts.
     * @param customer - array list of customer accounts
     * @param loc - location of customer in array list
     * @throws IOException - Throws exception
     */
    public void transferUI(Customer2 customer, int loc)throws IOException{
        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";
        String file = ("C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankStatement.txt");

        Scanner userInfo = new Scanner(System.in);
        int x = loc;

        try(FileWriter fw = new FileWriter(fileName, true);
                BufferedWriter bankLog = new BufferedWriter(fw);){

            System.out.println("Which account would you like to transfer from?");

            System.out.println("\t1.Checking Account");
            System.out.println("\t2.Savings Account");

            int transFrom = userInfo.nextInt();

            System.out.println("Which account would you like to transfer to?");

            System.out.println("\t1.Checking Account");
            System.out.println("\t2.Savings Account");
            System.out.println("\t3.Credit Account");

            int transTo = userInfo.nextInt();

            //cannot transfer into same account
            if (transFrom< 0 || transTo<0){

                System.out.println("Invalid Selection. Reason: Cannot transfer into same account type.");

                //bank log reads error
                bankLog.write(customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL() + " failed to transfer funds. Reason: Cannot transfer to same accounts.");
                bankLog.newLine();
            }

            //if transferring from checking 
            else if (transFrom == 1 ){

                System.out.println("How much would you like to transfer?");
                double transAmount = userInfo.nextDouble();

                 //if destination account is a savings account
                if (transTo == 2){

                    //executes withdrawing from checking and depositing to savings if sender has funds
                    if(customer.customers().get(x).check.transferFromChecking(transAmount, customer, x)){

                        customer.customers().get(x).save.transferToSavings(transAmount, customer, x);

                        //bank log reads error
                        bankLog.write(customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + " transferred $" + String.format("%.2f",transAmount) + " from Checking-" + customer.customers().get(x).check.getCheckingAccNum() + " to Saving-" + customer.customers().get(x).save.getSavingAccNum() + ". " + customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + "'s new balance for Checking-" + customer.customers().get(x).check.getCheckingAccNum()+ ": $"+ String.format("%.2f",customer.customers().get(x).check.getCheckingBal()) + ". " + customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL()+ "'s new balance for Saving-"+ customer.customers().get(x).save.getSavingAccNum() + ": $"+ String.format("%.2f",customer.customers().get(x).save.getSavingBal()));
                        bankLog.newLine();
                    }
                }

                //if destination account is a credit account
                else if(transTo == 3){

                    //executes withdrawing from checking and depositing to cred
                    if(customer.customers().get(x).check.transferFromChecking(transAmount, customer, x)){

                        customer.customers().get(x).credit.transferToCredit(transAmount, customer, x);

                        //bank log reads error
                        bankLog.write(customer.customers().get(loc).getNameF()+ " " + customer.customers().get(loc).getNameL() + " transferred $" + String.format("%.2f",transAmount) + " from Checking-" + customer.customers().get(loc).check.getCheckingAccNum() + " to Credit-" + customer.customers().get(loc).credit.getAccNumCr()+ ". " + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + "'s new balance for Checking-" + customer.customers().get(loc).check.getCheckingAccNum()+ ": $"+ String.format("%.2f",customer.customers().get(loc).check.getCheckingBal()) + ". " + customer.customers().get(loc).getNameF()+ " " + customer.customers().get(loc).getNameL()+ "'s new balance for Saving-"+ customer.customers().get(loc).credit.getAccNumCr()+ ": $"+ String.format("%.2f",customer.customers().get(loc).credit.getCreditBal()));
                        bankLog.newLine();
                    }
                }

                //gives error
                else{

                    System.out.println("Invalid Reason: Cannot transfer to self");
                }
            }

            //transferring from saving 
            else if(transFrom == 2){

                System.out.println("How much would you like to transfer?");
                double transAmount2 = userInfo.nextDouble();

                //if destination account is a checking account
                if(transTo == 1){

                    //executes withdrawing from savings and depositing to checking
                    if(customer.customers().get(x).save.transferFromSavings(transAmount2, customer, x)){

                        customer.customers().get(x).check.transferToChecking(transAmount2, customer, x);

                        //bank log reads error
                        bankLog.write(customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL() + " transferred $" + String.format("%.2f",transAmount2) + " from Saving-" + customer.customers().get(x).save.getSavingAccNum() + " to Checking-" + customer.customers().get(x).check.getCheckingAccNum()+ ". " + customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + "'s new balance for Checking-" + customer.customers().get(x).check.getCheckingAccNum()+ ": $"+ String.format("%.2f",customer.customers().get(x).check.getCheckingBal()) + ". " + customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL()+ "'s new balance for Saving-"+ customer.customers().get(x).save.getSavingAccNum()+ ": $"+ String.format("%.2f",customer.customers().get(x).save.getSavingBal()));
                        bankLog.newLine();

                    }
                }

                //if destination account is a credit account
                else if (transTo ==3){

                    //executes withdrawing from savings and depositing to credit
                    if(customer.customers().get(x).save.transferFromSavings(transAmount2, customer, x)){

                        customer.customers().get(x).credit.transferToCredit(transAmount2, customer, x);

                        //bank log transfer
                        bankLog.write(customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL() + " transferred $" + String.format("%.2f",transAmount2) + " from Saving-" + customer.customers().get(x).save.getSavingAccNum() + " to Credit-" + customer.customers().get(x).credit.getAccNumCr()+ ". " + customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + "'s new balance for Saving-" + customer.customers().get(x).save.getSavingAccNum()+ ": $"+ String.format("%.2f",customer.customers().get(x).check.getCheckingBal()) + ". " + customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL()+ "'s new balance for Credit-"+ customer.customers().get(x).credit.getAccNumCr() + ": $"+ String.format("%.2f",customer.customers().get(x).credit.getCreditBal()));
                        bankLog.newLine();
                    }
                }

                //gives error
                else{

                    System.out.println("Invalid Reason: Cannot transfer to same account");
                }
            }

            //any other input reads invalid
            else{
                System.out.println("Invalid Input. Failed to transfer");

                //bankLog error
                bankLog.write(customer.customers().get(x).getNameF()+ " " + customer.customers().get(x).getNameL() + " failed to transfer funds");
                bankLog.newLine();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    
    /** 
     * this method handles the UI for  payments between customers.
     * @param customer - array list of customer accounts
     * @param loc - location of customer in array list
     * @throws IOException - Throws exception
     */
    public void makePaymentUI(Customer2 customer, int loc)throws IOException{
        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";
        String file = ("C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankStatement.txt");

        Scanner userInfo = new Scanner(System.in);

        WriteToCSV write = new WriteToCSV();

        try(FileWriter fw = new FileWriter(fileName, true);
                BufferedWriter bankLog = new BufferedWriter(fw);){

            String payID;
            String payNF;
            String payNL;
            double pay;
            int x;

            System.out.println("What is the recipient ID?");
            payID = userInfo.next();

            x = Integer.valueOf(payID);

            System.out.println("What is the recipient first name?");
            payNF = userInfo.next();

            System.out.println("What is the recipient last Name?");
            payNL = userInfo.next();

            //check if recipient exists
            if(!customer.verifyIdent(payID, payNF, payNL)){

                System.out.println("Recipient doesn't exist.");

                //logs failed transaction
                bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " failed to give valid recipient information.");
                bankLog.newLine();

            }

            //user cannot pay themselves
            else if ((payID.equals(customer.customers().get(loc).getID()) && payNF.equals(customer.customers().get(loc).getNameF()) && payNL.equals(customer.customers().get(loc).getNameL()))){

                System.out.println("Invalid. Failed to make payment.");

                //log failed transaction.
                bankLog.write(customer.customers().get(loc).getNameF()+ " " + customer.customers().get(loc).getNameL() + " failed transaction. Reason: Cannot pay self.");
                bankLog.newLine();
            }
            else{

                System.out.println("User verified.");

                System.out.println("Which account would you like to make payment from?");
                System.out.println("\t1.Checking Account");
                System.out.println("\t2.Credit Account");

                int accS = userInfo.nextInt();

                System.out.println("Which account would you like to send payment to?");
                System.out.println("\t1.Checking Account");
                System.out.println("\t2.Savings Account");
                System.out.println("\t3.Credit Account");

                int accD = userInfo.nextInt();

                System.out.println("How much would you like to pay");
                pay = userInfo.nextDouble();

                //if checking is chose
                if(accS == 1){

                    //if sender is able to send money
                    if (customer.customers().get(loc).check.CheckingPay(pay, customer, loc)){

                        //if checking account is chosen
                        if(accD == 1){

                            //deposit money into checking account
                            customer.customers().get(x).check.depositChecking(pay, customer, x);

                            //put it into bank log.
                            bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " paid $" + String.format("%.2f", pay) + " to " + customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + " from Checking-" + customer.customers().get(loc).check.getCheckingAccNum() + ". " + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL()+ "'s new Checking balance is: $" + customer.customers().get(loc).check.getCheckingBal());
                            bankLog.newLine();
                        }

                        //if saving account is chosen
                        else if(accD == 2){

                            //deposit money into savings account
                            customer.customers().get(x).save.depositSavings(pay, customer, x);

                            //put it into bank log.
                            bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " paid $" + String.format("%.2f", pay) + " to " + customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + " from Checking-" + customer.customers().get(loc).check.getCheckingAccNum() + ". " + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL()+ "'s new Checking balance is: $" + customer.customers().get(loc).check.getCheckingBal());
                            bankLog.newLine();
                        }

                        //if credit account is chosen
                        else if(accD == 3){

                            //deposit money into credit account
                            customer.customers().get(x).credit.depositCredit(pay, customer, x);

                            //put it into bank log.
                            bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " paid $" + String.format("%.2f", pay) + " to " + customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + " from Checking-" + customer.customers().get(loc).check.getCheckingAccNum() + ". " + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL()+ "'s new Checking balance is: $" + customer.customers().get(loc).check.getCheckingBal());
                            bankLog.newLine();
                        }
                        else{

                            //if any other number is chosen, prints an error
                            System.out.println("Account does exist.");

                            //put it into bank log.
                            bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " unsuccessfully paid $" + String.format("%.2f", pay) + " to " + customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL());
                            bankLog.newLine();
                        }
                    }

                //if credit is chose
                }else if(accS == 2){

                    //if sender is able to send money
                    if(customer.customers().get(loc).credit.CreditPay(pay, customer, loc)){

                        //if checking account is chose
                        if(accD == 1){

                            //deposit into checking
                            customer.customers().get(x).check.depositChecking(pay, customer,x);

                            //put it into bank log.
                            bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " paid $" + String.format("%.2f", pay) + " to " + customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + " from Credit-" + customer.customers().get(loc).credit.getAccNumCr() + ". " + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL()+ "'s new Credit balance is: $" + customer.customers().get(loc).credit.getAccNumCr());
                            bankLog.newLine();
                        }

                        //if savings account is chose
                        else if(accD == 2){

                            //deposit into savings
                            customer.customers().get(x).save.depositSavings(pay, customer, x);

                            //put it into bank log.
                            bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " paid $" + String.format("%.2f", pay) + " to " + customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + " from Credit-" + customer.customers().get(loc).credit.getAccNumCr() + ". " + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL()+ "'s new Credit balance is: $" + customer.customers().get(loc).credit.getAccNumCr());
                            bankLog.newLine();
                        }

                        //if credit is chosen
                        else if(accD == 3){

                            //deposit into credit
                            customer.customers().get(x).credit.depositCredit(pay, customer, x);

                            //put it into bank log.
                            bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " paid $" + String.format("%.2f", pay) + " to " + customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL() + " from Credit-" + customer.customers().get(loc).credit.getAccNumCr() + ". " + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL()+ "'s new Credit balance is: $" + customer.customers().get(loc).credit.getAccNumCr());
                            bankLog.newLine();
                        }
                        else{

                            //if user chooses any other number, prints an error
                            System.out.println("Account doesn't exist.");

                            //put it into bank log.
                            bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " unsuccessfully paid $" + String.format("%.2f", pay) + " to " + customer.customers().get(x).getNameF() + " " + customer.customers().get(x).getNameL());
                            bankLog.newLine();
                        }
                    }
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    
    /** 
     * this method handles the MinerMall UI
     * @param customer - array list of customer accounts
     * @param loc - location of customer in array list
     * @throws IOException - Throws exception
     */
    public void buyFromMinerMallUI(Customer2 customer, int loc)throws IOException{
        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";
        String file = ("C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankStatement.txt");

        Scanner userInfo = new Scanner(System.in);

        try(FileWriter fw = new FileWriter(fileName, true);
                BufferedWriter bankLog = new BufferedWriter(fw);){

            String exit = " ";
            boolean paid = false;

            do{// repeat loop until customer is finished with shopping.

                Item prod = new Item();

                System.out.println("Here is our list of products.");
                prod.itemsListing();

                System.out.println("Enter ID of desired product.");
                String ID = userInfo.next();

                //adds item to cart. Assumption: I assumed that all items in cart is a sure decision so i didn't add a "remove from cart" option
                customer.customers().get(loc).addToCart(ID, customer, loc);

                //shows whats in shopping cart
                System.out.println("Items Currently in your cart: ");
                customer.customers().get(loc).printList();

                System.out.println("Complete? if yes press 'Y'. If not press any key to continue.");
                exit = userInfo.next();

            }while (!exit.equals("Y")); // exit when user presses "Y"

            //give total price
            System.out.println("Your total is: $" + customer.customers().get(loc).getPrice());

            do{ // repeat until customer customer
                System.out.println("How would you like to pay for it?");
                System.out.println("\t1. Checking.");
                System.out.println("\t2. Credit.");

                int payNo = userInfo.nextInt();

                if (payNo == 1){

                    paid = true;

                    //paid with checking balance
                    customer.customers().get(loc).check.bMCheckingBuy(customer.customers().get(loc).getPrice(), customer, loc);

                    //empties cart when user customer
                    customer.customers().get(loc).emptyCart();

                    //shows what user has spent so far in MinerMall
                    customer.customers().get(loc).setSpentSoFar(customer.customers().get(loc).getPrice());
                    System.out.println("You've spent $" + String.format("%.2f",customer.customers().get(loc).getSpentSoFar()) + " with MinerMall so far.");

                    //add to bank log
                    bankLog.write(customer.customers().get(loc).getNameF()+ " "+ customer.customers().get(loc).getNameL() + " paid " + String.format("%.2f",customer.customers().get(loc).getPrice()) + " from Checking-" + customer.customers().get(loc).check.getCheckingAccNum() + ". " + customer.customers().get(loc).getNameF()+ " "+ customer.customers().get(loc).getNameL()+ "'s new Checking-"+ customer.customers().get(loc).check.getCheckingAccNum()+ " balance is: $"+ String.format("%.2f",customer.customers().get(loc).check.getCheckingBal()) +". ");
                    bankLog.newLine();

                    //set price back to 0
                    customer.customers().get(loc).resetPrice();

                }else if( payNo == 2){

                    paid = true;

                    //paid with credit balance
                    customer.customers().get(loc).credit.payCredit(customer.customers().get(loc).getPrice(), customer, loc);

                    //empties cart when user pays for items
                    customer.customers().get(loc).emptyCart();

                    customer.customers().get(loc).setSpentSoFar(customer.customers().get(loc).getPrice());
                    System.out.println("You've spent " + String.format("%.2f",customer.customers().get(loc).getSpentSoFar()));

                    //add to bank log
                    bankLog.write(customer.customers().get(loc).getNameF()+ " "+ customer.customers().get(loc).getNameL() + " paid " + String.format("%.2f",customer.customers().get(loc).getPrice()) + " from Credit-" + customer.customers().get(loc).credit.getAccNumCr() + ". " + customer.customers().get(loc).getNameF()+ " "+ customer.customers().get(loc).getNameL()+ "'s new Credit-"+ customer.customers().get(loc).credit.getAccNumCr() + " balance is: $"+ String.format("%.2f",customer.customers().get(loc).credit.getCreditBal()) +". ");
                    bankLog.newLine();

                    //set price back to 0
                    customer.customers().get(loc).resetPrice();

                }else{

                    System.out.println("Please choose valid option.");
                }
                //exits only if the person customer
            }while(paid != true);
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    
    /** 
     * this method simply shows the account information of the one searched
     * @param customer - array list of customer accounts
     * @param loc - location of customer in array list
     * @throws IOException - Throws exception
     */
    public void bankManagerUI(Customer2 customer, int loc)throws IOException{
        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";
        String file = ("C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankStatement.txt");

        Scanner userInfo = new Scanner(System.in);

        try(FileWriter fw = new FileWriter(fileName, true);
                BufferedWriter bankLog = new BufferedWriter(fw);){

            System.out.println("Bank Manager Settings");
            System.out.println("\t1.Search Account by name?");
            System.out.println("\t2.Search Account by type/number?");
            int bankM = userInfo.nextInt();

            if(bankM ==1){

                System.out.println("What is the user's first name?");
                String inqF = userInfo.next();

                System.out.println("What is the user's last name?");
                String inqL = userInfo.next();

                for(Customer cus: customer.customers()){

                    if(cus.getNameF().equals(inqF) && cus.getNameL().equals(inqL)){

                        System.out.println("User ID: " + cus.getID());
                        System.out.println("User First Name: " + cus.getNameF());
                        System.out.println("User Last Name: " + cus.getNameL());
                        System.out.println("User Address: " + cus.getAddress());
                        System.out.println("User City: " + cus.getCity());
                        System.out.println("User State: "+ cus.getState());
                        System.out.println("User Zip: " + cus.getZip());
                        System.out.println("User Phone Number: " + cus.getPhoneNum());
                        System.out.println("User DOB: " + cus.getDOB());
                        System.out.println("User Checking Acc Number: " + cus.check.getCheckingAccNum());
                        System.out.println("User Checking Acc Balance: " + String.format("%.2f",cus.check.getCheckingBal()));
                        System.out.println("User Savings Acc Number: " + cus.save.getSavingAccNum());
                        System.out.println("User Savings Acc Balance: " + String.format("%.2f", cus.save.getSavingBal()));
                        System.out.println("User Credit Acc Number: "+ cus.credit.getAccNumCr());
                        System.out.println("User Credit Acc Balance: " + String.format("%.2f", cus.credit.getCreditBal()));
                        System.out.println("User Credit Score: " + cus.credit.getCreditScore());
                        System.out.println("User Credit Limit: " + cus.credit.getCreditLimit());

                        //log successful bank manager customer
                        bankLog.write("Bank Manager, " + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " successfully checked " + cus.getNameF()+ " " + cus.getNameL()+" account information." );
                        bankLog.newLine();
                    }
                }
            }else if (bankM ==2){

                System.out.println("What is the account number?");
                String acc = userInfo.next();

                for(Customer cus: customer.customers()){

                    if(cus.check.getCheckingAccNum().equals(acc) || cus.save.getSavingAccNum().equals(acc) || cus.credit.getAccNumCr().equals(acc)){

                        //allocate all appropriate information
                        System.out.println("User ID: " + cus.getID());
                        System.out.println("User First Name: " + cus.getNameF());
                        System.out.println("User Last Name: " + cus.getNameL());
                        System.out.println("User Address: " + cus.getAddress());
                        System.out.println("User City: " + cus.getCity());
                        System.out.println("User State: "+ cus.getState());
                        System.out.println("User Zip: " + cus.getZip());
                        System.out.println("User Phone Number: " + cus.getPhoneNum());
                        System.out.println("User DOB: " + cus.getDOB());
                        System.out.println("User Checking Acc Number: " + cus.check.getCheckingAccNum());
                        System.out.println("User Checking Acc Balance: " + String.format("%.2f", cus.check.getCheckingBal()));
                        System.out.println("User Savings Acc Number: " + cus.save.getSavingAccNum());
                        System.out.println("User Savings Acc Balance: " + String.format("%.2f", cus.save.getSavingBal()));
                        System.out.println("User Credit Acc Number: "+ cus.credit.getAccNumCr());
                        System.out.println("User Credit Acc Balance: " + String.format("%.2f", cus.credit.getCreditBal()));
                        System.out.println("User Credit Score: " + cus.credit.getCreditScore());
                        System.out.println("User Credit Limit: " + cus.credit.getCreditLimit());

                        //log successful bank manager customer
                        bankLog.write("Bank Manager, " + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " successfully checked " + cus.getNameF()+ " " + cus.getNameL()+" account information." );
                        bankLog.newLine();
                    }

                }
            }else{

                System.out.println("Error! Invalid Input.");

                //log successful bank manager customer
                bankLog.write("Bank Manager, " + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " unsuccessfully checked account information. Reason: ERROR! Invalid input." );
                bankLog.newLine();

            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /** 
     * this method is the extension transaction reader for the bank manager.
     * @param customer - array list of customer accounts
     * @throws IOException - Throws exception
     */
    public void bankManagerExtended(Customer2 customer)throws IOException{
        String fileName = "C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankLog3.txt";
        String file = ("C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankStatement.txt");

        Scanner userInfo = new Scanner(System.in);
        int i= 0;
        Item it= new Item();
        customer.read();

        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String form = it.time.format(timeFormat);

        int loc;
        int size = customer.tran().size();

        try(FileWriter fw = new FileWriter(fileName, true);
                BufferedWriter bankLog = new BufferedWriter(fw);){

            //while i is less than tran array list size
            while(i < size){

                //if the action is buy
                if(customer.tran().get(i).getAction().equals("Buy")){

                    //find and verify the person from the source
                    if(customer.verifyIdent(customer.tran().get(i).getNameF(), customer.tran().get(i).getNameL())){

                        //find their location in the array list
                        loc = customer.findLoc(customer.tran().get(i).getNameF(),  customer.tran().get(i).getNameL());

                        //if they are buying from their checking account
                        if(customer.tran().get(i).getAccountS().equals("Checking")){

                            //verify the item exists
                            if (customer.customers().get(loc).it.verifyItem(customer.tran().get(i).getMmID(), customer.tran().get(i).getMmDes(), customer.tran().get(i).getAmount())){

                                //call method to buy from checking
                                customer.customers().get(loc).check.bMCheckingBuy(customer.tran().get(i).getAmount(), customer, loc);

                                //set the item name and the time
                                customer.customers().get(loc).it.setItemName(customer.tran().get(i).getMmDes());
                                customer.customers().get(loc).it.setTime(it.time);

                                //put into log
                                bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " purchased "+ customer.tran().get(i).getMmDes()+ " at " + form + " for " + String.format("%.2f", customer.tran().get(i).getAmount()) + " from Checking-" + customer.customers().get(loc).check.getCheckingAccNum()+". " + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + "'s new Checking balance is: $" + String.format("%.2f",customer.customers().get(loc).check.getCheckingBal()));
                                bankLog.newLine();

                            }
                        }

                        //if they're buying from their credit account
                        else if(customer.tran().get(i).getAccountS().equals("Credit")){

                            //verify the item exists
                            if (customer.customers().get(loc).it.verifyItem(customer.tran().get(i).getMmID(), customer.tran().get(i).getMmDes(), customer.tran().get(i).getAmount())){

                                //call method to buy from credit
                                customer.customers().get(loc).credit.payCredit(customer.tran().get(i).getAmount(), customer, loc);

                                //set the item name and the time
                                customer.customers().get(loc).it.setItemName(customer.tran().get(i).getMmDes());
                                customer.customers().get(loc).it.setTime(it.time);

                                //put into log
                                bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " purchased "+ customer.tran().get(i).getMmDes()+ " at " + form + " for " + String.format("%.2f", customer.tran().get(i).getAmount())+ " from Credit-" + customer.customers().get(loc).credit.getAccNumCr()+ ". " + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + "'s new Checking balance is: $" + String.format("%.2f",customer.customers().get(loc).credit.getCreditBal()));
                                bankLog.newLine();

                            }

                        }
                    }

                }else if(customer.tran().get(i).getAction().equals("Pay")){

                    //if both customers are verified
                    if(customer.verifyIdent(customer.tran().get(i).getNameF(), customer.tran().get(i).getNameL()) && customer.verifyIdent(customer.tran().get(i).getNameFD(), customer.tran().get(i).getNameLD())){

                        //find both of their locations by index in the array list
                        loc = customer.findLoc(customer.tran().get(i).getNameF(),  customer.tran().get(i).getNameL());
                        int loc2 = customer.findLoc(customer.tran().get(i).getNameFD(), customer.tran().get(i).getNameLD());

                        //if sender and recipient are the same person
                        if (customer.customers().get(loc).getNameF() == customer.customers().get(loc2).getNameF() && customer.customers().get(loc).getNameL() == customer.customers().get(loc2).getNameL()){

                            //invalid because you cannot pay yourself.
                            System.out.println("Invalid. Reason: Cannot pay self.");

                        }

                        //if the source account a checking account
                        else if(customer.tran().get(i).getAccountS().equals("Checking")){

                            //if the destination account is a checking account
                            if (customer.tran().get(i).getAccountD().equals("Checking")){

                                //if the sender has enough money and the payment comes out of checking account
                                if (customer.customers().get(loc).check.CheckingPay(customer.tran().get(i).getAmount(), customer, loc)){

                                    //deposit payment into the recipient account
                                    customer.customers().get(loc2).check.depositChecking(customer.tran().get(i).getAmount(), customer, loc2);

                                    //add sender info to bankLog
                                    bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " sent " + String.format("%.2f", customer.tran().get(i).getAmount()) + " to " + customer.customers().get(loc2).getNameF() + " " + customer.customers().get(loc2).getNameL() + " from Checking-" + customer.customers().get(loc).check.getCheckingAccNum() + ". New checking balance is: $" + String.format("%.2f", customer.customers().get(loc).check.getCheckingBal()));
                                    bankLog.newLine();

                                    //add recipient info to bankLog
                                    bankLog.write(customer.customers().get(loc2).getNameF() + " " + customer.customers().get(loc2).getNameL() + " received "+ String.format("%.2f", customer.tran().get(i).getAmount()) + " from " + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " to Checking-" + customer.customers().get(loc2).check.getCheckingAccNum() + ". New checking balance is: $" + String.format("%.2f", customer.customers().get(loc2).check.getCheckingBal()));
                                    bankLog.newLine();
                                }
                            }

                            //if the destination account is a savings account
                            else if(customer.tran().get(i).getAccountD().equals("Savings")){

                                //if the sender has enough money and the payment goes through
                                if (customer.customers().get(loc).check.CheckingPay(customer.tran().get(i).getAmount(), customer, loc)){

                                    //deposit payment into the destination account
                                    customer.customers().get(loc2).save.depositSavings(customer.tran().get(i).getAmount(), customer, loc2);

                                    //add sender info to bankLog
                                    bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " sent " + String.format("%.2f", customer.tran().get(i).getAmount()) + " to " + customer.customers().get(loc2).getNameF() + " " + customer.customers().get(loc2).getNameL() + " from Checking-" + customer.customers().get(loc).check.getCheckingAccNum() + ". New checking balance is: $" + String.format("%.2f", customer.customers().get(loc).check.getCheckingBal()));
                                    bankLog.newLine();

                                    //add recipient info to bankLog
                                    bankLog.write(customer.customers().get(loc2).getNameF() + " " + customer.customers().get(loc2).getNameL() + " received "+ String.format("%.2f", customer.tran().get(i).getAmount()) + " from " + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " to Savings-" + customer.customers().get(loc2).save.getSavingAccNum() + ". New savings balance is: $" + String.format("%.2f", customer.customers().get(loc2).save.getSavingBal()));
                                    bankLog.newLine();
                                }
                            }

                            // if destination account is a credit account
                            else if(customer.tran().get(i).getAccountD().equals("Credit")){

                                //if sender payment goes through
                                if(customer.customers().get(loc).check.CheckingPay(customer.tran().get(i).getAmount(), customer, loc)){

                                    //deposit money into destination account
                                    customer.customers().get(loc2).credit.depositCredit(customer.tran().get(i).getAmount(), customer, loc2);

                                    //add sender info to bankLog
                                    bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " sent " + String.format("%.2f", customer.tran().get(i).getAmount()) + " to " + customer.customers().get(loc2).getNameF() + " " + customer.customers().get(loc2).getNameL() + " from Checking-" + customer.customers().get(loc).check.getCheckingAccNum() + ". New checking balance is: $" + String.format("%.2f", customer.customers().get(loc).check.getCheckingBal()));
                                    bankLog.newLine();

                                    //add recipient info to bankLog
                                    bankLog.write(customer.customers().get(loc2).getNameF() + " " + customer.customers().get(loc2).getNameL() + " received "+ String.format("%.2f", customer.tran().get(i).getAmount()) + " from " + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " to Credit-" + customer.customers().get(loc2).credit.getAccNumCr() + ". New checking balance is: $" + String.format("%.2f", customer.customers().get(loc2).credit.getAccNumCr()));
                                    bankLog.newLine();
                                }
                            }
                        }

                        //if the source is a credit account
                        else if(customer.tran().get(i).getAccountS().equals("Credit")){

                            //if the destination account is a checking account
                            if (customer.tran().get(i).getAccountD().equals("Checking")){

                                //if the sender payment goes through
                                if(customer.customers().get(loc).credit.CreditPay(customer.tran().get(i).getAmount(), customer, loc)){

                                    //add deposit money into checking account
                                    customer.customers().get(loc2).check.depositChecking(customer.tran().get(i).getAmount(), customer, loc2);

                                    //add sender info to bankLog
                                    bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " sent " + String.format("%.2f", customer.tran().get(i).getAmount()) + " to " + customer.customers().get(loc2).getNameF() + " " + customer.customers().get(loc2).getNameL() + " from Credit-" + customer.customers().get(loc).credit.getAccNumCr() + ". New credit balance is: $" + String.format("%.2f", customer.customers().get(loc).credit.getCreditBal()));
                                    bankLog.newLine();

                                    //add recipient info to bankLog
                                    bankLog.write(customer.customers().get(loc2).getNameF() + " " + customer.customers().get(loc2).getNameL() + " received "+ String.format("%.2f", customer.tran().get(i).getAmount()) + " from " + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " to Checking-" + customer.customers().get(loc2).check.getCheckingAccNum() + ". New checking balance is: $" + String.format("%.2f", customer.customers().get(loc2).check.getCheckingBal()));
                                    bankLog.newLine();
                                }
                            }

                            // if destination account is a savings account
                            else if(customer.tran().get(i).getAccountD().equals("Savings")){

                                //if sender payment goes through
                                if(customer.customers().get(loc).credit.CreditPay(customer.tran().get(i).getAmount(), customer, loc)){

                                    //deposit money into destination account
                                    customer.customers().get(loc2).save.depositSavings(customer.tran().get(i).getAmount(), customer, loc2);

                                    //add sender info to bankLog
                                    bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " sent " + String.format("%.2f", customer.tran().get(i).getAmount()) + " to " + customer.customers().get(loc2).getNameF() + " " + customer.customers().get(loc2).getNameL() + " from Credit-" + customer.customers().get(loc).credit.getAccNumCr() + ". New credit balance is: $" + String.format("%.2f", customer.customers().get(loc).credit.getCreditBal()));
                                    bankLog.newLine();

                                    //add recipient info to bankLog
                                    bankLog.write(customer.customers().get(loc2).getNameF() + " " + customer.customers().get(loc2).getNameL() + " received "+ String.format("%.2f", customer.tran().get(i).getAmount()) + " from " + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " to Savings-" + customer.customers().get(loc2).check.getCheckingAccNum() + ". New savings balance is: $" + String.format("%.2f", customer.customers().get(loc2).save.getSavingBal()));
                                    bankLog.newLine();
                                }

                            }
                            //if destination account is a credit account
                            else if(customer.tran().get(i).getAccountD().equals("Credit")){

                                //if sender payment goes through
                                if(customer.customers().get(loc).credit.CreditPay(customer.tran().get(i).getAmount(), customer, loc)){

                                    //deposit money into destination account
                                    customer.customers().get(loc2).credit.depositCredit(customer.tran().get(i).getAmount(), customer, loc2);

                                    //add sender info to bankLog
                                    bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " sent " + String.format("%.2f", customer.tran().get(i).getAmount()) + " to " + customer.customers().get(loc2).getNameF() + " " + customer.customers().get(loc2).getNameL() + " from Credit-" + customer.customers().get(loc).credit.getAccNumCr() + ". New credit balance is: $" + String.format("%.2f", customer.customers().get(loc).credit.getCreditBal()));
                                    bankLog.newLine();

                                    //add recipient info to bankLog
                                    bankLog.write(customer.customers().get(loc2).getNameF() + " " + customer.customers().get(loc2).getNameL() + " received "+ String.format("%.2f", customer.tran().get(i).getAmount()) + " from " + customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " to Savings-" + customer.customers().get(loc2).check.getCheckingAccNum() + ". New savings balance is: $" + String.format("%.2f", customer.customers().get(loc2).save.getSavingBal()));
                                    bankLog.newLine();

                                }
                            }
                        }
                    }

                }else if(customer.tran().get(i).getAction().equals("Deposit")){

                    if(customer.verifyIdent(customer.tran().get(i).getNameFD(), customer.tran().get(i).getNameLD())){

                        //find location of customer in array list
                        loc = customer.findLoc(customer.tran().get(i).getNameFD(),  customer.tran().get(i).getNameLD());

                        //if the destination account is a checking account
                        if(customer.tran().get(i).getAccountD().equals("Checking")){

                            //deposit the money into the checking account
                            customer.customers().get(loc).check.depositChecking(customer.tran().get(i).getAmount(), customer, loc);

                            //put it into the bankLog
                            bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " deposited " + String.format("%.2f", customer.tran().get(i).getAmount()) + " into Checking-" + customer.customers().get(loc).check.getCheckingAccNum() + ". New checking balance is: $" + String.format("%.2f", customer.customers().get(loc).check.getCheckingBal()));
                            bankLog.newLine();
                        }

                        //if the destination account is a savings account
                        else if(customer.tran().get(i).getAccountD().equals("Savings")){

                            //deposit the money into the savings account
                            customer.customers().get(loc).save.depositSavings(customer.tran().get(i).getAmount(), customer, loc);

                            // put into bankLog
                            bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " deposited " + String.format("%.2f", customer.tran().get(i).getAmount()) + " into Saving-" + customer.customers().get(loc).save.getSavingAccNum() + ". New checking balance is: $" + String.format("%.2f", customer.customers().get(loc).save.getSavingBal()));
                            bankLog.newLine();
                        }

                        //if the destination account is a credit account
                        else if(customer.tran().get(i).getAccountD().equals("Credit")){

                            //deposit money into credit account
                            customer.customers().get(loc).credit.depositCredit(customer.tran().get(i).getAmount(), customer, loc);

                            //add to bankLog
                            bankLog.write(customer.customers().get(loc).getNameF() + " " + customer.customers().get(loc).getNameL() + " deposited " + String.format("%.2f", customer.tran().get(i).getAmount()) + " into Credit-" + customer.customers().get(loc).credit.getAccNumCr() + ". New checking balance is: $" + String.format("%.2f", customer.customers().get(loc).credit.getCreditBal()));
                            bankLog.newLine();
                        }
                    }
                }

                //transaction reader failed
                else{

                    System.out.println("Transaction Reader Failed");

                    //put in bankLog
                    bankLog.write("Transaction Reader failed");
                    bankLog.newLine();

                }
                //increment i for while loop
                i++;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /** 
     * simply calls the bank statement method
     * @param customer - array list of customer accounts
     * @param loc - location of customer in array list
     * @throws IOException - Throws exception
     */
    public void bankStatement(Customer2 customer, int loc)throws IOException{
        //initialize scanner
        Scanner userInfo = new Scanner(System.in);
        int i = 0;
        int j = 0;

        //initialize file location
        String file = ("C:\\Users\\chica\\Documents\\AOOP\\PA3\\pa3-sp22-Court247\\BankStatement" + i +".txt");
        
        //initialize String
        String bNF;

        //initialize String
        String bNL;

        //initialize account for statement 
        int accS;

        

        System.out.println("Which customer would you like to make a bank statement for?");
        System.out.println("First name?");
        bNF = userInfo.nextLine();

        System.out.println("Last Name?");
        bNL = userInfo.nextLine();

        System.out.println("Which account do you want to make a statement for?");
        
        System.out.println("\t1.Checking Account");
        System.out.println("\t2.Savings Account");
        System.out.println("\t3.Credit Account");
        accS = userInfo.nextInt();

        i = customer.findLoc(bNF, bNL);
        customer.customers().get(i).bank.setAfterCheckBalance(customer.customers().get(i).check.getCheckingBal());
        customer.customers().get(i).bank.setAfterSaveBalance(customer.customers().get(i).save.getSavingBal());
        customer.customers().get(i).bank.setAfterCreditBalance(customer.customers().get(i).credit.getCreditBal());


        try{
            //adds to specified point
            bank.addInfo(0, 0, "MINERBANK ");
            if(accS == 1){ 
                int x = 18;
                int y = 0;
                for(j = 0; j < customer.customers().get(i).getHistory().size(); j++){

                    if (customer.customers().get(i).getHistory().get(j).getAccType().equals("Checking") || customer.customers().get(i).getHistory().size() ==0){
                        //add information
                        bank.addInfo(0,6, "Account Number: ");
                        bank.addInfo(1,6, customer.customers().get(i).check.getCheckingAccNum());
                        bank. addInfo(0,7, "Statement Date: ");
                        bank. addInfo(1,7, String.valueOf(customer.customers().get(i).it.getTime()));
                        bank. addInfo(0,9, customer.customers().get(i).getNameF()+" "+ customer.customers().get(i).getNameL());
                        bank. addInfo(0,10, customer.customers().get(i).getAddress());
                        bank. addInfo(0,11, customer.customers().get(i).getCity() + " " + customer.customers().get(i).getState() + " " + customer.customers().get(i).getZip());
                        bank. addInfo(4,9, "Opening Balance: ");
                        bank. addInfo(8,9, "$ "+ String.format("%.2f",customer.customers().get(i).bank.getInitialCheckBalance()));
                        bank. addInfo(4,10, "Total Credit Amount: ");
                        bank.addInfo(4,11, "Total Debit Amount: ");
                        bank. addInfo(4, 12, "Closing Balance: ");
                        bank.addInfo(8, 12, String.format("%.2f",customer.customers().get(i).bank.getAfterCheckBalance()));
                        bank.addInfo(0,13, "MINERBANK");
                        bank.addInfo(4, 13, "Account Type: ");
                        bank. addInfo(8, 13, "Checking Account");
                        bank. addInfo(4, 14, "Number of Transactions: ");
                        bank.addInfo(0, 16, "Transactions");
                        bank. addInfo(0,17, "Date");
                        bank. addInfo(1, 17, "Description");
                        bank. addInfo(4, 17, "Credit");
                        bank.addInfo(6, 17, "Debit");
                        bank. addInfo(8, 17, "Balance");
    
                        //save info
                        bank.saveInfo(new File(file));

                        System.out.println("Completed: ");
                    }
                }
            }
            else if(accS ==2){ 
                for(j = 0; j < customer.customers().get(i).getHistory().size(); j++){

                    if (customer.customers().get(i).getHistory().get(j).getAccType().equals("Saving")|| customer.customers().get(i).getHistory().size() ==0 ){
                        //add information
                        bank.addInfo(0,6, "Account Number: ");
                        bank.addInfo(1,6, customer.customers().get(i).check.getCheckingAccNum());
                        bank. addInfo(0,7, "Statement Date: ");
                        bank. addInfo(1,7, String.valueOf(customer.customers().get(i).it.getTime()));
                        bank. addInfo(0,9, customer.customers().get(i).getNameF()+" "+ customer.customers().get(i).getNameL());
                        bank. addInfo(0,10, customer.customers().get(i).getAddress());
                        bank. addInfo(0,11, customer.customers().get(i).getCity() + " " + customer.customers().get(i).getState() + " " + customer.customers().get(i).getZip());
                        bank. addInfo(4,9, "Opening Balance: ");
                        bank. addInfo(8,9, "$ "+ String.format("%.2f",customer.customers().get(i).bank.getAfterSaveBalance()));
                        bank. addInfo(4,10, "Total Credit Amount: ");
                        bank.addInfo(4,11, "Total Debit Amount: ");
                        bank. addInfo(4, 12, "Closing Balance: ");
                        bank.addInfo(8, 12, String.format("%.2f",customer.customers().get(i).bank.getAfterSaveBalance()));
                        bank.addInfo(0,13, "MINERBANK");
                        bank.addInfo(4, 13, "Account Type: ");
                        bank. addInfo(8, 13, "Saving Account");
                        bank. addInfo(4, 14, "Number of Transactions: ");
                        bank.addInfo(0, 16, "Transactions");
                        bank. addInfo(0,17, "Date");
                        bank. addInfo(1, 17, "Description");
                        bank. addInfo(4, 17, "Credit");
                        bank.addInfo(6, 17, "Debit");
                        bank. addInfo(8, 17, "Balance");
    
                        //save info
                        bank.saveInfo(new File(file));

                        System.out.println("Completed: ");
                    }
                }
            }
            else if(accS ==3 ){
                for(j = 0; j < customer.customers().get(i).getHistory().size(); j++){

                    if (customer.customers().get(i).getHistory().get(j).getAccType().equals("Credit") || customer.customers().get(i).getHistory().size() ==0){
                        //add information
                        bank.addInfo(0,6, "Account Number: ");
                        bank.addInfo(1,6, customer.customers().get(i).check.getCheckingAccNum());
                        bank. addInfo(0,7, "Statement Date: ");
                        bank. addInfo(1,7, String.valueOf(customer.customers().get(i).it.getTime()));
                        bank. addInfo(0,9, customer.customers().get(i).getNameF()+" "+ customer.customers().get(i).getNameL());
                        bank. addInfo(0,10, customer.customers().get(i).getAddress());
                        bank. addInfo(0,11, customer.customers().get(i).getCity() + " " + customer.customers().get(i).getState() + " " + customer.customers().get(i).getZip());
                        bank. addInfo(4,9, "Opening Balance: ");
                        bank. addInfo(8,9, "$ "+ String.format("%.2f",customer.customers().get(i).bank.getInitialCreditBalance()));
                        bank. addInfo(4,10, "Total Credit Amount: ");
                        bank.addInfo(4,11, "Total Debit Amount: ");
                        bank. addInfo(4, 12, "Closing Balance: ");
                        bank.addInfo(8, 12, String.format("%.2f",customer.customers().get(i).bank.getAfterCreditBalance()));
                        bank.addInfo(0,13, "MINERBANK");
                        bank.addInfo(4, 13, "Account Type: ");
                        bank. addInfo(8, 13, "Credit Account");
                        bank. addInfo(4, 14, "Number of Transactions: ");
                        bank.addInfo(0, 16, "Transactions");
                        bank. addInfo(0,17, "Date");
                        bank. addInfo(1, 17, "Description");
                        bank. addInfo(4, 17, "Credit");
                        bank.addInfo(6, 17, "Debit");
                        bank. addInfo(8, 17, "Balance");
    
                        //save info
                        bank.saveInfo(new File(file));

                        System.out.println("Completed: ");
                    }
                }
            }

            
        }catch (Exception e) {
        }
    }

    
    /** 
     * this method updates the customer bank information
     * @param customer - array list of customer accounts
     * @param loc - location of customer in array list
     * @throws IOException - Throws exception
     */
    public void update(Customer2 customer, int loc) throws IOException{
        WriteToCSV write = new WriteToCSV();
        write.writeCSV(customer);
        write.writeCSV(customer, loc);

    }

}