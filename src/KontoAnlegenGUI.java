import javax.swing.*;
import java.awt.*;

public class KontoAnlegenGUI extends JFrame {
    private JTextField inhaberField, guthabenField, ueberziehungsRahmenField;
    private JComboBox<String> kontoTypDropdown;
    private JButton erstellenButton;
    private KontoverwaltungGUI verwaltungGUI;

    public KontoAnlegenGUI(KontoverwaltungGUI verwaltungGUI) {
        this.verwaltungGUI = verwaltungGUI;

        setTitle("Konto anlegen");
        setSize(300, 250);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Inhaber:"));
        inhaberField = new JTextField();
        add(inhaberField);

        add(new JLabel("Startguthaben:"));
        guthabenField = new JTextField();
        add(guthabenField);

        add(new JLabel("Kontotyp:"));
        kontoTypDropdown = new JComboBox<>(new String[]{"Girokonto", "Sparkonto", "Kreditkonto"});
        add(kontoTypDropdown);

        add(new JLabel("Überziehungsrahmen:"));
        ueberziehungsRahmenField = new JTextField();
        add(ueberziehungsRahmenField);

        erstellenButton = new JButton("Erstellen");
        erstellenButton.addActionListener(e -> kontoErstellen());
        add(erstellenButton);

        setVisible(true);
    }

    private void kontoErstellen() {
        String inhaber = inhaberField.getText().trim();
        if (inhaber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte Inhaber eingeben!");
            return;
        }

        double guthaben;
        try {
            guthaben = Double.parseDouble(guthabenField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ungültige Eingabe für Guthaben!");
            return;
        }

        String kontoTyp = (String) kontoTypDropdown.getSelectedItem();
        double ueberziehungsRahmen = 0;

        if (kontoTyp.equals("Girokonto") || kontoTyp.equals("Kreditkonto")) {
            try {
                ueberziehungsRahmen = Double.parseDouble(ueberziehungsRahmenField.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ungültige Eingabe für Überziehungsrahmen!");
                return;
            }
        }

        // Hier wird geprüft, ob es sich um ein Kreditkonto handelt und ob das Guthaben negativ ist
        if (kontoTyp.equals("Kreditkonto") && guthaben >= 0) {
            JOptionPane.showMessageDialog(this, "Ein Kreditkonto darf nicht mit positivem Guthaben erstellt werden!");
            return;
        }

        verwaltungGUI.kontoHinzufuegen(inhaber, guthaben, kontoTyp, ueberziehungsRahmen);
        dispose(); // Schließt das Fenster
    }

}
