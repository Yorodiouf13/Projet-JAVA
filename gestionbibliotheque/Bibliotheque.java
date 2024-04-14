package gestionbibliotheque;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;

public class Bibliotheque {
    ArrayList<Livre> listeLivres;
    HashMap<Utilisateur, ArrayList<Livre>> empruntsUtilisateurs;
    private AtomicInteger dernierNumeroIdentification = new AtomicInteger(0); // Ajouté pour la génération d'un numéro
                                                                              // d'identification automatique
    List<Emprunt> listeEmprunts;

    // Constructeur pour initialiser les attributs de la bibliothèque
    public Bibliotheque() {
        this.listeLivres = new ArrayList<>();
        this.empruntsUtilisateurs = new HashMap<>();
        this.listeEmprunts = new ArrayList<>();
    }

    // Méthode pour ajouter un livre dans la bibliothèque
    public void ajouterLivre(Livre livre) throws LibraryException {
        if (livre == null) {
            throw new LibraryException("Le livre est vide.");
        }
        listeLivres.add(livre);
    }

   
    

    // Méthode pour supprimer un livre
    public void supprimerLivre(Livre livre) throws LibraryException {
        if (!listeLivres.contains(livre)) {
            throw new LibraryException("Le livre à supprimer n'existe pas dans la bibliothèque.");
        }
        listeLivres.remove(livre);
    }

    // Méthode pour rechercher un livre à partir de son auteur, son auteur ou son
    // son
    // ISBN
    public Livre rechercherLivre(String critere) {
        for (Livre livre : listeLivres) {
            if (livre.getTitre().equalsIgnoreCase(critere) || livre.getAuteur().equalsIgnoreCase(critere)
                    || livre.getISBN().equalsIgnoreCase(critere)) {
                return livre;
            }
        }
        return null;
    }

    // Méthode pour modifier les détails d'un livre
public void modifierLivre(Livre livre, String nouveauTitre, String nouvelAuteur, int nouvelleAnneePublication, String nouveauISBN) throws LibraryException {
    // Vérifier si le livre existe dans la bibliothèque
    if (!listeLivres.contains(livre)) {
        throw new LibraryException("Le livre à modifier n'existe pas dans la bibliothèque.");
    }

    // Modifier les attributs du livre
    livre.setTitre(nouveauTitre);
    livre.setAuteur(nouvelAuteur);
    livre.setAnneePublication(nouvelleAnneePublication);
    livre.setISBN(nouveauISBN);

    System.out.println("Livre modifié avec succès !");
}


    // Méthode pour retourner le nombre de livre empruntés par un utilisateur donné
    public int getNombreLivresEmpruntes(Utilisateur utilisateur) {
        int count = 0;
        for (Emprunt emprunt : listeEmprunts) {
            if (emprunt.getUtilisateur().equals(utilisateur)) {
                count++;
            }
        }
        return count;
    }

    // Méthode pour rechercher un utilisateur par son nom
    public Utilisateur rechercherUtilisateur(String nom) {
    for (Utilisateur utilisateur : empruntsUtilisateurs.keySet()) {
        if (utilisateur.getNom().equalsIgnoreCase(nom)) {
            return utilisateur;
        }
    }
    return null; // Retourne null si aucun utilisateur n'est trouvé avec le nom donné
}


    // Méthode pour ajouter un utilisateur à la bibliothèque
    public void ajouterUtilisateur(Utilisateur utilisateur) throws LibraryException {
        if (utilisateur == null) {
        throw new LibraryException("L'utilisateur est vide.");
        }
        empruntsUtilisateurs.put(utilisateur, new ArrayList<>());
}


  
   public void emprunterLivre(Utilisateur utilisateur, Livre livre) throws LibraryException {
    // Vérifier si l'utilisateur est éligible
    if (!utilisateur.isCotisationAJour()) {
        System.out.println("L'utilisateur n'est pas autorisé à emprunter des livres en raison des cotisations impayées.");
        throw new LibraryException("Vous n'êtes pas autorisé à emprunter des livres en raison des cotisations impayées.");
    }

    // Vérifier si le livre est disponible
    if (!listeLivres.contains(livre)) {
        System.out.println("Le livre " + livre.getTitre() + " n'est pas disponible.");
        throw new LibraryException("Le livre n'est pas disponible.");
    }

    // Vérifier combien de livres l'utilisateur a déjà empruntés
    int nombreLivresEmpruntes = getNombreLivresEmpruntes(utilisateur);
    System.out.println("Nombre de livres empruntés par " + utilisateur.getNom() + " : " + nombreLivresEmpruntes);
    if (nombreLivresEmpruntes >= 3) {
        System.out.println("L'utilisateur a atteint le nombre maximal de livres à emprunter.");
        throw new LibraryException("Vous avez atteint le nombre maximal de livres à emprunter.");
    }

    // Ajouter l'emprunt à la liste des emprunts en stockant une référence à l'utilisateur
    Emprunt emprunt = new Emprunt(utilisateur, livre);
    listeEmprunts.add(emprunt);
    utilisateur.ajouterEmprunt(emprunt); // Assurez-vous également d'ajouter l'emprunt à l'utilisateur
    System.out.println("Livre emprunté avec succès !");
}



