package gestionbibliotheque;

public class Livre {
    String titre;
    String auteur;
    int anneePublication;
    String ISBN;

    // Constructeur pour initialiser les attributs du livre
    public Livre(String titre, String auteur, int anneePublication, String ISBN) {
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.ISBN = ISBN;
    }

    // Getters et setters pour l'accès et la modification des attributs

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getAnneePublication() {
        return anneePublication;
    }

    public void setAnneePublication(int anneePublication) {
        this.anneePublication = anneePublication;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    // Méthode toString() pour afficher les détails du livre
    @Override
    public String toString() {
        return "Livre {" +
                "Titre='" + titre + '\n' +
                "Auteur='" + auteur + '\n' +
                "Année de Publication='" + anneePublication + '\n' +
                "ISBN='" + ISBN + '\n' +
                '}';

    }

}