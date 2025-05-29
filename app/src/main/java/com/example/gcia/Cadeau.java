package com.example.gcia;

public class Cadeau {
    private String type;
    private String nom;
    private String description;
    private double prix;
    private Enfant enfant;

    public Cadeau(String type, String nom, String description, double prix,Enfant enfant) {
        this.type = type;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.enfant=enfant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Enfant getEnfant() {
        return enfant;
    }

    public void setEnfant(Enfant enfant) {
        this.enfant = enfant;
    }
}
