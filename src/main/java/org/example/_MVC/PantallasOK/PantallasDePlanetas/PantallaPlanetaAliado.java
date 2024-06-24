package org.example._MVC.PantallasOK.PantallasDePlanetas;

import org.example.GameMaster.Juego;
import org.example._MVC.Controller.PlanetaAliadoController;
import org.example._MVC.Views.MapaEstelarView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaPlanetaAliado extends JFrame {

    public PantallaPlanetaAliado() {
        setTitle("Opciones de Nave");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1, 10, 10)); // 2 filas, 1 columna, con espacio vertical de 10px

        // Botón Reparar Nave
        JButton botonRepararNave = new JButton("Reparar Nave");
        botonRepararNave.setFont(new Font("Arial", Font.BOLD, 14));
        botonRepararNave.setBackground(new Color(0, 0, 0)); // Fondo negro
        botonRepararNave.setForeground(new Color(255, 255, 255)); // Texto blanco
        botonRepararNave.setFocusPainted(false); // Quitar el borde de enfoque
        botonRepararNave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(PlanetaAliadoController.repararNave()){
                    mostrarMensaje("Bienvenido al taller del UADE team!\n" + "Tu nave ha sido reparada con exito! \n"+"Mucha suerte en tus proximas batallas!");
                }

            }
        });

        // Botón Obtener Información
        JButton botonObtenerInformacion = new JButton("Obtener Información");
        botonObtenerInformacion.setFont(new Font("Arial", Font.BOLD, 14));
        botonObtenerInformacion.setBackground(new Color(0, 0, 0)); // Fondo negro
        botonObtenerInformacion.setForeground(new Color(255, 255, 255)); // Texto blanco
        botonObtenerInformacion.setFocusPainted(false); // Quitar el borde de enfoque
        botonObtenerInformacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(PlanetaAliadoController.localizarTesoro()){
                    MapaEstelarView mapaEstelarView = Juego.getInstancia().getMapaEstelar().mapaEstelarToView();
                    mostrarMensaje("El sistema con el tesoro es el:"+mapaEstelarView.getSistemaConTesoro());
                }
            }
        });

        // Añadir botones al frame
        add(botonRepararNave);
        add(botonObtenerInformacion);

        setVisible(true);
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
}
