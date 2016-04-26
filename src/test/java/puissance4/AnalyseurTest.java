package puissance4;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static puissance4.Couleur.*;

public class AnalyseurTest {

    private Grille grille;
    private Analyseur analyseur;

    @Before
    public void setUp() throws Exception {
        grille = new Grille();
        analyseur = new Analyseur(grille);
    }

    @Test
    public void shouldReturnRouge_WhenQuatreJetonsRougeAlignesSurLaPremierLigne_AGauche() throws Exception {
        // Given
        grille.addJeton(1, ROUGE);
        grille.addJeton(2, ROUGE);
        grille.addJeton(3, ROUGE);
        grille.addJeton(4, ROUGE);

        // When
        Couleur couleurGagnante = analyseur.couleurGagnante();

        // Then
        assertThat(couleurGagnante).isEqualTo(ROUGE);
    }

    @Test
    public void shouldReturnVide_WhenQuatreJetonsRougeEtJauneAlignesSurLaPremierLigne() throws Exception {
        // Given
        grille.addJeton(1, ROUGE);
        grille.addJeton(2, ROUGE);
        grille.addJeton(3, JAUNE);
        grille.addJeton(4, JAUNE);

        // When
        Couleur couleurGagnante = analyseur.couleurGagnante();

        // Then
        assertThat(couleurGagnante).isEqualTo(VIDE);
    }

    @Test
    public void shouldReturnVide_whenGridIsEmpty() throws Exception {
        // When
        Couleur couleurGagnante = analyseur.couleurGagnante();

        // Then
        assertThat(couleurGagnante).isEqualTo(VIDE);
    }

    @Test
    public void shouldReturnJaune_WhenQuatreJetonsJauneAlignesSurLaPremierLigne_ADroite() throws Exception {
        // Given
        grille.addJeton(4, JAUNE);
        grille.addJeton(5, JAUNE);
        grille.addJeton(6, JAUNE);
        grille.addJeton(7, JAUNE);

        // When
        Couleur couleurGagnante = analyseur.couleurGagnante();

        // Then
        assertThat(couleurGagnante).isEqualTo(JAUNE);
    }

    @Test
    public void shouldReturnJaune_WhenQuatreJetonsJauneAlignesSurLaPremierLigne_AuMilieu() throws Exception {
        // Given
        grille.addJeton(2, JAUNE);
        grille.addJeton(3, JAUNE);
        grille.addJeton(4, JAUNE);
        grille.addJeton(5, JAUNE);

        // When
        Couleur couleurGagnante = analyseur.couleurGagnante();

        // Then
        assertThat(couleurGagnante).isEqualTo(JAUNE);
    }

    @Test
    public void shouldReturnVide_WhenQuatreJetonsJauneAlignesSurLaPremierLigne_MaisPasConsecutifs() throws Exception {
        // Given
        ajouterLigneNonGagnante();

        // When
        Couleur couleurGagnante = analyseur.couleurGagnante();

        // Then
        assertThat(couleurGagnante).isEqualTo(VIDE);
    }

    @Test
    public void shouldReturnJaune_WhenQuatreJetonsJauneAlignesConsecutifs_SurLaDeuxiemeLigne() throws Exception {
        // Given
        ajouterLigneNonGagnante();
        grille.addJeton(2, JAUNE);
        grille.addJeton(3, JAUNE);
        grille.addJeton(4, JAUNE);
        grille.addJeton(5, JAUNE);

        // When
        Couleur couleurGagnante = analyseur.couleurGagnante();

        // Then
        assertThat(couleurGagnante).isEqualTo(JAUNE);
    }

    @Test
    public void shouldReturnRouge_WhenQuatreJetonsRougeAlignesSurLaPremiereColonne() throws Exception {
        // Given
        grille.addJeton(1, ROUGE);
        grille.addJeton(1, ROUGE);
        grille.addJeton(1, ROUGE);
        grille.addJeton(1, ROUGE);

        // When
        Couleur couleurGagnante = analyseur.couleurGagnante();

        // Then
        assertThat(couleurGagnante).isEqualTo(ROUGE);
    }

    @Test
    public void shouldReturnVide_WhenQuatreJetonsROUGE_NonConsecutifsSurLaPremiereColonne() throws Exception {
        // Given
        grille.addJeton(1, ROUGE);
        grille.addJeton(1, JAUNE);
        grille.addJeton(1, ROUGE);
        grille.addJeton(1, JAUNE);
        grille.addJeton(1, ROUGE);
        grille.addJeton(1, ROUGE);

        // When
        Couleur couleurGagnante = analyseur.couleurGagnante();

        // Then
        assertThat(couleurGagnante).isEqualTo(VIDE);
    }

    @Test
    public void shouldReturnJaune_WhenQuatreJetonsJauneAlignesSurLaPremiereColonne() throws Exception {
        // Given
        grille.addJeton(1, ROUGE);
        grille.addJeton(1, ROUGE);
        grille.addJeton(1, JAUNE);
        grille.addJeton(1, JAUNE);
        grille.addJeton(1, JAUNE);
        grille.addJeton(1, JAUNE);

        // When
        Couleur couleurGagnante = analyseur.couleurGagnante();

        // Then
        assertThat(couleurGagnante).isEqualTo(JAUNE);
    }

