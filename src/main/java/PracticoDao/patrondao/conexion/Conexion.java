package PracticoDao.patrondao.conexion;


import PracticoDao.patrondao.shared.DaoException;

import java.sql.Connection;

public abstract class Conexion {
    protected Connection conexion;
    public abstract void conectar() throws DaoException;
    public abstract void desconectar() throws DaoException;

    public Connection getConexion() {
        return conexion;
    }
}
