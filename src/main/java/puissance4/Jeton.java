package puissance4;

import static puissance4.Couleur.VIDE;

public class Jeton {

    private Couleur couleur;

    public Jeton(Couleur couleur) {
        this.couleur = couleur;
    }

    public Couleur getCouleur() {
        return this.couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public boolean isVide() {
        return VIDE == this.couleur;
    }
}
