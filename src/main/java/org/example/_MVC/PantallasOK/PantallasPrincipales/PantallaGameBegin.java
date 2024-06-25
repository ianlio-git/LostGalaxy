package org.example._MVC.PantallasOK.PantallasPrincipales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.example.Enums.Dificultad;
import org.example.Enums.TipoDeNave;
import org.example._MVC.Views.GameBeginView;

public class PantallaGameBegin extends JFrame {
    private JTextField nombreField;
    private JComboBox<TipoDeNave> naveComboBox;
    private JTextField sistemasEstelaresField;
    private JComboBox<Dificultad> dificultadComboBox;
    private JButton iniciarButton;

    private GameBeginView gameBeginView;
    private final Object lock = new Object();
    private boolean gameStarted = false;

    public PantallaGameBegin() {
        setTitle("Lost Galaxy - GameBegin");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10)); // Añadido espaciado entre filas y columnas

        // Centrar la pantalla
        setLocationRelativeTo(null);

        // Nombre del jugador
        add(new JLabel("Nombre del Jugador:"));
        nombreField = new JTextField();
        add(nombreField);

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

        // Espacio vacío para alinear el botón en el centro
        add(new JPanel());

        // Botón de iniciar juego centrado
        iniciarButton = new JButton("Iniciar Juego");
        iniciarButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Alineado al centro
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
        TipoDeNave nave = (TipoDeNave) naveComboBox.getSelectedItem();
        int sistemasEstelares = Integer.parseInt(sistemasEstelaresField.getText());
        Dificultad dificultad = (Dificultad) dificultadComboBox.getSelectedItem();

        gameBeginView = new GameBeginView(nombre, nave, sistemasEstelares, dificultad, 0);

        gameBeginView.setNombreDelJugador(nombre);
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
