package org.example._MVC.PantallasOK.PantallasInfomacion;

import org.example.GameMaster.Juego;
import org.example.Nave.PartesDeLaNave.Arma;
import org.example._MVC.PantallasOK.PantallasPrincipales.PantallaMain;
import org.example._MVC.Views.JugadorView;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class PantallaJugador extends JFrame {
    private boolean abiertoJugador = true;
    private static PantallaJugador instanciaUnica = null; // Variable estática para mantener la única instancia

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

    // Método estático para obtener la instancia única de PantallaJugador
    public static PantallaJugador obtenerInstancia(JugadorView jugadorView) {
        if (instanciaUnica == null) {
            instanciaUnica = new PantallaJugador(jugadorView);
        }
        return instanciaUnica;
    }

    private PantallaJugador(JugadorView jugadorView) {

        this.jugadorView = jugadorView;
        initUI();
        iniciarActualizarDatos(); // Iniciar el temporizador para actualizar los datos del jugador
    }

    private void initUI() {

        setTitle("Datos del Jugador");
        setSize(400, 500);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.BLACK); // Fondo negro

        sistemaLabel = crearLabelConBorder("-" + jugadorView.getSistemaActual().mostrarNombre(), "Dentro del sistema: ");
        mainPanel.add(sistemaLabel);

        posicionLabel = crearLabelConBorder("-" + jugadorView.getPosicionEnElEspacio(), "Posicionado en el Planeta:");
        mainPanel.add(posicionLabel);

        nombreLabel = crearLabelConBorder("-" + jugadorView.getNombre(), "Nombre del Jugador:");
        mainPanel.add(nombreLabel);

        uadeCoinsLabel = crearLabelConBorder("-" + jugadorView.getUadeCoins(), "Cantidad de uade coins:");
        mainPanel.add(uadeCoinsLabel);

        naveLabel = crearLabelConBorder("-" + jugadorView.getNave().soyNaveTipo(), "Mi nave es:");
        mainPanel.add(naveLabel);

        vidaLabel = crearLabelConBorder("-" + jugadorView.getNave().getVida(), "Vida:");
        mainPanel.add(vidaLabel);

        escudoActualLabel = crearLabelConBorder("-" + jugadorView.getNave().getEscudo().cantidadEscudoActual(), "Escudo Actual:");
        mainPanel.add(escudoActualLabel);

        escudoMaxLabel = crearLabelConBorder("-" + jugadorView.getNave().getEscudo().cantidadEscudoMax(), "Escudo Máximo:");
        mainPanel.add(escudoMaxLabel);

        combustibleLabel = crearLabelConBorder("-" + jugadorView.getNave().getTanque().getCombustible(), "Combustible:");
        mainPanel.add(combustibleLabel);

        armasPanel = crearArmasPanel();
        mainPanel.add(armasPanel);

        poderLabel = crearLabelConBorder("-" + jugadorView.getNave().poderAtaque(), "Poder de Ataque:");
        mainPanel.add(poderLabel);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane);
    }

    private JLabel crearLabelConBorder(String texto, String titulo) {
        JLabel label = new JLabel(texto);
        Border border = BorderFactory.createCompoundBorder(
                BorderFactory.createDashedBorder(Color.GREEN),
                BorderFactory.createEmptyBorder(5, 10, 5, 10));
        label.setBorder(BorderFactory.createTitledBorder(border, titulo, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.GREEN));
        label.setForeground(Color.GREEN); // Texto verde
        return label;
    }

    private JPanel crearArmasPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        Border border = BorderFactory.createCompoundBorder(
                BorderFactory.createDashedBorder(Color.GREEN),
                BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder(border, "Armas", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.GREEN));
        panel.setBackground(Color.BLACK); // Fondo negro
        actualizarArmas(panel);
        return panel;
    }

    private void actualizarArmas(JPanel panel) {
        panel.removeAll();

        List<Arma> armas = jugadorView.getNave().getArmas();
        if (!armas.isEmpty()) {
            for (Arma arma : armas) {
                JLabel armaLabel = new JLabel("//: " + arma.soyTipoDeArma()+"                            ");
                armaLabel.setForeground(Color.GREEN); // Texto verde
                panel.add(armaLabel);
            }
        } else {
            JLabel sinArmasLabel = new JLabel("Aún no tengo un arma, ¡pero ya tendré una!");
            sinArmasLabel.setForeground(Color.GREEN); // Texto verde
            panel.add(sinArmasLabel);
        }

        panel.revalidate();
        panel.repaint();
    }

    private void iniciarActualizarDatos() {
        // Iniciar el temporizador para actualizar los datos del jugador cada 5 segundos
        Timer timer = new Timer(10, e -> {
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
        sistemaLabel.setText("//: " + jugadorView.getSistemaActual().mostrarNombre()+"                                             ");
        posicionLabel.setText("//: " + jugadorView.getPosicionEnElEspacio()+"                                                      ");
        nombreLabel.setText("//: " + jugadorView.getNombre()+"                                                                     ");
        uadeCoinsLabel.setText("//: " + jugadorView.getUadeCoins()+"                                                               ");
        naveLabel.setText("//: " + jugadorView.getNave().soyNaveTipo()+"                                                           ");
        vidaLabel.setText("//: " + jugadorView.getNave().getVida()+"                                                               ");
        escudoActualLabel.setText("//: " + jugadorView.getNave().getEscudo().cantidadEscudoActual()+"                              ");
        escudoMaxLabel.setText("//: " + jugadorView.getNave().getEscudo().cantidadEscudoMax()+"                                    ");
        combustibleLabel.setText("//: " + jugadorView.getNave().getTanque().getCombustible()+"                                     ");
        actualizarArmas(armasPanel);
        poderLabel.setText("//: " + jugadorView.getNave().poderAtaque()+"                                                          ");

        revalidate();
        repaint();
    }

    public void mostrarDatosDelJugador() {
        PantallaMain pantallaMain = PantallaMain.getInstance();
        int x = pantallaMain.getX() - getWidth(); // Colocar a la izquierda restando el ancho de la ventana del jugador
        int y = pantallaMain.getY();
        setLocation(x, y);
        setVisible(alternarPantallaJugador());
    }

    private boolean alternarPantallaJugador() {
        abiertoJugador = !abiertoJugador; // Alternar el estado de abiertoSistemas
        return !abiertoJugador; // Devolver el estado anterior antes de la alternancia
    }


}