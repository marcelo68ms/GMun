/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.comercial.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author marcelo.santos
 */
public class BaseDAO<T extends Serializable> {

    /**
     * Atributo manager.
     */
    private EntityManager entityManager;

    /**
     * Atributo classe.
     */
    private Class<T> entity;

    /**
     * Nome: filterByField Filter by field.
     *
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
        }
        return result;
    }

    /**
     * Nome: filterByField Filter by field.
     *
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
        }
        return result;
    }

    /**
     * Construtor Padrao Instancia um novo objeto BaseDao.
     */
    public BaseDAO() {
        this.entityManager = ConnectionFactory.getConnection();
    }

    /**
     * Construtor Padrao Instancia um novo objeto BaseDao.
     *
     * @param clazz the clazz
     */
    public BaseDAO(Class<T> clazz) {
        this.entityManager = ConnectionFactory.getConnection();
        this.entity = clazz;
    }

    /**
     * Nome: gravar Gravar.
     *
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
     * Nome: inserir Inserir.
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
     *
     * @param <T> the generic type
     * @param id the id
     * @return valor do atributo 'enityById'
     * @see
     */
    public <T> T getEntityById(Object id) {
        this.entityManager.getEntityManagerFactory().getCache().evictAll();
        return (T) this.entityManager.find(this.entity, id);
    }

    /**
     * Nome: apagar deleta um registro na base de dados baseado em seu ID.
     *
     * @param id the id
     * @see
     */
    public void apagar(Object id) {

        T objeto = (T) this.entityManager.find(this.entity, id);
        this.apagar(objeto);

    }

    /**
     * Nome: apagar Apagar.
     *
     * @param objeto the objeto
     * @see
     */
    public void apagar(T objeto) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(objeto);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.getMessage();
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
