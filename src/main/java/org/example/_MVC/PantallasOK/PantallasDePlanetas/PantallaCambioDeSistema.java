package org.example._MVC.PantallasOK.PantallasDePlanetas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.example._MVC.Controller.CambiarDeSistemaController.cambiarSistema;

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
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(new JScrollPane(resultadoArea), gbc);

        add(panel);

        // Añadir ActionListener al botón
        cambiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoDeSistema = codigoSistemaField.getText();
                cambiarSistema(codigoDeSistema.toUpperCase());
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
}
