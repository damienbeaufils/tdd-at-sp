package puissance4;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static puissance4.EtatPartie.*;

public class ApplicationTest {

    private Application application;
    private Arbitre arbitre;
    private Console console;

    @Before
    public void setUp() throws Exception {
        arbitre = mock(Arbitre.class);
        console = mock(Console.class);
        application = new Application(arbitre, console);
        when(arbitre.aQuiLeTour()).thenReturn(Couleur.JAUNE);
        when(arbitre.getEtatPartie()).thenReturn(EN_COURS);
    }


    @Test
    public void shouldAfficheGrilleDansLaConsole() throws Exception {
        // Given
        String affichageDeLaGrille = ". . . . . . .";
        when(arbitre.affichageGrille()).thenReturn(affichageDeLaGrille);

        // When
        application.affichePartie();

        // Then
        verify(console).println(affichageDeLaGrille);
    }

    @Test
    public void shouldAfficheGrilleDansLaConsole_PartieDemarree() throws Exception {
        // Given
        String affichageDeLaGrille = "J . . . . . .";
        when(arbitre.affichageGrille()).thenReturn(affichageDeLaGrille);

        // When
        application.affichePartie();

        // Then
        verify(console).println(affichageDeLaGrille);
    }

    @Test
    public void shouldAfficheResultatPartieDansLaConsole_whenJoueurJauneGagnant() throws Exception {
        // Given
        when(arbitre.getEtatPartie()).thenReturn(GAGNANT_JAUNE);

        // When
        application.affichePartie();

        // Then
        verify(console).println("JOUEUR GAGNANT : JAUNE");
    }

    @Test
    public void shouldAfficheResultatPartieDansLaConsole_whenJoueurRougeGagnant() throws Exception {
        // Given
        when(arbitre.getEtatPartie()).thenReturn(GAGNANT_ROUGE);

        // When
        application.affichePartie();

        // Then
        verify(console).println("JOUEUR GAGNANT : ROUGE");
    }

    @Test
    public void shouldAfficheResultatPartieDansLaConsole_whenMatchNul() throws Exception {
        // Given
        when(arbitre.getEtatPartie()).thenReturn(NULLE);

        // When
        application.affichePartie();

        // Then
        verify(console).println("MATCH NUL");
    }

    @Test
    public void shouldAfficheJoueurCourantDansLaConsole() throws Exception {
        // Given
        when(arbitre.aQuiLeTour()).thenReturn(Couleur.ROUGE);

        // When
        application.affichePartie();

        // Then
        verify(console).println("JOUEUR COURANT : ROUGE");
    }

    @Test
    public void shouldDemandeALUtilisateurLeNumeroDeColonneAJouer() throws Exception {
        // Given
        when(console.recupereSaisie()).thenReturn("1");

        // When
        application.jouerSaisie();

        // Then
        verify(console).println("Entrez un numéro de colonne à jouer : ");
    }

    @Test
    public void shouldJoueCoupEnUtilisantSaisieUtilisateur_ColonneCinq() throws Exception {
        // Given
        int numeroColonneAJouer = 5;
        when(console.recupereSaisie()).thenReturn(String.valueOf(numeroColonneAJouer));

        // When
        application.jouerSaisie();

        // Then
        verify(arbitre).joue(numeroColonneAJouer);
    }

    @Test
    public void shouldJoueCoupEnUtilisantSaisieUtilisateur_ColonneDeux() throws Exception {
        // Given
        int numeroColonneAJouer = 2;
        when(console.recupereSaisie()).thenReturn(String.valueOf(numeroColonneAJouer));

        // When
        application.jouerSaisie();

        // Then
        verify(arbitre).joue(numeroColonneAJouer);
    }

    @Test
    public void shouldRedemandeSaisieUtilisateur_whenSaisieNestPasUnEntier() throws Exception {
        // Given
        when(console.recupereSaisie()).thenReturn("pasUnEntier", "5");

        // When
        application.jouerSaisie();

        // Then
        verify(arbitre, times(1)).joue(5);
    }

    @Test
    public void shouldJouerAucunCoup_whenJoueurRougeGagnant() throws Exception {
        // Given
        when(arbitre.getEtatPartie()).thenReturn(GAGNANT_ROUGE);

        // When
        application.jouerSaisie();

        // Then
        verify(arbitre, never()).joue(anyInt());
        verify(console, never()).recupereSaisie();
    }

    @Test
    public void shouldJouerAucunCoup_whenJoueurJauneGagnant() throws Exception {
        // Given
        when(arbitre.getEtatPartie()).thenReturn(GAGNANT_JAUNE);

        // When
        application.jouerSaisie();

        // Then
        verify(arbitre, never()).joue(anyInt());
        verify(console, never()).recupereSaisie();
    }

    @Test
    public void shouldJouerAucunCoup_whenPartieNulle() throws Exception {
        // Given
        when(arbitre.getEtatPartie()).thenReturn(NULLE);

        // When
        application.jouerSaisie();

        // Then
        verify(arbitre, never()).joue(anyInt());
        verify(console, never()).recupereSaisie();
    }

}
