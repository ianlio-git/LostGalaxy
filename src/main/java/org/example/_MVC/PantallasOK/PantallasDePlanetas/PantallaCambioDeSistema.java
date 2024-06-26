package org.example._MVC.PantallasOK.PantallasDePlanetas;

import org.example.MapaEstelar.Sistemas.Planetas.Planeta;
import org.example._MVC.Views.SistemasView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.example.MapaEstelar.Sistemas.SistemaEstelar;

import static org.example._MVC.Controller.CambiarDeSistemaController.cambiarSistema;
import static org.example._MVC.Controller.CambiarDeSistemaController.obtenerSistema;

public class PantallaCambioDeSistema extends JFrame {
    private JTextField codigoSistemaField;
    private JTextArea resultadoArea;

    // Instancia única como campo estático privado
    private static PantallaCambioDeSistema instancia = null;

    // Constructor privado para evitar instanciación externa
    private PantallaCambioDeSistema() {
        setTitle("Pantalla de Cambio de Sistema");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear componentes
        JLabel codigoSistemaLabel = new JLabel("Código del Sistema:");
        codigoSistemaField = new JTextField(20);
        JButton cambiarButton = new JButton("Cambiar de Sistema");
        JButton mostrarInfoButton = new JButton("Mostrar Información");
        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);

        // Configurar el layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(codigoSistemaLabel, gbc);

        gbc.gridx = 1;
        panel.add(codigoSistemaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(cambiarButton, gbc);

        gbc.gridy = 2;
        panel.add(mostrarInfoButton, gbc);

        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(new JScrollPane(resultadoArea), gbc);

        add(panel);

        // Añadir ActionListener al botón "Cambiar de Sistema"
        cambiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoDeSistema = codigoSistemaField.getText();
                cambiarSistema(codigoDeSistema.toUpperCase());
            }
        });

        // Añadir ActionListener al botón "Mostrar Información"
        mostrarInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoDeSistema = codigoSistemaField.getText();
                mostrarInformacionSistema(codigoDeSistema.toUpperCase());
            }
        });
    }

    // Método estático para obtener la instancia única
    public static PantallaCambioDeSistema getInstance() {
        if (instancia == null) {
            instancia = new PantallaCambioDeSistema();
        }
        return instancia;
    }

    // Método para mostrar la información del sistema en resultadoArea
    private void mostrarInformacionSistema(String codigoSistema) {
        SistemaEstelar sistema = obtenerSistema(codigoSistema);
        if (sistema != null) {
            StringBuilder info = new StringBuilder();
            info.append("Sistema Estelar: ").append(sistema.mostrarNombre()).append("\n");
            info.append("Lista de Planetas:\n");
            for (Planeta planeta : sistema.getPlanetas()) {
                info.append(" - Código del Planeta: ").append(planeta.getCodigoDePlaneta()).append("\n");
            }
            info.append("Tiene Cinturón de Asteroides: ").append(sistema.tieneCinturonAsteroides() ? "Sí" : "No").append("\n");
            if (sistema.tieneCinturonAsteroides()) {
                info.append("Cantidad de Asteroides: ").append(sistema.mostrarCinturonAsteroides().getCantidadDeAsteroides()).append("\n");
                info.append("Daño Posible: ").append(sistema.mostrarCinturonAsteroides().calcularPoder()).append("\n");
            }

            resultadoArea.setText(info.toString());
        } else {
            resultadoArea.setText("Sistema no encontrado.");
        }
    }
}


