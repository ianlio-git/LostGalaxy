package org.example.FuturoFront;

import org.example.Nave.PartesDeLaNave.Arma;
import org.example.Views.JugadorView;

import javax.swing.*;
import java.awt.*;

public class PantallaJugador extends JFrame {
    private JugadorView jugadorView;

    public PantallaJugador(JugadorView jugadorView) {
        this.jugadorView = jugadorView;
        initUI();
    }

    private void initUI() {
        setTitle("Datos del Jugador");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(crearPosicionPanel());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(crearDatosJugadorPanel());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(crearStatsPanel());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(crearArmasPanel());

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane);
    }

    private JPanel crearPosicionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Posición"));

        JLabel sistemaLabel = new JLabel("Dentro del sistema: " + jugadorView.getSistemaActual().mostrarNombre());
        panel.add(sistemaLabel);

        JLabel posicionLabel = new JLabel("Posicionado en el: " + jugadorView.getPosicionEnElEspacio());
        panel.add(posicionLabel);

        return panel;
    }

    private JPanel crearDatosJugadorPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Datos del Jugador"));

        JLabel nombreLabel = new JLabel("Nombre: " + jugadorView.getNombre());
        panel.add(nombreLabel);

        JLabel uadeCoinsLabel = new JLabel("Cantidad de uade coins: " + jugadorView.getUadeCoins());
        panel.add(uadeCoinsLabel);

        JLabel naveLabel = new JLabel("Mi nave es: " + jugadorView.getNave().soyNaveTipo());
        panel.add(naveLabel);

        return panel;
    }

    private JPanel crearStatsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Stats"));

        JLabel vidaLabel = new JLabel("Vida: " + jugadorView.getNave().getVida());
        panel.add(vidaLabel);

        JLabel escudoActualLabel = new JLabel("Escudo Actual: " + jugadorView.getNave().getEscudo().cantidadEscudoActual());
        panel.add(escudoActualLabel);

        JLabel escudoMaxLabel = new JLabel("Escudo Máximo: " + jugadorView.getNave().getEscudo().cantidadEscudoMax());
        panel.add(escudoMaxLabel);

        JLabel combustibleLabel = new JLabel("Combustible: " + jugadorView.getNave().getTanque().getCombustible());
        panel.add(combustibleLabel);

        return panel;
    }

    private JPanel crearArmasPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Armas"));

        if (jugadorView.getNave().tengoArmas()) {
            JLabel armasLabel = new JLabel("Mis armas son:");
            panel.add(armasLabel);
            // Asume que `getArmas` devuelve una lista de nombres de armas
            for (Arma arma : jugadorView.getNave().getArmas()) {
                JLabel armaLabel = new JLabel(" - " + arma.soyTipoDeArma());
                panel.add(armaLabel);
            }
        } else {
            JLabel sinArmasLabel = new JLabel("Aún no tengo un arma, ¡pero ya tendré una!");
            panel.add(sinArmasLabel);
        }

        JLabel poderLabel = new JLabel("Poder: " + jugadorView.getNave().poderAtaque());
        panel.add(poderLabel);

        return panel;
    }

    public void mostrarDatosDelJugador() {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }



}
