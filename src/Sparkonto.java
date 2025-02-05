public class Sparkonto extends Konto {
    public Sparkonto(String kontoinhaber, String kontonummer, double kontostand, double ueberziehungsrahmen) {
        super(kontoinhaber, kontonummer, kontostand, ueberziehungsrahmen);
    }

    public void einzahlen(double betrag) {
        setKontostand(getKontostand() + betrag);
    }

    @Override
    public void abheben(double betrag) {
        if (getKontostand() - betrag >= 0) {
            setKontostand(getKontostand() - betrag);
        } else {
            System.out.println("dieses Betrag " + betrag + " ist auf diesem konto nicht verfügbar");
        }
    }
}