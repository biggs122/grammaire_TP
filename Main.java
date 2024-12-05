package grammaire;

public class Main {

    public static void main(String[] args) {
        // Chaînes à tester
        String[] testStrings = {
                "cdcbc", "bcdcbcb", "cbdcbdcbc", "ccdcbcdcbcdcbbcr", "cdcbbb", "cdcb", ""
        };

        for (String ch : testStrings) {
            grammaire.TokenManager tm = new grammaire.TokenManager(ch);
            grammaire.ParseurG2 parseur = new grammaire.ParseurG2(tm);

            try {
                parseur.parse();
                System.out.println(ch + " est valide");
            } catch (RuntimeException exp) {
                System.out.println(ch + " n'est pas valide");
                System.out.println("Erreur: " + exp.getMessage());
            }
        }
    }
}
