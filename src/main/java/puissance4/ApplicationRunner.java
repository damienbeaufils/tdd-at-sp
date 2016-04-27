package puissance4;

public class ApplicationRunner {

    public static void main(String[] args) {
        Application application = new Application(new Arbitre(new Analyseur(new Grille())), new Console());
        while (true) {
            application.affichePartie();
            application.jouerSaisie();
        }
    }
}
