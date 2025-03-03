import java.util.Random;

public class Konto {
    private String kontoinhaber;
    private String kontonummer;
    private double kontostand;
    private String kontotyp;
    private double ueberziehungsrahmen;

    public Konto(String kontoinhaber, double kontostand, String kontoTyp, double ueberziehungsrahmen) {
        this.kontoinhaber = kontoinhaber;
        this.kontonummer = generateRandomKontonummer();
        this.kontostand = kontostand;
        kontotyp = kontoTyp;
        this.ueberziehungsrahmen = ueberziehungsrahmen;
        System.out.println("Konto erstellt: " + kontonummer);
    }

    public String getKontonummer() {
        return kontonummer;
    }

    public double getKontostand() {
        return kontostand;
    }

    public void setKontostand(double kontostand) {
        this.kontostand = kontostand;
    }

    private static String generateRandomKontonummer() {
        Random random = new Random();
        return String.valueOf(100000 + random.nextInt(900000));
    }
    public void einzahlen(double betrag) {
        this.kontostand += betrag;
    }
    public void abheben(double betrag) {
        if (betrag > 0 && this.kontostand - betrag >= -ueberziehungsrahmen) {
            this.kontostand -= betrag;
        } else {
            System.out.println("Abhebung nicht möglich! Unzureichendes Guthaben oder ungültiger Betrag.");
        }
    }

    public void ueberweisen(Konto empfaenger, double betrag) {
        if (betrag > 0 && this.kontostand - betrag >= -ueberziehungsrahmen) {
            this.kontostand -= betrag;
            empfaenger.einzahlen(betrag);
        } else {
            System.out.println("Überweisung fehlgeschlagen! Unzureichendes Guthaben oder ungültiger Betrag.");
        }
    }

    public void kontoauszug() {
        System.out.println("Kontoinhaber: " + kontoinhaber);
        System.out.println("Kontonummer: " + kontonummer);
        System.out.println("Kontostand: " + kontostand + " EUR");
    }

    public Object getInhaber() {
        return kontoinhaber;
    }

    public String getKontoTyp() {
        return kontotyp;
    }

    public double getUeberziehungsRahmen() {
        return ueberziehungsrahmen;
    }
}
