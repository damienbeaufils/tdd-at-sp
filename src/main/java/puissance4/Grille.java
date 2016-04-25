package puissance4;

import java.util.*;

import static puissance4.Couleur.VIDE;

public class Grille {

    private static final int NOMBRE_COLONNES = 7;
    private static final int NOMBRE_LIGNES = 6;

    private Map<Integer, List<Jeton>> jetons;

    public Grille() {
        initialiserNouvelleGrille();
    }

    public void addJeton(int colonne, Couleur couleur) throws ColonnePleineException {
        if (isColonneInvalide(colonne)) {
            throw new IllegalArgumentException();
        }
        Optional<Jeton> firstJetonVide = getFirstJetonVide(colonne);
        if (!firstJetonVide.isPresent()) {
            throw new ColonnePleineException();
        }
        firstJetonVide.get().setCouleur(couleur);
    }

    public Jeton getJeton(int ligne, int colonne) {
        if (isLigneInvalide(ligne) || isColonneInvalide(colonne)) {
            throw new IllegalArgumentException();
        }
        return jetons.get(colonne).get(ligne - 1);
    }

    public void vider() {
        initialiserNouvelleGrille();
    }

    public int getNombreColonnes() {
        return NOMBRE_COLONNES;
    }

    public int getNombreLignes() {
        return NOMBRE_LIGNES;
    }

    private void initialiserNouvelleGrille() {
        jetons = new HashMap<>();
        for (int indexColonne = 1; indexColonne <= getNombreColonnes(); indexColonne++) {
            List<Jeton> jetonsDeLaColonne = new ArrayList<>(getNombreLignes());
            for (int indexLigne = 0; indexLigne < getNombreLignes(); indexLigne++) {
                jetonsDeLaColonne.add(new Jeton(VIDE));
            }
            jetons.put(indexColonne, jetonsDeLaColonne);
        }
    }

    private Optional<Jeton> getFirstJetonVide(int colonne) {
        return this.jetons.get(colonne).stream()
                .filter(Jeton::isVide)
                .findFirst();
    }

    private boolean isLigneInvalide(int ligne) {
        return ligne < 1 || ligne > NOMBRE_LIGNES;
    }

    private boolean isColonneInvalide(int colonne) {
        return colonne < 1 || colonne > NOMBRE_COLONNES;
    }
}
