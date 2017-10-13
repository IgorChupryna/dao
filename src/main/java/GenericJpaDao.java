import sun.net.www.content.text.Generic;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;

public abstract class GenericJpaDao<K, E extends BaseEntity> implements GenericDao<K, E> {
    protected Class<E> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;


    public GenericJpaDao(Class<E> entityClass) {

        this.entityClass = entityClass;

        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[1];


    }

    //@Transactional(readOnly=true)
    public void persist(E entity) {
        entityManager.persist(entity);
    }

    //  @Transactional(readOnly=true)
    public void remove(E entity) {
        entityManager.remove(entity);
    }

    @SuppressWarnings("unchecked")
    //  @Transactional(readOnly=true)
    public E findById(K id) {
        return (E) entityManager.find(entityClass, id);
    }


    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}