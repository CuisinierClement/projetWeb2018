/*
 * Creation : 26 avr. 2016

 */
package com.inetpsa.pi201.manageprojects.infrastructure.repositories.jpa.project;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.seedstack.jpa.JpaUnit;
import org.seedstack.seed.it.AbstractSeedIT;
import org.seedstack.seed.transaction.Transactional;

import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.User;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.UserFactory;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.UserRepository;

public class UserRepositoryImplIT extends AbstractSeedIT {

    @Inject
    private UserFactory m_UserFactory;

    @Inject
    private UserRepository m_UserRepository;

    @Transactional
    @JpaUnit("manage-domain")
    @Test
    public void saveUserOnDataBase() {

        // List of methods to test
        // m_UserRepository.delete(id);
        // m_UserRepository.delete(aggregate);
        // m_UserRepository.persist(aggregate);
        // m_UserRepository.persistList(p_Users);
        // m_UserRepository.load(id)
        // m_UserRepository.loadAll();
        // m_UserRepository.save(aggregate);

        this.scenarioOnePersistOneUSer();
        this.scenarioOnePersistListOfUSer();
        this.scenarioSaveUserInDataBase();

    }

    private void scenarioOnePersistOneUSer() {

        // The data base is normally empty ( create mode..)
        String l_login = "17485560";
        String l_name = "SOUMARE";
        String l_firstName = "Ahmed Salem";

        // try to load not created user id in the persist list
        Assertions.assertThat(m_UserRepository.load(l_login)).isNull();

        // create a user
        User l_User_1 = m_UserFactory.createUser(l_login, l_name, l_firstName);
        Assertions.assertThat(l_User_1).isNotNull();

        // Persist the user on data base
        m_UserRepository.persist(l_User_1);

        // verify that the user exist in data base
        Assertions.assertThat(m_UserRepository.load(l_login)).isNotNull();

        // try to persist the loaded user from data base : Normally no error
        String l_newFirstName = "Ahmed";
        l_User_1.setfirstName(l_newFirstName);
        m_UserRepository.persist(l_User_1);

        // verify the user is updated in data base not duplicated
        Assertions.assertThat(m_UserRepository.load(l_login)).isEqualTo(l_User_1);

        // verify that we can't persist an existing user in the data base if this user is not provided by the data base
        User l_User_3 = m_UserFactory.createUser(l_login, l_name, l_firstName);
        // try {
        // m_UserRepository.persist(l_User_3);
        // } catch (EntityExistsException e) {
        // System.out.println("\n Entity Exists Exception: catched\n");
        // }

        // update a user in data base and load it
        // in this case we verify that user exist in data base and it's updated
        // m_UserRepository.persist(l_User_1);

        // delete an existing user in the persistence list
        m_UserRepository.delete(l_login);

        // verify that the user have been deleted
        Assertions.assertThat(m_UserRepository.load(l_login)).isNull();

    }

    private void scenarioOnePersistListOfUSer() {

        // create an empty list of user
        ArrayList<User> l_listOfUsers = new ArrayList<User>();

        // user 1
        String l_login_1 = "13484302";
        String l_name_1 = "NAVARRO";
        String l_firstName_1 = "Eric";

        // user 2
        String l_login_2 = "17485560";
        String l_name_2 = "SOUMARE";
        String l_firstName_2 = "Ahmed Salem";

        // user 3
        String l_login_3 = "1211256";
        String l_name_3 = "FOUCHE";
        String l_firstName_3 = "Theo";

        // create a new users
        User l_User_1 = m_UserFactory.createUser(l_login_1, l_name_1, l_firstName_1);
        Assertions.assertThat(l_User_1).isNotNull();

        User l_User_2 = m_UserFactory.createUser(l_login_2, l_name_2, l_firstName_2);
        Assertions.assertThat(l_User_2).isNotNull();

        User l_User_3 = m_UserFactory.createUser(l_login_3, l_name_3, l_firstName_3);
        Assertions.assertThat(l_User_3).isNotNull();

        // create a new list of project
        l_listOfUsers.add(l_User_1);
        l_listOfUsers.add(l_User_2);
        l_listOfUsers.add(l_User_3);

        // persist a list project
        m_UserRepository.persistList(l_listOfUsers);

        // load all created list of user in the data base
        List<User> l_listOfUsersInDataBase = null;

        l_listOfUsersInDataBase = m_UserRepository.loadAll();
        Assertions.assertThat(l_listOfUsersInDataBase.containsAll(l_listOfUsers));

        // verify that the user1 exist in data base
        Assertions.assertThat(m_UserRepository.load(l_login_1)).isNotNull();

        // try to load created user id in the persist list
        l_User_1 = m_UserRepository.load(l_login_1);
        Assertions.assertThat(l_User_1).isNotNull();

        // verify that l_user_3 is updated in data base
        l_User_3.setfirstName("Andrea Ines");
        Assertions.assertThat(m_UserRepository.save(l_User_3).getEntityId()).isEqualTo(l_User_3.getEntityId());

        // try to duplicate existing user in the persistence list
        m_UserRepository.persist(l_User_2);
        Assertions.assertThat(l_User_2).isNotNull();

        // delete an existing user in the persistence list
        m_UserRepository.delete(l_login_2);

        // verify that the user have been deleted
        Assertions.assertThat(m_UserRepository.load(l_login_2)).isNull();

        // verify there is only 2 users left in the persisted data base
        l_listOfUsersInDataBase = m_UserRepository.loadAll();
        l_listOfUsers.remove(l_User_2);
        Assertions.assertThat(l_listOfUsersInDataBase.containsAll(l_listOfUsers));
    }

    private void scenarioSaveUserInDataBase() {

        String l_login = "17485560";
        String l_name = "SOUMARE";
        String l_firstame = "Ahmed";
        User l_User_1 = m_UserFactory.createUser(l_login, l_name, l_firstame);

        // create a user in data base and verify this user exist i data base
        Assertions.assertThat(m_UserRepository.save(l_User_1).getEntityId()).isEqualTo(l_User_1.getEntityId());

        l_User_1.setfirstName("Ahmed Salem");

        // change the user name and verify the user is updated not duplicated
        Assertions.assertThat(m_UserRepository.save(l_User_1).getEntityId()).isEqualTo(l_User_1.getlogin());

    }
}
