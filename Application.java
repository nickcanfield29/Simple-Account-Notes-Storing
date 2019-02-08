package com.nickcanfield;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {

    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Account> accounts = new ArrayList<Account>();
    private String name;

    public void startApp() {
        System.out.println("Welcome to the App!");

            boolean quit2 = false;
            while (!quit2) {
                printOptions();

                if (scanner.hasNextInt()) {
                    int option = scanner.nextInt();
                    scanner.nextLine();
                    switch (option) {
                        default:
                            System.out.println("Invalid option");
                            break;
                        case 1:
                            createAccount();
                            break;
                        case 2:
                            loginToAccount();
                            break;
                        case 3:
                            quit2=true;
                            break;
                        case 4:
                            printNote();
                            break;
                    }
                } else {
                    System.out.println("Invalid entry");
                    System.out.println();
                    scanner.nextLine();
                }

            }
        }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void printOptions() {
        System.out.println();
        System.out.println("Choose Your Option:");
        System.out.println("1. Create Account");
        System.out.println("2. Login & Add a New Note to your Account");
        System.out.println("3. Exit Application");
        System.out.println("4. Print your notes");
    }

    public Application(String name) {
        this.name = name;
    }

    public void createAccount() {

        boolean loginNameNew = false;

        while (!loginNameNew) {
            System.out.println();
            System.out.println("Creating New Account");
            System.out.print("Please type in your new login name: \n");
            String loginName = scanner.next();
            scanner.nextLine();

            if (findAccount(loginName)==null) {

                boolean passwordMatch = false;
                while (!passwordMatch) {

                    System.out.println();
                    System.out.print("Please type in your new password: \n");
                    String password = scanner.next();
                    scanner.nextLine();

                    System.out.print("Please retype in your password: \n");
                    String password2 = scanner.next();
                    scanner.nextLine();

                    if (password.equals(password2)) {
                        Account createdAccount = new Account(password, loginName);
                        accounts.add(createdAccount);
                        System.out.println("Success! Your account has been added!");
                        passwordMatch = true;
                        loginNameNew=true;
                    } else {
                        System.out.println("Your passwords didn't match!");
                    }
                }
            } else {
                System.out.println("Login already created!");
            }
        }
    }


    public Account findAccount(String loginName) {
        for (int i = 0; i < getAccounts().size(); i++) {
            Account searchedAccount = accounts.get(i);
            if (searchedAccount.getAccountName().equals(loginName)) {
                return searchedAccount;
            }
        }
        return null;
    }

    public void loginToAccount() {

        if (accounts.size() > 0) {
            int loginAttempts = 0;
            boolean quit1 = false;

            while (!quit1) {
                System.out.println();
                System.out.println("Let's Find Your Account");
                System.out.print("Please type in your login name: \n");
                String loginName = scanner.next();
                scanner.nextLine();

                if (findAccount(loginName) != null) {
                    Account foundAccount = findAccount(loginName);
                    String acctPassword = foundAccount.getPassword();

                    boolean quit2 = false;
                    while (!quit2) {
                        System.out.print("Please Type in your password: \n");
                        String password = scanner.next();
                        scanner.nextLine();
                        if (acctPassword.equals(password)) {
                            System.out.print("Please add your note to save: \n");
                            String note = scanner.nextLine();
                            foundAccount.addNote(note);
                            quit1 = true;
                            quit2 = true;
                        } else {
                            System.out.println("Incorrect Password");
                            loginAttempts++;
                            if (loginAttempts > 2) {
                                System.out.println("You're locked out of your acccount");
                                quit1 = true;
                                quit2 = true;
                            } else {
                                System.out.println("You have: " + (3 - loginAttempts) + " password attempts left");
                            }
                        }
                    }
                } else {
                    System.out.println("Login Name not found!");
                    printLoginNames();
                }

            }
        } else {
            System.out.println("There are 0 accounts");
            System.out.println("You need to create an account first!");
        }
    }

    public void printNote() {
        System.out.println();
        if (accounts.size() > 0) {
            int loginAttempts = 0;
            boolean quit1 = false;

            while (!quit1) {
                System.out.print("Please type in your login name: \n");
                String loginName = scanner.next();
                scanner.nextLine();

                if (findAccount(loginName) != null) {
                    Account foundAccount = findAccount(loginName);
                    String acctPassword = foundAccount.getPassword();

                    boolean quit2 = false;
                    while (!quit2) {
                        System.out.print("Please Type in your password: \n");
                        String password = scanner.next();
                        scanner.nextLine();
                        if (acctPassword.equals(password)) {
                            printAllNotes(loginName);
                            quit1 = true;
                            quit2 = true;
                        } else {
                            System.out.println("Incorrect Password");
                            loginAttempts++;
                            if (loginAttempts > 2) {
                                System.out.println("You're locked out of your account");
                                System.out.println("Exiting application");
                                quit1 = true;
                                quit2 = true;
                            } else {
                                System.out.println("You have: " + (3 - loginAttempts) + " password attempts left");
                            }
                        }
                    }
                } else {
                    System.out.println("Login Name not found!");
                    System.out.println("Please go create an account!");
                    quit1 = true;
                }

            }
        } else {
            System.out.println("You need to create an account first!");
        }
    }

    public void printAllNotes(String loginName) {
        System.out.println();
        Account foundAccount = findAccount(loginName);
        ArrayList notes = foundAccount.getNotes();

        if(notes.size()>0) {
            System.out.println("Printing "+loginName+"'s notes");
            for (int i = 0; i < notes.size(); i++) {
                System.out.println("Note #" + (i + 1) + ": " + notes.get(i));
            }
        } else {
            System.out.println("You don't have any notes");
            System.out.println("Please log-in to add a note");
        }
    }

    public void printLoginNames() {
        if(accounts.size()>0) {
            System.out.println("Login Names Registered:");
            for (int i = 0; i < accounts.size(); i++) {
                System.out.println((i + 1) + ": " + accounts.get(i).getAccountName());
            }
        } else {
            System.out.println("There are 0 accounts registered");
            System.out.println("Please create an account");
        }
    }

}
