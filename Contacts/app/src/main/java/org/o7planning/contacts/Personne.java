package org.o7planning.contacts;

import java.io.Serializable;

public class Personne implements Serializable {

    private String  nom,prenom,sexe,facebook,numero;
    private int image;

    public Personne(String name, String prename,String sexe,String face,String num,int img)
    {
        this.nom=name;
        this.prenom=prename;
        this.sexe=sexe;
        this.facebook=face;
        this.numero=num;
        this.image=img;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String name)
    {
        this.nom=name;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prename)
    {
        this.prenom=prename;
    }
    public String getSexe() {
        return sexe;
    }
    public void setSexe(String sexe)
    {
        this.sexe=sexe;
    }
    public String getFacebook() {
        return facebook;
    }
    public void setFacebook(String face)
    {
        this.facebook=face;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String num)
    {
        this.numero=num;
    }

    public int getImage() {
        return image;
    }
    public void setNom(int img)
    {
        this.image=img;
    }

}

