package Ordenamientos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DibujoOrdenamiento implements IDibujable{

    private int[] arregloNumeros;
    private PropertyChangeSupport observado;
    private static Logger logger = LogManager.getRootLogger();

    public DibujoOrdenamiento(int[] valores){// tengo mis 400 aqui
        arregloNumeros =  valores;
        observado = new PropertyChangeSupport(this);
    }

    @Override
    public void dibujar(Graphics g) {
        for (int i = 0; i < arregloNumeros.length; i++) {
            g.drawLine((i * 2), 380, (i * 2), 380-(arregloNumeros[i]));
        }
    }

    public void ordenarBurbuja(){
        int auxiliar2;
        for (int i = 0; i < arregloNumeros.length - 1; i++) {
            for (int j = 0; j < arregloNumeros.length - 1; j++) {
                if (arregloNumeros[j] > arregloNumeros[j + 1]) {
                    auxiliar2 = arregloNumeros[j];
                    arregloNumeros[j] = arregloNumeros[j + 1];
                    arregloNumeros[j + 1] = auxiliar2;
                }
            }
        }
        observado.firePropertyChange("Cambio", false, true);
    }

    public void ordenarInsercion() {
        int posicion;
        int auxiliar;
        for (int i = 0; i < arregloNumeros.length; i++) {
            posicion = i;
            auxiliar = arregloNumeros[i];
            while (posicion > 0 && arregloNumeros[posicion - 1] > auxiliar) {
                arregloNumeros[posicion] = arregloNumeros[posicion - 1];
                posicion--;
            }
            arregloNumeros[posicion] = auxiliar;
        }
        observado.firePropertyChange("Cambio", false, true);
    }

    public void ordenarQuicksort(int[] arreglo, int primero, int ultimo) {
        int i;
        int j;
        int pivote;
        int auxiliar;
        i = primero;
        j = ultimo;
        pivote = arreglo[(primero + ultimo) / 2];
        do {
            while (arreglo[i] < pivote) {
                i++;
            }
            while (arreglo[j] > pivote) {
                j--;
            }
            if (i <= j) {
                auxiliar = arreglo[i];
                arreglo[i] = arreglo[j];
                arreglo[j] = auxiliar;
                i++;
                j--;
            }
        } while (i <= j);
        if (primero < j) {
            ordenarQuicksort(arreglo, primero, j);
        }
        if (i < ultimo) {
            ordenarQuicksort(arreglo, i, ultimo);
        }
        observado.firePropertyChange("Cambio", false, true);
    }

    public void addObserver(PropertyChangeListener observador) {
        try {
            observado.addPropertyChangeListener(observador);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarArreglo() {
        for (int i = 0; i < arregloNumeros.length; i++) {
            System.out.print(arregloNumeros[i] + " - ");
        }
        System.out.println();
    }
}
