package com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user;

import java.util.List;

import org.seedstack.business.domain.GenericRepository;

public interface UserRepository extends GenericRepository<User, String> {

    /**
     * Load all users in the Data base
     */
    public List<User> loadAll();

    /**
     * save a list of user in Data base
     * 
     * @param p_Users
     */
    public void persistList(List<User> pUsers);

    /**
     * Delete all users
     *
     * @return number of users deleted
     */
    long deleteAll();

}