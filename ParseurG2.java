package grammaire;

public class ParseurG2 {
    private grammaire.TokenManager tm;
    private char tc;

    public ParseurG2(grammaire.TokenManager tm) {
        this.tm = tm;
        avancer();
    }

    private void avancer() {
        tc = tm.suivant();
    }

    private void consommer(char attendu) {
        if (tc == attendu)
            avancer();
        else
            throw new RuntimeException(attendu + " attendu, " + tc + " trouvé");
    }

    // Règle pour S -> bSb | cAc
    private void S() {
        if (tc == 'b') {
            consommer('b');
            S();
            consommer('b');
        } else if (tc == 'c') {
            consommer('c');
            A();
            consommer('c');
        } else {
            throw new RuntimeException(tc + " : token non valide pour S");
        }
    }

    // Règle pour A -> bAA | cASAb | dcb
    private void A() {
        if (tc == 'b') {
            consommer('b');
            A();
            A();
        } else if (tc == 'c') {
            consommer('c');
            A();
            S();
            A();
            consommer('b');
        } else if (tc == 'd') {
            consommer('d');
            consommer('c');
            consommer('b');
        } else {
            throw new RuntimeException(tc + " : token non valide pour A");
        }
    }

    // Démarrage de l'analyse
    public void parse() {
        S();
        if (tc != '#') {
            throw new RuntimeException("Fin de chaîne attendue, mais " + tc + " trouvé");
        }
    }
}
