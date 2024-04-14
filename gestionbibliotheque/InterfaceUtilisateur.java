import java.util.Scanner;

import gestionbibliotheque.Bibliotheque;

public class InterfaceUtilisateur {
    Bibliotheque bibliotheque;
    Scanner scanner;

    public InterfaceUtilisateur(Bibliotheque bibliotheque) {
        this.bibliotheque = bibliotheque;
        this.scanner = new Scanner(System.in);
}
}
