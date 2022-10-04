package Dibujo2.funcionamiento;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;

public class Imagen implements IDibujable {
    private int ancho;
    private int alto;

    private int[][] pixeles;

    private PropertyChangeSupport observado;
    private static Logger logger = LogManager.getRootLogger();

    public Imagen(int w, int h) { // 400 400
        ancho = w;
        alto = h;
        pixeles = new int[ancho][alto];
        observado = new PropertyChangeSupport(this);
    }


    @Override
    public void dibujar(Graphics g) {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                g.setColor(new Color(pixeles[i][j]));
                g.drawLine(i, j, i, j);
            }
        }
    }

    public void cargarImagen(File archivoImagen) {
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(archivoImagen);
        } catch (IOException e) {
            logger.error("No puedo cargar la imagen", e);
            return;
        }

        ancho = bi.getWidth();
        alto = bi.getHeight();

        pixeles = new int[ancho][alto];
        for (int j = 0; j < alto; j++) {
            for (int i = 0; i < ancho; i++) {
                int bgr = bi.getRGB(i, j);
                pixeles[i][j] = bgr;
            }
        }

        observado.firePropertyChange("IMAGEN", true, false);
    }

    public void addListener(PropertyChangeListener listener){
        observado.addPropertyChangeListener(listener);
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public void setColor(int x, int y, int r, int g, int b) {
        pixeles[x][y] = b | (g << 8) | (r << 16);
    }
}
