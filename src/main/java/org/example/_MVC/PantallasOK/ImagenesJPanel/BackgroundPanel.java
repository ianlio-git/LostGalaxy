package org.example._MVC.PantallasOK.ImagenesJPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String resourcePath) {
        // Cargar la imagen de fondo desde recursos
        try (InputStream is = getClass().getResourceAsStream(resourcePath)) {
            if (is != null) {
                backgroundImage = ImageIO.read(is);
            } else {
                System.err.println("Error: No se pudo encontrar el recurso " + resourcePath);
            }
        } catch (IOException e) {
            System.err.println("Error: No se pudo cargar la imagen de fondo.");
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Dibujar la imagen de fondo
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
