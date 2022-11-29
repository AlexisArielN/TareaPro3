package PracticoDao.graficoarboles;

import java.awt.*;

public class DibujoOrganigrama<E> {

    public static final int ANCHO_NODO = 50;
    public static final int ESPACIO_HORIZONTAL = 70;
    public static final int ESPACIO_VERTICAL = 70;
    private Organigrama<E> modelo;
    private DibujoNodoOrganigrama<E> raiz;

    public DibujoOrganigrama(Organigrama<E> src) {
        modelo = src;
        raiz = new DibujoNodoOrganigrama<>(src.getRaiz());
    }

    public void dibujar(int x, int y, Graphics g) {

        raiz.dibujar(x, y, g);
    }

    static class DibujoNodoOrganigrama<E> {
        private Organigrama.Nodo<E> modelo;

        DibujoNodoOrganigrama(Organigrama.Nodo<E> src) {
            this.modelo = src;
        }

        public void dibujar(int x, int y, Graphics g) {
            if (modelo == null) {
                g.drawString("[VACIO]", x, y);
                return;
            }
            dibujarNodo(modelo, x, y, g);
        }

        private int dibujarNodo(Organigrama.Nodo<E> nodo, int x, int y, Graphics g) {
            int anchoTotal = getAnchoTotal(nodo);
            int xNodo = x + anchoTotal / 2;
            int yNodo = y;
            int xHijo = x;
            int yHijo = y + ANCHO_NODO + ESPACIO_VERTICAL;
            for (Organigrama.Nodo<E> hijo : nodo.getHijos()) {
                int anchoHijo = getAnchoTotal(hijo);
                g.drawLine(xNodo, yNodo + ANCHO_NODO / 2,
                        xHijo + anchoHijo / 2, yHijo + ANCHO_NODO / 2);
                dibujarNodo(hijo, xHijo, yHijo, g);
                xHijo += (anchoHijo + ESPACIO_HORIZONTAL);
            }
            g.setColor(color(nodo));
            g.fillRect(xNodo - ANCHO_NODO / 2, yNodo, ANCHO_NODO+50, ANCHO_NODO);
            g.setColor(Color.black);
            g.drawRect(xNodo - ANCHO_NODO / 2, yNodo, ANCHO_NODO+50, ANCHO_NODO);
            g.drawString(nodo.getNombre(), xNodo, yNodo + ANCHO_NODO / 2);

            return anchoTotal;
        }

        public Color color(Organigrama.Nodo<E> nodo) {
            if (nodo.getColor().equals("Rojo")) {
                return Color.RED;
            }
            if (nodo.getColor().equals("Verde")) {
                return Color.GREEN;
            }
            if (nodo.getColor().equals("Azul")) {
                return Color.BLUE;
            }
            if (nodo.getColor().equals("Amarillo")) {
                return Color.YELLOW;
            }
            return Color.WHITE;
        }

        public int getAnchoTotal(Organigrama.Nodo<E> nodo) {
            if (nodo.getHijos().getTamano() == 0) {
                return ANCHO_NODO;
            }
            int ancho = 0;
            int espacio = 0;
            for (Organigrama.Nodo<E> hijo : nodo.getHijos()) {
                int anchoHijo = getAnchoTotal(hijo);
                ancho = ancho + espacio + anchoHijo;
                espacio = ESPACIO_HORIZONTAL;
            }

            return ancho;
        }
    }
}

