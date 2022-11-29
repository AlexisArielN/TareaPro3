package PracticoDao.patrondao.shared;


import PracticoDao.listas.Lista;

public interface Dao <T> {
    T get(int id) throws DaoException;

    T get(String id) throws DaoException;
    Lista<T> getAll() throws DaoException;
    void insert(T t) throws DaoException;
    void update(T t) throws DaoException;
    void delete(int id) throws DaoException;

    void delete(String id) throws DaoException;

    int getTamano() throws DaoException;
}
