package gestionbibliotheque;

import java.util.Scanner;

class InterfaceUtilisateur {
    Bibliotheque bibliotheque;
    Scanner scanner;

    public InterfaceUtilisateur(Bibliotheque bibliotheque) {
        this.bibliotheque = bibliotheque;
        this.scanner = new Scanner(System.in);
    }
public class Main {

    public static void main(String[] args)  {
        // Création de la bibliothèque
        Bibliotheque bibliotheque = new Bibliotheque();

        // Exemple d'ajout de quelques livres à la bibliothèque
        Livre livre1 = new Livre("Le Petit Prince", "Antoine de Saint-Exupéry", 1943, "978-20061258");
        Livre livre2 = new Livre("Batouala", "René Maran", 1921, "2211075450X");
        Livre livre3 = new Livre("Oeuvre Poétique", "Léopold Sédar Senghor", 2020, "2757884077");
        Livre livre4 = new Livre("Une Vie de Boy", "Ferdinand Oyono", 1956, "2266169289");
        Livre livre5 = new Livre("Le Pagne Noir", "Bernard Dadié", 2001, "2708700251");
        Livre livre6 = new Livre("Le Fils d'Agatha Moudio", "Francis Bebey", 1987, "2708700251");
        Livre livre7 = new Livre("Trois prétendants...Un mari", "Guillaume Oyônô-Mbia", 1960, "2723500357");
        Livre livre8 = new Livre("Les Contes d'Amadou Koumba", "Birago Diop", 1966, "2708700537");
        Livre livre9 = new Livre("Une si longue lettre", "Mariama Bâ", 1980, "2385060396");
        Livre livre10 = new Livre("Ville cruelle", "EZA Boto", 1954, "2708702629");

        try {
            bibliotheque.ajouterLivre(livre1);
            bibliotheque.ajouterLivre(livre2);
            bibliotheque.ajouterLivre(livre3);
            bibliotheque.ajouterLivre(livre4);
            bibliotheque.ajouterLivre(livre5);
            bibliotheque.ajouterLivre(livre6);
            bibliotheque.ajouterLivre(livre7);
            bibliotheque.ajouterLivre(livre8);
            bibliotheque.ajouterLivre(livre9);
            bibliotheque.ajouterLivre(livre10);

            Utilisateur utilisateur1 = new Utilisateur("Moussa", 1, true);
            Utilisateur utilisateur2 = new Utilisateur("Jean", 2, false);
            Utilisateur utilisateur3 = new Utilisateur("Abdou", 3, true);

            bibliotheque.ajouterUtilisateur(utilisateur1);
            bibliotheque.ajouterUtilisateur(utilisateur2);
            bibliotheque.ajouterUtilisateur(utilisateur3);
        } catch (LibraryException e) {
            System.out.println("Erreur lors de l'ajout d'un livre ou d'un utilisateur : " + e.getMessage());
            return; // Sortie du programme en cas d'erreur
        }

        InterfaceUtilisateur interfaceUtilisateur = new InterfaceUtilisateur(bibliotheque);
        interfaceUtilisateur.demarrer();
    }
}



    public void demarrer() {
        int choix;
        do {
            afficherMenu();
            System.out.print("Choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1:
                    ajouterLivre();
                    break;
                case 2:
                    supprimerLivre();
                    break;
                case 3:
                    rechercherLivre();
                    break;
                case 4:
                    emprunterLivre();
                    break;
                case 5:
                    retournerLivre();
                    break;
                case 6:
                    afficherStatistiques();
                    break;
                case 0:
                    System.out.println("Au revoir");
                    break;
                default:
                    System.out.println("Votre choix est invalide");
            }

        } while (choix != 0);
    }

    private void afficherMenu() {
        System.out.println("============ MENU ==========");
        System.out.println("1. Ajouter un livre");
        System.out.println("============================");
        System.out.println("2. Supprimer un livre");
        System.out.println("============================");
        System.out.println("3. Rechercher un livre");
        System.out.println("============================");
        System.out.println("4. Emprunter un livre");
        System.out.println("============================");
        System.out.println("5. Retourner un livre");
        System.out.println("============================");
        System.out.println("6. Afficher les statistiques");
        System.out.println("============================");
        System.out.println("0. Quitter");
        System.out.println("============================");

    }

