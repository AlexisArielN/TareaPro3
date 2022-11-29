package PracticoDao.graficoarboles;

import javax.swing.*;
import java.awt.*;

public class OrganigramaPanel  extends JPanel {

    private DibujoOrganigrama<String> modelo;

    public OrganigramaPanel(DibujoOrganigrama<String> src) {
        modelo = src;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600,600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        modelo.dibujar(0,0, g);
    }
}

