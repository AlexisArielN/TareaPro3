package Dibujo2.Figuras;

import Dibujo2.funcionamiento.IDibujable;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Texto implements IDibujable {

    private String texto = "Aqui va un texto";
    private int x;
    private int y;
    private int tamaño_total;
    private Color color;
    private PropertyChangeSupport observado;
    private boolean seleccionado;

    public Texto(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        tamaño_total = texto.length();
        observado = new PropertyChangeSupport(this);
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.drawString(texto,x,y);
    }

    public void addListener(PropertyChangeListener listener) {
        observado.addPropertyChangeListener(listener);
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
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

    public int getTamaño_total() {
        return tamaño_total;
    }

    public void setTamaño_total(int tamaño_total) {
        this.tamaño_total = tamaño_total;
    }

    public void moverA(int x, int y) {
        this.x = x;
        this.y = y;
        observado.firePropertyChange("TEXTO", false, true);
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
}
