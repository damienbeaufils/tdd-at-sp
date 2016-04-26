package puissance4;

public enum Couleur {

    JAUNE("J"), VIDE("."), ROUGE("R");

    private String display;

    Couleur(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
