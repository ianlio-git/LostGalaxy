package org.example._MVC.PantallasOK.PantallasInfomacion;

import org.example.GameMaster.Juego;
import org.example._MVC.PantallasOK.PantallasPrincipales.PantallaMain;
import org.example._MVC.Views.GameBeginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaTurno extends JFrame {
    private boolean abiertoTurno = true;
    private static PantallaTurno instancia;
    private JLabel lblTurno;

    // Constructor privado para evitar instanciación desde fuera de la clase
    private PantallaTurno() {
        setTitle("Turno Actual");
        setSize(550, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el JLabel para mostrar el turno
        lblTurno = new JLabel("Turno: ");
        lblTurno.setFont(new Font("Arial", Font.BOLD, 16));
        lblTurno.setHorizontalAlignment(SwingConstants.CENTER);
        lblTurno.setForeground(Color.GREEN); // Color de texto blanco
        getContentPane().setBackground(Color.BLACK); // Fondo negro

        // Añadir el JLabel al contenido del JFrame
        getContentPane().add(lblTurno, BorderLayout.CENTER);
    }

    // Método estático para obtener la instancia única
    public static PantallaTurno getInstancia() {
        if (instancia == null) {
            instancia = new PantallaTurno();
        }
        return instancia;
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

    public void mostrarTurno() {
        PantallaMain pantallaMain = PantallaMain.getInstance();
        int x = pantallaMain.getX(); // Misma posición X que PantallaMain
        int y = pantallaMain.getY() - PantallaTurno.getInstancia().getHeight(); // Ajustar en Y arriba de PantallaMain

        setLocation(x, y);
        setVisible(alternarTurno());
    }

    private boolean alternarTurno() {
        abiertoTurno = !abiertoTurno; // Alternar el estado de abiertoSistemas
        return !abiertoTurno; // Devolver el estado anterior antes de la alternancia
    }
}
