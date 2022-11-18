package caminoMasCorto.graficografos;


import caminoMasCorto.grafo.GrafoCompleto;
import caminoMasCorto.listas.Lista;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.HashMap;
import java.util.TreeMap;

public class DibujadorTablero<E> implements IDibujador {

    private GrafoCompleto modelo;
    private DibujoNodoGrafo<E> raiz;
    private static Lista<String> camino;
    private static String origen;
    private static String destino;

    protected static int mitadNodo1X;
    protected static int mitadNodo1Y;
    protected static int mitadNodo2X;
    protected static int mitadNodo2Y;
    protected static int mitadNodo3X;
    protected static int mitadNodo3Y;

    protected static boolean terremoto = false;

    private static Logger logger = LogManager.getRootLogger();

    public Lista<String> iniciarCamino(GrafoCompleto<String> g, String a, String s){
        origen = a;
        destino = s;
        camino = g.dijkstra(a, s);
        return camino;
    }

    public static Lista<String> getCamino() {
        return camino;
    }

    public static void setCamino(Lista<String> camino) {
        DibujadorTablero.camino = camino;
    }

    public DibujadorTablero(GrafoCompleto src) {
        modelo = src;
        raiz = new DibujoNodoGrafo<>(src.getNodos());
    }

    @Override
    public void dibujar(int x, int y, Graphics g) {
        raiz.dibujar(x, y, g);
    }


    static class DibujoNodoGrafo<E> {

        private HashMap<String, GrafoCompleto.Nodo<E>> modelo;

        public DibujoNodoGrafo(HashMap<String, GrafoCompleto.Nodo<E>> src) {
            this.modelo = src;
        }

        public void dibujar(int x, int y, Graphics g) {
            if (modelo == null) {
                System.out.println("[VACIO]");
            }
            dibujarNodo(modelo, x, y, g);
        }


        private void dibujarNodo(HashMap<String, GrafoCompleto.Nodo<E>> modelo, int x, int y, Graphics g) {

            // Ordenarlo
            TreeMap<String, GrafoCompleto.Nodo> modeloOrdenado = new TreeMap<>();
            modeloOrdenado.putAll(modelo);


            int contador = 0;
            int ancho = 40;
            int alto = 40;

            int separacionHorizontal = 60;
            int separacionVertical = 60;

            for (String nodo : modeloOrdenado.keySet()) {

                x += separacionHorizontal;
                if (contador % 9 == 0) {
                    x = 20;
                    y += separacionVertical;
                }

                // calculo para coordenadas de circulo, id y lineas
                mitadNodo1X = x + (ancho / 2);
                mitadNodo1Y = y + (alto / 2);
                mitadNodo2X = mitadNodo1X + ancho;
                mitadNodo2Y = mitadNodo1Y;
                mitadNodo3X = mitadNodo1X;
                mitadNodo3Y = mitadNodo1Y + alto;


                contador++;
                if(contador % 9 == 0){
                    if(contador != 63){
                        g.drawLine(mitadNodo1X, mitadNodo1Y, mitadNodo3X, mitadNodo3Y);
                    }
                } else if(contador > 54){
                    g.drawLine(mitadNodo1X, mitadNodo1Y, mitadNodo2X, mitadNodo2Y);
                } else {
                    g.drawLine(mitadNodo1X, mitadNodo1Y, mitadNodo2X, mitadNodo2Y);
                    g.drawLine(mitadNodo1X, mitadNodo1Y, mitadNodo3X, mitadNodo3Y);
                }

                // Para colorear el camino mas corto
                for (int j = 0; j < camino.getTamano(); j++) {
                    if(nodo.equals(origen) || nodo.equals(destino) || nodo.equals(camino.get(j))){
                        g.setColor(Color.GREEN);
                        break;
                    } else {
                        g.setColor(Color.WHITE);
                    }
                }

                // dibujo el circulo y el id
                g.fillOval(x, y, ancho, alto);
                g.setColor(Color.BLACK);
                g.drawOval(x, y, ancho, alto);
                g.drawString(nodo, mitadNodo1X-10,mitadNodo1Y+5);
            }
            logger.debug("Se dibujo el tablero");
            terremoto = false;
        }
    }
}
