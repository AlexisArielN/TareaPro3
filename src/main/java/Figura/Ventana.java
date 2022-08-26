package Figura;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.beans.PropertyChangeSupport;

public class Ventana extends JFrame {

    private static final Logger log = LogManager.getLogger(Ventana.class);
    private Figura cuadrado;
    private Panel panel;
    private PropertyChangeSupport objeto;

    public Ventana() {
        setTitle("Principal");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cuadrado = new Figura(1);

        panel = new Panel(cuadrado);
        getContentPane().add(panel);
        iniciar();
    }


    public void iniciar(){
        // Crear barra menú
        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);
        // Crear barra opciones menú
        JMenu menu = new JMenu("Opciones");
        mb.add(menu);
        JMenuItem salir = new JMenuItem("Salir");
        JMenuItem figura = new JMenuItem("Figura");
        menu.add(figura);
        menu.add(salir);

        salir.addActionListener( e->{
            log.debug("Selecciono opción salir");
            dispose();
            System.exit(0);
        });

        figura.addActionListener( e->{
            log.debug("Selecciono opción figura");
            figura();
        });
    }

    public void figura(){
        log.debug("Selecciono ocpion figura");
        String profundidad = JOptionPane.showInputDialog("¿Ingrese la profundidad?");
        try{
            int numeroUsuario = Integer.parseInt(profundidad);
            if(numeroUsuario <= 0){
                JOptionPane.showMessageDialog(null,"Debes ingresar numero mayor a 0");
            } else {
                cuadrado.setProfundidad(numeroUsuario);
                cuadrado.cambio();
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Debes ingresar numeros no letras");
        }
    }

    public static void main(String[] args) {
        Ventana ventana = new Ventana();
        ventana.setVisible(true);
    }
}