 // Méthode pour enregistrer le retour d'un livre par un utilisateur
public void retournerLivre(Utilisateur utilisateur, Livre livre) {
    // Vérifier si l'utilisateur a emprunté le livre
    Emprunt emprunt = null;
    for (Emprunt e : listeEmprunts) {
        if (e.getUtilisateur().equals(utilisateur) && e.getLivre().equals(livre)) {
            emprunt = e;
            break;
        }
    }
    if (emprunt != null) {
        // Retirer l'emprunt de la liste des emprunts
        listeEmprunts.remove(emprunt);
        utilisateur.retournerLivre(livre);
        System.out.println("Livre retourné avec succès !");
    } else {
        System.out.println("Vous n'avez pas emprunté ce livre.");
    }
}







    // Méthode pour vérifier si un utilisateur particulier a emprunté un livre
    // spécifique dans la bibliothèque
    public boolean estUtilisateurAEmprunteLivre(Utilisateur utilisateur, Livre livre) {
        if (empruntsUtilisateurs.containsKey(utilisateur)) {
            return empruntsUtilisateurs.get(utilisateur).contains(livre);
        }
        return false;
    }

    // Méthode pour générer un numéro d'identification unique
    public int genererNumeroIdentification() {
        return dernierNumeroIdentification.incrementAndGet();
    }

    public void ajouterEmprunt(Emprunt emprunt) {
        this.listeEmprunts.add(emprunt);
    }




    public void afficherStatistiques() {
    System.out.println("=== Statistiques de la bibliothèque ===");
    System.out.println("Nombre total de livres : " + listeLivres.size());
    System.out.println("Nombre total d'utilisateurs : " + empruntsUtilisateurs.size());

    // Créer une copie temporaire de la liste des emprunts
    List<Emprunt> empruntsTemp = new ArrayList<>(listeEmprunts);

    // Créer une copie temporaire de la map des emprunts par utilisateur
    HashMap<Utilisateur, ArrayList<Livre>> empruntsUtilisateursTemp = new HashMap<>(empruntsUtilisateurs);

    // Réinitialiser le nombre total de livres empruntés
    int totalLivresEmpruntes = 0;

    // Afficher les livres empruntés par chaque utilisateur
    for (Emprunt emprunt : empruntsTemp) {
        Utilisateur utilisateur = emprunt.getUtilisateur();
        Livre livre = emprunt.getLivre();
        ArrayList<Livre> livresEmpruntes = empruntsUtilisateursTemp.get(utilisateur);

        // Si l'utilisateur n'a pas encore emprunté de livres, créer une nouvelle liste pour lui
        if (livresEmpruntes == null) {
            livresEmpruntes = new ArrayList<>();
            empruntsUtilisateursTemp.put(utilisateur, livresEmpruntes);
        }

        livresEmpruntes.add(livre); // Ajouter le livre emprunté à la liste de l'utilisateur
        totalLivresEmpruntes++;
    }

    // Afficher les livres empruntés par chaque utilisateur
    for (Utilisateur utilisateur : empruntsUtilisateursTemp.keySet()) {
        ArrayList<Livre> livresEmpruntes = empruntsUtilisateursTemp.get(utilisateur);
        System.out.println("Livres empruntés par " + utilisateur.getNom() + " :");
        if (livresEmpruntes.isEmpty()) {
            System.out.println("Aucun livre emprunté.");
        } else {
            for (Livre livre : livresEmpruntes) {
                System.out.println("- " + livre.getTitre() + " par " + livre.getAuteur());
            }
        }
    }
    System.out.println("Nombre total de livres empruntés : " + totalLivresEmpruntes);
}


}
