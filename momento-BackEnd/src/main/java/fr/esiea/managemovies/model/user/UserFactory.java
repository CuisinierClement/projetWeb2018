package com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user;

import org.seedstack.business.domain.GenericFactory;

public interface UserFactory extends GenericFactory<User> {

    /**
     * @param p_login
     * @param p_name
     * @param p_firstName
     */
    public User createUser(String pLogin, String pName, String pFirstName);

}