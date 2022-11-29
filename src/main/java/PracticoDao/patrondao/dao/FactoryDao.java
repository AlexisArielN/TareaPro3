package PracticoDao.patrondao.dao;


import PracticoDao.patrondao.shared.DaoException;

public interface FactoryDao {
    public abstract DepartamentoDaoMySql getDepartamentoDao() throws DaoException;
}
