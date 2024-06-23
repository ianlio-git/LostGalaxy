package org.example._MVC.PantallasOK.PantallasInfomacion;

import org.example._MVC.Views.SistemasView;
import org.example.MapaEstelar.Sistemas.Planetas.Planeta;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PantallaSistemasEstelares extends JFrame {
    private List<SistemasView> sistemasViews;

    public PantallaSistemasEstelares(List<SistemasView> sistemasViews) {
        this.sistemasViews = sistemasViews;
        initUI();
    }

    private void initUI() {
        setTitle("Información de los Sistemas Estelares");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane);
    }

    public void mostrarSistemas() {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }

    // Método estático para mostrar los sistemas estelares
    public static void mostrarSistemas(List<SistemasView> sistemasViews) {
        try {
            PantallaSistemasEstelares pantallaSistemasEstelares = new PantallaSistemasEstelares(sistemasViews);
            pantallaSistemasEstelares.mostrarSistemas();
        } catch (Exception e) {
            mostrarError(e);
        }
    }

    // Método de ejemplo para mostrar errores
    private static void mostrarError(Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
