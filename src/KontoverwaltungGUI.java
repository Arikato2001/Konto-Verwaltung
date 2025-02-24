import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KontoverwaltungGUI {
    private JFrame  frame;
    private JButton anlegenButton;
    private JButton einzahlenButton;
    private JButton abhebenButton;
    private JButton ueberweisenButton;

    public KontoverwaltungGUI() {
        frame = new JFrame("Kontoverwaltung");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(4, 1));

        anlegenButton = new JButton("Konto anlegen");
        einzahlenButton = new JButton("Einzahlen");
        abhebenButton = new JButton("Abheben");
        ueberweisenButton = new JButton("Überweisen");

        anlegenButton.addActionListener(e -> new KontoAnlegenGUI());
        frame.add(anlegenButton);
        frame.add(einzahlenButton);
        frame.add(abhebenButton);
        frame.add(ueberweisenButton);

        frame.setVisible(true);
    }
}
}
