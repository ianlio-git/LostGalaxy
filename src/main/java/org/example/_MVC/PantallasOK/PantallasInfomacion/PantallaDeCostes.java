package org.example._MVC.PantallasOK.PantallasInfomacion;

import org.example._MVC.Model.GameMaster.Juego;
import org.example._MVC.Model.GameMaster.Jugador;
import org.example._MVC.PantallasOK.PantallasPrincipales.PantallaMain;
import org.example._MVC.Views.JugadorView;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class PantallaDeCostes extends JFrame {
    private static PantallaDeCostes instancia;
    private boolean abiertoSistemas = true;
    private JLabel lblTitulo;
    private JTextArea txtAreaCostes;
    private JButton btnSalir;
    private Timer timer;

    // Private constructor to prevent instantiation
    private PantallaDeCostes() {
        setTitle("Tabla de Costes");
        setSize(550, 250); // Ancho ajustado a 500
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear el título
        lblTitulo = new JLabel("Tabla de Costes");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setForeground(Color.GREEN); // Texto verde
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(Color.BLACK); // Fondo negro
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Margen

        // Panel para mostrar los costes
        txtAreaCostes = new JTextArea();
        txtAreaCostes.setFont(new Font("Arial", Font.PLAIN, 14));
        txtAreaCostes.setEditable(false);
        txtAreaCostes.setBackground(Color.BLACK); // Fondo negro
        txtAreaCostes.setForeground(Color.GREEN); // Texto verde

        // Botón Salir
        btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalir.setBackground(Color.BLACK); // Fondo negro
        btnSalir.setForeground(Color.GREEN); // Texto verde
        btnSalir.setFocusPainted(false); // Quitar el borde de enfoque
        btnSalir.addActionListener(e -> dispose()); // Cerrar la ventana al hacer clic en Salir

        // Panel para botón Salir
        JPanel panelBotonSalir = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotonSalir.setBackground(Color.BLACK); // Fondo negro
        panelBotonSalir.add(btnSalir);

        // Añadir componentes al JFrame
        add(lblTitulo, BorderLayout.NORTH);
        add(new JScrollPane(txtAreaCostes), BorderLayout.CENTER);
        add(panelBotonSalir, BorderLayout.SOUTH);

        // Crear el timer para actualizar cada 800 milisegundos
        timer = new Timer(800, e -> actualizarCostes());
        timer.start(); // Iniciar el timer

        // Calcular y mostrar los costes inicialmente
        calcularYMostrarCostes();
    }

    // Public method to get the singleton instance
    public static synchronized PantallaDeCostes getInstancia() {
        if (instancia == null) {
            instancia = new PantallaDeCostes();
        }
        return instancia;
    }

    private void calcularYMostrarCostes() {
        Jugador jugador = Juego.getInstancia().getJugador();
        JugadorView jugadorView = jugador.toViewJugador();
        double tasaDeAumento;
        if (jugadorView.getCantidadDeArmas() == 0) {
            tasaDeAumento = 1;
        } else {
            tasaDeAumento = Math.pow(1.5, jugadorView.getCantidadDeArmas());
        }

        DecimalFormat df = new DecimalFormat("#.##");

        StringBuilder sb = new StringBuilder();
        sb.append("Coste Planeta Neutral: ").append(df.format(tasaDeAumento * 10)).append(" uade coins\n");
        sb.append("Coste Planeta Aliado: ").append(df.format(tasaDeAumento * 15)).append(" uade coins\n");
        sb.append("Coste Planeta Hostil: ").append(df.format(tasaDeAumento * 20)).append(" uade coins\n");
        sb.append("Coste Sistema Estelar: ").append(df.format(tasaDeAumento * 30)).append(" uade coins\n");

        txtAreaCostes.setText(sb.toString());
    }

    private void actualizarCostes() {
        calcularYMostrarCostes(); // Actualizar los costes cada 800 ms
    }

    public static void mostrarPantalla() {
        SwingUtilities.invokeLater(() -> {
            PantallaMain pantallaMain = PantallaMain.getInstance();
            PantallaDeCostes pantalla = PantallaDeCostes.getInstancia();

            // Obtener las coordenadas de la pantalla principal
            int x = pantallaMain.getX();
            int y = pantallaMain.getY() + pantallaMain.getHeight(); // Colocar debajo de la pantalla principal

            pantalla.setLocation(x, y);
            pantalla.setVisible(pantalla.alternarPantallaSistemas());
        });
    }

    private boolean alternarPantallaSistemas() {
        abiertoSistemas = !abiertoSistemas; // Alternar el estado de abiertoSistemas
        return !abiertoSistemas; // Devolver el estado anterior antes de la alternancia
    }
}