    private void ajouterLivre() {
        System.out.println("===== Ajouter un livre ====");
        System.out.print("Titre:");
        String titre = scanner.nextLine();
        System.out.print("Auteur:");
        String auteur = scanner.nextLine();
        System.out.print("Année de publication:");
        int anneePublication = scanner.nextInt();
        scanner.nextLine();
        System.out.print("ISBN:");
        String isbn = scanner.nextLine();

        Livre livre = new Livre(titre, auteur, anneePublication, isbn);
        try {
            bibliotheque.ajouterLivre(livre);
            System.out.println("Livre ajouté avec succès!");
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
    }

    private void supprimerLivre() {
        System.out.println("==== Supprimer un livre ====");
        System.out.print("Titre du livre à supprimer: ");
        String titre = scanner.nextLine();
        Livre livre = bibliotheque.rechercherLivre(titre);
        try {
            bibliotheque.supprimerLivre(livre);
            System.out.println("Livre supprimé avec succès!");
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
    }

    private void rechercherLivre() {
    System.out.println("==== Rechercher un livre ====");
    System.out.print("Critère de recherche (titre, auteur ou ISBN) :");
    String critere = scanner.nextLine();
    Livre livre = bibliotheque.rechercherLivre(critere);
    if (livre != null) {
        System.out.println("Livre trouvé :" + livre);
        System.out.println("Souhaitez-vous modifier ce livre ? (Oui/Non)");
        String choix = scanner.nextLine();
        if (choix.equalsIgnoreCase("Oui")) {
            modifierLivre(livre);
        }
    } else {
        System.out.println("Nous n'avons pas trouvé le livre");
    }
}

private void modifierLivre(Livre livre) {
    System.out.println("==== Modifier le livre ====");
    System.out.print("Nouveau titre : ");
    String nouveauTitre = scanner.nextLine();
    System.out.print("Nouvel auteur : ");
    String nouvelAuteur = scanner.nextLine();
    System.out.print("Nouvelle année de publication : ");
    int nouvelleAnneePublication = scanner.nextInt();
    scanner.nextLine(); // Pour consommer le saut de ligne restant
    System.out.print("Nouvel ISBN : ");
    String nouveauISBN = scanner.nextLine();

    try {
        bibliotheque.modifierLivre(livre, nouveauTitre, nouvelAuteur, nouvelleAnneePublication, nouveauISBN);
    } catch (LibraryException e) {
        System.out.println(e.getMessage());
    }
}


   private void emprunterLivre() {
    System.out.println("===== Emprunter un livre ====");
    System.out.print("Titre du livre à emprunter : ");
    String titre = scanner.nextLine();
    Livre livre = bibliotheque.rechercherLivre(titre);

    if (livre != null) {
        Utilisateur utilisateur = null;
        boolean cotisationAJour = false;

        while (utilisateur == null || !cotisationAJour) {
            System.out.print("Veuillez renseigner votre nom d'utilisateur : ");
            String nom = scanner.nextLine();
            utilisateur = bibliotheque.rechercherUtilisateur(nom);

            if (utilisateur == null) {
                System.out.println("Utilisateur non trouvé. Veuillez réessayer.");
                continue;
            }

            cotisationAJour = utilisateur.isCotisationAJour();

            if (!cotisationAJour) {
                System.out.println("Vous n'êtes pas autorisé à emprunter des livres en raison des cotisations impayées.");
            }
        }

        try {
            // Vérifier si l'utilisateur peut emprunter plus de livres
            if (bibliotheque.getNombreLivresEmpruntes(utilisateur) >= 2) {
                System.out.println("Vous avez déjà emprunté le nombre maximal de livres autorisé.");
            } else {
                Emprunt emprunt = new Emprunt(utilisateur, livre);
                utilisateur.ajouterEmprunt(emprunt);
                bibliotheque.ajouterEmprunt(emprunt);
                System.out.println("Livre emprunté avec succès!");
            }
        
        } catch (Exception e) {
            System.out.println("Une erreur inattendue s'est produite : " + e.getMessage());
        }
    }
}



private void retournerLivre() {
    System.out.println("==== Retourner un livre ====");
    System.out.print("Titre du livre à retourner: ");
    String titre = scanner.nextLine();
    Livre livre = bibliotheque.rechercherLivre(titre);

    if (livre != null) {
        System.out.print("Veuillez renseigner votre nom d'utilisateur: ");
        String nom = scanner.nextLine();
        Utilisateur utilisateur = bibliotheque.rechercherUtilisateur(nom);

        if (utilisateur != null) {
            bibliotheque.retournerLivre(utilisateur, livre);
        } else {
            System.out.println("Utilisateur non trouvé.");
        }
    } else {
        System.out.println("Le livre n'a pas été trouvé.");
    }
}



    private void afficherStatistiques() {
        System.out.println("==== Statistiques de la bibliothèque ====");
        bibliotheque.afficherStatistiques();
    }

}

