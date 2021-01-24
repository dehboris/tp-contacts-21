package com.example.contacts;

public class Contact {
    private String nom;
    private String prenom;
    private String numero;

    public String getNom(){
        return nom;
    }
    public void  setNom(String nom){
        this.nom = nom;
    }

    public String getPrenom(){
        return prenom;
    }
    public void  setPrenom(String prenom){
        this.prenom = prenom;
    }

    public String getNumero(){
        return numero;
    }
    public void  setNumero(String numero){
        this.numero = numero;
    }
}
