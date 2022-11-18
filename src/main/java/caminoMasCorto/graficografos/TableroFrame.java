package caminoMasCorto.graficografos;

import caminoMasCorto.grafo.GrafoCompleto;
import caminoMasCorto.listas.Lista;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableroFrame extends JFrame {

    public JPanel camino =new JPanel();
    public JLabel lbl;

    private static Logger logger = LogManager.getRootLogger();

    public TableroFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
        DibujadorTablero<String> modelo = inicializarGrafo();
        TableroPanel panel = new TableroPanel(modelo);
        this.getContentPane().add(panel, BorderLayout.CENTER);

        camino.setLayout(new BorderLayout());
        camino.setPreferredSize(new Dimension(100,100));
        camino.add(lbl);

        initMenu();

        this.getContentPane().add(panel, BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);
    }

    private void initMenu() {
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        JMenuItem menuitem = new JMenuItem("Reset");
        menuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuitem);
        menubar.add(menu);

        menuitem = new JMenuItem("Terremoto");
        menuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DibujadorTablero.terremoto = true;
            }
        });
        menu.add(menuitem);
        menubar.add(menu);

        menuitem = new JMenuItem("Salir");
        menuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(menuitem);
        menubar.add(menu);

        this.setJMenuBar(menubar);
    }

    public static void main(String[] args) {
        new TableroFrame();
    }

    private DibujadorTablero<String> inicializarGrafo() {
        GrafoCompleto<String> a = new GrafoCompleto<>();

        for (int i = 1; i <= 63; i++) {
            if(i < 10){
                a.anadirNodo("00"+i,"Nodo "+i);
            } else {
                a.anadirNodo("0"+i,"Nodo "+i);
            }
        }

        logger.debug("Se crearon los nodos desde 1 al 63");

        /*a.conectar("001", "002",1,true);
        a.conectar("002", "003",1,true);
        a.conectar("003", "004",1,true);
        a.conectar("010", "011",1,true);
        a.conectar("011", "012",1,true);
        a.conectar("012", "013",1,true);
        a.conectar("019", "020",1,true);
        a.conectar("020", "021",1,true);
        a.conectar("021", "022",1,true);

        a.conectar("001","010",1,true);
        a.conectar("010","019",1,true);

        a.conectar("002", "011",1,true);
        a.conectar("011", "020",1,true);

        a.conectar("003", "012",1,true);
        a.conectar("012", "021",1,true);

        a.conectar("004", "013",1,true);
        a.conectar("013", "022",1,true);*/

        a.conectar("001", "002");
        a.conectar("002", "003");
        a.conectar("003", "004");
        a.conectar("004", "005");
        a.conectar("005", "006");
        a.conectar("006", "007");
        a.conectar("007", "008");
        a.conectar("008", "009");

        a.conectar("010", "011");
        a.conectar("011", "012");
        a.conectar("012", "013");
        a.conectar("013", "014");
        a.conectar("014", "015");
        a.conectar("015", "016");
        a.conectar("016", "017");
        a.conectar("017", "018");


        a.conectar("019", "020");
        a.conectar("020", "021");
        a.conectar("021", "022");
        a.conectar("022", "023");
        a.conectar("023", "024");
        a.conectar("024", "025");
        a.conectar("025", "026");
        a.conectar("026", "027");

        a.conectar("028", "029");
        a.conectar("029", "030");
        a.conectar("030", "031");
        a.conectar("031", "032");
        a.conectar("032", "033");
        a.conectar("033", "034");
        a.conectar("034", "035");
        a.conectar("035", "036");

        a.conectar("037", "038");
        a.conectar("038", "039");
        a.conectar("039", "040");
        a.conectar("040", "041");
        a.conectar("041", "042");
        a.conectar("042", "043");
        a.conectar("043", "044");
        a.conectar("044", "045");

        a.conectar("046", "047");
        a.conectar("047", "048");
        a.conectar("048", "049");
        a.conectar("049", "050");
        a.conectar("050", "051");
        a.conectar("051", "052");
        a.conectar("052", "053");
        a.conectar("053", "054");

        a.conectar("055", "056");
        a.conectar("056", "057");
        a.conectar("057", "058");
        a.conectar("058", "059");
        a.conectar("059", "060");
        a.conectar("060", "061");
        a.conectar("061", "062");
        a.conectar("062", "063");

        a.conectar("001","010");
        a.conectar("010","019");
        a.conectar("019","028");
        a.conectar("028","037");
        a.conectar("037","046");
        a.conectar("046","055");

        a.conectar("002","011");
        a.conectar("011","020");
        a.conectar("020","029");
        a.conectar("029","038");
        a.conectar("038","047");
        a.conectar("047","056");

        a.conectar("003","012");
        a.conectar("012","021");
        a.conectar("021","030");
        a.conectar("030","039");
        a.conectar("039","048");
        a.conectar("048","057");

        a.conectar("004","013");
        a.conectar("013","022");
        a.conectar("022","031");
        a.conectar("031","040");
        a.conectar("040","049");
        a.conectar("049","058");

        a.conectar("005","014");
        a.conectar("014","023");
        a.conectar("023","032");
        a.conectar("032","041");
        a.conectar("041","050");
        a.conectar("050","059");

        a.conectar("006","015");
        a.conectar("015","024");
        a.conectar("024","033");
        a.conectar("033","042");
        a.conectar("042","051");
        a.conectar("051","060");

        a.conectar("007","016");
        a.conectar("016","025");
        a.conectar("025","034");
        a.conectar("034","043");
        a.conectar("043","052");
        a.conectar("052","061");

        a.conectar("008","017");
        a.conectar("017","026");
        a.conectar("026","035");
        a.conectar("035","044");
        a.conectar("044","053");
        a.conectar("053","062");

        a.conectar("009","018");
        a.conectar("018","027");
        a.conectar("027","036");
        a.conectar("036","045");
        a.conectar("045","054");
        a.conectar("054","063");

        logger.debug("Se conectaron los nodos");

        String gOrigen = "002";
        String gDestino = "055";
        DibujadorTablero<String> resultado = new DibujadorTablero<>(a);
        resultado.iniciarCamino(a, gOrigen, gDestino);
        Lista<String> camino = a.dijkstra(gOrigen, gDestino);
        lbl = new JLabel("camino:" + camino);
        System.out.println(camino);
        return resultado;
    }

}
