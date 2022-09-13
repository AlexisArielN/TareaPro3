package Dibujo.funcionamiento;

import Dibujo.Figuras.Circulo;
import Dibujo.Figuras.Cuadrado;
import Dibujo.Figuras.Linea;
import Dibujo.Figuras.Texto;
import Dibujo.Vista.PanelCentral;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Escena implements IDibujable {

    private Imagen imagen;
    private ArrayList<Cuadrado> objetosCuadrado;
    private ArrayList<Circulo> objetosCirculo;
    private ArrayList<Linea> objetosLinea;
    private ArrayList<Texto> objetosTexto;
    private PropertyChangeSupport observado;

    public Escena() {
        imagen = new Imagen(400, 400);
        objetosCuadrado = new ArrayList<>();
        objetosCirculo = new ArrayList<>();
        objetosTexto = new ArrayList<>();
        objetosLinea = new ArrayList<>();
        observado = new PropertyChangeSupport(this);
    }

    @Override
    public void dibujar(Graphics g) {
        if (imagen != null) {
            BufferedImage rsm = new BufferedImage(
                    imagen.getAncho(), imagen.getAlto(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = rsm.createGraphics();

            imagen.dibujar(g2d);
            g.drawImage(rsm, 0, 0, null);
        }

        for (Cuadrado c : objetosCuadrado) {
            c.dibujar(g);
        }
        for (Circulo cir : objetosCirculo) {
            cir.dibujar(g);
        }
        for (Linea l : objetosLinea) {
            l.dibujar(g);
        }
        for (Texto txt : objetosTexto) {
            txt.dibujar(g);
        }
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void addListener(PanelCentral panelCentral) {
        observado.addPropertyChangeListener(panelCentral);
        this.imagen.addListener(panelCentral);
        for (Cuadrado c : objetosCuadrado) {
            c.addListener(panelCentral);
        }
        for (Circulo c : objetosCirculo) {
            c.addListener(panelCentral);
        }
        for (Texto c : objetosTexto) {
            c.addListener(panelCentral);
        }
        for (Linea c : objetosLinea) {
            c.addListener(panelCentral);
        }
    }

    public void addCuadrado(Cuadrado c) {
        objetosCuadrado.add(c);
        observado.firePropertyChange("ESCENA", true, false);
    }

    public void addCirculo(Circulo c) {
        objetosCirculo.add(c);
        observado.firePropertyChange("ESCENA", true, false);
    }

    public void addTexto(Texto c) {
        objetosTexto.add(c);
        observado.firePropertyChange("ESCENA", true, false);
    }

    public void addLinea(Linea c) {
        objetosLinea.add(c);
        observado.firePropertyChange("ESCENA", true, false);
    }


    public void seleccionarObjeto(int x, int y) {
        for (Cuadrado c : objetosCuadrado) {
            if (x > c.getX() && x < (c.getX() + c.getTamaño()) &&
                    y > c.getY() && y < (c.getY() + c.getTamaño())) {
                c.setSeleccionado(true);
            }
        }

        for (Circulo cir : objetosCirculo) {
            if (x > cir.getX() && x < (cir.getX() + cir.getTamaño()) &&
                    y > cir.getY() && y < (cir.getY() + cir.getTamaño())) {
                cir.setSeleccionado(true);
            }
        }

        for (Texto txt : objetosTexto) {
            if (x > txt.getX() && x < (txt.getX() + 100) &&
                    y > txt.getY() && y < (txt.getY() + 10)) {
                txt.setSeleccionado(true);
            }
        }

        for (Linea linea : objetosLinea) {
            if (x > linea.getX() && x < (linea.getX() + linea.getX2()) &&
                    y > linea.getY() && y < (linea.getY() + linea.getY2())) {
                linea.setSeleccionado(true);
            }
        }

    }

    public void soltarObjeto() {
        for (Cuadrado c : objetosCuadrado) {
            c.setSeleccionado(false);
        }
        for (Circulo cir : objetosCirculo) {
            cir.setSeleccionado(false);
        }
        for (Texto txt : objetosTexto) {
            txt.setSeleccionado(false);
        }
        for (Linea linea : objetosLinea) {
            linea.setSeleccionado(false);
        }
    }

    public Cuadrado getObjetoSeleccionadoCuadrado() {
        for (Cuadrado c : objetosCuadrado) {
            if (c.isSeleccionado()) return c;
        }
        return null;
    }

    public Circulo getObjetoSeleccionadoCirculo() {
        for (Circulo cir : objetosCirculo) {
            if (cir.isSeleccionado())
                return cir;
        }
        return null;
    }

    public Texto getObjetoSeleccionadoTexto() {
        for (Texto txt : objetosTexto) {
            if (txt.isSeleccionado())
                return txt;
        }
        return null;
    }

    public Linea getObjetoSeleccionadoLinea() {
        for (Linea linea : objetosLinea) {
            if (linea.isSeleccionado())
                return linea;
        }
        return null;
    }
}
