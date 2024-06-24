package org.example._MVC.PantallasOK.PantallasInfomacion;

import org.example.GameMaster.Juego;
import org.example.Nave.PartesDeLaNave.Arma;
import org.example._MVC.Views.JugadorView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PantallaJugador extends JFrame {

    private JugadorView jugadorView;

    private JLabel sistemaLabel;
    private JLabel posicionLabel;
    private JLabel nombreLabel;
    private JLabel uadeCoinsLabel;
    private JLabel naveLabel;
    private JLabel vidaLabel;
    private JLabel escudoActualLabel;
    private JLabel escudoMaxLabel;
    private JLabel combustibleLabel;
    private JPanel armasPanel;
    private JLabel poderLabel;

    public PantallaJugador(JugadorView jugadorView) {
        this.jugadorView = jugadorView;
        initUI();
        iniciarActualizarDatos(); // Iniciar el temporizador para actualizar los datos del jugador
    }

    private void initUI() {
        setTitle("Datos del Jugador");
        setSize(400, 500);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        sistemaLabel = crearLabelConBorder("Dentro del sistema: " + jugadorView.getSistemaActual().mostrarNombre(), "Posición");
        mainPanel.add(sistemaLabel);

        posicionLabel = crearLabelConBorder("Posicionado en el: " + jugadorView.getPosicionEnElEspacio(), "Posición");
        mainPanel.add(posicionLabel);

        nombreLabel = crearLabelConBorder("Nombre: " + jugadorView.getNombre(), "Datos del Jugador");
        mainPanel.add(nombreLabel);

        uadeCoinsLabel = crearLabelConBorder("Cantidad de uade coins: " + jugadorView.getUadeCoins(), "Datos del Jugador");
        mainPanel.add(uadeCoinsLabel);

        naveLabel = crearLabelConBorder("Mi nave es: " + jugadorView.getNave().soyNaveTipo(), "Datos del Jugador");
        mainPanel.add(naveLabel);

        vidaLabel = crearLabelConBorder("Vida: " + jugadorView.getNave().getVida(), "Stats");
        mainPanel.add(vidaLabel);

        escudoActualLabel = crearLabelConBorder("Escudo Actual: " + jugadorView.getNave().getEscudo().cantidadEscudoActual(), "Stats");
        mainPanel.add(escudoActualLabel);

        escudoMaxLabel = crearLabelConBorder("Escudo Máximo: " + jugadorView.getNave().getEscudo().cantidadEscudoMax(), "Stats");
        mainPanel.add(escudoMaxLabel);

        combustibleLabel = crearLabelConBorder("Combustible: " + jugadorView.getNave().getTanque().getCombustible(), "Stats");
        mainPanel.add(combustibleLabel);

        armasPanel = new JPanel();
        armasPanel.setLayout(new BoxLayout(armasPanel, BoxLayout.Y_AXIS));
        armasPanel.setBorder(BorderFactory.createTitledBorder("Armas"));
        actualizarArmas();
        mainPanel.add(armasPanel);

        poderLabel = crearLabelConBorder("Poder: " + jugadorView.getNave().poderAtaque(), "Stats");
        mainPanel.add(poderLabel);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane);
    }

    private JLabel crearLabelConBorder(String texto, String titulo) {
        JLabel label = new JLabel(texto);
        label.setBorder(BorderFactory.createTitledBorder(titulo));
        return label;
    }

    private void actualizarArmas() {
        List<Arma> armas = jugadorView.getNave().getArmas();
        armasPanel.removeAll();

        if (!armas.isEmpty()) {
            for (Arma arma : armas) {
                JLabel armaLabel = new JLabel(" - " + arma.soyTipoDeArma());
                armasPanel.add(armaLabel);
            }
        } else {
            JLabel sinArmasLabel = new JLabel("Aún no tengo un arma, ¡pero ya tendré una!");
            armasPanel.add(sinArmasLabel);
        }

        armasPanel.revalidate();
        armasPanel.repaint();
    }

    private void iniciarActualizarDatos() {
        // Iniciar el temporizador para actualizar los datos del jugador cada 5 segundos
        Timer timer = new Timer(800, e -> {
            try {
                jugadorView = Juego.getInstancia().getJugador().toViewJugador();
                SwingUtilities.invokeLater(() -> actualizarDatos(jugadorView));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        timer.start();
    }

    private void actualizarDatos(JugadorView jugadorView) {
        sistemaLabel.setText("Dentro del sistema: " + jugadorView.getSistemaActual().mostrarNombre());
        posicionLabel.setText("Posicionado en el: " + jugadorView.getPosicionEnElEspacio());
        nombreLabel.setText("Nombre: " + jugadorView.getNombre());
        uadeCoinsLabel.setText("Cantidad de uade coins: " + jugadorView.getUadeCoins());
        naveLabel.setText("Mi nave es: " + jugadorView.getNave().soyNaveTipo());
        vidaLabel.setText("Vida: " + jugadorView.getNave().getVida());
        escudoActualLabel.setText("Escudo Actual: " + jugadorView.getNave().getEscudo().cantidadEscudoActual());
        escudoMaxLabel.setText("Escudo Máximo: " + jugadorView.getNave().getEscudo().cantidadEscudoMax());
        combustibleLabel.setText("Combustible: " + jugadorView.getNave().getTanque().getCombustible());
        actualizarArmas();
        poderLabel.setText("Poder: " + jugadorView.getNave().poderAtaque());

        revalidate();
        repaint();
    }

    public void mostrarDatosDelJugador() {
        setVisible(true);
    }
}
