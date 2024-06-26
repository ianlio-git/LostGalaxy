package org.example._MVC.PantallasOK.PantallasDePlanetas;

import org.example._MVC.Model.GameMaster.Juego;
import org.example._MVC.Controller.PlanetaAliadoController;
import org.example._MVC.Views.MapaEstelarView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaPlanetaAliado extends JFrame {

    private static PantallaPlanetaAliado instancia = null;

    private PantallaPlanetaAliado() {
        setTitle("Opciones de Nave");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel(new GridLayout(2, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelBotones.setBackground(Color.BLACK);

        JButton botonRepararNave = new JButton("Reparar Nave");
        configurarBoton(botonRepararNave);
        botonRepararNave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (PlanetaAliadoController.repararNave()) {
                    mostrarMensaje("¡Tu nave ha sido reparada con éxito!\n¡Buena suerte en tus próximas batallas!");
                }
            }
        });

        JButton botonObtenerInformacion = new JButton("Obtener Información");
        configurarBoton(botonObtenerInformacion);
        botonObtenerInformacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (PlanetaAliadoController.localizarTesoro()) {
                    MapaEstelarView mapaEstelarView = Juego.getInstancia().getMapaEstelar().mapaEstelarToView();
                    mostrarMensaje("El sistema con el tesoro es: " + mapaEstelarView.getSistemaConTesoro());
                }
            }
        });

        panelBotones.add(botonRepararNave);
        panelBotones.add(botonObtenerInformacion);

        JLabel costoTexto = new JLabel("Costo de obtener la información: 4000 UADEcoins");
        costoTexto.setHorizontalAlignment(SwingConstants.CENTER);
        costoTexto.setForeground(Color.WHITE);
        costoTexto.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel panelTexto = new JPanel(new BorderLayout());
        panelTexto.add(costoTexto, BorderLayout.NORTH);
        panelTexto.setBackground(Color.BLACK);
        panelTexto.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton botonCerrar = new JButton("Cerrar");
        configurarBoton(botonCerrar);
        botonCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.BLACK);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        panelPrincipal.add(panelTexto, BorderLayout.NORTH);
        panelPrincipal.add(botonCerrar, BorderLayout.SOUTH);

        add(panelPrincipal);

        setVisible(true);
    }

    public static PantallaPlanetaAliado getInstance() {
        if (instancia == null) {
            instancia = new PantallaPlanetaAliado();
        }
        return instancia;
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void configurarBoton(JButton boton) {
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBackground(Color.BLACK);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
    }
}