    @Test
    public void shouldReturnJaune_WhenQuatreJetonsJauneAlignesSurLaTroisiemeColonne() throws Exception {
        // Given
        grille.addJeton(3, ROUGE);
        grille.addJeton(3, ROUGE);
        grille.addJeton(3, JAUNE);
        grille.addJeton(3, JAUNE);
        grille.addJeton(3, JAUNE);
        grille.addJeton(3, JAUNE);

        // When
        Couleur couleurGagnante = analyseur.couleurGagnante();

        // Then
        assertThat(couleurGagnante).isEqualTo(Couleur.JAUNE);
    }

    @Test
    public void shouldReturnJaune_WhenQuatreJetonsJauneAlignesEnDiagonaleDroite_EnPartantEnBasAGauche() throws Exception {
        // Given
        grille.addJeton(1, JAUNE);
        grille.addJeton(2, ROUGE);
        grille.addJeton(3, ROUGE);
        grille.addJeton(4, ROUGE);

        grille.addJeton(2, JAUNE);
        grille.addJeton(3, ROUGE);
        grille.addJeton(4, JAUNE);

        grille.addJeton(3, JAUNE);
        grille.addJeton(4, ROUGE);

        grille.addJeton(4, JAUNE);

        // When
        Couleur couleurGagnante = analyseur.couleurGagnante();

        // Then
        assertThat(couleurGagnante).isEqualTo(Couleur.JAUNE);
    }

    @Test
    public void shouldReturnRouge_WhenQuatreJetonsRougesAlignesEnDiagonaleDroite_EnPartantEnBasAGauche() throws Exception {
        // Given
        grille.addJeton(1, ROUGE);
        grille.addJeton(2, JAUNE);
        grille.addJeton(3, JAUNE);
        grille.addJeton(4, JAUNE);

        grille.addJeton(2, ROUGE);
        grille.addJeton(3, JAUNE);
        grille.addJeton(4, ROUGE);

        grille.addJeton(3, ROUGE);
        grille.addJeton(4, JAUNE);

        grille.addJeton(4, ROUGE);

        // When
        Couleur couleurGagnante = analyseur.couleurGagnante();

        // Then
        assertThat(couleurGagnante).isEqualTo(Couleur.ROUGE);
    }


    @Test
    public void shouldReturnJaune_WhenQuatreJetonsJaunesAlignesEnDiagonaleGauche_EnPartantHautMilieu() throws Exception {
        // Given
        grille.addJeton(7, ROUGE);
        grille.addJeton(7, JAUNE);

        grille.addJeton(6, JAUNE);
        grille.addJeton(6, ROUGE);
        grille.addJeton(6, JAUNE);

        grille.addJeton(5, JAUNE);
        grille.addJeton(5, JAUNE);
        grille.addJeton(5, ROUGE);
        grille.addJeton(5, JAUNE);

        grille.addJeton(4, ROUGE);
        grille.addJeton(4, ROUGE);
        grille.addJeton(4, JAUNE);
        grille.addJeton(4, JAUNE);
        grille.addJeton(4, JAUNE);

        // When
        Couleur couleurGagnante = analyseur.couleurGagnante();

        // Then
        assertThat(couleurGagnante).isEqualTo(Couleur.JAUNE);
    }

    @Test
    public void shouldReturnTrue_WhenGrillePleineMaisNonGagnante() throws Exception {
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
        boolean partieNulle = analyseur.partieNulle();

        // Then
        assertThat(partieNulle).isTrue();
    }

    @Test
    public void shouldReturnFalse_WhenGrilleIsEmpty() throws Exception {
        // When
        boolean partieNulle = analyseur.partieNulle();

        // Then
        assertThat(partieNulle).isFalse();
    }

    @Test
    public void shouldReturnFalse_WhenGrilleGagnantePremierColonne() throws Exception {
        // Given
        grille.addJeton(1,ROUGE);
        grille.addJeton(1,ROUGE);
        grille.addJeton(1,ROUGE);
        grille.addJeton(1,ROUGE);

        // When
        boolean partieNulle = analyseur.partieNulle();

        // Then
        assertThat(partieNulle).isFalse();
    }

    @Test
    public void shouldReturnFalse_WhenQuatreJetonsROUGE_NonConsecutifsSurLaPremiereColonne() throws Exception {
        // Given
        grille.addJeton(1, ROUGE);
        grille.addJeton(1, JAUNE);
        grille.addJeton(1, ROUGE);
        grille.addJeton(1, JAUNE);
        grille.addJeton(1, ROUGE);
        grille.addJeton(1, ROUGE);

        // When
        boolean partieNulle = analyseur.partieNulle();

        // Then
        assertThat(partieNulle).isFalse();
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
