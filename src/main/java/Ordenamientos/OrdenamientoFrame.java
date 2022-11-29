package Ordenamientos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class OrdenamientoFrame extends JFrame {

    private DibujoOrdenamiento modelo;
    private int[] valores;
    private int ancho;
    private int alto;
    private static Logger logger = LogManager.getRootLogger();

    public OrdenamientoFrame(){
        setTitle("Titulo");
        ancho = 850;
        alto = 500;
        setSize(ancho,alto);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        asignarValores();
        modelo = new DibujoOrdenamiento(valores);
        OrdenamientoPanel panelOrdenamiento = new OrdenamientoPanel(modelo, valores);
        // Panel de ordenamiento
        this.getContentPane().add(panelOrdenamiento, BorderLayout.CENTER);
        setVisible(true);
    }

    public void asignarValores(){
        valores = new int[400];
        for (int i = 0; i < valores.length; i++) {
            valores[i] = 1+(int)(Math.random()*100) ;
        }
        logger.info("Se asignaron los valores a las diferentes posiciones");
    }


    public static void main(String[] args) {
        new OrdenamientoFrame();
    }
}
