package com.example.contactsbyvicodart;

public class Contact {
    int id;
    String nom,phenom,sex,num,email,facebook;

    public Contact(int id, String nom, String phenom, String sex, String num, String email,String facebook) {
        this.id = id;
        this.nom = nom;
        this.phenom = phenom;
        this.sex = sex;
        this.num = num;
        this.email = email;
        this.facebook = facebook;
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

    public String getPhenom() {
        return phenom;
    }

    public void setPhenom(String phenom) {
        this.phenom = phenom;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public Contact(String nom, String phenom, String sex, String num, String email, String facebook) {
        this.nom = nom;
        this.phenom = phenom;
        this.sex = sex;
        this.num = num;
        this.email = email;
        this.facebook = facebook;
    }


    public Contact(){

    }
}
