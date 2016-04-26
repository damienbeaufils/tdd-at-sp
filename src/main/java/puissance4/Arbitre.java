package puissance4;

import static puissance4.Couleur.JAUNE;
import static puissance4.Couleur.ROUGE;
import static puissance4.EtatPartie.*;

public class Arbitre {

    private Analyseur analyseur;

    private int nbCoupsJoues;

    public Arbitre(Analyseur analyseur) {
        this.analyseur = analyseur;
    }

    public Couleur aQuiLeTour() {
        return joueurCourant();
    }

    public boolean joue(int numeroColonneAJouer) throws ColonnePleineException {
        try {
            analyseur.getGrille().addJeton(numeroColonneAJouer, joueurCourant());
        } catch (IllegalArgumentException | ColonnePleineException e) {
            return false;
        }
        nbCoupsJoues++;
        return true;
    }

    public EtatPartie getEtatPartie() {
        Couleur couleurGagnante = analyseur.couleurGagnante();
        EtatPartie etatPartie;
        switch (couleurGagnante) {
            case ROUGE:
                etatPartie = GAGNANT_ROUGE;
                break;
            case JAUNE:
                etatPartie = GAGNANT_JAUNE;
                break;
            default:
                if (analyseur.partieNulle()) {
                    etatPartie = NULLE;
                } else {
                    etatPartie = EN_COURS;
                }
                break;
        }
        return etatPartie;
    }

    private Couleur joueurCourant() {
        return nbCoupsJoues % 2 == 0 ? JAUNE : ROUGE;
    }
}
