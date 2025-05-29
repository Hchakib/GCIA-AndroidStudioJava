package com.example.gcia;

import java.util.ArrayList;
import java.util.List;

public class EnfantDB {
    public static List<Enfant> enfants = new ArrayList<>();



    public static boolean isUsernameTaken(String username) {
        for (Enfant enfant : enfants) {
            if (enfant.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPasswordTaken(String password) {
        for (Enfant enfant : enfants) {
            if (enfant.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


    public static List<String> getAllPays() {
        List<String> countries = new ArrayList<>();

        for (Enfant enfant : enfants) {
            if (!countries.contains(enfant.getPays())) {
                countries.add(enfant.getPays());
            }
        }

        return countries;
    }

    public static List<String> getAllVilles() {
        List<String> villes = new ArrayList<>();

        for (Enfant enfant : enfants) {
            if (!villes.contains(enfant.getVille())) {
                villes.add(enfant.getVille());
            }
        }

        return villes;
    }
}
