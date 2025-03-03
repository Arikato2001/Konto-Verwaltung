class Kreditkonto extends Konto {
    public Kreditkonto(String kontoinhaber, double kontostand) {
        super(kontoinhaber, kontostand, "Kreditkonto", 0);
    }
        @Override
        public void einzahlen(double betrag) {
            if (betrag <= 0) {
                throw new IllegalArgumentException("Fehler: Der Betrag muss positiv sein!");
            }
            double neuerKontostand = getKontostand() + betrag;
            if (neuerKontostand > 0) {
                throw new IllegalArgumentException("Fehler: Ein Kreditkonto darf nicht ins Plus gehen!");
            }

            setKontostand(neuerKontostand);
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

