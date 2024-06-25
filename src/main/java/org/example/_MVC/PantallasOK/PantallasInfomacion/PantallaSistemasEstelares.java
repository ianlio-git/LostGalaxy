package org.example._MVC.PantallasOK.PantallasInfomacion;

import org.example._MVC.PantallasOK.PantallasPrincipales.PantallaMain;
import org.example._MVC.Views.SistemasView;
import org.example.MapaEstelar.Sistemas.Planetas.Planeta;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PantallaSistemasEstelares extends JFrame {
    private boolean abiertoSistemas = true;
    private static PantallaSistemasEstelares instancia;
    private List<SistemasView> sistemasViews;
    private JPanel mainPanel;

    public void destruirPantalla(){
        instancia = null;
    }

    private PantallaSistemasEstelares(List<SistemasView> sistemasViews) {
        this.sistemasViews = sistemasViews;
        initUI();
        iniciarActualizarSistemas();
    }

    private void initUI() {
        setTitle("Información de los Sistemas Estelares");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane);

        actualizarSistemas();
    }

    private void actualizarSistemas() {
        mainPanel.removeAll();

        for (SistemasView sistemasView : sistemasViews) {
            JPanel sistemaPanel = new JPanel();
            sistemaPanel.setLayout(new BoxLayout(sistemaPanel, BoxLayout.Y_AXIS));
            sistemaPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createTitledBorder("Sistema Estelar: " + sistemasView.getNombreSistemaEstelar()),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            JLabel planetasLabel = new JLabel("Lista de Planetas:");
            sistemaPanel.add(planetasLabel);

            for (Planeta planeta : sistemasView.getPlanetas()) {
                JLabel planetaLabel = new JLabel(" - Código del Planeta: " + planeta.getCodigoDePlaneta());
                sistemaPanel.add(planetaLabel);
            }

            JLabel cinturonLabel = new JLabel("Tiene Cinturón de Asteroides: " + (sistemasView.isTieneCinturonDeAsteroides() ? "Sí" : "No"));
            sistemaPanel.add(cinturonLabel);

            mainPanel.add(sistemaPanel);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void iniciarActualizarSistemas() {
        Timer timer = new Timer(800, e -> actualizarSistemas()); // Actualizar cada 10 segundos
        timer.start();
    }

    public static PantallaSistemasEstelares getInstancia(List<SistemasView> sistemasViews) {
        if (instancia == null) {
            instancia = new PantallaSistemasEstelares(sistemasViews);
        }
        return instancia;
    }


    public void mostrarSistemas() {
        PantallaMain pantallaMain = PantallaMain.getInstance();
        int x = pantallaMain.getX() + pantallaMain.getWidth(); // Colocar a la derecha sumando el ancho de la ventana principal
        int y = pantallaMain.getY();
        setLocation(x, y);
        setVisible(alternarPantallaSistemas());
    }
    private boolean alternarPantallaSistemas() {
        abiertoSistemas = !abiertoSistemas; // Alternar el estado de abiertoSistemas
        return !abiertoSistemas; // Devolver el estado anterior antes de la alternancia
    }

}
