package PracticoDao.patrondao.conexion;




import PracticoDao.patrondao.shared.DaoException;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySql extends Conexion {
    public ConexionMySql() throws DaoException {
        conectar();
    }

    @Override
    public void conectar() throws DaoException {
        try {
            conexion = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/departamento?"
                            + "user=root&password=root");
        } catch (SQLException e) {
            throw new DaoException("No se pudo conectar a la BD: " + e.getMessage(), e);
        }
    }

    @Override
    public void desconectar() throws DaoException {
        try {
            conexion.close();
        } catch (SQLException e) {
            throw new DaoException("No se pudo desconectar de la BD: " + e.getMessage(), e);
        }
    }
}
