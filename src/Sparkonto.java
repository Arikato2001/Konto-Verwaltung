public class Sparkonto extends Konto {
    public Sparkonto(String kontoinhaber, double kontostand, String kontotyp) {
        if (kontostand <= 0) {
            throw new IllegalArgumentException("Fehler: Ein Kreditkonto kann nur mit Positiven Guthaben eröffnet werden!");
        }

        super(kontoinhaber, kontostand,kontotyp, 0);
    }

    @Override
    public void abheben(double betrag) {
        if (betrag > 0) {
            double neuerKontostand = getKontostand() - betrag;

            // Prüfen, ob die Abhebung den Kontostand negativ machen würde
            if (neuerKontostand < 0) {
                System.out.println("Abhebung abgelehnt! Nicht genug Guthaben. Maximal abhebbar: " + getKontostand() + " EUR");
            } else {
                setKontostand(neuerKontostand);
                System.out.println("Abhebung erfolgreich! Neuer Kontostand: " + getKontostand() + " EUR");
            }
        }
    }
    @Override
    public void ueberweisen(Konto empfaenger, double betrag) {
        if (betrag > 0) {
            double neuerKontostand = getKontostand() - betrag;

            // Prüfen, ob die Überweisung den Kontostand negativ machen würde
            if (neuerKontostand < 0) {
                System.out.println("Überweisung abgelehnt! Nicht genug Guthaben. Maximal überweisbar: " + getKontostand() + " EUR");
            } else {
                setKontostand(neuerKontostand);
                empfaenger.einzahlen(betrag);
                System.out.println("Überweisung erfolgreich! Neuer Kontostand: " + getKontostand() + " EUR");
            }
        } else {
            System.out.println("Überweisung fehlgeschlagen! Betrag muss positiv sein.");
        }
    }
}