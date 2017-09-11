/*
 * Creation : 19 avr. 2016
 */
package com.inetpsa.pi201.domains.manageprojects.infrastructure.repositories.jpa.user;

import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.seedstack.jpa.BaseJpaRepository;

import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.User;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.UserRepository;

public class UserRepositoryImpl extends BaseJpaRepository<User, String> implements UserRepository {

    /**
     * {@inheritDoc}
     * 
     * @see com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.UserRepository#loadAll()
     */
    public List<User> loadAll() {

        CriteriaBuilder lCriteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> lCriteriaQuery = lCriteriaBuilder.createQuery(User.class);

        return entityManager.createQuery(lCriteriaQuery.select(lCriteriaQuery.from(User.class))).getResultList();
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.UserRepository#persistList(java.util.List)
     */
    public void persistList(List<User> pUsers) {

        Iterator<User> lIterator = pUsers.iterator();
        while (lIterator.hasNext()) {
            User lUser = lIterator.next();
            persist(lUser);

        }
    }

    @Override
    public long deleteAll() {
        return entityManager.createQuery("DELETE FROM User").executeUpdate();
    }

}
