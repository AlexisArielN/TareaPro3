package Figura;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Figura implements IDibujo {
    private int tamaño = 200;
    private int posicionX = 500;
    private int posicionY = 250;
    private int profundidad = 1;

    private PropertyChangeSupport objeto;
    private Logger log = LogManager.getLogger(Figura.class);

    public Figura(int profundidad) {
        objeto = new PropertyChangeSupport(this);
        this.profundidad = profundidad;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    @Override
    public void dibujar(Graphics g) {
        hacerCuadrado(posicionX, posicionY, tamaño, this.profundidad, g);
    }



    public void hacerCuadrado(int x, int y, int tamaño, int profundidad, Graphics g) {
        if (profundidad == 1) {
            g.drawRect(x, y, tamaño, tamaño);
        } else {
            int tamañoR = tamaño / 2;
            int x1 = x - (tamaño / 2);
            int x2 = ((tamaño / 2) / 2) + x;
            int x3 = x + tamaño;
            int x4 = ((tamaño / 2) / 2) + x;
            int y1 = y + (tamañoR / 2);
            int y2 = y - (tamaño / 2);
            int y3 = y + (tamañoR / 2);
            int y4 = y + tamaño;

            hacerCuadrado(x, y, tamaño, profundidad - 1, g);
            hacerCuadrado(x1, y1, tamañoR, profundidad - 1, g);
            hacerCuadrado(x2, y2, tamañoR, profundidad - 1, g);
            hacerCuadrado(x3, y3, tamañoR, profundidad - 1, g);
            hacerCuadrado(x4, y4, tamañoR, profundidad - 1, g);
        }
    }

    public void cambio() {
        objeto.firePropertyChange("Figura", false, true);
    }

    public void addListener(PropertyChangeListener listener) {
        objeto.addPropertyChangeListener(listener);
    }
}
