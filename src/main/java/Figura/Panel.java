package Figura;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Panel extends JPanel implements PropertyChangeListener {

    private Figura objetoFigura;

    public Panel(Figura objeto){
        setLayout(null);
        objetoFigura = objeto;
        objetoFigura.addListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(objetoFigura != null){
            objetoFigura.dibujar(g);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.repaint();
    }
}
