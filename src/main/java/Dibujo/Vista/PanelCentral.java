package Dibujo.Vista;

import Dibujo.funcionamiento.Escena;
import Dibujo.Figuras.Circulo;
import Dibujo.Figuras.Cuadrado;
import Dibujo.Figuras.Linea;
import Dibujo.Figuras.Texto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PanelCentral extends JPanel implements PropertyChangeListener, MouseListener, MouseMotionListener {

    private Escena modelo;

    public PanelCentral(Escena escena){
        modelo = escena;
        modelo.addListener(this);
        this.setPreferredSize(new Dimension(500,500));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(modelo.getImagen().getAncho(), modelo.getImagen().getAlto());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (modelo == null)
            return;
        modelo.dibujar(g);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(Ventana.tipo_figura.equals("Cuadrado")){
            crearCuadrados(e.getX(), e.getY());
        }
        if(Ventana.tipo_figura.equals("Circulo")){
            crearCirculos(e.getX(), e.getY());
        }
        if(Ventana.tipo_figura.equals("Texto")){
            crearTexto(e.getX(), e.getY());
        }
        if(Ventana.tipo_figura.equals("Linea")){
            crearLinea(e.getX(), e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        modelo.seleccionarObjeto(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        modelo.soltarObjeto();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Cuadrado cuadrado = modelo.getObjetoSeleccionadoCuadrado();
        if (cuadrado != null) {
            cuadrado.moverA(e.getX(), e.getY());
        }
        Circulo circulo = modelo.getObjetoSeleccionadoCirculo();
        if (circulo != null) {
            circulo.moverA(e.getX(), e.getY());
        }

        Texto texto = modelo.getObjetoSeleccionadoTexto();
        if (texto != null) {
            texto.moverA(e.getX(), e.getY());
        }
        Linea linea = modelo.getObjetoSeleccionadoLinea();
        if (linea != null) {
            linea.moverA(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public void crearCuadrados(int x, int y){
        Cuadrado c = new Cuadrado(x,y, Ventana.color);// x y / tamaño
        c.addListener(this);
        modelo.addCuadrado(c);
    }

    public void crearCirculos(int x, int y){
        Circulo c = new Circulo(x,y,Ventana.color);// x y / tamaño
        c.addListener(this);
        modelo.addCirculo(c);
    }

    public void crearTexto(int x, int y){
        Texto c = new Texto(x,y,Ventana.color);// x y / tamaño
        c.addListener(this);
        modelo.addTexto(c);
    }

    public void crearLinea(int x, int y){
        Linea c = new Linea(x,y,Ventana.color);// x y / tamaño
        c.addListener(this);
        modelo.addLinea(c);
    }
}
