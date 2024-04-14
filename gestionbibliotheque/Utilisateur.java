package gestionbibliotheque;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
    String nom;
    int numeroIdentification;
    static int dernierNumeroIdentification = 0;
    ArrayList<Livre> livresEmpruntes;
    boolean cotisationAJour;
    private List<Emprunt> emprunts;
    

    

    // Constructeur pour initialiser les attributs de l'utilisateur
    public Utilisateur(String nom, int numeroIdentification, boolean cotisationAJour) {
        this.nom = nom;
        if (numeroIdentification == 0) {
            this.numeroIdentification = ++dernierNumeroIdentification;
        }
        this.livresEmpruntes = new ArrayList<>();
        this.cotisationAJour = cotisationAJour;
        this.emprunts = new ArrayList<>();
    }



    public String getNom() {
        return nom;
    }

   public void setNom(String nom) {
    this.nom = nom;
}


    public boolean isCotisationAJour() {
        return cotisationAJour;
    }

    public void setCotisationAJour(boolean cotisationAJour) {
        this.cotisationAJour = cotisationAJour;
    }

    public void emprunterLivre(Livre livre) {
    if (livresEmpruntes.size() < 2) { 
        livresEmpruntes.add(livre);
        System.out.println("Livre emprunté avec succès!");
    } else {
        System.out.println("Vous ne pouvez pas emprunter plus de 2 livres.");
    }
}



    // Méthode pour retourner un livre
    public void retournerLivre(Livre livre) {
    if (livresEmpruntes.contains(livre)) {
        livresEmpruntes.remove(livre);
    } else {
        System.out.println("Ce livre n'a pas été emprunté par cet utilisateur.");
    }
}

    public void ajouterEmprunt(Emprunt emprunt) {
    this.emprunts.add(emprunt);
    this.livresEmpruntes.add(emprunt.getLivre());
}


    // Méthode pour afficher les livres empruntés par l'utilisateur
    public void afficherLivresEmpruntes() {
        System.out.println("Livre(s) emprunté(s) par " + nom + ":");
        for (Livre livre : livresEmpruntes) {
            System.out.println(livre);
        }
    }
}
