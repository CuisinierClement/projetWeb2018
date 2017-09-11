/*
 * Creation : 18 avr. 2016
 */
package com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.internal;

import org.seedstack.business.domain.BaseFactory;

import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.User;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.UserFactory;

public class UserFactoryDefault extends BaseFactory<User> implements UserFactory {

    /**
     * {@inheritDoc}
     * 
     * @see com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.UserFactory#createUser(java.lang.String,
     *      java.lang.String, java.lang.String)
     */

    public User createUser(String pLogin, String pName, String pFirstName) {

        User lUser = new User();

        if (pLogin != null && !"".equals(pLogin)) {
            lUser.setlogin(pLogin);
            lUser.setname(pName);
            lUser.setfirstName(pFirstName);
            System.out.println("User.login  = " + lUser.getlogin() + " firstName = " + lUser.getfirstName() + " name = " + lUser.getname());
            return lUser;

        }
        return null;

    }
}
