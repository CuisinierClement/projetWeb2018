/*
 * Creation : 25 avr. 2016
 */
package com.inetpsa.pi201.manageprojects.model.manageprojectsbudgetsresources.user;

import javax.inject.Inject;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.seedstack.seed.it.AbstractSeedIT;

import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.User;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.UserFactory;

public class UserFactoryDefaultIT extends AbstractSeedIT {

    @Inject
    private UserFactory m_UserFactory;

    @Before
    @Test
    public void injectionTest() {

        Assertions.assertThat(m_UserFactory).isNotNull();
    }

    @Test
    public void createUser() {
        this.scenarioCreateUserwithLogin();
        this.scenarioCreateUserWithoutLogin();
        this.scenarioCreateUserWithEmptyLogin();
    }

    private void scenarioCreateUserwithLogin() {

        String l_login = "userScenarioCreateUserwithLogin";
        String l_name = "User_Name";
        String l_firstName = "User_FirstName";

        User l_User = m_UserFactory.createUser(l_login, l_name, l_firstName);
        Assertions.assertThat(l_User).isNotNull();

    }

    private void scenarioCreateUserWithoutLogin() {

        String l_login = null;
        String l_name = "UserWithoutLogin_Name";
        String l_firstName = "UserWithoutLogin_FirstName";

        // create a user
        User l_User = m_UserFactory.createUser(l_login, l_name, l_firstName);
        Assertions.assertThat(l_User).isNull();

    }

    private void scenarioCreateUserWithEmptyLogin() {

        String l_login = "";
        String l_name = "UserWithEmptyLogin_Name";
        String l_firstName = "UserWithEmptyLogin_FirstName";

        // create a user
        User l_User = m_UserFactory.createUser(l_login, l_name, l_firstName);
        Assertions.assertThat(l_User).isNull();

    }

}
