package Dibujo.Figuras;

import Dibujo.funcionamiento.IDibujable;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Linea implements IDibujable {

    private int x;
    private int y;
    private int x2;
    private int y2;
    private Color color;
    private PropertyChangeSupport observado;
    private boolean seleccionado;
    public Linea(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.x2 = x +100;
        this.y2 = y;
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

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.drawLine(x,y,getX2(),y2);
    }

    public void addListener(PropertyChangeListener listener) {
        observado.addPropertyChangeListener(listener);
    }

    public void moverA(int x, int y) {
        this.x = x;
        this.y = y;
        this.x2 = x + 100;
        this.y2 = y;
        observado.firePropertyChange("LINEA", false, true);
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
}
