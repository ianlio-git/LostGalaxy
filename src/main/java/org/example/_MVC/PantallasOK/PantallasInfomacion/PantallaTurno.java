package org.example._MVC.PantallasOK.PantallasInfomacion;

import org.example.GameMaster.Juego;
import org.example._MVC.Views.GameBeginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaTurno extends JFrame {

    private JLabel lblTurno;

    public PantallaTurno() {
        setTitle("Turno Actual");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el JLabel para mostrar el turno
        lblTurno = new JLabel("Turno: ");
        lblTurno.setFont(new Font("Arial", Font.BOLD, 16));
        lblTurno.setHorizontalAlignment(SwingConstants.CENTER);

        // Añadir el JLabel al contenido del JFrame
        getContentPane().add(lblTurno, BorderLayout.CENTER);
    }

    // Método para iniciar el temporizador y actualizar el turno
    public void iniciarActualizarTurno() {
        // Iniciar el temporizador para actualizar el turno cada segundo
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTurno();
            }
        });
        timer.start();
    }

    // Método para actualizar el texto del JLabel con el turno actual
    private void actualizarTurno() {
        try {
            GameBeginView gameBeginView = Juego.getInstancia().toViewgameBegin(); // Obtener la vista del juego
            int turnoActual = gameBeginView.getTurno(); // Obtener el turno actual
            lblTurno.setText("Turno: " + turnoActual); // Actualizar el texto del JLabel
        } catch (Exception e) {
            lblTurno.setText("Error al obtener el turno"); // Manejar errores
            e.printStackTrace();
        }
    }
}
