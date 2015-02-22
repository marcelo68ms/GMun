package br.com.sw2it.odontodo.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.eclipse.persistence.exceptions.DatabaseException;

import br.com.sw2it.odontodo.exception.DataBaseException;

/**
 * <b>Descrição: Clase base para os DAO�s do sistema.</b> <br>
 * .
 * @param <T> the generic type
 * @author: SW2
 * @version 1.0 Copyright 2015.
 */
public class BaseDao<T extends Serializable> {

    /** Atributo manager. */
    private EntityManager entityManager;

    /** Atributo classe. */
    private Class<T> entity;

    /**
     * Nome: filterByField Filter by field.
     * @param field the field
     * @param value the value
     * @return list
     * @see
     */
    public List<T> filterByField(String field, String value) {
        StringBuffer jpql = new StringBuffer("SELECT entity FROM ");
        jpql.append(this.entity.getSimpleName());
        jpql.append(" entity");
        jpql.append(" WHERE entity.");
        jpql.append(field);
        jpql.append(" = :value");
        Query query = getEntityManager().createQuery(jpql.toString());
        query.setParameter("value", value);
        List<T> result;
        try {
            result = query.getResultList();
        } catch (NoResultException exception) {
            result = null;
        } catch (DatabaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                exception.getMessage());
        }
        return result;
    }

    /**
     * Nome: filterByField Filter by field.
     * @param field the field
     * @param value the value
     * @return list
     * @see
     */
    public List<T> filterByField(String field, Date value) {
        StringBuffer jpql = new StringBuffer("SELECT entity FROM ");
        jpql.append(this.entity.getSimpleName());
        jpql.append(" entity");
        jpql.append(" WHERE entity.");
        jpql.append(field);
        jpql.append(" = :value");
        Query query = getEntityManager().createQuery(jpql.toString());
        query.setParameter("value", value);
        List<T> result;
        try {
            result = query.getResultList();
        } catch (NoResultException exception) {
            result = null;
        } catch (DatabaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                exception.getMessage());
        }
        return result;
    }

    /**
     * Construtor Padrao Instancia um novo objeto BaseDao.
     */
    public BaseDao() {
        this.entityManager = ConnectionFactory.getConnection();
    }

    /**
     * Construtor Padrao Instancia um novo objeto BaseDao.
     * @param clazz the clazz
     */
    public BaseDao(Class<T> clazz) {
        try {
            this.entityManager = ConnectionFactory.getConnection();
        } catch (Exception e) {
            throw new DataBaseException("Não foi possível obter uma conexão com o banco de dados", e);
        }
        this.entity = clazz;
    }

    /**
     * Nome: gravar Gravar.
     * @param objeto the objeto
     * @see
     */
    public void gravar(T objeto) {

        if (!this.entityManager.getTransaction().isActive()) {
            this.entityManager.getTransaction().begin();
        }
        this.entityManager.merge(objeto);
        this.entityManager.flush();
        this.entityManager.getTransaction().commit();
    }

    /**
     * Nome: inserir
     * Inserir.
     *
     * @param objeto the objeto
     * @see
     */
    public void inserir(T objeto) {

        if (!this.entityManager.getTransaction().isActive()) {
            this.entityManager.getTransaction().begin();
        }
        this.entityManager.persist(objeto);
        this.entityManager.flush();
        this.entityManager.getTransaction().commit();
    }

    /**
     * Nome: getEnityById Retorna uma entity baseada no seu ID.
     * @param <T> the generic type
     * @param id the id
     * @return valor do atributo 'enityById'
     * @throws DataBaseException the data base exception
     * @see
     */
    public <T> T getEntityById(Object id) throws DataBaseException {
        this.entityManager.getEntityManagerFactory().getCache().evictAll();
        return (T) this.entityManager.find(this.entity, id);
    }

    /**
     * Nome: apagar deleta um registro na base de dados baseado em seu ID.
     * @param id the id
     * @throws DataBaseException the data base exception
     * @see
     */
    public void apagar(Object id) throws DataBaseException {

        T objeto = (T) this.entityManager.find(this.entity, id);
        this.apagar(objeto);

    }

    /**
     * Nome: apagar Apagar.
     * @param objeto the objeto
     * @throws DataBaseException the data base exception
     * @see
     */
    public void apagar(T objeto) throws DataBaseException {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(objeto);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (e.getMessage().contains("Error Code: 1451")) {
                throw new DataBaseException(DataBaseException.DELETE_VIOLACAO_CONSTRAINT,
                    e.getMessage());
            }
        }
    }

    /**
     * Nome: getEntityManager Recupera o valor do atributo 'entityManager'.
     * @return valor do atributo 'entityManager'
     * @see
     */
    public EntityManager getEntityManager() {
        this.entityManager.getEntityManagerFactory().getCache().evictAll();
        return this.entityManager;
    }

    /**
     * Nome: setEntityManager Registra o valor do atributo 'entityManager'.
     * @param entityManager valor do atributo entity manager
     * @see
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
