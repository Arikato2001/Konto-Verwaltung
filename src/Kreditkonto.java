class Kreditkonto extends Konto {
    public Kreditkonto(String kontoinhaber, double kontostand, String kontotyp) {
        if (kontostand >= 0) {
            // mit throw machen, weil cout nicht funktioniert wenn Super nicht top statement ist
            throw new IllegalArgumentException("Fehler: Ein Kreditkonto kann nur mit negativem Guthaben eröffnet werden!");
        }
        super(kontoinhaber, kontostand,kontotyp, 0);
    }

    public void einzahlen(double betrag) {
        if (betrag > 0) {
            double neuerKontostand = getKontostand() + betrag;

            // Prüfen, ob die Einzahlung den Kontostand positiv machen würde
            if (neuerKontostand > 0) {
                System.out.println("Einzahlung abgelehnt! Betrag ist zu hoch. Maximal erlaubt: " + (-getKontostand()) + " EUR");
            } else {
                setKontostand(neuerKontostand);
                System.out.println("Einzahlung erfolgreich! Neuer Kontostand: " + getKontostand() + " EUR");
            }
        } else {
            System.out.println("Einzahlung fehlgeschlagen! Betrag muss positiv sein.");
        }
    }
    @Override
    public void ueberweisen(Konto empfaenger, double betrag) {
        if (betrag > 0) {
            double neuerKontostand = getKontostand() - betrag;
                setKontostand(neuerKontostand);
                empfaenger.einzahlen(betrag);
                System.out.println("Überweisung erfolgreich! Neuer Kreditkontostand: " + getKontostand() + " EUR");

        } else {
            System.out.println("Überweisung fehlgeschlagen! Betrag muss positiv sein.");
        }
    }

}

