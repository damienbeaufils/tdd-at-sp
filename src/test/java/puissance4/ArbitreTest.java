package puissance4;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static puissance4.Couleur.JAUNE;
import static puissance4.Couleur.ROUGE;
import static puissance4.EtatPartie.*;

public class ArbitreTest {

    private Grille grille;
    private Analyseur analyseur;
    private Arbitre arbitre;

    @Before
    public void setUp() throws Exception {
        grille = new Grille();
        analyseur = new Analyseur(grille);
        arbitre = new Arbitre(analyseur);
    }

    @Test
    public void shouldReturnJaune_WhenNouvellePartie() throws Exception {
        // When
        Couleur couleur = arbitre.aQuiLeTour();

        // Then
        assertThat(couleur).isEqualTo(JAUNE);
    }

    @Test
    public void shouldAddJetonJauneDansGrille_WhenJouePremierCoup() throws Exception {
        // Given
        int colonne = 1;

        // When
        arbitre.joue(colonne);

        // Then
        assertThat(grille.getJeton(1, colonne).getCouleur()).isEqualTo(JAUNE);
    }

    @Test
    public void shouldAddJetonRougeDansGrille_WhenJoueDeuxiemeCoup() throws Exception {
        // Given
        arbitre.joue(4);

        // When
        arbitre.joue(5);

        // Then
        assertThat(grille.getJeton(1, 5).getCouleur()).isEqualTo(ROUGE);
    }

    @Test
    public void shouldReturnRouge_WhenPremierCoupJoue() throws Exception {
        // Given
        arbitre.joue(3);

        // When
        Couleur couleur = arbitre.aQuiLeTour();

        // Then
        assertThat(couleur).isEqualTo(ROUGE);
    }

    @Test
    public void shouldReturnPartieEnCours_WhenNouvellePartie() throws Exception {
        // When
        EtatPartie etatPartie = arbitre.getEtatPartie();

        // Then
        assertThat(etatPartie).isEqualTo(EN_COURS);
    }

    @Test
    public void shouldReturnPartieGagneJaune_WhenVictoireJaune() throws Exception {
        // Given
        arbitre.joue(1);    // J
        arbitre.joue(2);    // R
        arbitre.joue(1);    // J
        arbitre.joue(2);    // R
        arbitre.joue(1);    // J
        arbitre.joue(2);    // R
        arbitre.joue(1);    // J

        // When
        EtatPartie etatPartie = arbitre.getEtatPartie();

        // Then
        assertThat(etatPartie).isEqualTo(GAGNANT_JAUNE);
    }

    @Test
    public void shouldReturnPartieGagneRouge_WhenVictoireRouge() throws Exception {
        // Given
        arbitre.joue(1);    // J
        arbitre.joue(2);    // R
        arbitre.joue(3);    // J
        arbitre.joue(2);    // R
        arbitre.joue(4);    // J
        arbitre.joue(2);    // R
        arbitre.joue(5);    // J
        arbitre.joue(2);    // R

        // When
        EtatPartie etatPartie = arbitre.getEtatPartie();

        // Then
        assertThat(etatPartie).isEqualTo(GAGNANT_ROUGE);
    }

    @Test
    public void shouldReturnEnCours_WhenPartieCommenceNonGagnee() throws Exception {
        // Given
        arbitre.joue(1);
        arbitre.joue(2);

        // When
        EtatPartie etatPartie = arbitre.getEtatPartie();

        // Then
        assertThat(etatPartie).isEqualTo(EN_COURS);
    }

    @Test
    public void shouldReturnPartieNulle_WhenPartieTermineeNonGagnee() throws Exception {
        // Given
        ajouterLigneNonGagnante();
        ajouterLigneNonGagnante();
        ajouterLigneNonGagnante();
        grille.addJeton(1, ROUGE);
        grille.addJeton(2, JAUNE);
        grille.addJeton(3, ROUGE);
        grille.addJeton(4, JAUNE);
        grille.addJeton(5, ROUGE);
        grille.addJeton(6, JAUNE);
        grille.addJeton(7, ROUGE);
        ajouterLigneNonGagnante();
        ajouterLigneNonGagnante();

        // When
        EtatPartie etatPartie = arbitre.getEtatPartie();

        // Then
        assertThat(etatPartie).isEqualTo(NULLE);
    }

    @Test
    public void shouldReturnTrueSiCoupPossible() throws ColonnePleineException {
        //When
        boolean coupPossible = arbitre.joue(1);

        //Then
        assertThat(coupPossible).isTrue();
    }

    @Test
    public void shouldReturnFalseSiCoupImpossible() throws ColonnePleineException {
        //When
        boolean coupPossible = arbitre.joue(-1);

        //Then
        assertThat(coupPossible).isFalse();
    }

    @Test
    public void shouldReturnFalseSiColonnePleine() throws ColonnePleineException {
        //Given
        arbitre.joue(1);
        arbitre.joue(1);
        arbitre.joue(1);
        arbitre.joue(1);
        arbitre.joue(1);
        arbitre.joue(1);

        //When
        boolean coupPossible = arbitre.joue(1);

        //Then
        assertThat(coupPossible).isFalse();
    }

    private void ajouterLigneNonGagnante() throws ColonnePleineException {
        grille.addJeton(1, JAUNE);
        grille.addJeton(2, ROUGE);
        grille.addJeton(3, JAUNE);
        grille.addJeton(4, ROUGE);
        grille.addJeton(5, JAUNE);
        grille.addJeton(6, ROUGE);
        grille.addJeton(7, JAUNE);
    }
}
