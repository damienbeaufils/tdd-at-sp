package puissance4;

import static puissance4.EtatPartie.EN_COURS;

public class Application {

    private Arbitre arbitre;
    private Console console;

    public Application(Arbitre arbitre, Console console) {
        this.console = console;
        this.arbitre = arbitre;
    }

    public void affichePartie() {
        console.println(arbitre.affichageGrille());
        console.println("JOUEUR COURANT : " + arbitre.aQuiLeTour().toString());
        EtatPartie etatPartie = arbitre.getEtatPartie();

        switch (etatPartie) {
            case GAGNANT_JAUNE:
                console.println("JOUEUR GAGNANT : JAUNE");
                break;
            case GAGNANT_ROUGE:
                console.println("JOUEUR GAGNANT : ROUGE");
                break;
            case NULLE:
                console.println("MATCH NUL");
                break;
        }
    }


    public void jouerSaisie() {
        if (arbitre.getEtatPartie() == EN_COURS) {
            console.println("Entrez un numéro de colonne à jouer : ");
            String numeroColonne = console.recupereSaisie();
            try {
                int numeroColonneAJouer = Integer.parseInt(numeroColonne);
                arbitre.joue(numeroColonneAJouer);
            } catch (NumberFormatException e) {
                jouerSaisie();
            }
        }
    }

}
