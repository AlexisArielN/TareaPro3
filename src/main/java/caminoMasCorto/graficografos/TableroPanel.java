package caminoMasCorto.graficografos;

import javax.swing.*;
import java.awt.*;

public class TableroPanel extends JPanel {

    private DibujadorTablero<String> modelo;

    public TableroPanel(DibujadorTablero<String> src){
        modelo = src;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600,600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        modelo.dibujar(20,50,g);
    }
}
