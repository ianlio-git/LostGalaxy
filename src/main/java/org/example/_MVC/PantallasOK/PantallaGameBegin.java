package org.example.FuturoFront;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.example.Enums.Dificultad;
import org.example.Enums.TipoDeNave;
import org.example.Views.GameBeginView;

public class PantallaGameBegin extends JFrame {
    private JTextField nombreField;
    private JTextField uadeCoinsField;
    private JComboBox<TipoDeNave> naveComboBox;
    private JTextField sistemasEstelaresField;
    private JComboBox<Dificultad> dificultadComboBox;
    private JButton iniciarButton;


    private GameBeginView gameBeginView;
    private final Object lock = new Object();
    private boolean gameStarted = false;


    public PantallaGameBegin() {
        setTitle("Iniciar Juego");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // Nombre del jugador
        add(new JLabel("Nombre del Jugador:"));
        nombreField = new JTextField();
        add(nombreField);

        // UadeCoins del jugador
        add(new JLabel("UadeCoins del Jugador:"));
        uadeCoinsField = new JTextField();
        add(uadeCoinsField);

        // Nave del jugador
        add(new JLabel("Nave del Jugador:"));
        naveComboBox = new JComboBox<>(TipoDeNave.values());
        add(naveComboBox);

        // Cantidad de sistemas estelares
        add(new JLabel("Cantidad de Sistemas Estelares:"));
        sistemasEstelaresField = new JTextField();
        add(sistemasEstelaresField);

        // Dificultad
        add(new JLabel("Dificultad:"));
        dificultadComboBox = new JComboBox<>(Dificultad.values());
        add(dificultadComboBox);

        // Botón de iniciar juego
        iniciarButton = new JButton("Iniciar Juego");
        add(iniciarButton);

        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJuego();
            }
        });
    }

    private void iniciarJuego() {
        String nombre = nombreField.getText();
        double uadeCoins = Double.parseDouble(uadeCoinsField.getText());
        TipoDeNave nave = (TipoDeNave) naveComboBox.getSelectedItem();
        int sistemasEstelares = Integer.parseInt(sistemasEstelaresField.getText());
        Dificultad dificultad = (Dificultad) dificultadComboBox.getSelectedItem();

        gameBeginView = new GameBeginView(nombre, uadeCoins, nave, sistemasEstelares, dificultad, 0);

        gameBeginView.setNombreDelJugador(nombre);
        gameBeginView.setUadeCoinsJugador(uadeCoins);
        gameBeginView.setNaveJugador(nave);
        gameBeginView.setCantidadSistemasEstelares(sistemasEstelares);
        gameBeginView.setDificultad(dificultad);

        synchronized (lock) {
            gameStarted = true;
            lock.notify();
        }
        setVisible(false);
        dispose();
    }

    public GameBeginView getGameBeginView() {
        return gameBeginView;
    }

    public void waitForGameStart() throws InterruptedException {
        synchronized (lock) {
            while (!gameStarted) {
                lock.wait();
            }
        }
    }
}