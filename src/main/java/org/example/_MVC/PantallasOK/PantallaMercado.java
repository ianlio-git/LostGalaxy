package org.example._MVC.PantallasOK;

import org.example.Enums.TipoDeArma;
import org.example._MVC.Controller.MainController;
import org.example._MVC.Controller.PlanetaNeutralController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaMercado extends JFrame {

    private JComboBox<TipoDeArma> armaComboBox;
    private JTextField campoCombustible;
    private JTextField campoEscudoMaximo;
    private JTextField campoRecargarEscudo;

    public PantallaMercado() {
        setTitle("Mercado");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 3));

        // Armas
        armaComboBox = new JComboBox<>(TipoDeArma.values());
        JButton botonComprarArma = new JButton("Comprar Arma");
        botonComprarArma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipoDeArma armaSeleccionada = (TipoDeArma) armaComboBox.getSelectedItem();
                if (armaSeleccionada != null) {
                    PlanetaNeutralController.comprarArma(armaSeleccionada);
                    mostrarMensaje("Compra de arma exitosa.");
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
                    PlanetaNeutralController.venderArma(armaSeleccionada);
                    mostrarMensaje("Venta de arma exitosa.");
                } else {
                    mostrarMensaje("Seleccione un arma.");
                }
            }
        });

        // Combustible
        campoCombustible = new JTextField();
        JButton botonComprarCombustible = new JButton("Comprar Combustible");
        botonComprarCombustible.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double cantidad = Double.parseDouble(campoCombustible.getText());
                    PlanetaNeutralController.comprarCombustible(cantidad);
                    mostrarMensaje("Compra de combustible exitosa.");
                } catch (NumberFormatException ex) {
                    mostrarMensaje("Ingrese una cantidad válida de combustible.");
                }
            }
        });

        // Escudo Máximo
        campoEscudoMaximo = new JTextField();
        JButton botonComprarEscudoMaximo = new JButton("Comprar Escudo Máximo");
        botonComprarEscudoMaximo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double cantidad = Double.parseDouble(campoEscudoMaximo.getText());
                    PlanetaNeutralController.comprarEscudoMaximo(cantidad);
                    mostrarMensaje("Compra de escudo máximo exitosa.");
                } catch (NumberFormatException ex) {
                    mostrarMensaje("Ingrese una cantidad válida de escudo máximo.");
                }
            }
        });

        // Recargar Escudo
        campoRecargarEscudo = new JTextField();
        JButton botonRecargarEscudo = new JButton("Recargar Escudo");
        botonRecargarEscudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double cantidad = Double.parseDouble(campoRecargarEscudo.getText());
                    PlanetaNeutralController.recargarEscudo(cantidad);
                    mostrarMensaje("Recarga de escudo exitosa.");
                } catch (NumberFormatException ex) {
                    mostrarMensaje("Ingrese una cantidad válida para recargar el escudo.");
                }
            }
        });

        // Añadir componentes al frame
        add(new JLabel("Arma:"));
        add(armaComboBox);
        add(new JLabel());
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
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
}
