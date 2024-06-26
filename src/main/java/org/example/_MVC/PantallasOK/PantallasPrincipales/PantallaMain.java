package org.example._MVC.PantallasOK.PantallasPrincipales;

import org.example._MVC.Controller.JuegoController;
import org.example._MVC.Controller.MainController;
import org.example._MVC.PantallasOK.ImagenesJPanel.BackgroundPanel;
import org.example._MVC.PantallasOK.PantallasDePlanetas.PantallaMercado;
import org.example._MVC.PantallasOK.PantallasInfomacion.PantallaDeCostes;
import org.example._MVC.Views.JugadorView;

import javax.swing.*;
import java.awt.*;

public class PantallaMain extends JFrame {

    private static PantallaMain instance = null;

    private boolean mercadoAbierto = false; // Flag para controlar si la pantalla de mercado está abierta

    private PantallaMain() {
        setTitle("Lost Galaxy - Main Menu");
        setSize(550, 500); // Tamaño inicial más grande
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear panel principal con fondo de imagen
        BackgroundPanel panelPrincipal = new BackgroundPanel("/imagenes/espacio.jpg");
        panelPrincipal.setLayout(new GridBagLayout()); // Usar GridBagLayout para mejor control de la disposición

        // Panel para los primeros tres botones alineados a la izquierda
        JPanel panelBotonesIzquierda = new JPanel();
        panelBotonesIzquierda.setOpaque(false); // Hacer el panel transparente
        panelBotonesIzquierda.setLayout(new GridLayout(4, 1, 0, 10)); // Usar GridLayout para alinear verticalmente los botones

        // Crear botones con estilo para la sección izquierda
        JButton btnDetalleJugador = createButton("Detalle del Jugador", "Muestra los detalles del jugador.");
        btnDetalleJugador.addActionListener(e -> JuegoController.mostrarDetalleDelJugador());

        JButton btnMostrarTurno = createButton("Mostrar Turno", "Muestra el turno actual.");
        btnMostrarTurno.addActionListener(e -> JuegoController.mostrarTurno());

        JButton btnMostrarSistemas = createButton("Mostrar Sistemas Estelares", "Muestra los sistemas estelares.");
        btnMostrarSistemas.addActionListener(e -> JuegoController.mostrarSistemas());

        // Añadir los primeros tres botones al panel izquierdo
        panelBotonesIzquierda.add(btnDetalleJugador);
        panelBotonesIzquierda.add(btnMostrarTurno);
        panelBotonesIzquierda.add(btnMostrarSistemas);

        // Nuevo botón para mostrar los costes
        JButton btnMostrarCostes = createButton("Mostrar Costes", "Muestra los costes actuales.");
        btnMostrarCostes.addActionListener(e -> PantallaDeCostes.mostrarPantalla());
        panelBotonesIzquierda.add(btnMostrarCostes);

        // GridBagConstraints para panelBotonesIzquierda
        GridBagConstraints gbcPanelIzquierda = new GridBagConstraints();
        gbcPanelIzquierda.anchor = GridBagConstraints.WEST; // Alinear a la izquierda
        gbcPanelIzquierda.insets = new Insets(10, 20, 10, 10); // Márgenes
        gbcPanelIzquierda.weighty = 1.0; // Distribuir espacio verticalmente

        // Añadir panelBotonesIzquierda al panel principal en la primera columna
        gbcPanelIzquierda.gridx = 0;
        gbcPanelIzquierda.gridy = 0;
        panelPrincipal.add(panelBotonesIzquierda, gbcPanelIzquierda);

        // Panel para los botones restantes
        JPanel panelBotonesDerecha = new JPanel();
        panelBotonesDerecha.setOpaque(false); // Hacer el panel transparente
        panelBotonesDerecha.setLayout(new GridBagLayout()); // Usar GridBagLayout para controlar la disposición de los botones

        // GridBagConstraints para los botones restantes
        GridBagConstraints gbcRestoBotones = new GridBagConstraints();
        gbcRestoBotones.gridx = 0;
        gbcRestoBotones.gridy = 0;
        gbcRestoBotones.gridwidth = GridBagConstraints.REMAINDER;
        gbcRestoBotones.fill = GridBagConstraints.HORIZONTAL;
        gbcRestoBotones.insets = new Insets(10, 10, 10, 10); // Márgenes entre componentes
        gbcRestoBotones.weightx = 1.0; // Distribuir espacio horizontalmente
        gbcRestoBotones.weighty = 0.25; // Distribuir espacio verticalmente

        JButton btnMercado = createButton("Mercado", "Abre el mercado para comprar valiosos items.");
        btnMercado.addActionListener(e -> {
            if (!mercadoAbierto) { // Si el mercado no está abierto
                mercadoAbierto = true; // Establecer el flag a true

                PantallaMercado.getInstance().setVisible(true); // Mostrar la pantalla de mercado
                PantallaMercado.getInstance().addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        mercadoAbierto = false; // Cuando se cierre la ventana, establecer el flag a false
                    }
                });
            }
        });
        panelBotonesDerecha.add(btnMercado, gbcRestoBotones);

        JButton btnAtacarPlanetaHostil = createButton("Atacar Planeta Hostil", "Ingresa el planeta que quieres atacar.");
        btnAtacarPlanetaHostil.addActionListener(e -> JuegoController.combate());
        gbcRestoBotones.gridy++;
        panelBotonesDerecha.add(btnAtacarPlanetaHostil, gbcRestoBotones);

        // Nuevo botón Planeta Aliado
        JButton btnPlanetaAliado = createButton("Planeta Aliado", "Visita un planeta aliado para obtener ayuda.");
        btnPlanetaAliado.addActionListener(e -> JuegoController.planetaAliado());
        gbcRestoBotones.gridy++;
        panelBotonesDerecha.add(btnPlanetaAliado, gbcRestoBotones);

        // Nuevo botón Cambiar de Sistema Estelar
        JButton btnCambiarSistema = createButton("Cambiar Sistema Estelar", "Cambiar al siguiente sistema estelar disponible.");
        btnCambiarSistema.addActionListener(e -> JuegoController.cambiarDeSistema());
        gbcRestoBotones.gridy++;
        panelBotonesDerecha.add(btnCambiarSistema, gbcRestoBotones);

        // Botón Reset
        JButton btnReset = createButton("Reset", "Reinicia el juego.");
        btnReset.addActionListener(e -> {
            MainController.gameReset();
            JOptionPane.showMessageDialog(this, "Juego reiniciado con exito");
        });
        gbcRestoBotones.gridy++;
        panelBotonesDerecha.add(btnReset, gbcRestoBotones);

        // Botón Salir
        JButton btnSalir = createButton("Salir", "Salir de la aplicación.");
        btnSalir.addActionListener(e -> System.exit(1));
        gbcRestoBotones.gridy++;
        panelBotonesDerecha.add(btnSalir, gbcRestoBotones);

        // GridBagConstraints para el panel de botones derecho
        GridBagConstraints gbcPanelDerecha = new GridBagConstraints();
        gbcPanelDerecha.anchor = GridBagConstraints.CENTER; // Alinear al centro
        gbcPanelDerecha.gridx = 1;
        gbcPanelDerecha.gridy = 0;
        gbcPanelDerecha.weightx = 1.0; // Espacio adicional horizontal
        gbcPanelDerecha.weighty = 1.0; // Espacio adicional vertical

        // Añadir panelBotonesDerecha al panel principal en la segunda columna
        panelPrincipal.add(panelBotonesDerecha, gbcPanelDerecha);

        // Añadir panel principal al JFrame
        add(panelPrincipal);

        setVisible(true);
    }

    // Método para crear botones con estilos y tooltips
    private JButton createButton(String text, String tooltipText) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(0, 0, 0)); // Fondo negro
        button.setForeground(new Color(255, 255, 255)); // Texto blanco
        button.setFocusPainted(false); // Quitar el borde de enfoque
        button.setMargin(new Insets(10, 20, 10, 20)); // Ajustar márgenes
        button.setToolTipText(tooltipText); // Agregar tooltip
        return button;
    }

    public static PantallaMain getInstance() {
        if (instance == null) {
            instance = new PantallaMain();
        }
        return instance;
    }
}
