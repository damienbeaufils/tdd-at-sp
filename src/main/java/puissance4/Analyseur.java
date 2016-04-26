package puissance4;

import static puissance4.Analyseur.SensDeRecherche.*;
import static puissance4.Couleur.VIDE;

public class Analyseur {

    private Grille grille;

    public Analyseur(Grille grille) {
        this.grille = grille;
    }

    public Couleur couleurGagnante() {

        for (int indexLigne = 1; indexLigne <= grille.getNombreLignes(); indexLigne++) {
            for (int indexColonne = 1; indexColonne <= grille.getNombreColonnes(); indexColonne++) {
                Couleur couleurGagnante = couleurGagnanteEnPartantDe(indexLigne, indexColonne);
                if (couleurGagnante != VIDE) {
                    return couleurGagnante;
                }
            }
        }
        return VIDE;
    }


    public boolean partieNulle() {
        int indexDerniereLigne = grille.getNombreLignes();
        for (int indexColonne = 1; indexColonne <= grille.getNombreColonnes(); indexColonne++) {
            if (grille.getJeton(indexDerniereLigne, indexColonne).isVide()) {
                return false;
            }
        }

        return couleurGagnante() == VIDE;
    }

    private Couleur couleurGagnanteEnPartantDe(int indexLigne, int indexColonne) {
        Jeton jetonDeDepart = grille.getJeton(indexLigne, indexColonne);
        Couleur couleurJetonDeDepart = jetonDeDepart.getCouleur();

        boolean verticalGagnant = rechercheLigneGagnante(couleurJetonDeDepart, indexLigne, indexColonne, EN_AVANT, FIXE);
        boolean horizontalGagnant = rechercheLigneGagnante(couleurJetonDeDepart, indexLigne, indexColonne, FIXE, EN_AVANT);
        boolean diagonaleDroiteGagnant = rechercheLigneGagnante(couleurJetonDeDepart, indexLigne, indexColonne, EN_AVANT, EN_AVANT);
        boolean diagonaleGaucheGagnant = rechercheLigneGagnante(couleurJetonDeDepart, indexLigne, indexColonne, EN_ARRIERE, EN_AVANT);

        if (diagonaleDroiteGagnant || horizontalGagnant || verticalGagnant || diagonaleGaucheGagnant) {
            return couleurJetonDeDepart;
        }

        return VIDE;
    }

    private boolean rechercheLigneGagnante(Couleur couleurJetonDeDepart, int indexLigne, int indexColonne, SensDeRecherche directionLigne, SensDeRecherche directionColonne) {
        int sensDeRechercheSurLaLigne = directionLigne.getValue();
        int sensDeRechercheSurLaColonne = directionColonne.getValue();
        return grille.getJeton(indexLigne + (sensDeRechercheSurLaLigne * 1), indexColonne + (sensDeRechercheSurLaColonne * 1)).getCouleur() == couleurJetonDeDepart &&
                grille.getJeton(indexLigne + (sensDeRechercheSurLaLigne * 2), indexColonne + (sensDeRechercheSurLaColonne * 2)).getCouleur() == couleurJetonDeDepart &&
                grille.getJeton(indexLigne + (sensDeRechercheSurLaLigne * 3), indexColonne + (sensDeRechercheSurLaColonne * 3)).getCouleur() == couleurJetonDeDepart;
    }

    public Grille getGrille() {
        return grille;
    }

    enum SensDeRecherche {
        EN_ARRIERE(-1),
        FIXE(0),
        EN_AVANT(1);

        private int value;

        SensDeRecherche(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


}
