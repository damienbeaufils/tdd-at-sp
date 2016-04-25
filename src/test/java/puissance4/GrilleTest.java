package puissance4;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static puissance4.Couleur.*;


public class GrilleTest {

    private Grille grille;

    @Before
    public void setUp() throws Exception {
        grille = new Grille();
    }

    @Test
    public void shouldInitialiserNouvelleGrille_AyantSixLignesEtSeptColonnes() throws Exception {
        // When
        int nombreLignes = grille.getNombreLignes();
        int nombreColonnes = grille.getNombreColonnes();

        // Then
        assertThat(nombreLignes).isEqualTo(6);
        assertThat(nombreColonnes).isEqualTo(7);
    }

    @Test
    public void shouldReturnJetonRougeLigne1Colonne1_WhenAjoutJetonRougeColonne1() throws Exception {
        // Given
        int colonne = 1;
        int ligne = 1;
        Couleur couleur = ROUGE;

        // When
        grille.addJeton(colonne, couleur);

        // When
        assertThat(grille.getJeton(ligne, colonne).getCouleur()).isEqualTo(couleur);
    }


    @Test
    public void shouldReturnJetonJauneLigne1Colonne1_WhenAjoutJetonJauneColonne1() throws Exception {
        // Given
        int colonne = 1;
        int ligne = 1;
        Couleur couleur = JAUNE;

        // When
        grille.addJeton(colonne, couleur);

        // When
        assertThat(grille.getJeton(ligne, colonne).getCouleur()).isEqualTo(couleur);
    }

    @Test
    public void shouldReturnJetonJauneLigne2Colonne1_WhenAjoutJetonRougeColonne1AndAjoutJetonJauneColonne1() throws Exception {
        // Given
        int colonne = 1;
        int ligne = 2;
        grille.addJeton(colonne, ROUGE);

        // When
        grille.addJeton(colonne, JAUNE);

        // When
        assertThat(grille.getJeton(ligne, colonne).getCouleur()).isEqualTo(JAUNE);
    }

    @Test(expected = ColonnePleineException.class)
    public void shouldThrowColonnePleineException_WhenAjoutJetonDansColonnePleine() throws Exception {
        // Given
        int colonne = 1;
        grille.addJeton(colonne, ROUGE);
        grille.addJeton(colonne, JAUNE);
        grille.addJeton(colonne, ROUGE);
        grille.addJeton(colonne, JAUNE);
        grille.addJeton(colonne, ROUGE);
        grille.addJeton(colonne, JAUNE);

        // When
        grille.addJeton(colonne, ROUGE);
    }

    @Test
    public void shouldReturnJetonJauneLigne1Colonne2_WhenAjoutJetonJauneColonne2() throws Exception {
        // Given
        int colonne = 2;
        int ligne = 1;
        grille.addJeton(1, ROUGE);

        // When
        grille.addJeton(colonne, JAUNE);

        // When
        assertThat(grille.getJeton(ligne, colonne).getCouleur()).isEqualTo(JAUNE);
    }

    @Test
    public void shouldReturnCouleurVide_WhenGetJetonSurUneColonneEtUneLigneSansJeton() throws Exception {
        // When
        Jeton jeton = grille.getJeton(1, 1);

        // Then
        assertThat(jeton.getCouleur()).isEqualTo(VIDE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException_WhenAjoutJetonColonneZeroInvalide() throws Exception {
        // When
        grille.addJeton(0, ROUGE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException_WhenAjoutJetonColonneHuitInvalide() throws Exception {
        // When
        grille.addJeton(8, JAUNE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException_WhenGetJetonLigneZeroInvalide() throws Exception {
        // When
        grille.getJeton(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException_WhenGetJetonLigneSeptInvalide() throws Exception {
        // When
        grille.getJeton(7, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException_WhenGetJetonColonneZeroInvalide() throws Exception {
        // When
        grille.getJeton(1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException_WhenGetJetonColonneHuitInvalide() throws Exception {
        // When
        grille.getJeton(1, 8);
    }

    @Test
    public void shouldVideGrille_WhenGrilleEstCompletementRemplieAvecJetonsRougeEtJaune() throws Exception {
        // Given
        for (int indexColonne = 1; indexColonne <= grille.getNombreColonnes(); indexColonne++) {
            grille.addJeton(indexColonne, ROUGE);
            grille.addJeton(indexColonne, JAUNE);
            grille.addJeton(indexColonne, ROUGE);
            grille.addJeton(indexColonne, JAUNE);
            grille.addJeton(indexColonne, JAUNE);
            grille.addJeton(indexColonne, ROUGE);
        }

        // When
        grille.vider();

        // Then
        for (int indexColonne = 1; indexColonne <= grille.getNombreColonnes(); indexColonne++) {
            assertThat(grille.getJeton(1, indexColonne).getCouleur()).isEqualTo(VIDE);
        }
    }
}
