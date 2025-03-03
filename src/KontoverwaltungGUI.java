import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class KontoverwaltungGUI extends JFrame {
    private JComboBox<String> inhaberDropdown;
    private JTextArea kontoDetails;
    private JTextField betragEinzahlen, betragAbheben, betragUeberweisen;
    private JButton kontoAnlegenButton, einzahlenButton, abhebenButton, ueberweisenButton;
    private JPanel contentPane;
    private List<Konto> konten = new ArrayList<>();

    public KontoverwaltungGUI() {

        setTitle("Kontoverwaltung");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // content was GUI gemacht worden für Ausgabe zuweisen
        setContentPane(contentPane);
        setVisible(true);

        // ActionListener für inhaberDropdown
        inhaberDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontoAnzeigen();
            }
        });

        // ActionListener für einzahlenButton
        einzahlenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                einzahlen();
            }
        });

        // ActionListener für abhebenButton
        abhebenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abheben();
            }
        });

        // ActionListener für ueberweisenButton
        ueberweisenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ueberweisen();
            }
        });

        kontoAnlegenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hier öffnest du das Fenster zum Erstellen eines neuen Kontos
                new KontoAnlegenGUI(KontoverwaltungGUI.this);  // Übergibt das KontoverwaltungGUI an das KontoAnlegenGUI
            }
        });
    }
    // Methode zum Hinzufügen eines Kontos in KontoverwaltungGUI
    // Diese Methode wird aus dem KontoAnlegenGUI aufgerufen, wenn ein Konto hinzugefügt wurde
    public void kontoHinzufuegen(String inhaber, double guthaben, String kontoTyp, double ueberziehungsRahmen) {
        // Ein neues Konto hinzufügen
        Konto neuesKonto = new Konto(inhaber, guthaben, kontoTyp, ueberziehungsRahmen);
        konten.add(neuesKonto);

        // Den Inhaber der Dropdown-Liste hinzufügen
        inhaberDropdown.addItem(inhaber);

        // Aktualisiere die ComboBox, falls ein neuer Inhaber hinzugefügt wurde
        inhaberDropdown.setSelectedItem(inhaber);

        // Aktualisiere die Kontodetails (JTextArea)
        kontoAnzeigen();
    }



    private void kontoAnzeigen() {
        String inhaber = (String) inhaberDropdown.getSelectedItem();
        for (Konto k : konten) {
            if (k.getInhaber().equals(inhaber)) {
                // Aktualisiere die JTextArea mit den neuen Kontoinformationen
                kontoDetails.setText("Kontoinhaber: " + k.getInhaber() + "\n" +
                        "Kontostand: " + k.getKontostand() + " €\n" +
                        "Kontotyp: " + k.getKontoTyp() + "\n" +
                        "Überziehungsrahmen: " + (k.getUeberziehungsRahmen() > 0 ? k.getUeberziehungsRahmen() + " €" : "Nicht verfügbar"));
                return;
            }
        }
    }


    private void einzahlen() {
        try {
            double betrag = Double.parseDouble(betragEinzahlen.getText().trim());
            if (betrag <= 0) throw new IllegalArgumentException("Betrag muss positiv sein!");

            String inhaber = (String) inhaberDropdown.getSelectedItem();
            for (Konto k : konten) {
                if (k.getInhaber().equals(inhaber)) {
                    k.einzahlen(betrag);
                    kontoAnzeigen();
                    return;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ungültige Eingabe für Betrag!");
        }
    }

    private void abheben() {
        try {
            double betrag = Double.parseDouble(betragAbheben.getText().trim());
            if (betrag <= 0) throw new IllegalArgumentException("Betrag muss positiv sein!");

            String inhaber = (String) inhaberDropdown.getSelectedItem();
            for (Konto k : konten) {
                if (k.getInhaber().equals(inhaber)) {
                    k.abheben(betrag);
                    kontoAnzeigen();
                    return;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ungültige Eingabe für Betrag!");
        }
    }

    private void ueberweisen() {
        JOptionPane.showMessageDialog(this, "Überweisungs-Funktion noch nicht implementiert!");
    }

    public static void main(String[] args) {
        new KontoverwaltungGUI();
    }
}
