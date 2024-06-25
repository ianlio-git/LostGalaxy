package org.example._MVC.PantallasOK.PantallasDePlanetas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.example._MVC.Controller.PlanetaHostilController.atacarPlanetaHostil;

public class PantallaDeCombate extends JFrame {
    private JTextField codigoPlanetaField;
    private JTextArea resultadoArea;

    public PantallaDeCombate() {
        setTitle("Pantalla de Combate");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear componentes
        JLabel codigoPlanetaLabel = new JLabel("Código del Planeta:");
        codigoPlanetaField = new JTextField(20);
        JButton atacarButton = new JButton("Atacar Planeta Hostil");
        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);

        // Configurar el layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(codigoPlanetaLabel, gbc);

        gbc.gridx = 1;
        panel.add(codigoPlanetaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(atacarButton, gbc);

        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(new JScrollPane(resultadoArea), gbc);

        add(panel);

        // Añadir ActionListener al botón
        atacarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoDePlaneta = codigoPlanetaField.getText();
                atacarPlanetaHostil(codigoDePlaneta);
            }
        });
    }
}