package com.nickcanfield;

import java.util.ArrayList;

public class Account {

    private String password;
    private String accountName;
    private ArrayList<String> notes;

    public void addNote(String note) {
        String newNote = note;
        this.notes.add(newNote);
    }

    public Account(String password, String accountName) {
        this.password = password;
        this.accountName = accountName;
        this.notes = new ArrayList<String>();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }
}
