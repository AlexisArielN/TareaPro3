package Ordenamientos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OrdenamientoPanel extends JPanel implements PropertyChangeListener {

    private JButton ordenarBurbuja;
    private JButton ordenarInsercion;
    private JButton ordenarQuicksort;
    private int arreglo[];
    private DibujoOrdenamiento modelo;
    private static Logger logger = LogManager.getRootLogger();

    public OrdenamientoPanel(DibujoOrdenamiento escena, int[] valores) {
        modelo = escena;
        arreglo = valores;
        setLayout(null);
        ordenarBurbuja = new JButton("Ordenar Burbuja");
        ordenarInsercion = new JButton("Ordenar Insercion");
        ordenarQuicksort = new JButton("Ordenar Quicksort");
        ordenarBurbuja.setBounds(10, 10, 120, 30);
        ordenarInsercion.setBounds(140, 10, 120, 30);
        ordenarQuicksort.setBounds(270, 10, 120, 30);
        this.add(ordenarBurbuja);
        this.add(ordenarInsercion);
        this.add(ordenarQuicksort);
        modelo.mostrarArreglo();
        ordenarBurbuja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelo.ordenarBurbuja();
                modelo.mostrarArreglo();
                logger.debug("Se ordeno con Burbuja");
            }
        });
        ordenarInsercion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelo.ordenarInsercion();
                modelo.mostrarArreglo();
                logger.debug("Se ordeno con Insercion");
            }
        });
        ordenarQuicksort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelo.ordenarQuicksort(valores, 0 ,arreglo.length-1);
                modelo.mostrarArreglo();
                logger.debug("Se ordeno con Quicksort");
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (modelo == null) {
            return;
        }
        modelo.dibujar(g);
        logger.debug("Se dibujaron las lineas");
        modelo.addObserver(this);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.repaint();
    }
}
