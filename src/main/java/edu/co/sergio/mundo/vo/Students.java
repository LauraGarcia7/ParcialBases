/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.vo;

/**
 *
 * @author Labing
 */
public class Students {
    int SID;
    String First;
    String Last;
    String Email;

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public String getFirst() {
        return First;
    }

    public void setFirst(String First) {
        this.First = First;
    }

    public String getLast() {
        return Last;
    }

    public void setLast(String Last) {
        this.Last = Last;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
}
