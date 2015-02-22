package br.com.sw2it.odontodo.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * A factory for creating Connection objects.
 */
public final class ConnectionFactory {

    /**
     * Construtor Padrao
     * Instancia um novo objeto ConnectionFactory.
     */
    private ConnectionFactory() {
        super();
    }

    /**
     * Nome: getConnection Recupera o valor do atributo 'connection'.
     * @return valor do atributo 'connection'
     * @see
     */
    public static EntityManager getConnection() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Odontodo");
        EntityManager manager = emf.createEntityManager();
        return manager;
    }

}
