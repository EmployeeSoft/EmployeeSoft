package dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public abstract class AbstractHibernateDao<T extends Serializable> {
    @Autowired
    protected SessionFactory sessionFactory;

    protected Class<T> clazz;

    protected final void setClazz(final Class<T> clazz) {
        this.clazz = clazz;
    }

    public T findById(final Integer id) {
        return getCurrentSession().get(clazz, id);
    }

    public T merge(T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
