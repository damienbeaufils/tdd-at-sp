package puissance4;

import java.util.Scanner;

public class Console {

    public void println(String stringToPrint) {
        System.out.println(stringToPrint);
    }

    public String recupereSaisie() {
        return new Scanner(System.in).nextLine();
    }
}
