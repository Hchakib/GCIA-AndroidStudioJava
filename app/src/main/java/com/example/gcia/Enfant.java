package com.example.gcia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Enfant {

    private String nom;
    private String prenom;
    private int age;
    private String pays;
    private String ville;
    private String username;
    private String password;
    private ArrayList<Cadeau> cadeaux;

    public Enfant(String nom, String prenom, int age, String pays, String ville, String username, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.pays = pays;
        this.ville = ville;
        this.username = username;
        this.password = password;
        this.cadeaux=new ArrayList<>();
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Cadeau> getCadeaux() {
        return cadeaux;
    }

    public void setCadeaux(List<Cadeau> cadeaux) {
        this.cadeaux = (ArrayList<Cadeau>) cadeaux;
    }

    public void addCadeau(Cadeau cadeau) {
        this.cadeaux.add(cadeau);
    }

    public void removeCadeau(Cadeau cadeau) {
        this.cadeaux.remove(cadeau);
    }
}
