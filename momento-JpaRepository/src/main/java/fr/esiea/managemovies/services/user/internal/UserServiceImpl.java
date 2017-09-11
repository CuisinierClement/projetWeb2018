/*
 * Creation : 19 avr. 2016
 */
package com.inetpsa.pi201.domains.manageprojects.services.user.internal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.inject.Inject;

import org.seedstack.jpa.JpaUnit;
import org.seedstack.seed.transaction.Transactional;

import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.User;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.UserFactory;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.UserRepository;
import com.inetpsa.pi201.domains.manageprojects.services.user.UserService;

public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository mUserRepository;

    @Inject
    private UserFactory mUserFactory;

    @Transactional
    @JpaUnit("manage-domain")
    public void importUsersFromCsvFile(String pFileName) {

        BufferedReader lBuffer = null;
        String lLine = "";
        String l_cvsSplitBy = ";";
        Scanner scanner = new Scanner(pFileName);
        String lLogin = "";
        String lUserName = "";
        String lFirstName = "";

        List<User> lListOfUserFromDataBase = mUserRepository.loadAll();

        List<User> l_listOfUsers = new ArrayList<User>();

        try {

            lBuffer = new BufferedReader(new FileReader(pFileName));

            while ((lLine = lBuffer.readLine()) != null) {

                // use comma as separator
                String[] l_projectImport = lLine.split(l_cvsSplitBy);

                // Add the user login which is located in column [0]
                lLogin = l_projectImport[0];

                // Add the user name which is located in column [1]
                lUserName = l_projectImport[1];

                // Add the user first name which is located in column [2]
                lFirstName = l_projectImport[2];

                System.out.println(
                        "\n Users [Login= " + l_projectImport[0] + " , Name= " + l_projectImport[1] + " , First Name= " + l_projectImport[2] + "]");

                while (scanner.hasNextLine()) {

                    final String lineFromFile = scanner.nextLine();

                    if (lineFromFile.contains(lUserName)) {
                        // a match with the same project
                        System.out.println("I found " + lUserName + " in file " + pFileName);
                        break;
                    }
                    // create a new deliverable object
                    User lUser = mUserFactory.createUser(lLogin, lUserName, lFirstName);

                    int l_index = lListOfUserFromDataBase.indexOf(lUser);

                    // if index != -1 the user already exist in the data base
                    if (l_index != -1) {

                        l_listOfUsers.add(lListOfUserFromDataBase.get(l_index));

                    } else {
                        // else we add the user in the data base
                        l_listOfUsers.add(lUser);
                    }
                }
                // create persist project
                mUserRepository.persistList(l_listOfUsers);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if (lBuffer != null) {
                try {
                    lBuffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Done");
    }
}
