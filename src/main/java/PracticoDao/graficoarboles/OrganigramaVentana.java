package PracticoDao.graficoarboles;

import PracticoDao.patrondao.dao.DepartamentoDao;
import PracticoDao.patrondao.dao.FactoryDao;
import PracticoDao.patrondao.dao.FactoryDaoSingleton;
import PracticoDao.patrondao.dto.DepartamentoDto;
import PracticoDao.patrondao.shared.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class OrganigramaVentana extends JFrame {

    private static Logger logger = LogManager.getRootLogger();


    public OrganigramaVentana() throws DaoException {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.getContentPane().setLayout(new BorderLayout());

        DibujoOrganigrama<String> modelo = inicializarArbol();
        OrganigramaPanel pnl = new OrganigramaPanel(modelo);

        this.getContentPane().add(pnl, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) throws DaoException {
        new OrganigramaVentana();
    }

    private DibujoOrganigrama<String> inicializarArbol() throws DaoException {
        FactoryDao factory = FactoryDaoSingleton.getFactoryDao();
        DepartamentoDao daoDepartamento = factory.getDepartamentoDao();
        //DepartamentoDto p = daoDepartamento.get("1");
        //System.out.println(p);

        int tamano = daoDepartamento.getTamano();//5
        logger.debug("Tamano total de los datos: " + tamano);
        Organigrama<String> a = new Organigrama<>();
        for (int i = 1; i <= tamano; i++) {
            DepartamentoDto p = daoDepartamento.get(""+i);
            String id = p.getId();
            String nombre = p.getNombre();
            String idpadre = p.getIdpadre();
            String color = p.getColor();
            a.anadir(id,nombre,idpadre,color);
            logger.debug("Se añadio el siguiente registro: " + p);
        }

        //a.anadir("1", "Gerencia", null, "Rojo");
        //a.anadir("2", "RR.HH", "1", "Verde");
        //a.anadir("3", "Contabilidad", "1", "Verde");
        //a.anadir("4", "direccionLPZ", "1", "Verde");
        //a.anadir("4", "direccionLPZ", "1", "Verde");


        DibujoOrganigrama<String> resultado = new DibujoOrganigrama<>(a);
        logger.info("Se dibujo el organigrama");
        return resultado;
    }
}

