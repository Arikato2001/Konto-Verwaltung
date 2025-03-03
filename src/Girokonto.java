public class Girokonto extends Konto {
    public Girokonto(String kontoinhaber, double startguthaben, String kontotyp, double ueberziehungsrahmen) {
        // Überprüfung, dass ein Girokonto nur mit einem Überziehungsrahmen oder positivem Guthaben eröffnet werden kann
        if (startguthaben < 0 && ueberziehungsrahmen <= 0) {
            throw new IllegalArgumentException("Fehler: Ein Girokonto kann nur mit einem positiven Überziehungsrahmen oder positivem Guthaben eröffnet werden!");
        }

        // Überprüfen, ob der Überziehungsrahmen negativ ist und sicherstellen, dass er korrekt angewendet wird
        super(kontoinhaber, startguthaben,kontotyp, ueberziehungsrahmen);
    }
    public double getUeberziehungsrahmen() {
        return super.getKontostand();
    }
    @Override
    public void abheben(double betrag) {
        if (betrag > 0 && getKontostand() - betrag >= -getUeberziehungsrahmen()) {
            setKontostand(getKontostand() - betrag);  // Abhebung innerhalb des Überziehungsrahmens
        } else {
            System.out.println("Abhebung nicht möglich! Unzureichendes Guthaben oder ungültiger Betrag.");
        }
    }

    @Override
    public void ueberweisen(Konto empfaenger, double betrag) {
        if (betrag > 0 && getKontostand() - betrag >= -getUeberziehungsrahmen()) {
            setKontostand(getKontostand() - betrag);  // Überweisung innerhalb des Überziehungsrahmens
            empfaenger.einzahlen(betrag);
        } else {
            System.out.println("Überweisung fehlgeschlagen! Unzureichendes Guthaben oder ungültiger Betrag.");
        }
    }
}
