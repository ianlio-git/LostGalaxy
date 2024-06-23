package org.example._MVC.PantallasOK;

import org.example._MVC.Controller.JuegoController;
import org.example._MVC.Controller.PlanetaAliadoController;
import org.example._MVC.Controller.PlanetaHostilController;

import javax.swing.*;
import java.awt.*;

public class PantallaMain extends JFrame {

    public PantallaMain() {
        setTitle("Lost Galaxy - Main Menu");
        setSize(600, 400); // Aumentar tamaño para acomodar mejor los botones
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear panel principal con fondo gris claro
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(240, 240, 240));
        panelPrincipal.setLayout(new GridBagLayout()); // Usar GridBagLayout para mejor control de la disposición

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Márgenes entre componentes

        // Crear botones con estilo
        JButton btnDetalleJugador = createButton("Detalle del Jugador");
        btnDetalleJugador.addActionListener(e -> JuegoController.mostrarDetalleDelJugador());

        JButton btnMostrarTurno = createButton("Mostrar Turno");
        btnMostrarTurno.addActionListener(e -> JuegoController.mostrarTurno());

        JButton btnMostrarSistemas = createButton("Mostrar Sistemas Estelares");
        btnMostrarSistemas.addActionListener(e -> JuegoController.mostrarSistemas());

        JButton btnMercado = createButton("Mercado");
        btnMercado.addActionListener(e -> JuegoController.mercado());

        JButton btnRepararNave = createButton("Reparar Nave (Aliado)");
        btnRepararNave.addActionListener(e -> PlanetaAliadoController.repararNave());

        JButton btnLocalizarTesoro = createButton("Localizar Tesoro (Aliado)");
        btnLocalizarTesoro.addActionListener(e -> PlanetaAliadoController.localizarTesoro());

        JButton btnAtacarPlanetaHostil = createButton("Atacar Planeta Hostil");
        btnAtacarPlanetaHostil.addActionListener(e -> PlanetaHostilController.atacarPlanetaHostil("codigoPlaneta"));

        // Añadir botones al panel principal
        panelPrincipal.add(btnDetalleJugador, gbc);
        panelPrincipal.add(btnMostrarTurno, gbc);
        panelPrincipal.add(btnMostrarSistemas, gbc);
        panelPrincipal.add(btnMercado, gbc);
        panelPrincipal.add(btnRepararNave, gbc);
        panelPrincipal.add(btnLocalizarTesoro, gbc);
        panelPrincipal.add(btnAtacarPlanetaHostil, gbc);

        // Añadir panel principal al JFrame
        add(panelPrincipal);

        setVisible(true);
    }

    // Método para crear botones con estilos
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(180, 180, 180));
        button.setFocusPainted(false); // Quitar el borde de enfoque
        button.setMargin(new Insets(10, 20, 10, 20)); // Ajustar márgenes
        return button;
    }
}
