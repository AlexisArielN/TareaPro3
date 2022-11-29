package PracticoDao.patrondao.dao;


import PracticoDao.listas.Lista;
import PracticoDao.patrondao.conexion.Conexion;
import PracticoDao.patrondao.conexion.ConexionMySql;
import PracticoDao.patrondao.dto.DepartamentoDto;
import PracticoDao.patrondao.shared.DaoException;

import java.sql.ResultSet;
import java.sql.Statement;

public class DepartamentoDaoMySql implements DepartamentoDao{


    private final Conexion conexion;

    public DepartamentoDaoMySql() throws DaoException {
        conexion = new ConexionMySql();
    }

    @Override
    public DepartamentoDto get(int id) throws DaoException {
        return null;
    }
        //retorna solo un cuadrado
        @Override
        public DepartamentoDto get(String id) throws DaoException {
            try {
                Statement st = conexion.getConexion().createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM departamentos WHERE id = '" + id+"'");
                if (rs.next()) {
                    return new DepartamentoDto(
                            rs.getString("id"),
                            rs.getString("nombre"),
                            rs.getString("idpadre"),
                            rs.getString("color")
                    );
                }
            } catch (Exception e) {
                throw new DaoException("No pudo obtener Persona con id '" + id + "'", e);
            }
            return null;
        }
    //retornatodo en una lista
    @Override
    public Lista<DepartamentoDto> getAll() throws DaoException {
        Lista<DepartamentoDto> departamentos = new Lista<>();
        try {
            Statement st = conexion.getConexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT id, nombre, idpadre, color FROM departamentos");
            while (rs.next()) {
                departamentos.insertar(new DepartamentoDto(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("idpadre"),
                        rs.getString("color")
                ));
            }
        } catch (Exception e) {
            throw new DaoException("No pudo obtener Personas", e);
        }
        return departamentos;
    }

    @Override
    public void insert(DepartamentoDto departamentoDto) throws DaoException {
        try {
            Statement st = conexion.getConexion().createStatement();
            int numeroFilasAfectadas =
                    st.executeUpdate("INSERT INTO departamentos (nombre, idpadre, color) VALUES ('" +
                                    departamentoDto.getNombre() + "', " +
                                    departamentoDto.getIdpadre() + ", '" +
                                    departamentoDto.getColor() + "')",
                            Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            int id = 0;
            if (rs.next()){
                id = rs.getInt(1);
            }
            departamentoDto.setId(""+id);
        } catch (Exception e) {
            throw new DaoException("No pudo insertar Departamentos", e);
        }
    }

    @Override
    public void update(DepartamentoDto d) throws DaoException {
        try {
            Statement st = conexion.getConexion().createStatement();
            st.executeUpdate("UPDATE departamentos SET " +
                    "nombre = '" + d.getNombre() +
                    "', idpadre='" + d.getIdpadre() +
                    "', color='" + d.getColor() +
                    "' WHERE id = '" + d.getId() + "'");
        } catch (Exception e) {
            throw new DaoException("No pudo actualizar departamentos con id " + d.getId(), e);
        }
    }

    @Override
    public void delete(int id) throws DaoException {

    }

    @Override
    public void delete(String id) throws DaoException {
        try {
            Statement st = conexion.getConexion().createStatement();
            st.executeUpdate("DELETE FROM departamentos WHERE id = '" + id + "'");
        } catch (Exception e) {
            throw new DaoException("No pudo borrar Persona con id '" + id + "'", e);
        }
    }

    @Override
    public int getTamano() throws DaoException {
        int tamano = 0;
        try {
            Statement st = conexion.getConexion().createStatement();
            ResultSet rs = st.executeQuery("select count(id) as tamano from departamentos");
            if(rs.next()){
                tamano = rs.getInt("tamano");
            }
        } catch (Exception e) {
            throw new DaoException("No se devolvio el tamano", e);
        }
        return tamano;
    }
}
