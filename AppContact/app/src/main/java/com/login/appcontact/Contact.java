package com.login.appcontact;

public class Contact {
    int id;
    String nom,prenom,sex,num;

    public Contact(int id, String nom, String prenom, String sex, String num) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sex = sex;
        this.num = num;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }


    public Contact(String nom, String prenom, String sex, String num) {
        this.nom = nom;
        this.prenom = prenom;
        this.sex = sex;
        this.num = num;

    }


    public Contact(){

    }

}
