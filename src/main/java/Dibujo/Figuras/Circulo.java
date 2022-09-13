package Dibujo.Figuras;

import Dibujo.funcionamiento.IDibujable;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Circulo implements IDibujable {

    private int x;
    private int y;
    private int tamaño;
    private Color color;
    private PropertyChangeSupport observado;
    private boolean seleccionado;

    public Circulo(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.tamaño = 100;
        this.color = color;
        observado = new PropertyChangeSupport(this);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.fillOval(x,y,tamaño,tamaño);
    }

    public void addListener(PropertyChangeListener listener) {
        observado.addPropertyChangeListener(listener);
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void moverA(int x, int y) {
        this.x = x;
        this.y = y;
        observado.firePropertyChange("CIRCULO", false, true);
    }
}
