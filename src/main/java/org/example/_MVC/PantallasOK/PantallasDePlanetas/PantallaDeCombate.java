package org.example._MVC.PantallasOK.PantallasDePlanetas;

import org.example._MVC.Model.MapaEstelar.Sistemas.Planetas.Planeta;
import org.example._MVC.Controller.PlanetaHostilController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaDeCombate extends JFrame {
    private JTextField codigoPlanetaField;
    private JTextArea resultadoArea;

    private static PantallaDeCombate instancia = null;

    private PantallaDeCombate() {
        setTitle("Pantalla de Combate");
        setSize(550, 500); // Tamaño ajustado a 550x500
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear componentes
        JLabel codigoPlanetaLabel = new JLabel("Código del Planeta:");
        codigoPlanetaLabel.setForeground(Color.GREEN); // Texto verde

        codigoPlanetaField = new JTextField(20);

        JButton atacarButton = new JButton("Atacar Planeta Hostil");
        atacarButton.setBackground(Color.BLACK); // Fondo negro
        atacarButton.setForeground(Color.GREEN); // Texto verde
        atacarButton.setBorder(BorderFactory.createLineBorder(Color.GREEN)); // Borde verde

        JButton mostrarEnemigoButton = new JButton("Mostrar Enemigo");
        mostrarEnemigoButton.setBackground(Color.BLACK); // Fondo negro
        mostrarEnemigoButton.setForeground(Color.GREEN); // Texto verde
        mostrarEnemigoButton.setBorder(BorderFactory.createLineBorder(Color.GREEN)); // Borde verde

        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        resultadoArea.setBackground(Color.BLACK); // Fondo negro
        resultadoArea.setForeground(Color.GREEN); // Texto verde
        resultadoArea.setBorder(BorderFactory.createLineBorder(Color.GREEN)); // Borde verde

        JButton salirButton = new JButton("Salir");
        salirButton.setBackground(Color.BLACK); // Fondo negro
        salirButton.setForeground(Color.GREEN); // Texto verde
        salirButton.setBorder(BorderFactory.createLineBorder(Color.GREEN)); // Borde verde

        // Configurar el layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.BLACK); // Fondo negro
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
        panel.add(mostrarEnemigoButton, gbc);

        gbc.gridy = 3;
        gbc.weighty = 1.0; // Ajuste para que el área de resultado ocupe más espacio verticalmente
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(new JScrollPane(resultadoArea), gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.weighty = 0.0; // Restablecer weighty para el botón de salir
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(salirButton, gbc);

        add(panel);

        // Añadir ActionListener al botón Atacar
        atacarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoDePlaneta = codigoPlanetaField.getText();

                PlanetaHostilController planetaHostilController = new PlanetaHostilController();
                planetaHostilController.atacarPlanetaHostil(codigoDePlaneta.toUpperCase());
            }
        });

        // Añadir ActionListener al botón Mostrar Enemigo
        mostrarEnemigoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoDePlaneta = codigoPlanetaField.getText();

                PlanetaHostilController planetaHostilController = new PlanetaHostilController();
                Planeta planetaHostil = planetaHostilController.obtenerPlaneta(codigoDePlaneta.toUpperCase());

                if (planetaHostil != null) {
                    resultadoArea.setText("Nombre del enemigo: " + planetaHostil.getCodigoDePlaneta() + "\n" +
                            "Poder de ataque: " + planetaHostil.getNavePirata().poderAtaque() + "\n" +
                            "Vida: " + planetaHostil.getNavePirata().getVida());
                } else {
                    resultadoArea.setText("No se encontró un planeta hostil con el código proporcionado.");
                }
            }
        });

        // Añadir ActionListener al botón Salir
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana de combate
            }
        });
    }

    public static PantallaDeCombate getInstance() {
        if (instancia == null) {
            instancia = new PantallaDeCombate();
        }
        return instancia;
    }
}
