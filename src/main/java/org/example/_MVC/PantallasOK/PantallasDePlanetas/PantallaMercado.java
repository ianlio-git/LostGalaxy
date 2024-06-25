package org.example._MVC.PantallasOK.PantallasDePlanetas;

import org.example.Enums.TipoDeArma;
import org.example._MVC.Controller.PlanetaNeutralController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaMercado extends JFrame {

    private static PantallaMercado instance = null;

    private JComboBox<TipoDeArma> armaComboBox;
    private JPanel panelPrecio;
    private JLabel etiquetaPrecio;
    private JTextField campoCombustible;
    private JTextField campoEscudoMaximo;
    private JTextField campoRecargarEscudo;

    // Constructor privado para evitar la instanciación directa
    private PantallaMercado() {
        setTitle("Mercado");
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 3));

        armaComboBox = new JComboBox<>(TipoDeArma.values());
        armaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipoDeArma armaSeleccionada = (TipoDeArma) armaComboBox.getSelectedItem();
                if (armaSeleccionada != null) {
                    etiquetaPrecio.setText("PRECIO: $" + armaSeleccionada.getPrecio());
                }
            }
        });

        etiquetaPrecio = new JLabel();
        etiquetaPrecio.setHorizontalAlignment(SwingConstants.CENTER);

        panelPrecio = new JPanel(new BorderLayout());
        panelPrecio.add(etiquetaPrecio, BorderLayout.CENTER);

        JButton botonComprarArma = new JButton("Comprar Arma");
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
        JButton botonComprarCombustible = new JButton("Comprar Combustible");
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
        JButton botonComprarEscudoMaximo = new JButton("Comprar Escudo Máximo");
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
        JButton botonRecargarEscudo = new JButton("Recargar Escudo");
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

        add(new JLabel("Arma:"));
        add(armaComboBox);
        add(panelPrecio); // Agregar el panel que contiene el precio centrado
        add(botonComprarArma);
        add(botonVenderArma);
        add(new JLabel());
        add(new JLabel("Combustible:"));
        add(campoCombustible);
        add(botonComprarCombustible);
        add(new JLabel("Escudo Máximo:"));
        add(campoEscudoMaximo);
        add(botonComprarEscudoMaximo);
        add(new JLabel("Recargar Escudo:"));
        add(campoRecargarEscudo);
        add(botonRecargarEscudo);
        etiquetaPrecio.setText("PRECIO: $150");
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
