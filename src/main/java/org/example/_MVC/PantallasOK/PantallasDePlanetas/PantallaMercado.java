package org.example._MVC.PantallasOK.PantallasDePlanetas;

import org.example._MVC.Model.Enums.TipoDeArma;
import org.example._MVC.Controller.PlanetaNeutralController;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PantallaMercado extends JFrame {

    private static PantallaMercado instance = null;

    private JComboBox<TipoDeArma> armaComboBox;
    private JLabel etiquetaPrecio;
    private JTextField campoCombustible;
    private JTextField campoEscudoMaximo;
    private JTextField campoRecargarEscudo;

    // Constructor privado para evitar la instanciación directa
    private PantallaMercado() {
        setTitle("Mercado");
        setSize(550, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana
        setLocationRelativeTo(null);

        // Panel principal con disposición GridLayout
        JPanel panelPrincipal = new JPanel(new GridLayout(6, 2, 10, 10)); // Filas, Columnas, Espacio Horizontal, Espacio Vertical
        panelPrincipal.setBackground(Color.BLACK);
        setContentPane(panelPrincipal);

        armaComboBox = new JComboBox<>(TipoDeArma.values());
        armaComboBox.setBackground(Color.BLACK);
        armaComboBox.setForeground(Color.WHITE);
        armaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipoDeArma armaSeleccionada = (TipoDeArma) armaComboBox.getSelectedItem();
                if (armaSeleccionada != null) {
                    etiquetaPrecio.setText("PRECIO: $" + armaSeleccionada.getPrecio());
                }
            }
        });

        etiquetaPrecio = new JLabel("PRECIO: $150");
        etiquetaPrecio.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaPrecio.setForeground(Color.WHITE);

        JButton botonComprarArma = new JButton("Comprar Arma");
        configurarBoton(botonComprarArma);
        botonComprarArma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipoDeArma armaSeleccionada = (TipoDeArma) armaComboBox.getSelectedItem();
                if (armaSeleccionada != null) {
                    if (PlanetaNeutralController.comprarArma(armaSeleccionada)) {
                        mostrarMensaje("Compra de arma exitosa.");
                    }
                } else {
                    mostrarMensaje("Seleccione un arma.");
                }
            }
        });

        JButton botonVenderArma = new JButton("Vender Arma");
        configurarBoton(botonVenderArma);
        botonVenderArma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipoDeArma armaSeleccionada = (TipoDeArma) armaComboBox.getSelectedItem();
                if (armaSeleccionada != null) {
                    if (PlanetaNeutralController.venderArma(armaSeleccionada)) {
                        mostrarMensaje("Venta de arma exitosa.");
                    }
                } else {
                    mostrarMensaje("Seleccione un arma.");
                }
            }
        });

        campoCombustible = new JTextField();
        configurarCampoTexto(campoCombustible);
        JButton botonComprarCombustible = new JButton("Comprar Combustible");
        configurarBoton(botonComprarCombustible);
        botonComprarCombustible.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double cantidad = Double.parseDouble(campoCombustible.getText());
                    if (PlanetaNeutralController.comprarCombustible(cantidad)) {
                        mostrarMensaje("Compra de combustible exitosa.");
                    }

                } catch (NumberFormatException ex) {
                    mostrarMensaje("Ingrese una cantidad válida de combustible.");
                }
            }
        });

        campoEscudoMaximo = new JTextField();
        configurarCampoTexto(campoEscudoMaximo);
        JButton botonComprarEscudoMaximo = new JButton("Comprar Escudo Máximo");
        configurarBoton(botonComprarEscudoMaximo);
        botonComprarEscudoMaximo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double cantidad = Double.parseDouble(campoEscudoMaximo.getText());
                    if (PlanetaNeutralController.comprarEscudoMaximo(cantidad)) {
                        mostrarMensaje("Compra de escudo máximo exitosa.");
                    }

                } catch (NumberFormatException ex) {
                    mostrarMensaje("Ingrese una cantidad válida de escudo máximo.");
                }
            }
        });

        campoRecargarEscudo = new JTextField();
        configurarCampoTexto(campoRecargarEscudo);
        JButton botonRecargarEscudo = new JButton("Recargar Escudo");
        configurarBoton(botonRecargarEscudo);
        botonRecargarEscudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double cantidad = Double.parseDouble(campoRecargarEscudo.getText());
                    if (PlanetaNeutralController.recargarEscudo(cantidad)) {
                        mostrarMensaje("Recarga de escudo exitosa.");
                    }

                } catch (NumberFormatException ex) {
                    mostrarMensaje("Ingrese una cantidad válida para recargar el escudo.");
                }
            }
        });

        // Botón para cerrar la pantalla
        JButton botonCerrar = new JButton("Cerrar");
        configurarBoton(botonCerrar);
        botonCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });

        // Añadir componentes al panel principal con títulos punteados
        panelPrincipal.add(crearPanelConTitulo(armaComboBox, "Arma"));
        panelPrincipal.add(crearPanelConTitulo(botonVenderArma, ""));
        panelPrincipal.add(crearPanelConTitulo(etiquetaPrecio, ""));
        panelPrincipal.add(crearPanelConTitulo(botonComprarArma, ""));
        panelPrincipal.add(crearPanelConTitulo(campoCombustible, "Combustible"));
        panelPrincipal.add(crearPanelConTitulo(botonComprarCombustible, ""));
        panelPrincipal.add(crearPanelConTitulo(campoEscudoMaximo, "Escudo Máximo"));
        panelPrincipal.add(crearPanelConTitulo(botonComprarEscudoMaximo, ""));
        panelPrincipal.add(crearPanelConTitulo(campoRecargarEscudo, "Recargar Escudo"));
        panelPrincipal.add(crearPanelConTitulo(botonRecargarEscudo, ""));
        panelPrincipal.add(new JLabel()); // Espacio vacío
        panelPrincipal.add(crearPanelConTitulo(botonCerrar, ""));

    }

    // Método para crear un panel con borde punteado y título
    private JPanel crearPanelConTitulo(Component componente, String titulo) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(componente, BorderLayout.CENTER);
        Border borde = BorderFactory.createTitledBorder(
                BorderFactory.createDashedBorder(Color.WHITE),
                titulo,
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                null,
                Color.WHITE
        );
        panel.setBorder(borde);
        panel.setBackground(Color.BLACK); // Fondo negro
        return panel;
    }

    // Método para configurar botones
    private void configurarBoton(JButton boton) {
        boton.setBackground(Color.BLACK);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false); // Remueve el borde de enfoque
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        // Añadir MouseListener para cambiar el color al pasar el mouse por encima
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(Color.DARK_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(Color.BLACK);
            }
        });
    }

    // Método para configurar campos de texto
    private void configurarCampoTexto(JTextField campo) {
        campo.setBackground(Color.BLACK);
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE); // Cursor de texto verde
    }

    // Método estático para obtener la instancia única
    public static PantallaMercado getInstance() {
        if (instance == null) {
            instance = new PantallaMercado();
        }
        return instance;
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

}