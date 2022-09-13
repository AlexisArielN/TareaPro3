package Dibujo.Vista;

import Dibujo.funcionamiento.Escena;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Ventana extends JFrame {
    private PanelCentral panelCentral;
    private JPanel panelHerramientas;
    private JButton botonCuadrado = new JButton("Cuadrado");
    private JButton botonCirculo = new JButton("Circulo");
    private JButton botonLinea = new JButton("Linea");
    private JButton botonTexto = new JButton("Texto");
    private JButton botonColor = new JButton("Seleccionar Color...");
    private Escena modelo;
    public static String tipo_figura = "";
    public static Color color;

    public Ventana(){
        setTitle("PRINCIPAl");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        inicializarImagen();

        panelHerramientas = new JPanel();
        cargarPanelHerramientas();
        panelCentral = new PanelCentral(modelo);

        this.getContentPane().setLayout(new BorderLayout());

        this.getContentPane().add(panelHerramientas, BorderLayout.NORTH);
        this.getContentPane().add(panelCentral, BorderLayout.CENTER);

        init();

        this.pack();
        setVisible(true);
    }

    public void cargarPanelHerramientas(){
        panelHerramientas.setLayout(null);
        panelHerramientas.setPreferredSize(new Dimension(100,100));
        botonCuadrado.setBounds(10,10,80,30);
        botonCirculo.setBounds(100,10,80,30);
        botonTexto.setBounds(190,10,80,30);
        botonLinea.setBounds(280,10,80,30);
        botonColor.setBounds(370,10,80,30);
        panelHerramientas.add(botonCuadrado);
        panelHerramientas.add(botonCirculo);
        panelHerramientas.add(botonLinea);
        panelHerramientas.add(botonTexto);
        panelHerramientas.add(botonColor);

        botonCuadrado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipo_figura = "Cuadrado";
            }
        });
        botonCirculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipo_figura = "Circulo";
            }
        });
        botonTexto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipo_figura = "Texto";
            }
        });
        botonLinea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipo_figura = "Linea";
            }
        });
        botonColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color colorSeleccionado = JColorChooser.showDialog(null, "Escoja un Color" , Color.DARK_GRAY);
                if(colorSeleccionado != null){
                    color = colorSeleccionado;
                }
            }
        });
    }

    public void init(){
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Archivo");

        JMenuItem menuitem = new JMenuItem("Abrir");
        menuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuAbrirArchivo();
            }
        });

        menu.add(menuitem);
        menubar.add(menu);
        setJMenuBar(menubar);
    }

    private void menuAbrirArchivo() {
        String direccion = "C:\\Users\\Alexis\\Desktop\\Carpeta de proyectos\\TareaPro3\\imagenes";
        JFileChooser inputFile = new JFileChooser();
        if(!direccion.equals("")){
            inputFile.setCurrentDirectory(new File(direccion));

        }

        inputFile.showOpenDialog(this);
        if(inputFile.getSelectedFile() == null){
            JOptionPane.showMessageDialog(null, "Debes elegir imagen en la carpeta");
            return;
        }

        File archivoImagen = inputFile.getSelectedFile();
        modelo.getImagen().cargarImagen(archivoImagen);
    }

    private void inicializarImagen() {
        modelo = new Escena();
        for (int i = 0; i < 300; i++) {
            modelo.getImagen().setColor(i, 100, 0,0,0);
        }
    }


    public static void main(String[] args) {
        Ventana v = new Ventana();
    }
}
