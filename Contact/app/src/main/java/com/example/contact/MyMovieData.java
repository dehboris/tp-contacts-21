package com.example.contact;

public class MyMovieData {
    private String movieName;
    private String moviedate;
    private Integer movieImage;
    private String nom;
    private String sexe;

    public MyMovieData(String movieName, String moviedate, Integer movieImage, String nom, String sexe) {
        this.movieName = movieName;
        this.moviedate = moviedate;
        this.movieImage = movieImage;
        this.nom = nom;
        this.sexe = sexe;
    }

    //    public MyMovieData(String movieName, String moviedate, Integer movieImage) {
//        this.movieName = movieName;
//        this.moviedate = moviedate;
//        this.movieImage = movieImage;
//    }

//    public String getMovieName() {
//        return movieName;
//    }
//
//    public void setMovieName(String movieName) {
//        this.movieName = movieName;
//    }
//
//    public String getMoviedate() {
//        return moviedate;
//    }
//
//    public void setMoviedate(String moviedate) {
//        this.moviedate = moviedate;
//    }
//
//    public Integer getMovieImage() {
//        return movieImage;
//    }
//
//    public void setMovieImage(Integer movieImage) {
//        this.movieImage = movieImage;
//    }


    public String getMovieName() {
        return movieName;
    }

    public void setMovieName (String movieName) {
        this.movieName = movieName;
    }

    public String getMoviedate() {
        return moviedate;
    }

    public void setMoviedate(String moviedate) {
        this.moviedate = moviedate;
    }

    public Integer getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(Integer movieImage) {
        this.movieImage = movieImage;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
}
