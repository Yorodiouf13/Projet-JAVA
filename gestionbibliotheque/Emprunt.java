package gestionbibliotheque;

import java.time.LocalDate;

public class Emprunt {
    Utilisateur utilisateur;
    Livre livre;
    LocalDate dateEmprunt;

    public Emprunt(Utilisateur utilisateur, Livre livre) {
        this.utilisateur = utilisateur;
        this.livre = livre;
        this.dateEmprunt = LocalDate.now();
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Livre getLivre() {
        return livre;
    }
    public void setLivre(Livre livre) {
        this.livre = livre;
    }
    
    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }


    @Override
    public String toString() {
        return "Emprunt [Utilisateur=" + utilisateur + ", Livre=" + livre + ",DateEmprunt" + dateEmprunt + "]";
    }
}
