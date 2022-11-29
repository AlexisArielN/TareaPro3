package PracticoDao.patrondao.dao;


import PracticoDao.patrondao.shared.DaoException;

public class FactoryDaoMySql implements FactoryDao {

    @Override
    public DepartamentoDaoMySql getDepartamentoDao() throws DaoException {
        return new DepartamentoDaoMySql();
    }
}
