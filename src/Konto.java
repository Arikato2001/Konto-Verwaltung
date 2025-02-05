public class Konto {
    private String kontoinhaber;
    private String kontonummer;
    public double kontostand;
    private double ueberziehungsrahmen;

    public Konto(String kontoinhaber, String kontonummer, double kontostand, double ueberziehungsrahmen) {
        this.kontoinhaber = kontoinhaber;
        this.kontonummer = kontonummer;
        this.kontostand = kontostand;
        this.ueberziehungsrahmen = ueberziehungsrahmen;
    }

    public double getKontostand() {
        return kontostand;
    }

    public void setKontostand(double kontostand) {
        this.kontostand = kontostand;
    }

    public void einzahlen(double betrag) {
        this.kontostand += betrag;
    }
    public void abheben(double betrag) {
            setKontostand(getKontostand() - betrag);
    }
    public void kontoauszug() {
        System.out.println("Kontoinhaber: " + kontoinhaber);
        System.out.println("Kontonummer: " + kontonummer);
        System.out.println("Kontostand: " + kontostand + " EUR");
    }
}
